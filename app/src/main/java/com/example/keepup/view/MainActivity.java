package com.example.keepup.view;

import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.R;
import com.example.keepup.view.adapter.TaskAdapter;
import com.example.keepup.model.data.GeneralTask;
import com.example.keepup.model.repository.FirebaseRepositoryImpl;
import com.example.keepup.model.data.Task;
import com.example.keepup.model.service.FirebaseService;
import com.example.keepup.view.ui.GroupFragment;
import com.example.keepup.view.ui.HomeFragment;
import com.example.keepup.view.ui.ProfileFragment;
import com.example.keepup.view.ui.TaskFragment;
import com.example.keepup.viewmodel.TaskViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

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

        setupBottomNavBar();
    }

    private void setupBottomNavBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(onNavItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private final NavigationBarView.OnItemSelectedListener onNavItemSelected =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.ui_container, HomeFragment.getInstance())
                                    .commit();

                        case R.id.task:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.ui_container, TaskFragment.getInstance())
                                    .commit();

                        case R.id.group:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.ui_container, GroupFragment.getInstance())
                                    .commit();

                        case R.id.profile:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.ui_container, ProfileFragment.getInstance())
                                    .commit();
                    }
                    return false;
                }
            };

    private final View.OnClickListener onAddTaskClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            task.setTaskId(generateRandomId());
            task.setChainId(generateRandomId());
            String stackPath = "topStack/" + task.getChainId();
            String chainPath = "historyTask/" + task.getChainId() + "/" + task.getTaskId();

            viewModel.write(task, stackPath);
            viewModel.write(task, chainPath);
        }
    };

    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt();
    }

    private void setup() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("task");
        addTaskButton = findViewById(R.id.add_task_button);
        viewModel = new TaskViewModel(new FirebaseRepositoryImpl(new FirebaseService(databaseReference)));

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