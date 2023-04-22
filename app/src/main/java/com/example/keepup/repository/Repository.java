package com.example.keepup.repository;

import androidx.lifecycle.LiveData;
import com.example.keepup.data.model.Task;

import java.util.List;

public interface Repository {

    void addTask(Task task, String key);

    LiveData<Task> getTaskById(int id);

    LiveData<List<Task>> getAllTasks();
}
