package com.django.repository;

import com.django.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.isDeleted = false")
    Task findTaskById(String id);

    @Query("SELECT t FROM Task t WHERE t.shopping.id = :id ORDER BY t.id desc ")
    List<Task> findAllByShoppingOrderByTaskIdDesc(@Param("id") String id);
}
