package com.django.service;



import com.django.domain.Task;

import java.util.List;

public interface TaskService {


    /**
     * gets all Tasks from Database
     * @return  a List containing Tasks
     */
    List<Task> getAllTasks();

    /**
     * finds a task  in DB by its ID
     * @param id   Database ID of task
     * @return          Task with ID = taskId
     */
    Task getOneTask(String id);

    /**
     * Create  a task  with
     * @param task           task  details from EDIT FORM
     */
    Task createTask(Task task);

    /**
     * Update a task  with
     * @param task           task  details from EDIT FORM
     */
    Task updateTask(Task task);

    /**
     * delete a task from DB
     * @param id    ID of Task
     */
    boolean deleteTask(String id);

    /**
     * change status of task
     * @param id    ID of Task
     */
    Object activeTask(String id);
}
