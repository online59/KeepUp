package com.example.keepup.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PopTask extends Task {

    @SerializedName("chain_id_list")
    private int chainIdList;

    @SerializedName("task")
    private Task task;

    @Override
    public void pop(int id) {

    }

    @Override
    public void push(int id) {

    }

    @Override
    public int top() {
        return 0;
    }

    @Override
    public int search() {
        return 0;
    }
}
