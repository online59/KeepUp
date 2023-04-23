package com.example.keepup.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.model.repository.FirebaseRepositoryImpl;
import com.example.keepup.model.service.FirebaseService;
import com.example.keepup.view.adapter.TaskAdapter;
import com.example.keepup.viewmodel.TaskViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private TaskViewModel viewModel;
    private String chainId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);

        setup();
        displayChainTaskById();
    }

    private void displayChainTaskById() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new TaskAdapter();

        String chainPath = "chainTask/" + chainId;
        viewModel.readAll(chainPath).observe(this, tasks -> {
            adapter.setTaskList(tasks);
        });

        recyclerView.setAdapter(adapter);
    }

    private void setup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("task");
        viewModel = new TaskViewModel(new FirebaseRepositoryImpl(new FirebaseService(reference)));

        chainId = getIntent().getStringExtra("chain_id");
    }
}
