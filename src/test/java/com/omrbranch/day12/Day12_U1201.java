package com.omrbranch.day12;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.day12.pojo.ChangeProfilePic_Output_Pojo;
import com.omrbranch.day6.pojo.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.day8.pojo.Logout_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Day12_U1201 extends BaseClass{
	
	String logtoken;
	

	@Test(priority = 1)

	private void postmanBasicAuthLogin() {

		addHeader("accept", "application/json");

		addBasicAuth("ganeshnaren29@gmail.com", "Naren@1998");

		Response res = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		int statusCode = getStatusCode(res);
		System.out.println("Login Status Code :" + statusCode);

		Assert.assertEquals(statusCode, 200, "Verify Status Code");

		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = res
				.as(PostmanBasicAuthLogin_Output_Pojo.class);

		String message = postmanBasicAuthLogin_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Login successfully", "Verify Login Success Message");

		logtoken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
	}
	
	@Test(priority = 2)
	private void changeProfilePic() {
		
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "multipart/form-data");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		addMultiPartFormdata("profile_picture", "Green Mountain.jpeg");
		
		Response res = addRequest("POST", "https://omrbranch.com/api/changeProfilePic");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200,"Verify Change Profile Picture Status Code");
		System.out.println("Change Profile Picture Status Code :"+statusCode);
		
		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = res.as(ChangeProfilePic_Output_Pojo.class);
		System.out.println(changeProfilePic_Output_Pojo.getMessage());

	}
	
	@Test(priority = 3)
	private void logout() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		Response res = addRequest("POST", "https://omrbranch.com/api/logout");

		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Logout Status Code");

		System.out.println("Logout Status Code :" + statusCode);

		Logout_Output_Pojo logout_Output_Pojo = res.as(Logout_Output_Pojo.class);
		String message = logout_Output_Pojo.getMessage();

		Assert.assertEquals(message, "You have logged out", "Verify Logout Success Message");
		System.out.println(message);
	}
	
}
