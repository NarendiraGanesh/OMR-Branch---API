package com.omrbranch.day9.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SearchProduct {

	private String image;
	private String type;
	private String text;
	private int id;
	private int category_id;

}
