package com.example.keepup.entity;

import lombok.Data;

import java.util.List;

@Data
public abstract class TeamGroup {

    private int groupId;
    private List<Integer> teamMembers;
}
