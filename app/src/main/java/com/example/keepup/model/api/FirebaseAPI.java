package com.example.keepup.model.api;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> readAll();

    LiveData<T> readById(int id);

    void removeAll();

    void removeById(int id);

    void removeChainById(int id);

    void write(T obj, String key);
}
