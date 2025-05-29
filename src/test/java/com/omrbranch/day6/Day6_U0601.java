package com.omrbranch.day6;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;

import io.restassured.response.Response;

public class Day6_U0601 extends BaseClass{
	@Test (priority = 1)
	private String createFlight() throws ParseException {
		
		addHeader("Content-Type", "application/json");
		
		addPayload("{\r\n" + "    \"flightName\": \"AirIndia\",\r\n" + "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n" + "}");
		
		Response res = addRequest("POST", "https://www.omrbranch.com/api/flights");
		
		int statusCode = getStatusCode(res);
		System.out.println("Create Flight Status Code :"+statusCode);
		
		
		Assert.assertEquals(statusCode, 201,"Verifying Status Code For Create Flight");
		
		String asPrettyString = getResBodyAsPrettyString(res);
		System.out.println(asPrettyString);
		
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("data");
		JSONObject jsonObject2 = (JSONObject) object2;
		Object object3 = jsonObject2.get("id");
		String flightId = object3.toString();
	    System.out.println("Flight Id :"+flightId);
	    return flightId;
	}
	
	@Test (priority = 2)
	private void getFlight() throws ParseException {
		
		String flightId = createFlight();
		
		addHeader("Content-Type", "application/json");
		
		Response res = addRequest("GET", "https://www.omrbranch.com/api/flight/"+flightId);
		
		int statusCode = res.getStatusCode();
		System.out.println("Get Flight Detail Status Code :"+statusCode);
		
		Assert.assertEquals(statusCode, 200,"Verify Status Code For Get Flight Detail");
		
		String asPrettyString = getResBodyAsPrettyString(res);
		System.out.println(asPrettyString);
	}

}
