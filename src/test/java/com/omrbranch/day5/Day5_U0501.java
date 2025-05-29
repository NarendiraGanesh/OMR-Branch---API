package com.omrbranch.day5;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Day5_U0501 {

	RequestSpecification reqSpec;

	public String createFlight() throws ParseException {
		System.out.println("Create Flight");

		// 1. Intialize the Rest Assured Class
		reqSpec = RestAssured.given();

		// 2. Header, Auth, Req Body
		reqSpec = reqSpec.header("Content-Type", "application/json");

		// 3. Req Body
		reqSpec = reqSpec.body("{\r\n" + "    \"flightName\": \"AirIndia\",\r\n" + "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n" + "}");

		// 4. Endpoint, Req type
		Response response = reqSpec.post("https://www.omrbranch.com/api/flights");

		// Get the status Code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Res Body-->asPrettyString()
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("data");
		JSONObject jsonObject2 = (JSONObject) object2;
		Object object3 = jsonObject2.get("id");
		String flightId = object3.toString();
		return flightId;

	}

	public void getSingleFlight(String flightId) {

		System.out.println("\nGet Single Flight Detail");

		reqSpec = RestAssured.given();
		reqSpec = reqSpec.header("Content-Type", "application/json");
		Response response = reqSpec.get("https://www.omrbranch.com/api/flight/" + flightId);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Res Body-->asPrettyString()
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	}

	public void updateFlightDetails(String flightId) {

		System.out.println("\nUpdate Flight Details -> Multiple Data");

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");

		reqSpec.body("{\r\n" + "    \"flightName\": \"DeccanAirlines\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Deccan_Airlines\"\r\n" + "}");

		Response responsePut = reqSpec.put("https://www.omrbranch.com/api/flight/" + flightId);

		int statusCode = responsePut.getStatusCode();
		System.out.println(statusCode);

		String updatedFlightDetails = responsePut.asPrettyString();
		System.out.println(updatedFlightDetails);
	}

	public void updateFlight(String flightId) {

		System.out.println("\nUpdate Flight Detail -> Single Data");

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");

		reqSpec.body("{\"Country\": \"Singapore\"}");

		Response responsePatch = reqSpec.patch("https://www.omrbranch.com/api/flight/" + flightId);

		int statusCode = responsePatch.getStatusCode();
		System.out.println(statusCode);

		String updatedFlightDetails = responsePatch.asPrettyString();
		System.out.println(updatedFlightDetails);

	}

	public void deleteFlight(String flightId) {
		
		System.out.println("\nDelete Flight");

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		
		Response responseDelete = reqSpec.delete("https://www.omrbranch.com/api/flight/" + flightId);
		
		int statusCode = responseDelete.getStatusCode();
		System.out.println(statusCode);
	}

	public void getListFlight(int pageNum) {
		
		System.out.println("\nList of Flight");

		reqSpec = RestAssured.given();

		reqSpec = reqSpec.header("Content-Type", "application/json");
		
		Response responseGet = reqSpec.get("https://www.omrbranch.com/api/flights?page="+pageNum);
		
		int statusCode = responseGet.getStatusCode();
		System.out.println(statusCode);
		
		String flightList = responseGet.asPrettyString();
		System.out.println(flightList);
	}

	public static void main(String[] args) throws ParseException {
		Day5_U0501 day5_U0501ref = new Day5_U0501();
		
		String flightId = day5_U0501ref.createFlight();
		day5_U0501ref.getSingleFlight(flightId);
		day5_U0501ref.updateFlightDetails(flightId);
		day5_U0501ref.updateFlight(flightId);
		day5_U0501ref.deleteFlight(flightId);
		day5_U0501ref.getListFlight(5);

	}
}