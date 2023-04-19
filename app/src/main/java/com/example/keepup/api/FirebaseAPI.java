package com.example.keepup.api;

import com.example.keepup.entity.Task;

import java.util.List;

public interface FirebaseAPI {

    List<Task> getAllTasks();

    Task getTaskById(int id);

    void deleteAllTask();

    void deleteTaskById(int id);

    void deleteChainTaskById(int id);

    void pushNewTask(Task task);
}
