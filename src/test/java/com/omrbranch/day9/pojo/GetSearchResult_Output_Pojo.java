package com.omrbranch.day9.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetSearchResult_Output_Pojo {

	private int status;
    private String message;
    private String currency;
    private ArrayList<GetSearchResult> data;
    private int cart_count;
}
