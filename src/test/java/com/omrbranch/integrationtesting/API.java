package com.omrbranch.integrationtesting;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.day6.pojo.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.day8.pojo.Logout_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import omrbranch.day11.cancelorder.pojo.CancelOrder_Input_Pojo;
import omrbranch.day11.cancelorder.pojo.CancelOrder_Output_Pojo;
import omrbranch.day11.getorderdetails.pojo.GetOrderDetails_Output_Pojo;
import omrbranch.day11.getorderitemstatus.pojo.GetOrderItemStatus_Input_Pojo;
import omrbranch.day11.getorderitemstatus.pojo.GetOrderItemStatus_Output_Pojo;
import omrbranch.day11.getorderstatus.pojo.GetOrderStatus_Input_Pojo;
import omrbranch.day11.getorderstatus.pojo.GetOrderStatus_Output_Pojo;
import omrbranch.day11.getorderstatus.pojo.Product;
import omrbranch.day11.pojo.GetAllOrders;
import omrbranch.day11.pojo.GetAllOrders_Output_Pojo;
import omrbranch.day11.trackorder.pojo.TrackOrder_Output_Pojo;

public class API extends BaseClass {
	
	String logtoken;
	String orderNo;
	String orderId;
	String itemId;
	String productName;
	
	@Test(priority = 6)

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
	
	@Test(priority = 7)
	private void getAllOrders() {
		List<Header> listHeader = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		Response res = addRequest("GET", "https://omrbranch.com/api/getAllOrders");
		GetAllOrders_Output_Pojo allOrders_Output_Pojo = res.as(GetAllOrders_Output_Pojo.class);

		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Get All Orders Status Code");
		System.out.println("Get All Orders Status Code :" + statusCode);

		ArrayList<GetAllOrders> datas = allOrders_Output_Pojo.getData();
		for (GetAllOrders data : datas) {
			if (data.getOrder_no().equals(orderNo)) {
				int order_id = data.getId();
				System.out.println("Order Id :" + order_id);
				orderId = String.valueOf(order_id);
				break;
			}
		}
	}
	
	@Test(priority = 8)
	private void getOrderDetails() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);

		addHeaders(headers);

		Response res = addRequest("GET", "https://omrbranch.com/api/getOrderDetails?order_id=" + orderId);
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Get Order Details Status Code");
		System.out.println("Get Order Details Status Code :" + statusCode);

		GetOrderDetails_Output_Pojo getOrderDetails_Output_Pojo = res.as(GetOrderDetails_Output_Pojo.class);
		String message = getOrderDetails_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Get Order Details Success Message");

		ArrayList<omrbranch.day11.getorderdetails.pojo.Product> products = getOrderDetails_Output_Pojo.getProducts();
		for (omrbranch.day11.getorderdetails.pojo.Product product : products) {
			if (product.getName().equals(productName)) {
				System.out.println("Order Verification Success");
				System.out.println("Order Staus :" + product.getStatus());
				break;
			}
		}

	}
	
	@Test(priority = 9)
	private void getOrderStatus() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);

		addHeaders(headers);

		GetOrderStatus_Input_Pojo getOrderStatus_Input_Pojo = new GetOrderStatus_Input_Pojo(orderId);
		addPayload(getOrderStatus_Input_Pojo);

		Response res = addRequest("POST", "https://omrbranch.com/api/getOrderStatus");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Status Code of Get Order Status");
		System.out.println("Status Code of Get Order Status :" + statusCode);

		GetOrderStatus_Output_Pojo getOrderStatus_Output_Pojo = res.as(GetOrderStatus_Output_Pojo.class);
		String message = getOrderStatus_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Get Order Status Succes Message");

		ArrayList<Product> products = getOrderStatus_Output_Pojo.getProducts();
		for (Product product : products) {
			if (product.getName().equals(productName)) {
				int item_id = product.getId();
				System.out.println("Item Id :" + item_id);
				itemId = String.valueOf(item_id);
				break;
			}
		}

	}

	@Test(priority = 10)
	private void getOrderItemStatus() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);

		addHeaders(headers);

		GetOrderItemStatus_Input_Pojo getOrderItemStatus_Input_Pojo = new GetOrderItemStatus_Input_Pojo(orderId,
				itemId);
		addPayload(getOrderItemStatus_Input_Pojo);

		Response res = addRequest("POST", "https://omrbranch.com/api/getOrderItemStatus");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Status Code of Get Order Item Status");
		System.out.println("Status Code Get Order Item Status :" + statusCode);

		GetOrderItemStatus_Output_Pojo getOrderItemStatus_Output_Pojo = res.as(GetOrderItemStatus_Output_Pojo.class);
		String message = getOrderItemStatus_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Get Order Item Status Success Message");

		if (getOrderItemStatus_Output_Pojo.getProducts().getName().equals(productName)) {
			System.out.println("Status of item :" + getOrderItemStatus_Output_Pojo.getSchedules().get(0).getStatus());
		}
	}

	@Test(priority = 11)
	private void trackOrder() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);

		addHeaders(headers);

		Response res = addRequest("Get", "https://omrbranch.com/api/trackOrder/" + orderId + "/" + itemId);
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Track Order Status Code");
		System.out.println("Track Order Status Code  :" + statusCode);

		TrackOrder_Output_Pojo trackOrder_Output_Pojo = res.as(TrackOrder_Output_Pojo.class);

		String message = trackOrder_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Track Order Success Message");

		if (trackOrder_Output_Pojo.getProducts().getName().equals(productName)) {
			System.out.println("Price :" + trackOrder_Output_Pojo.getProducts().getPrice());
			System.out.println("Special Price :" + trackOrder_Output_Pojo.getProducts().getSpecial_price());
		}

	}

	@Test(priority = 12)
	private void cancelOrder() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);

		addHeaders(headers);

		CancelOrder_Input_Pojo cancelOrder_Input_Pojo = new CancelOrder_Input_Pojo(orderId);
		addPayload(cancelOrder_Input_Pojo);

		Response res = addRequest("POST", "https://omrbranch.com/api/cancelOrder");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Cancel Order Status Code");
		System.out.println("Cancel Order Status Code  :" + statusCode);

		CancelOrder_Output_Pojo cancelOrder_Output_Pojo = res.as(CancelOrder_Output_Pojo.class);
		String message = cancelOrder_Output_Pojo.getMessage();
		
		boolean contains = message.contains("Order has been cancelled");
		Assert.assertTrue(contains, "Verify Cancel Order Success Message");
		
		System.out.println(message);
		
		getOrderItemStatus();

	}
	
	@Test(priority = 13)
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
