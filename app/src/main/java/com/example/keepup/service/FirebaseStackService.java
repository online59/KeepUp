package com.example.keepup.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.api.FirebaseAPI;
import com.example.keepup.data.model.GeneralTask;
import com.example.keepup.data.model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStackService implements FirebaseAPI<Task> {

    private final DatabaseReference databaseRef;
    private final MutableLiveData<List<Task>> taskListMutableLiveData;
    private final MutableLiveData<Task> taskMutableLiveData;

    public FirebaseStackService(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef.child("topStack");
        taskListMutableLiveData = new MutableLiveData<>();
        taskMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Task>> getAll() {

        // Get data from top of the database stack
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
    public LiveData<Task> getById(int id) {

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
    public void deleteAll() {
        databaseRef.removeValue();
    }

    @Override
    public void deleteById(int id) {
        databaseRef.child(String.valueOf(id)).removeValue();
    }

    @Override
    public void deleteChainById(int id) {
    }

    @Override
    public void push(Task obj, String key) {

        // Add new task to the top of the database stack on its chain
        databaseRef.child(key).setValue(obj);
    }

}
