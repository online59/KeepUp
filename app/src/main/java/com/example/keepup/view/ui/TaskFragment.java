package com.example.keepup.view.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.R;
import com.example.keepup.model.data.Task;
import com.example.keepup.model.repository.FirebaseRepositoryImpl;
import com.example.keepup.model.service.FirebaseService;
import com.example.keepup.view.ChainActivity;
import com.example.keepup.view.adapter.TaskAdapter;
import com.example.keepup.view.event.ItemClickListener;
import com.example.keepup.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskFragment extends Fragment {

    public static TaskFragment instance;

    private FloatingActionButton addTaskButton;
    private TaskViewModel viewModel;
    private Task task;
    private TaskAdapter adapter;
    private RecyclerView recyclerView;

    private TaskFragment() {
        // Required empty public constructor
    }


    public static TaskFragment getInstance() {

        if (instance == null) {
            instance = new TaskFragment();
        }

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialSetup();
    }

    private void initialSetup() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("task");
        viewModel = new TaskViewModel(new FirebaseRepositoryImpl(new FirebaseService(databaseReference)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        displayActiveTasksInRecyclerView(view);
        return view;
    }

    private void displayActiveTasksInRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new TaskAdapter();

        viewModel.readAll("topStack").observe(getActivity(), tasks -> {
            adapter.setTaskList(tasks);
            adapter.setItemClickListener(new ItemClickListener() {
                @Override
                public void setOnItemClick(int position) {
                    Intent intent = new Intent(getActivity(), ChainActivity.class);
                    intent.putExtra("chain_id", tasks.get(position).getChainId());
                    getActivity().startActivity(intent);
                }
            });
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}