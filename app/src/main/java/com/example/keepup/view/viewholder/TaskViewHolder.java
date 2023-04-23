package com.example.keepup.view.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.keepup.R;
import com.example.keepup.view.event.ItemClickListener;
import org.jetbrains.annotations.NotNull;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView details;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TaskViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_title);
        details = itemView.findViewById(R.id.tv_details);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.setOnItemClick(getAdapterPosition());
            }
        });
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDetails() {
        return details;
    }
}
