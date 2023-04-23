package com.example.keepup.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
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
    }

    @Test
    public void testAddTaskToFirebase_IsTaskAdded() {

    }
}
