package com.django.service.impl;

import com.django.domain.Share;
import com.django.domain.Shopping;
import com.django.exceptions.ShoppingException;
import com.django.repository.ShoppingDao;
import com.django.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ShoppingServiceImpl implements ShoppingService {



    @Autowired
    private  ShoppingDao shoppingDao;



    @Override
    public List<Shopping> getAllShoppings() {

        log.info("Fetching all shoppings");
        return shoppingDao.findAll();
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
    public Shopping findAllShoppingArchived() {
        return null;
    }

    @Override
    public Object shoppingShared(Share share, String shareId, String shopId) {
        return null;
    }


}
