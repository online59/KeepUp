package com.example.keepup.model.api;

import androidx.lifecycle.LiveData;

import javax.annotation.Nullable;
import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> readAll(@Nullable String key);

    LiveData<T> readById(int id, @Nullable String key);

    void removeAll(@Nullable String key);

    void removeById(int id, @Nullable String key);

    void write(T obj, @Nullable String key);
}
