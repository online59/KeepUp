package com.example.keepup.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class Task {

    public static final int ACTIVE = 1;
    public static final int ON_HOLD = 99;
    public static final int IN_ACTIVE = -1;
    public static final int CLOSE = 0;

    @SerializedName("task_id")
    private int taskId;

    @SerializedName("status")
    private int status;

    @SerializedName("chain_id")
    private int chainId;

    @SerializedName("pre_id")
    private int preTaskId;

    @SerializedName("post_id")
    private int nexTaskId;

    @SerializedName("urgent")
    private boolean isUrgent;

    @SerializedName("important")
    private boolean isImportant;

    @SerializedName("title")
    private String title;

    @SerializedName("detail")
    private String detail;

    @SerializedName("deadline")
    private float deadline;

    @SerializedName("start_date")
    private float startDate;

    public int getPriority() {
        int priority = 0;
        if (isUrgent && isImportant) {
            priority = 4;
        } else if (isUrgent && !isImportant) {
            priority = 1;
        } else if (!isUrgent && isImportant) {
            priority = 3;
        } else {
            priority = 2;
        }

        return priority;
    }

    public abstract void pop(int id);
    public abstract void push(int id);
    public abstract int top();
    public abstract int search();
}
