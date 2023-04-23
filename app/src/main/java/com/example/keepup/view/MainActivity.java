package com.example.keepup.view;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.view.adapter.TaskAdapter;
import com.example.keepup.model.data.GeneralTask;
import com.example.keepup.model.repository.FirebaseRepositoryImpl;
import com.example.keepup.model.data.Task;
import com.example.keepup.model.service.FirebaseService;
import com.example.keepup.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addTaskButton;
    private TaskViewModel viewModel;
    private Task task;
    private TaskAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();

        addTaskButton.setOnClickListener(onAddTaskClick);

        displayTaskList();
    }

    private void displayTaskList() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TaskAdapter();

        viewModel.readAll().observe(this, tasks -> {
            adapter.setTaskList(tasks);
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private final View.OnClickListener onAddTaskClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            task.setTaskId(generateRandomId());
            task.setChainId(generateRandomId());
            String stackPath = "topStack/" + task.getChainId();
            String chainPath = "chainTask/" + task.getChainId() + task.getTaskId();

            viewModel.write(task, stackPath);
            viewModel.write(task, chainPath);
        }
    };

    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt();
    }

    private void setup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("task");
        addTaskButton = findViewById(R.id.add_task_button);
        viewModel = new TaskViewModel(new FirebaseRepositoryImpl(new FirebaseService(reference)));

        // Add new task to topStack
        task = new GeneralTask();

        task.setPreTaskId(0);
        task.setNexTaskId(99);
        task.setTitle("New Task");
        task.setDeadline(122561f);
        task.setStartDate(18536f);
        task.setStatus(0);
        task.setImportant(true);
        task.setUrgent(true);
        task.setDetail("New task added");
    }
}