package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Acountry {

	private int id;
	private String code;
	private String name;
	private int phonecode;
	private String currency_name;
	private String currency_symbol;
	private String currency_code;
	private String status;
}
