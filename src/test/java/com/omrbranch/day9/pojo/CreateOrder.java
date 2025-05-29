package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateOrder {
	
	private String order_code;
    private int order_no;
    private int user_id;
    private int address_id;
    private String payment_method;
    private String wallet;
    private String updated_at;
    private String created_at;
    private int id;
    private String total_amount;
    private String coupon_discount;
    private String shipping_fee;
    private String grand_total;
    private String savings;
    private String credits_used;
    private int amount;
    private UserAddress user_address;

}
