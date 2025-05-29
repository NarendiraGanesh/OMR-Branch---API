package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetCartItems {
	
	private int id;
    private int cart_id;
    private int product_id;
    private int product_variation_id;
    private int qty;
    private String price;
    private String special_price;
    private Object offer_id;
    private Object coupon_code_id;
    private Object coupon_discount;
    private int scheduled;
    private String start_date;
    private String end_date;
    private int delivery_slot_id;
    private String status;
    private String created_at;
    private String updated_at;
    private int is_available;
    private String product_name;
    private String product_image;
    private String product_medium_image;
    private int discount;
    private String delivery_slots;
    private String delivery_slot_type;
    private int is_favorite;
    private int max_qty;

}
