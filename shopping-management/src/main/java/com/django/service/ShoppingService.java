package com.django.service;


import com.django.domain.Share;
import com.django.domain.Shopping;

import java.util.Collection;
import java.util.List;

public interface ShoppingService {



    /**
     * gets all Shoppings from Database
     * @return  a List containing Shoppings
     */
    List<Shopping> getAllShoppings();

    /**
     * finds a shopping  in DB by its ID
     * @param id    Database ID of shopping
     * @return          Shopping with ID = shopId
     */
    Shopping getOneShopping(String id);

    /**
     * Create  a shopping  with
     * @param shopping           shopping  details from EDIT FORM
     */
    Shopping createShopping(Shopping shopping);

    /**
     * Update a shopping  with
     * @param shopping           shopping  details from EDIT FORM
     */
    Shopping updateShopping(Shopping shopping);

    /**
     * delete a shopping from DB
     * @param id    ID of Shopping
     */
    boolean deleteShopping(String id);

    /**
     * archive a shopping
     * @param id    ID of Shopping
     */
    Object archiveShopping(String id);

    /**
     * gets all Shoppings archived
     * @return  a List containing Shoppings archived
     */
    Collection<Shopping> findAllShoppingArchived();


    /**
     * shopping shared
    * */
    Object sharedShopping();
}
