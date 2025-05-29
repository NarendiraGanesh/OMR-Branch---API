package com.omrbranch.day2;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Day2_U0201 {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader fileReader = new FileReader("D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\src\\test\\resources\\Flight.json");
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(fileReader);
		
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("data");
		
		JSONArray array = (JSONArray) object2;
		Object object3 = array.get(1);
		
		JSONObject jsonObject2 = (JSONObject) object3;
		Object object4 = jsonObject2.get("Country");
		System.out.println("Country:\t"+object4);
		
		Object object5 = array.get(5);
		JSONObject jsonObject3 = (JSONObject) object5;
		Object object6 = jsonObject3.get("flightName");
		System.out.println("Flight Name:\t"+object6);
		
		for (int i = 0; i < array.size(); i++) {
			Object object7 = array.get(i);
			JSONObject jsonObject4 = (JSONObject) object7;
			
			Object object8 = jsonObject4.get("id");
			System.out.println("Flight Id:\t"+object8);
			
			Object object9 = jsonObject4.get("flightName");
			System.out.println("Flight Name:\t"+object9);
			
			Object object10 = jsonObject4.get("Country");
			System.out.println("Country:\t"+object10);
			
			Object object11 = jsonObject4.get("Destinations");
			System.out.println("Destinations:\t"+object11);
			
			Object object12 = jsonObject4.get("URL");
			System.out.println("URL:\t"+object12);
		}
	}
}
