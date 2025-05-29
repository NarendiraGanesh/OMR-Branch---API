package com.omrbranch.day10;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.day10.pojo.AddToCart_Input_Pojo;
import com.omrbranch.day10.pojo.CategoryList;
import com.omrbranch.day10.pojo.CategoryList_Output_Pojo;
import com.omrbranch.day10.pojo.ChildCatList;
import com.omrbranch.day10.pojo.ProductList;
import com.omrbranch.day10.pojo.ProductList_Input_Pojo;
import com.omrbranch.day10.pojo.ProductList_Output_Pojo;
import com.omrbranch.day10.pojo.Variation;
import com.omrbranch.day10.pojo.addtocart.AddToCart_Output_Pojo;
import com.omrbranch.day6.pojo.AddUserAddress_Input_Pojo;
import com.omrbranch.day6.pojo.AddUserAddress_Output_Pojo;
import com.omrbranch.day6.pojo.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.day8.pojo.CityList;
import com.omrbranch.day8.pojo.CityList_Input_Pojo;
import com.omrbranch.day8.pojo.CityList_Output_Pojo;
import com.omrbranch.day8.pojo.Logout_Output_Pojo;
import com.omrbranch.day8.pojo.StateList;
import com.omrbranch.day8.pojo.StateList_Output_Pojo;
import com.omrbranch.day9.pojo.CreateOrder_Input_Pojo;
import com.omrbranch.day9.pojo.CreateOrder_Output_Pojo;
import com.omrbranch.day9.pojo.GetCartItems_Output_Pojo;
import com.omrbranch.day9.pojo.SetAddress_Input_Pojo;
import com.omrbranch.day9.pojo.SetAddress_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Day10_U1001 extends BaseClass {

	String logtoken;
	int state_Id;
	int city_Id;
	String categoryId;
	String productId;
	String variantId;
	String cartId;
	String addressId;

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
	private void stateList() {

		addHeader("accept", "application/json");

		Response res = addRequest("GET", "https://omrbranch.com/api/stateList");

		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify State List Status Code");
		System.out.println("Get State List Status Code :" + statusCode);

		StateList_Output_Pojo stateList_Output_Pojo = res.as(StateList_Output_Pojo.class);
		String message = stateList_Output_Pojo.getMessage();

		Assert.assertEquals(message, "OK", "Verify State List Success Message");

		ArrayList<StateList> stateLists = stateList_Output_Pojo.getData();

		for (StateList eachState : stateLists) {

			String name = eachState.getName();

			if (name.equals("Tamil Nadu")) {
				state_Id = eachState.getId();
				// String stateId = String.valueOf(state_Id);
				System.out.println("State Id of State " + name + " is :" + state_Id);
				break;
			}
		}
	}

	@Test(priority = 3)
	private void cityList() {
		List<Header> lstHeader = new ArrayList<>();
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

		Assert.assertEquals(statusCode, 200, "Verify City List Status Code");
		System.out.println("City List Status Code :" + statusCode);

		CityList_Output_Pojo cityList_Output_Pojo = res.as(CityList_Output_Pojo.class);
		String message = cityList_Output_Pojo.getMessage();

		Assert.assertEquals(message, "OK", "Verify City List Success Message");

		ArrayList<CityList> CityLists = cityList_Output_Pojo.getData();

		for (CityList eachCity : CityLists) {
			String name = eachCity.getName();
			if (name.equals("Tittakudi")) {
				city_Id = eachCity.getId();
				System.out.println("City ID of " + name + " is :" + city_Id);
				break;

			}
		}

	}

	@Test(priority=4)
	private void categoryList() {
		addHeader("accept", "application/json");
		
		Response res = addRequest("GET", "https://omrbranch.com/api/categoryList");
		
		int statusCode = getStatusCode(res);
		System.out.println("Category List Status Code :"+statusCode);
		Assert.assertEquals(statusCode, 200,"Verify Category List Status Code");
		
		CategoryList_Output_Pojo categoryList_Output_Pojo = res.as(CategoryList_Output_Pojo.class);
		
		String message = categoryList_Output_Pojo.getMessage();
		Assert.assertEquals(message,"OK", "Verify Category List Success Message");
		
		ArrayList<CategoryList> categoryDatas = categoryList_Output_Pojo.getData();
		
		for (CategoryList categoryData : categoryDatas) {
			if (categoryData.getName().equals("Grocery")) {
				System.out.println(categoryData.getName());
				ArrayList<ChildCatList> child_cat_list = categoryData.getChild_cat_list();
				for (ChildCatList child_cat_data : child_cat_list) {
					if (child_cat_data.getName().equals("Fruit & Nuts")) {
						System.out.println(child_cat_data.getName());
						int category_id = child_cat_data.getId();
						categoryId = String.valueOf(category_id);
						System.out.println("Category Id :"+categoryId);
					}
					
				}
				
			}
		}
		
	}

	@Test(priority = 5)
	private void productList() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		ProductList_Input_Pojo productList_Input_Pojo = new ProductList_Input_Pojo(categoryId, "0");
		addPayload(productList_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/productList");
		int statusCode = getStatusCode(res);
		System.out.println("Product List Status Code :"+statusCode);
		Assert.assertEquals(statusCode,200,"Verify Product List Status Code");
		
		ProductList_Output_Pojo productList_Output_Pojo = res.as(ProductList_Output_Pojo.class);
		String message = productList_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK","Verify Product List Success Message");
		
		ArrayList<ProductList> productListDatas = productList_Output_Pojo.getData();
		
		for (ProductList productListData : productListDatas) {
			if (productListData.getName().equals("Nuts & Seeds - Raw Peanut")) {
				System.out.println(productListData.getName());
				int product_id = productListData.getId();
				productId = String.valueOf(product_id);
				System.out.println("Product Id :"+productId);
				ArrayList<Variation> productVariations = productListData.getVariations();
			
	
				for (Variation productVariation : productVariations) {
					if (productVariation.getSpecifications().equals("1 kg")) {
						System.out.println(productVariation.getSpecifications());
						int variant_id = productVariation.getOptions().get(0).getVariation_id();
						variantId = String.valueOf(variant_id);
						System.out.println("Variant Id :"+variantId);
						break;
					}
					
				}
			}
		}
	}
	
	@Test(priority = 6)
	private void addToCart() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		AddToCart_Input_Pojo addToCart_Input_Pojo = new AddToCart_Input_Pojo(productId,variantId,"plus");
		addPayload(addToCart_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/addToCart");
		
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200,"Verify Add to Cart Status Code");
		System.out.println("Add to Cart Status Code :"+statusCode);
		AddToCart_Output_Pojo addToCart_Output_Pojo = res.as(AddToCart_Output_Pojo.class);
		
		String message = addToCart_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Product added in cart","Verify Add to Cart Success Message");
		System.out.println(message);
		
	}
	
	@Test(priority = 7)
	private void getCartItems() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		Response res = addRequest("GET", "https://omrbranch.com/api/getCartItems");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Get Cart Items Status Code");
		System.out.println("Get Cart Items Status Code :" + statusCode);

		GetCartItems_Output_Pojo getCartItems_Output_Pojo = res.as(GetCartItems_Output_Pojo.class);
		String message = getCartItems_Output_Pojo.getMessage();

		int cart_id = getCartItems_Output_Pojo.getData().get(0).getCart_id();
		cartId = String.valueOf(cart_id);

		Assert.assertEquals(message, "OK", "Verify Get Cart Items Success Message");

	}
	
	@Test(priority = 8)

	private void addUserAddress() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("Narendira", "Ganesh",
				"9080042831", "337", state_Id, city_Id, 101, "606106", "Main Road", "Home");
		addPayload(addUserAddress_Input_Pojo);

		Response res = addRequest("POST", "https://omrbranch.com/api/addUserAddress");
		int statusCode = getStatusCode(res);
		System.out.println("Add User Address Status Code :" + statusCode);

		Assert.assertEquals(statusCode, 200, "Verify Add User Address Status Code");

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = res.as(AddUserAddress_Output_Pojo.class);
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		addressId = String.valueOf(address_id);
		System.out.println("Address Id :" + addressId);
	}

	@Test(priority = 9)
	private void setAddress() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);


		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		SetAddress_Input_Pojo setAddress_Input_Pojo = new SetAddress_Input_Pojo(addressId, cartId);
		addPayload(setAddress_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/setAddress");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Set Address Status Code");
		System.out.println("Set Address Status Code :" + statusCode);

		SetAddress_Output_Pojo setAddress_Output_Pojo = res.as(SetAddress_Output_Pojo.class);
		String message = setAddress_Output_Pojo.getMessage();

		Assert.assertEquals(message, "Cart updated successfully", "Verify Set Address Success Message");

	}
	
	@Test(priority = 10)
	private void createOrder() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);


		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		CreateOrder_Input_Pojo createOrder_Input_Pojo = new CreateOrder_Input_Pojo("debit_card", "5555555555552222", "visa", "2025", "08", "123");
		addPayload(createOrder_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/createOrder");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Create Order Status Code");
		System.out.println("Create Order Status Code :" + statusCode);

		CreateOrder_Output_Pojo createOrder_Output_Pojo = res.as(CreateOrder_Output_Pojo.class);
		String message = createOrder_Output_Pojo.getMessage();
		System.out.println(message);

		Assert.assertEquals(message, "Order created successfully", "Verify Order Created Success Message");
		
		int order_no = createOrder_Output_Pojo.getData().getOrder_no();
		System.out.println("Order Number : "+order_no);

	}
	
	
	@Test(priority = 11)
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
