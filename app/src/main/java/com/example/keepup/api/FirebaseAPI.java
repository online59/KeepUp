package com.example.keepup.api;

import androidx.lifecycle.LiveData;
import com.example.keepup.data.model.Task;

import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> getAllTasks();

    LiveData<T> getTaskById(int id);

    void deleteAllTask();

    void deleteTaskById(int id);

    void deleteChainTaskById(int id);

    void pushNewTask(T obj, String key);
}
