package com.example.keepup.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {

    @SerializedName("user_id")
    private int userId;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("position")
    private String position;
}
