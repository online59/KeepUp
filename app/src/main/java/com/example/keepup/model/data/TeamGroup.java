package com.example.keepup.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public abstract class TeamGroup {

    @SerializedName("group_id")
    private int groupId;
    @SerializedName("group_members")
    private List<Integer> teamMembers;
}
