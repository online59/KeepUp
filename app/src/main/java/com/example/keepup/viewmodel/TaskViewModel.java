package com.example.keepup.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.keepup.data.model.Task;
import com.example.keepup.repository.Repository;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private final Repository repository;

    public TaskViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addTask(Task task, String key) {
        repository.addTask(task, key);
    }

    public LiveData<List<Task>> getAllTasks() {
        return repository.getAllTasks();
    }

    public LiveData<Task> getTaskById(int id) {
        return repository.getTaskById(id);
    }
}
