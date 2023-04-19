package com.example.keepup.service;

import androidx.annotation.NonNull;
import com.example.keepup.entity.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

public class FirebaseService {

    private DatabaseReference databaseRef;

    public FirebaseService(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;
    }

    public void addTask(Task task) {
        databaseRef.child("task").setValue(task);
    }

}
