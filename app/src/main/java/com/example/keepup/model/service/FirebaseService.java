package com.example.keepup.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.model.api.FirebaseAPI;
import com.example.keepup.model.data.GeneralTask;
import com.example.keepup.model.data.Task;
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
    public LiveData<List<Task>> readAll() {

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                List<Task> taskList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    Task task = snap.getValue(GeneralTask.class);
                    taskList.add(task);
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
    public LiveData<Task> readById(int id) {

        databaseRef.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                taskMutableLiveData.postValue(snapshot.getValue(GeneralTask.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return taskMutableLiveData;
    }

    @Override
    public void removeAll() {
        databaseRef.removeValue();
    }

    @Override
    public void removeById(int id) {
        databaseRef.child(String.valueOf(id)).removeValue();
    }

    @Override
    public void removeChainById(int id) {
    }

    @Override
    public void write(Task obj, String key) {

        databaseRef.child(key).setValue(obj);
    }

}
