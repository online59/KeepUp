package com.example.keepup.model.repository;

import androidx.lifecycle.LiveData;
import com.example.keepup.model.data.Task;

import javax.annotation.Nullable;
import java.util.List;

public interface Repository {

    void write(Task task, String key);

    LiveData<Task> readById(int id, String key);

    LiveData<List<Task>> readAll(String key);
}
