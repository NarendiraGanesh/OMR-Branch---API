package com.omrbranch.day10.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChildCatList{
	private int id;
    private String category_code;
    private String name;
    private String image;
    private String banner;
    private int parent_id;
    private String description;
    private String status;
    private String created_at;
    private String updated_at;
    private ArrayList<Object> child_cat_list;
}

