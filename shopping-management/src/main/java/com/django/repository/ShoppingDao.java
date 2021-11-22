package com.django.repository;

import com.django.domain.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingDao extends JpaRepository<Shopping, String> {

    @Query("SELECT s FROM  Shopping s WHERE s.id = :id AND s.isDeleted = false ")
    Shopping findShoppingById(String id);

    List<Shopping> findShoppingByArchived(Boolean archived);
    List<Shopping>findShoppingByShared(Boolean shared);
    List<Shopping> findByUsers_id(Long id);
}
