package com.example.keepup.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.keepup.data.model.Task;
import com.example.keepup.repository.Repository;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private final Repository repository;
    private MutableLiveData<List<Task>> taskListMutableLiveData;

    public TaskViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }
}
