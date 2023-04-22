package com.example.keepup.repository;

import com.example.keepup.data.model.Task;
import com.example.keepup.repository.Repository;
import com.example.keepup.service.FirebaseService;

public class RepositoryImpl implements Repository {

    private FirebaseService service;

    public RepositoryImpl(FirebaseService service) {
        this.service = service;
    }

    @Override
    public void addTask(Task task) {
        service.getAllTasks();
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }
}
