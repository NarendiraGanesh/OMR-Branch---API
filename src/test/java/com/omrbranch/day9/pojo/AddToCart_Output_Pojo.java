package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddToCart_Output_Pojo {

	private int status;
    private String message;
    private String currency;
    private int cart_count;
    private AddToCart data;
	

}
