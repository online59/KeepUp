package com.example.keepup.viewmodel;

import com.example.keepup.data.model.Task;
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
