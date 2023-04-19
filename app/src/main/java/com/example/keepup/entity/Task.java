package com.example.keepup.entity;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class Task {

    public static final int ACTIVE = 1;
    public static final int ON_HOLD = 99;
    public static final int IN_ACTIVE = -1;
    public static final int CLOSE = 0;

    private int taskId;
    private int status;
    private int preTaskId;
    private int nexTaskId;
    private boolean urgent;
    private boolean important;
    private String title;
    private String detail;
    private float deadline;
    private float startDate;

    public int getPriority() {
        int priority = 0;
        if (urgent && important) {
            priority = 4;
        } else if (urgent && !important) {
            priority = 1;
        } else if (!urgent && important) {
            priority = 3;
        } else {
            priority = 2;
        }

        return priority;
    }
}
