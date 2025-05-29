package com.omrbranch.day12.clearcart.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClearCart_Output_Pojo {

	private int status;
	private String message;
	private int cart_count;
}
