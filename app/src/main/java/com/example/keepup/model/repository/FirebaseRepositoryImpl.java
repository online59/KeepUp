package com.example.keepup.model.repository;

import androidx.lifecycle.LiveData;
import com.example.keepup.model.api.FirebaseAPI;
import com.example.keepup.model.data.Task;

import java.util.List;

public class FirebaseRepositoryImpl implements Repository {

    private final FirebaseAPI<Task> service;

    public FirebaseRepositoryImpl(FirebaseAPI<Task> service) {
        this.service = service;
    }

    @Override
    public void write(Task task, String key) {
        service.write(task, key);
    }

    @Override
    public LiveData<Task> readById(int id) {
        return service.readById(id);
    }

    @Override
    public LiveData<List<Task>> readAll() {
        return service.readAll();
    }
}
