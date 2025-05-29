package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Astate {
	
	private int id;
    private String name;
    private int country_id;
    private String status;
    private Country country;

}
