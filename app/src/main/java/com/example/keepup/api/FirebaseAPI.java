package com.example.keepup.api;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> getAll();

    LiveData<T> getById(int id);

    void deleteAll();

    void deleteById(int id);

    void deleteChainById(int id);

    void push(T obj, String key);
}
