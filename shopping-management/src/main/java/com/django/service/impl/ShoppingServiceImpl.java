package com.django.service.impl;

import com.django.Repository.UserDao;
import com.django.domain.Share;
import com.django.domain.Shopping;
import com.django.domain.Task;
import com.django.domain.UserApp;
import com.django.exceptions.ShoppingException;
import com.django.repository.ShoppingDao;
import com.django.repository.TaskDao;
import com.django.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ShoppingServiceImpl implements ShoppingService {



    @Autowired
    private  ShoppingDao shoppingDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;



    @Override
    public List<Shopping> getAllShoppings() {
        log.info("Fetching all shoppings");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserApp user = userDao.findUserAppByUsername(auth.getName());
        List<Shopping> shoppings = shoppingDao.findShoppingByArchived(false);
        List<Shopping> shoppings1 = shoppingDao.findByUsers_id(user.getId());
        List<Shopping> shoppingList = new ArrayList<>();
        for (Shopping shopping : shoppings){
            for(int i = 0; i<shoppings1.size(); i++){
                if (shopping.getId().equals(shoppings1.get(i).getId()));{
                    shoppingList.add(shoppings1.get(i));
                }
            }
        }

        for (Shopping shopping : shoppingList){
            List<Task> tasks = new ArrayList<>();
            tasks.addAll(shopping.getTasks());
            List<Task> tasks1 = new ArrayList<>();
            for (Task task : tasks){
                if (task.getStatus() != null && task.getStatus()) tasks1.add(task);
            }
            if ((tasks.size() != 0) && tasks.size()==tasks1.size()){
                shopping.setStatut(true);
            }

        }

        List<Integer> numbers= new ArrayList<>();
        for (Shopping shopping : shoppingList){
            Collection<Task> tasks= shopping.getTasks();
            Collection<Task> tasks1=new ArrayList<>();

            for (Task task:tasks){
                if (task.getStatus() != null  && task.getStatus() == true){
                    tasks1.add(task);
                }
            }
            int nombre = tasks1.size();
            numbers.add(nombre);
        }

        return shoppingList;
    }

    @Override
    public Shopping getOneShopping(String id) {
        log.info("Fetching shopping by id", id);
        return shoppingDao.findShoppingById(id);
    }

    @Override
    public Shopping createShopping(Shopping shopping) {
        try{
            log.info("Saving new Shopping: {}", shopping.getName());
            if (shopping == null) throw  new ShoppingException("Shopping find is empty");
            Shopping shopping1 = shoppingDao.save(shopping);
            return shopping1;
        }catch (ShoppingException s){
            s.getMessage();
        }
        return shopping;
    }

    @Override
    public Shopping updateShopping(Shopping shopping) {
        log.info("Updating shopping: {}", shopping.getName());
        Shopping shopping1 = shoppingDao.findShoppingById(shopping.getId());
        shopping1.setName(shopping.getName());
        shopping1.setComment(shopping.getComment());
        shopping1.setSaverName(shopping.getSaverName());
        shopping1.setStatut(shopping.getStatut());
        shoppingDao.save(shopping1);
        return shopping;
    }

    @Override
    public boolean deleteShopping(String id) {
        try{
            log.info("delete shopping by id", id);
            Shopping shopping = shoppingDao.findShoppingById(id);
            if (shopping == null) throw new ShoppingException("Shopping do not exist");
            if (shopping.getIsDeleted() == true) shopping.setIsDeleted(false);
            else shopping.setIsDeleted(true);
            shoppingDao.save(shopping);

        }catch (ShoppingException s){
           s.getMessage();
        }

        return true;
    }

    @Override
    public Object archiveShopping(String id) {
        List<Shopping> shoppings = shoppingDao.findShoppingByArchived(true);
        Shopping shopping = shoppingDao.findShoppingById(id);
        if (shopping.getArchived() == true) shopping.setArchived(false);
        else shopping.setArchived(true);
        shoppingDao.save(shopping);
        return shoppings;
    }

    @Override
    public Collection<Shopping> findAllShoppingArchived() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserApp user = userDao.findUserAppByUsername(auth.getName());
        List<Shopping>shoppings = shoppingDao.findShoppingByArchived(true);
        List<Shopping>shoppings2 = shoppingDao.findByUsers_id(user.getId());
        List<Shopping>shoppings1 = new ArrayList<>();
        for(Shopping shopping : shoppings){
            for (int i=0; i<shoppings2.size(); i++ ){
                if (shopping.getId().equals(shoppings2.get(i).getId())){
                    shoppings1.add(shopping);
                }
            }
        }
        return shoppings1;
    }


    @Override
    public Object sharedShopping() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp user = userDao.findUserAppByUsername(authentication.getName());
        List<Shopping>shoppings1 = shoppingDao.findShoppingByArchived(true);
        List<Shopping>shoppings2 = shoppingDao.findByUsers_id(user.getId());
        List<Shopping> shoppings3= new ArrayList<>();
        for (Shopping shopping: shoppings1){
            for (int i=0; i<shoppings2.size(); i++){
                if (shopping.getId().equals(shoppings2.get(i).getId())){
                    shoppings3.add(shoppings2.get(i));

                }
            }

        }
        return shoppings3;
    }


}
