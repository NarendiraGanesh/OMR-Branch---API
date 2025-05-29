package com.omrbranch.day12.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChangeProfilePic_Output_Pojo {

    private int status;
    private String message;
    private ChangeProfilePic data;
    private int cart_count;
}