package com.omrbranch.day5;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.restassured.path.json.JsonPath;

public class Day5_U0502 {

	public static void main(String[] args) throws FileNotFoundException {
		
		FileReader fileReader = new FileReader("D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\src\\test\\resources\\Data.json");
		JsonPath jsonPath = new JsonPath(fileReader);
		Object object = jsonPath.get("data.first_name");
		System.out.println("First Name:\t"+object);
		
		Object object2 = jsonPath.get("data.user_role.created_at");
		System.out.println("Created At:\t"+object2);
		
		Object object3 = jsonPath.get("refer_msg");
		System.out.println("ReferMessage:\t"+object3);
	}
}
