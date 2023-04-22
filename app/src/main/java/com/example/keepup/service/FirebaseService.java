package com.example.keepup.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.api.FirebaseAPI;
import com.example.keepup.data.model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService implements FirebaseAPI<Task> {

    private final DatabaseReference databaseRef;
    private final MutableLiveData<List<Task>> taskListMutableLiveData;
    private final MutableLiveData<Task> taskMutableLiveData;

    public FirebaseService(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;
        taskListMutableLiveData = new MutableLiveData<>();
        taskMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Task> taskList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    taskList.add(snap.getValue(Task.class));
                }
                taskListMutableLiveData.postValue(taskList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return taskListMutableLiveData;
    }

    @Override
    public LiveData<Task> getTaskById(int id) {

        databaseRef.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                taskMutableLiveData.postValue(snapshot.getValue(Task.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return taskMutableLiveData;
    }

    @Override
    public void deleteAllTask() {

    }

    @Override
    public void deleteTaskById(int id) {

    }

    @Override
    public void deleteChainTaskById(int id) {

    }

    @Override
    public void pushNewTask(Task obj, String key) {
        databaseRef.child(key).setValue(obj);
    }

}
