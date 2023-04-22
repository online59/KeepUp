package com.example.keepup;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.adapter.TaskAdapter;
import com.example.keepup.data.model.GeneralTask;
import com.example.keepup.repository.RepositoryImpl;
import com.example.keepup.data.model.Task;
import com.example.keepup.service.FirebaseService;
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

        addTaskButton.setOnClickListener(onClickListener);

        displayTaskList();
    }

    private void displayTaskList() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TaskAdapter();

        viewModel.getAllTasks().observe(this, tasks -> {
            adapter.setTaskList(tasks);
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            task.setTaskId(getUid());

            viewModel.addTask(task, String.valueOf(task.getTaskId()));
        }
    };

    private int getUid() {
        Random random = new Random();
        return random.nextInt();
    }

    private void setup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("task");
        addTaskButton = findViewById(R.id.add_task_button);
        viewModel = new TaskViewModel(new RepositoryImpl(new FirebaseService(reference)));

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