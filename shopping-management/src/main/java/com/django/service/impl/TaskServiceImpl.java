package com.django.service.impl;

import com.django.domain.Task;
import com.django.exceptions.TaskException;
import com.django.repository.TaskDao;
import com.django.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private  TaskDao taskDao;

    @Override
    public List<Task> getAllTasks() {
        log.info("Fetching all taks");

        return taskDao.findAll();
    }

    @Override
    public Task getOneTask(String id) {
        log.info("Fetching task by id", id);
        return taskDao.findTaskById(id);
    }

    @Override
    public Task createTask(Task task) {
        try{
            log.info("Saving task: {}", task.getName());
            if (task == null) throw new TaskException("task find is empty");
            Task task1 = taskDao.save(task);
            return task1;
        }catch (TaskException e){
            e.getMessage();
        }

        return task;
    }

    @Override
    public Task updateTask(Task task) {
        log.info("Updating task: {}", task.getName());
        Task task1 = taskDao.findTaskById(task.getId());
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setShopping(task.getShopping());
        task1.setStatus(task.getStatus());
        taskDao.save(task1);

        return task;
    }

    @Override
    public boolean deleteTask(String id) {
        try{
            log.info("delete task by id", id);
            Task task = taskDao.findTaskById(id);
            if (task == null) throw new TaskException("task do not exist");
            if (task.getIsDeleted() == true) task.setIsDeleted(false);
            else task.setIsDeleted(true);
        }catch (TaskException t) {
            t.getMessage();
        }

        return true;
    }

    @Override
    public Object activeTask(String id) {
        Task task = taskDao.findTaskById(id);
        if (task.getStatus() == true) task.setStatus(false);
        else task.setStatus(true);
        taskDao.save(task);
        return task;
    }
}
