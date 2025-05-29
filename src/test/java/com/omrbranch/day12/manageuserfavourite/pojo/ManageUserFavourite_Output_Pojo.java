package com.omrbranch.day12.manageuserfavourite.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ManageUserFavourite_Output_Pojo{
    private int status;
    private String message;
    private ArrayList<ManageUserFavourite> data;
    private int cart_count;
    private String currency;
    
}

