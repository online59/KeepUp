package com.example.keepup.model.api;

import androidx.lifecycle.LiveData;

import javax.annotation.Nullable;
import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> readAll(String key);

    LiveData<T> readById(int id, String key);

    void removeAll(String key);

    void removeById(int id, String key);

    void write(T obj, String key);
}
