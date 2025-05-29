package com.omrbranch.day12.deletecartitems.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteCartItems_Output_Pojo {

	private int status;
	private String message;
	private int cart_count;
}
