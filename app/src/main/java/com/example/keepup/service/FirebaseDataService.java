package com.example.keepup.service;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.api.FirebaseDataAPI;
import com.example.keepup.data.model.GeneralTask;
import com.example.keepup.data.model.PopTask;
import com.example.keepup.data.model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataService implements FirebaseDataAPI<Task> {

    private final DatabaseReference databaseRef;
    private final MutableLiveData<List<Task>> taskListMutableLiveData;
    private final MutableLiveData<Task> taskMutableLiveData;

    public FirebaseDataService(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;
        taskListMutableLiveData = new MutableLiveData<>();
        taskMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Task>> getAll() {
        databaseRef.child("popTask").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Task> taskList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    PopTask task = snap.getValue(PopTask.class);
                    System.out.println(task);
                    Log.e("TAG", "onDataChange: " + task);
                    taskList.add(task.getTask());
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
        databaseRef.child("popTask").child(String.valueOf(obj.getChainId())).child(key).setValue(obj);
    }

}
