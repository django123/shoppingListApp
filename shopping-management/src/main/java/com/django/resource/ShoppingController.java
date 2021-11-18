package com.django.resource;


import com.django.domain.Shopping;
import com.django.service.impl.ShoppingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shoppings")
public class ShoppingController {

    private final ShoppingServiceImpl shoppingService;

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


}
