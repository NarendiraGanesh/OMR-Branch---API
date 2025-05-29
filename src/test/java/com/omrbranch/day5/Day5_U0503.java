package com.omrbranch.day5;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Day5_U0503 {
	RequestSpecification reqSpec;
	
	public String createData() throws ParseException {
		System.out.println("Create Data");
		reqSpec = RestAssured.given();
		
		reqSpec.header("x-api-key", "reqres-free-v1");
		
		reqSpec.body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}");
		
		Response res = reqSpec.post("https://reqres.in/api/users");
		System.out.println("Status Code:"+res.getStatusCode());
		
		String asPrettyString = res.asPrettyString();
		System.out.println(asPrettyString);
		
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("id");
		String id = object2.toString();
		return id;
	}
	
	public void getSingleData(String id) {
		System.out.println("\nGet Single Data");
		reqSpec = RestAssured.given();
		reqSpec.header("x-api-key", "reqres-free-v1");
		Response res = reqSpec.get("https://reqres.in/api/users/" + id);
		System.out.println("Status Code:"+res.getStatusCode());
		String asPrettyString = res.asPrettyString();
		System.out.println(asPrettyString);
	}
	
	private void updateMultipleData(String id) {
		System.out.println("\nUpdate Multiple Data");
		reqSpec = RestAssured.given();
		reqSpec.header("x-api-key", "reqres-free-v1");
		reqSpec.body("{\"first_name\": \"Steve\",\r\n"
				+ "    \"last_name\": \"Austin\",}");
		Response res = reqSpec.put("https://reqres.in/api/users/" + id);
		System.out.println("Status Code:"+res.getStatusCode());
		System.out.println(res.asPrettyString());
	}
	
	private void updateSingleData(String id) {
		System.out.println("\nUpdate Single Data");
		reqSpec = RestAssured.given();
		reqSpec.header("x-api-key", "reqres-free-v1");
		reqSpec.body("{\"last_name\": \"Hawk\"}");
		Response res = reqSpec.put("https://reqres.in/api/users/" + id);
		System.out.println("Status Code:"+res.getStatusCode());
		System.out.println(res.asPrettyString());
	}
	
	public void getListOfData(int pageNum) {
		System.out.println("\nGet List of Data");
		reqSpec = RestAssured.given();
		reqSpec.header("x-api-key", "reqres-free-v1");
		Response res = reqSpec.get("https://reqres.in/api/users?page=" + pageNum);
		System.out.println("Status Code:"+res.getStatusCode());
		String asPrettyString = res.asPrettyString();
		System.out.println(asPrettyString);
	}
	
	public void deleteData(String id) {
		System.out.println("\nDelete Data");
		reqSpec = RestAssured.given();
		reqSpec.header("x-api-key", "reqres-free-v1");
		Response res = reqSpec.get("https://reqres.in/api/users/" + id);
		System.out.println("Status Code:"+res.getStatusCode());
		String asPrettyString = res.asPrettyString();
		System.out.println(asPrettyString);
	}
	
	public static void main(String[] args) throws ParseException {
		Day5_U0503 day5_U0503ref = new Day5_U0503();
		
		String id = day5_U0503ref.createData();
		day5_U0503ref.getSingleData(id);
		day5_U0503ref.updateMultipleData(id);
		day5_U0503ref.updateSingleData(id);
		day5_U0503ref.getListOfData(2);
		day5_U0503ref.deleteData(id);
	}

}
