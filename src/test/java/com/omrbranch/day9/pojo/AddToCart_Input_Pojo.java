package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddToCart_Input_Pojo {
	
    private String product_id;
    private String product_variation_id;
    private String type;

}
