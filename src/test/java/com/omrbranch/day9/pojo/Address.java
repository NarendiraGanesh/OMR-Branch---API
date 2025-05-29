package com.omrbranch.day9.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
	
	private int id;
    private String address_code;
    private int user_id;
    private String first_name;
    private String last_name;
    private String mobile;
    private String apartment;
    private String address;
    private int country_id;
    private int state_id;
    private int city_id;
    private String zipcode;
    private Object lat;
    private Object lng;
    @JsonProperty("default") 
    private int mydefault;
    private String status;
    private String address_type;
    private String created_at;
    private String updated_at;
    private String city;
    private String state;
    private String country;

}
