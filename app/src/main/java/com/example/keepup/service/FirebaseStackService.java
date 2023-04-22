package com.example.keepup.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.keepup.api.FirebaseStackAPI;
import com.example.keepup.data.model.GeneralTask;
import com.example.keepup.data.model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirebaseStackService implements FirebaseStackAPI<Integer> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<Integer>> taskIdListMutableLiveData;
    private final MutableLiveData<Integer> taskIdMutableLiveData;


    private final MutableLiveData<Task> taskMutableLiveData;

    public FirebaseStackService(DatabaseReference reference) {
        this.reference = reference;
        taskIdListMutableLiveData = new MutableLiveData<>();
        taskIdMutableLiveData = new MutableLiveData<>();
        taskMutableLiveData = new MutableLiveData<>();
    }

    private LiveData<Task> getTaskById(int id) {
        reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Task task = snapshot.getValue(GeneralTask.class);
                taskMutableLiveData.postValue(task);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return taskMutableLiveData;
    }

    private void saveTaskById(Task task, String key) {
        reference.child("chainTask").child(key).setValue(task);
    }

    @Override
    public void pop(int id) {

        // Get task from provided id
        reference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Task task = snapshot.getValue(GeneralTask.class);
                taskIdMutableLiveData.postValue(task.getTaskId());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        // Save task to PopTask class
    }

    @Override
    public void push(int id) {

    }

    @Override
    public LiveData<Integer> top() {
        return null;
    }

    @Override
    public LiveData<Integer> search() {
        return null;
    }
}
