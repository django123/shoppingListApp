package com.django.resource;


import com.django.Repository.UserDao;
import com.django.domain.Share;
import com.django.domain.Shopping;
import com.django.domain.UserApp;
import com.django.repository.ShareDao;
import com.django.repository.ShoppingDao;
import com.django.service.impl.ShoppingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/shoppings")
public class ShoppingController {

    private final ShoppingServiceImpl shoppingService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private ShareDao shareDao;

    public ShoppingController(ShoppingServiceImpl shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Shopping>> getAllShopping(){
        return ResponseEntity.ok(shoppingService.getAllShoppings());
    }

    @PostMapping("/save")
    public ResponseEntity<Shopping> createShopping(@Valid @RequestBody Shopping shopping){
        return ResponseEntity.ok(shoppingService.createShopping(shopping));
    }

    @PutMapping("/update")
    public ResponseEntity<Shopping> updateShopping(@Valid @RequestBody Shopping shopping){
        return ResponseEntity.ok(shoppingService.updateShopping(shopping));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Shopping> getOneShopping(String id){
        return ResponseEntity.ok(shoppingService.getOneShopping(id));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteShopping(@PathVariable("id") String id){
        return ResponseEntity.ok(shoppingService.getOneShopping(id));
    }


    @GetMapping("/archive/{id}")
    public ResponseEntity<Object> archiveShopping(@PathVariable("id") String id){
        return ResponseEntity.ok(shoppingService.archiveShopping(id));
    }

    @GetMapping("/shared")
    public ResponseEntity<Object> shareShopping(){
        return ResponseEntity.ok(shoppingService.sharedShopping());
    }


    @PostMapping("/shoppings/share/{shopId}/{id}")
    public ResponseEntity<Object> shareShopping(Share share, @PathVariable Long id, @PathVariable String shopId)
            throws URISyntaxException {
        UserApp user = userDao.findById(id).get();
        Shopping shopping = shoppingDao.findShoppingById(shopId);
        shopping.add(user);
        shopping.setShared(true);
        share.setId(user.getUserId());
        share.setShopId(shopping.getId());
        shoppingDao.save(shopping);
        shareDao.save(share);
        return new ResponseEntity<>(shopping, null, HttpStatus.OK);

    }


    @GetMapping("/archive")
    public ResponseEntity<Collection<Shopping>> findAllArchive() throws URISyntaxException {
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
        return new ResponseEntity<>(shoppings1, null, HttpStatus.OK);
    }


}
