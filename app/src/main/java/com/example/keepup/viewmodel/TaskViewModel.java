package com.example.keepup.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.example.keepup.entity.Task;
import com.example.keepup.repository.Repository;

public class TaskViewModel {

    private Repository repository;

    public TaskViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }
}
