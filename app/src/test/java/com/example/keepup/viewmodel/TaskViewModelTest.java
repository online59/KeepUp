package com.example.keepup.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.keepup.data.model.GeneralTask;
import com.example.keepup.repository.RepositoryImpl;
import com.example.keepup.data.model.Task;
import com.example.keepup.service.FirebaseDataService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;

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
        viewModel = new TaskViewModel(new RepositoryImpl(new FirebaseDataService(reference)));
    }

    @Test
    public void testAddTaskToFirebase_IsTaskAdded() {
        Task task = new GeneralTask();

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

    }
}
