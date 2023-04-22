package com.example.keepup.repository;

import com.example.keepup.data.model.Task;

public interface Repository {

    void addTask(Task task);

    Task getTaskById(int id);
}
