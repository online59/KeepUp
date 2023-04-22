package com.example.keepup.data.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ChainTask {

    @SerializedName("chain_id")
    private int chainId;

    @SerializedName("task_list")
    private Task[] taskList;
}
