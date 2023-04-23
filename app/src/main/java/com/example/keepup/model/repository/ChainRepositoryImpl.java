package com.example.keepup.model.repository;

import androidx.lifecycle.LiveData;
import com.example.keepup.model.api.FirebaseAPI;
import com.example.keepup.model.data.Task;

import java.util.List;

public class ChainRepositoryImpl implements Repository{

    private final FirebaseAPI<Task> service;

    public ChainRepositoryImpl(FirebaseAPI<Task> service) {
        this.service = service;
    }

    @Override
    public void addTask(Task task, String key) {
        service.write(task, key);
    }

    @Override
    public LiveData<Task> getTaskById(int id) {
        return service.readById(id);
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        return service.readAll();
    }
}
