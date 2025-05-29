package com.omrbranch.day10.pojo.addtocart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Option {

	private int id;
    private int product_id;
    private int variation_id;
    private String attribute_id;
    private int attribute_value_id;
    private String status;
    private String created_at;
    private String updated_at;
    private AttributeOption attribute_option;
}
