package com.example.keepup.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.R;
import com.example.keepup.model.data.Task;
import com.example.keepup.view.event.ItemClickListener;
import com.example.keepup.view.viewholder.TaskViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Task> taskList;
    private ItemClickListener itemClickListener;

    public TaskAdapter() {
        this.taskList = new ArrayList<>();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList.clear();
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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

        // Set item click on view holder
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return (taskList != null)? taskList.size() : 0;
    }
}
