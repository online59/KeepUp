package com.example.keepup.repository;

import androidx.lifecycle.LiveData;
import com.example.keepup.api.FirebaseDataAPI;
import com.example.keepup.data.model.Task;

import java.util.List;

public class RepositoryImpl implements Repository {

    private final FirebaseDataAPI<Task> service;

    public RepositoryImpl(FirebaseDataAPI<Task> service) {
        this.service = service;
    }

    @Override
    public void addTask(Task task, String key) {
        service.push(task, key);
    }

    @Override
    public LiveData<Task> getTaskById(int id) {
        return service.getById(id);
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        return service.getAll();
    }
}
