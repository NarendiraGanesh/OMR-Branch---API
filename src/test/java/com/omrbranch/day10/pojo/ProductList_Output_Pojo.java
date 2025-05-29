package com.omrbranch.day10.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductList_Output_Pojo{
    private int status;
    private String message;
    private String currency;
    private ArrayList<ProductList> data;
    private String banner;
    private int cart_count;
}

