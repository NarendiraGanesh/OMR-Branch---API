package com.omrbranch.day6;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.day6.pojo.AddUserAddress_Input_Pojo;
import com.omrbranch.day6.pojo.AddUserAddress_Output_Pojo;
import com.omrbranch.day6.pojo.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.day7.pojo.DeleteAddress_Input_Pojo;
import com.omrbranch.day7.pojo.DeleteAddress_Output_Pojo;
import com.omrbranch.day7.pojo.GetAddress;
import com.omrbranch.day7.pojo.GetUserAddress_Output_Pojo;
import com.omrbranch.day7.pojo.UpdateUserAddress_Input_Pojo;
import com.omrbranch.day7.pojo.UpdateUserAddress_Output_Pojo;
import com.omrbranch.day8.pojo.CityList;
import com.omrbranch.day8.pojo.CityList_Input_Pojo;
import com.omrbranch.day8.pojo.CityList_Output_Pojo;
import com.omrbranch.day8.pojo.StateList;
import com.omrbranch.day8.pojo.StateList_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Day6_U0602 extends BaseClass {
	String logtoken;
	String addressId;
	int state_Id;
	int city_Id;
	
	@Test (priority = 1)
	
	private void postmanBasicAuthLogin() {
		
		addHeader("accept", "application/json");
		
		addBasicAuth("ganeshnaren29@gmail.com", "Naren@1998");
		
		Response res = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		
		int statusCode = getStatusCode(res);
		System.out.println("Login Status Code :"+statusCode);
		
		Assert.assertEquals(statusCode, 200,"Verify Status Code");
		
		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = res.as(PostmanBasicAuthLogin_Output_Pojo.class);
		
		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
		System.out.println("First Name :"+first_name);
		
		String email = postmanBasicAuthLogin_Output_Pojo.getData().getEmail();
		System.out.println("Email :"+email);
		
		Assert.assertEquals(email, "ganeshnaren29@gmail.com","Verify Email");
		
		logtoken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();		
	}
	
	@Test (priority = 4)
	
	private void addUserAddress() {
		List<Header>lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("Narendira","Ganesh","9080042831","337",state_Id,city_Id,101,"606106","Main Road","Home");
		addPayload(addUserAddress_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/addUserAddress");
		int statusCode = getStatusCode(res);
		System.out.println("Add User Address Status Code :"+statusCode);
		
		Assert.assertEquals(statusCode, 200,"Verify Add User Address Status Code");
		
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = res.as(AddUserAddress_Output_Pojo.class);
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		addressId = String.valueOf(address_id);
		System.out.println("Address Id :"+addressId);
	}
	
	@Test(priority = 6)
	private void getUserAddress() {
		
		List<Header>lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		Response res = addRequest("GET", "https://omrbranch.com/api/getUserAddress");
		int statusCode = getStatusCode(res);
		System.out.println("Get User Address Status Code :"+statusCode);
		
		Assert.assertEquals(statusCode, 200,"Verify Get User Address Status Code");
		
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = res.as(GetUserAddress_Output_Pojo.class);
		String message = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK","Verify Get User Address Success Message");
		
		ArrayList<GetAddress> datas = getUserAddress_Output_Pojo.getData();
		
		for (int i = 0; i < datas.size(); i++) {
			
			GetAddress data = datas.get(i);
			
			if (String.valueOf(data.getId()).equals(addressId)) {
				
				GetAddress userCreatedAddress = datas.get(i);
				System.out.println("User Created Address : "+userCreatedAddress);
				
				Assert.assertEquals(userCreatedAddress.first_name, "Narendira","Verify User First Name");
				Assert.assertEquals(userCreatedAddress.last_name, "Ganesh","Verify User Last Name");
				Assert.assertEquals(userCreatedAddress.mobile, "9080042831","Verify User Mobile Number");
				Assert.assertEquals(userCreatedAddress.first_name, "Narendira","Verify User First Name");
				Assert.assertEquals(userCreatedAddress.apartment, "NG","Verify User Apartment Name");
				Assert.assertEquals(userCreatedAddress.state_id, 35,"Verify User State ID");
				Assert.assertEquals(userCreatedAddress.city_id, 4338,"Verify User City ID");
				Assert.assertEquals(userCreatedAddress.country_id, 101,"Verify User Country ID");
				Assert.assertEquals(userCreatedAddress.zipcode, "606106","Verify User PinCode");
				Assert.assertEquals(userCreatedAddress.address, "337, Main Road","Verify User Address");
				Assert.assertEquals(userCreatedAddress.address_type, "Home","Verify User Address Type");
				
			}
			
		}
		
		System.out.println("User Address List :");
		res.prettyPrint();
		
	}
	
	
	@Test(priority = 5)
	private void updateUserAddress() {
		
		List<Header>lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(addressId,"Narendira","Ganesh","9080042831","NG",state_Id,city_Id,101,"606106","337, Main Road","Home");
		
		addPayload(updateUserAddress_Input_Pojo);
		
		Response res = addRequest("PUT", "https://omrbranch.com/api/updateUserAddress");
		
		int statusCode = getStatusCode(res);
		System.out.println("Update User Address Status Code :"+statusCode);
		
		Assert.assertEquals(statusCode, 200,"Verify Update User Address Status Code");
		
		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = res.as(UpdateUserAddress_Output_Pojo.class);
		String message = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Address updated successfully","Verify Success Message Update User Address");
		
	}
	
	@Test(priority = 7)
	private void deleteAddress() {
		
		List<Header>lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(addressId);
		addPayload(deleteAddress_Input_Pojo);
		
		Response res = addRequest("DELETE", "https://omrbranch.com/api/deleteAddress");
		int statusCode = getStatusCode(res);
		System.out.println("Delete Address Status Code :"+statusCode);

		Assert.assertEquals(statusCode, 200,"Verify Delete Address Status Code");
		
		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = res.as(DeleteAddress_Output_Pojo.class);
		String message = deleteAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Address deleted successfully","Verify Delete Address Success Message");
	}
	
	@Test(priority=2)
	private void stateList() {
		
		addHeader("accept", "application/json");
		
		Response res = addRequest("GET", "https://omrbranch.com/api/stateList");
		
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200,"Verify State List Status Code");
		System.out.println("Get State List Status Code :"+statusCode);
		
		StateList_Output_Pojo stateList_Output_Pojo = res.as(StateList_Output_Pojo.class);
		String message = stateList_Output_Pojo.getMessage();
		
		Assert.assertEquals(message, "OK","Verify State List Success Message");
		
		ArrayList<StateList> stateLists = stateList_Output_Pojo.getData();
		
		for (StateList eachState : stateLists) {
			
			String name = eachState.getName();
			
			if (name.equals("Tamil Nadu")) {
				state_Id = eachState.getId();
				//String stateId = String.valueOf(state_Id);
				System.out.println("State Id of State "+name+" is :"+state_Id);
				break;
			}
			
		}

	}
	
	@Test (priority = 3)
	private void cityList() {
		List<Header>lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(state_Id);
		addPayload(cityList_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/cityList");
		int statusCode = getStatusCode(res);
		
		Assert.assertEquals(statusCode, 200,"Verify City List Status Code");
		System.out.println("City List Status Code :"+statusCode);
		
		CityList_Output_Pojo cityList_Output_Pojo = res.as(CityList_Output_Pojo.class);
		String message = cityList_Output_Pojo.getMessage();
		
		Assert.assertEquals(message, "OK","Verify City List Success Message");
		
		ArrayList<CityList> CityLists = cityList_Output_Pojo.getData();
		
		for (CityList eachCity : CityLists) {
			String name = eachCity.getName();
			if (name.equals("Tittakudi")) {
				city_Id = eachCity.getId();
				System.out.println("City ID of "+name+" is :"+city_Id);
				break;
				
			}
		}
		

	}
	
	
}