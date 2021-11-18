package com.django.repository;

import com.django.domain.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDao extends JpaRepository<Share,String> {

    @Query("SELECT s FROM Share s WHERE s.id = :id AND s.isDeleted = false ")
    Share findShareById(String id);
}
