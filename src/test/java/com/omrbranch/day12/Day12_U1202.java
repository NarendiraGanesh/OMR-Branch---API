package com.omrbranch.day12;

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
import com.omrbranch.day12.clearcart.pojo.ClearCart_Output_Pojo;
import com.omrbranch.day12.deletecartitems.pojo.DeleteCartItems_Input_Pojo;
import com.omrbranch.day12.deletecartitems.pojo.DeleteCartItems_Output_Pojo;
import com.omrbranch.day12.manageuserfavourite.pojo.ManageUserFavourite_Input_Pojo;
import com.omrbranch.day12.manageuserfavourite.pojo.ManageUserFavourite_Output_Pojo;
import com.omrbranch.day6.pojo.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.day8.pojo.Logout_Output_Pojo;
import com.omrbranch.day9.pojo.GetCartItems_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Day12_U1202 extends BaseClass {
	
	String logtoken;
	String categoryId;
	String productName;
	String productId;
	String variantId;
	String itemId;

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
	private void categoryList() {
		addHeader("accept", "application/json");

		Response res = addRequest("GET", "https://omrbranch.com/api/categoryList");

		int statusCode = getStatusCode(res);
		System.out.println("Category List Status Code :" + statusCode);
		Assert.assertEquals(statusCode, 200, "Verify Category List Status Code");

		CategoryList_Output_Pojo categoryList_Output_Pojo = res.as(CategoryList_Output_Pojo.class);

		String message = categoryList_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Category List Success Message");

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
						System.out.println("Category Id :" + categoryId);
					}

				}

			}
		}

	}

	@Test(priority = 3)
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
		System.out.println("Product List Status Code :" + statusCode);
		Assert.assertEquals(statusCode, 200, "Verify Product List Status Code");

		ProductList_Output_Pojo productList_Output_Pojo = res.as(ProductList_Output_Pojo.class);
		String message = productList_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Product List Success Message");

		ArrayList<ProductList> productListDatas = productList_Output_Pojo.getData();

		for (ProductList productListData : productListDatas) {
			if (productListData.getName().equals("Nuts & Seeds - Raw Peanut")) {
				productName = productListData.getName();
				System.out.println(productListData.getName());
				int product_id = productListData.getId();
				productId = String.valueOf(product_id);
				System.out.println("Product Id :" + productId);
				ArrayList<Variation> productVariations = productListData.getVariations();

				for (Variation productVariation : productVariations) {
					if (productVariation.getSpecifications().equals("1 kg")) {
						System.out.println(productVariation.getSpecifications());
						int variant_id = productVariation.getOptions().get(0).getVariation_id();
						variantId = String.valueOf(variant_id);
						System.out.println("Variant Id :" + variantId);
						break;
					}

				}
			}
		}
	}

	
	@Test(priority = 4,invocationCount = 2)
	private void manageUserFavourite() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		ManageUserFavourite_Input_Pojo manageUserFavourite_Input_Pojo = new ManageUserFavourite_Input_Pojo(productId, variantId);
		addPayload(manageUserFavourite_Input_Pojo);
		
		Response res = addRequest("POST", "https://omrbranch.com/api/manageUserFavourite");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Manage User Favourite Status Code");
		System.out.println("Manage User Favourite Status Code :" + statusCode);
		
		ManageUserFavourite_Output_Pojo manageUserFavourite_Output_Pojo = res.as(ManageUserFavourite_Output_Pojo.class);
		String message = manageUserFavourite_Output_Pojo.getMessage();
		if(message.contains("Product removed from your favorites")|| message.contains("Product added in your favorites")) {
			System.out.println(message);
		}
	}
	
	@Test(priority = 5)
	private void clearCart() {
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		
		lstHeader.add(h1);
		lstHeader.add(h2);
		
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		Response res = addRequest("GET", "https://omrbranch.com/api/clearCart");
		int statusCode = getStatusCode(res);
		
		System.out.println("Clear Cart Status Code :" + statusCode);
		
		ClearCart_Output_Pojo cart_Output_Pojo = res.as(ClearCart_Output_Pojo.class);
		String message = cart_Output_Pojo.getMessage();
		
		if (message.equals("Cart has been cleared.")||message.equals("Currently Your Cart is empty")) {
			System.out.println(message);
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

		AddToCart_Input_Pojo addToCart_Input_Pojo = new AddToCart_Input_Pojo(productId, variantId, "plus");
		addPayload(addToCart_Input_Pojo);

		Response res = addRequest("POST", "https://omrbranch.com/api/addToCart");

		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Add to Cart Status Code");
		System.out.println("Add to Cart Status Code :" + statusCode);
		
		AddToCart_Output_Pojo addToCart_Output_Pojo = res.as(AddToCart_Output_Pojo.class);

		String message = addToCart_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Product added in cart", "Verify Add to Cart Success Message");
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

		int item_id = getCartItems_Output_Pojo.getData().get(0).getId();
		itemId = String.valueOf(item_id);

		Assert.assertEquals(message, "OK", "Verify Get Cart Items Success Message");

	}
	
	@Test(priority = 8)
	private void deleteCartItems() {
		
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);

		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		DeleteCartItems_Input_Pojo deleteCartItems_Input_Pojo = new DeleteCartItems_Input_Pojo(itemId);
		addPayload(deleteCartItems_Input_Pojo);
		
		Response res = addRequest("DELETE", "https://omrbranch.com/api/deleteCartItems");
		int statusCode = getStatusCode(res);
		Assert.assertEquals(statusCode, 200, "Verify Delete Cart Items Status Code");
		System.out.println("Delete Cart Items Status Code :" + statusCode);
		
		DeleteCartItems_Output_Pojo deleteCartItems_Output_Pojo = res.as(DeleteCartItems_Output_Pojo.class);
		String message = deleteCartItems_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Item has been removed from cart.","Verify Delete Cart Items Success Message");
		System.out.println(message);
	}
	
	@Test(priority = 9)
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
