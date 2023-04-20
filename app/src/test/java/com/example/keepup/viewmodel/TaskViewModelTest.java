package com.example.keepup.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.runner.AndroidJUnit4;
import com.example.keepup.di.RepositoryImpl;
import com.example.keepup.entity.Task;
import com.example.keepup.service.FirebaseService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

public class TaskViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private Process process;

    private TaskViewModel viewModel;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Process.class);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("task");
        viewModel = new TaskViewModel(new RepositoryImpl(new FirebaseService(reference)));
    }

    @Test
    public void testAddTaskToFirebase_IsTaskAdded() {
        Task task = new Task();

        task.setTaskId(1);
        task.setPreTaskId(0);
        task.setNexTaskId(99);
        task.setTitle("New Task");
        task.setDeadline(122561f);
        task.setStartDate(18536f);
        task.setStatus(0);
        task.setImportant(true);
        task.setUrgent(true);
        task.setDetail("New task added");

        viewModel.addTask(task);
    }
}
