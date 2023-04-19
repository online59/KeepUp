package com.example.keepup.di;

import com.example.keepup.entity.Task;
import com.example.keepup.repository.Repository;
import com.example.keepup.service.FirebaseService;

public class RepositoryImpl implements Repository {

    private FirebaseService service;

    public RepositoryImpl(FirebaseService service) {
        this.service = service;
    }

    @Override
    public void addTask(Task task) {
        service.addTask(task);
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }
}
