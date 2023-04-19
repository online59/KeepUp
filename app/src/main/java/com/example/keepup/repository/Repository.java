package com.example.keepup.repository;

import com.example.keepup.entity.Task;

public interface Repository {

    void addTask(Task task);

    Task getTaskById(int id);
}
