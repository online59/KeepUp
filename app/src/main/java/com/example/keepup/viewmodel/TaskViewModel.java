package com.example.keepup.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.keepup.model.data.Task;
import com.example.keepup.model.repository.Repository;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private final Repository repository;

    public TaskViewModel(Repository repository) {
        this.repository = repository;
    }

    public void write(Task task, String key) {
        repository.write(task, key);
    }

    public LiveData<List<Task>> readAll() {
        return repository.readAll();
    }

    public LiveData<Task> readById(int id) {
        return repository.readById(id);
    }
}
