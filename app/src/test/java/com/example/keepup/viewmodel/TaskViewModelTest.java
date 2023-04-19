package com.example.keepup.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.keepup.di.RepositoryImpl;
import com.example.keepup.entity.Task;
import com.example.keepup.service.FirebaseService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.MockitoAnnotations;

public class TaskViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private TaskViewModel viewModel;

    @Before
    public void setup() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("task");
        viewModel = new TaskViewModel(new RepositoryImpl(new FirebaseService(reference)));
    }

    @Test
    public void addTaskToFirebase_IsTaskAdded() {
        Task task = new Task();

        task.setTaskId(1);
        task.setPreTaskId(0);
        task.setNexTaskId(99);
        task.setTitle("New Task");
        task.setDeadline(122561f);
        task.setStartDate(15536f);
        task.setStatus(0);
        task.setImportant(true);
        task.setUrgent(true);
        task.setDetail("New task added");

        viewModel.addTask(task);
    }
}
