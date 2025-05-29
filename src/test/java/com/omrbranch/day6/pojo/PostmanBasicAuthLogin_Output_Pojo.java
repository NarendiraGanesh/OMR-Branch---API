package com.omrbranch.day6.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostmanBasicAuthLogin_Output_Pojo {
	
	private int status;
    private String message;
    private Login data;
    private String refer_msg;
    private int cart_count;
    private String role;

}
