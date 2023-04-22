package com.example.keepup.service;

import androidx.annotation.NonNull;
import com.example.keepup.api.FirebaseAPI;
import com.example.keepup.data.model.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirebaseService implements FirebaseAPI {

    private final DatabaseReference databaseRef;

    public FirebaseService(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task getTaskById(int id) {
        return null;
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
    public void pushNewTask(Task task) {
        databaseRef.child("task").setValue(task);
    }


    private final ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

}
