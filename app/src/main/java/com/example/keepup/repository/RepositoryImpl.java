package com.example.keepup.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.data.model.Task;
import com.example.keepup.repository.Repository;
import com.example.keepup.service.FirebaseService;

import java.util.List;

public class RepositoryImpl implements Repository {

    private final FirebaseService service;

    public RepositoryImpl(FirebaseService service) {
        this.service = service;
    }

    @Override
    public void addTask(Task task) {
        service.getAllTasks();
    }

    @Override
    public LiveData<Task> getTaskById(int id) {
        return service.getTaskById(id);
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        return service.getAllTasks();
    }
}
