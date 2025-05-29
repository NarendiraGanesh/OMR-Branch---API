package com.omrbranch.day1;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Day1_U0101 {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader fileReader = new FileReader("D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\src\\main\\resources\\jsonFile.json");
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(fileReader);
		JSONObject jsonObject = (JSONObject) object;
		
		Object object2 = jsonObject.get("data");
		JSONObject jsonObject2 = (JSONObject) object2;
		Object object3 = jsonObject2.get("first_name");
		System.out.println("First Name :"+object3);
		
		Object object4 = jsonObject2.get("mobile_number");
		System.out.println("Mobile Number :"+object4);
		
		Object object5 = jsonObject2.get("logtoken");
		System.out.println("Log Token :"+object5);
		
		Object object6 = jsonObject2.get("user_role");
		JSONObject jsonObject3 = (JSONObject) object6;
		Object object7 = jsonObject3.get("created_at");
		System.out.println("Created at :"+object7);
		
		Object object8 = jsonObject3.get("pivot");
		JSONObject jsonObject4 = (JSONObject) object8;
		Object object9 = jsonObject4.get("user_id");
		System.out.println("User Id :"+object9);
		
		Object object10 = jsonObject.get("role");
		System.out.println("Role :"+object10);

		
	}
}
