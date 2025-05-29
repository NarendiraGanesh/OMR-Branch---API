package com.omrbranch.day3.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sample {

	private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private ArrayList<Datum> data;
    private Support support;
}
