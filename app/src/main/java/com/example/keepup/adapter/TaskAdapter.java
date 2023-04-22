package com.example.keepup.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.R;
import com.example.keepup.data.model.Task;
import com.example.keepup.viewholder.TaskViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter() {
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.getTitle().setText(task.getTitle());
        holder.getDetails().setText(task.getDetail());
    }

    @Override
    public int getItemCount() {
        return (taskList != null)? taskList.size() : 0;
    }
}
