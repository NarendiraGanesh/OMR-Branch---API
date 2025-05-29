package com.omrbranch.day7.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetUserAddress_Output_Pojo {
	
	private int status;
    private String message;
    private ArrayList<GetAddress> data;

}
