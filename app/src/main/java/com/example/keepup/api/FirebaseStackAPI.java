package com.example.keepup.api;

import androidx.lifecycle.LiveData;

public interface FirebaseStackAPI<T> {

    void pop(int id);

    void push(int id);

    LiveData<T> top();

    LiveData<T> search();
}
