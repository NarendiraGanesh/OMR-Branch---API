package com.omrbranch.integrationtesting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClassTestNg;
import com.omrbranch.day12.clearcart.pojo.ClearCart_Output_Pojo;
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

public class GroceryPlaceOrder extends BaseClassTestNg {
	
	String productName;
	String variantName;
	String orderNo;
	String logtoken;
	String orderId;
	String itemId;

	@BeforeClass
	private void beforeClass() {
		browserLaunch("chrome");
		maximizeWindow();
		enterApplnUrl("https://www.omrbranch.com/");
		implicitWait();
	}
	
	@BeforeMethod
	private void beforeMethod() {
		long startTime = System.currentTimeMillis();
		//System.out.println("Start Time : "+startTime);
	}
	
	@AfterMethod
	private void afterMethod() {
		long endTime = System.currentTimeMillis();
		//System.out.println("End Time : "+endTime);
	}
	
	
	@Test(priority = 1)
	private void login() throws IOException {
		WebElement txtUserName = findLocatorById("email");
		elementSendKeys(txtUserName, getCellData("Grocery Order Data", 1, 0));
		WebElement txtPassword = findLocatorById("pass");
		elementSendKeys(txtPassword, getCellData("Grocery Order Data", 1, 1));
		WebElement btnLogin = findLocatorByXpath("//button[text()='Login']");
		elementClick(btnLogin);
		
		WebElement text = findLocatorByXpath("//a[contains(text(),'Welcome')]");
		String textWelcome = elementGetText(text);
		boolean contains = textWelcome.contains(getCellData("Grocery Order Data", 1, 4));
		Assert.assertTrue(contains, "Verifying Successful Login Message");
		System.out.println("Welcome Message : "+textWelcome);
		
	}
	
	@Test(priority = 2)
	private void searchProduct() throws IOException {
		WebElement lnkGrocery = findLocatorByXpath("//h3[text()='Grocery']");
		elementClick(lnkGrocery);
		WebElement txtSearch = findLocatorById("search");
		elementSendKeys(txtSearch, getCellData("Grocery Order Data", 1, 2));
		WebElement btnSearch = findLocatorByXpath("//button[@data-testid='searchbtn']");
		elementClick(btnSearch);
	}
	
	@Test(priority = 3)
	private void selectProduct() {
		WebElement text = findLocatorByXpath("//h5[text()='Search Result - nuts']");
		System.out.println(elementGetText(text));
		
		List<WebElement> products = driver.findElements(By.xpath("//h5[contains(@class,'productName')]"));
		List<WebElement> btnAdd = driver.findElements(By.xpath("//a[text()='Add']"));
		
		for (int i = 0; i < products.size(); i++) {
			WebElement product = products.get(i);
			productName = elementGetText(product);
			if (productName.contains("Tata Sampann")) {
				System.out.println("Product name : "+productName);
				elementClick(btnAdd.get(i));
				break;
			}
		}
		
		List<WebElement> variants = driver.findElements(By.xpath("//p[contains(text(),'"+productName+"')]"));
		List<WebElement> btnAdd2 = driver.findElements(By.xpath("//button[text()='Add' and @data-product='20']"));
		
		
		for (int i = 0; i < variants.size(); i++) {
			WebElement variant = variants.get(i);
			variantName = elementGetText(variant);
			if (variantName.contains("500 g")) {
				System.out.println("Variant : "+variantName);
				elementClick(btnAdd2.get(i));
				break;
			}
		}
		
		WebElement goToCart = findLocatorByClassName("hover1");
		elementClickjs(goToCart);
		
		WebElement text2 = findLocatorByXpath("//h5[text()='My Cart']");
		System.out.println(elementGetText(text2));
		
	}
	
	@Test(priority = 4)
	private void myCart() throws IOException, InterruptedException {
		WebElement textScroll = findLocatorByXpath("//div[text()='  Payment Method ']");
		scroll(textScroll);
		System.out.println(elementGetText(textScroll));
		
		WebElement ddnPaymentType = findLocatorById("payment_type");
		Thread.sleep(5000);
		selectOptionByValue(ddnPaymentType, getCellData("Grocery Order Data", 1, 12));
		
		Thread.sleep(5000);
		
		WebElement rdoVisaCard = findLocatorByXpath("//label[text()=' Visa ']");
		elementClickjs(rdoVisaCard);
		
		WebElement txtCardNo = findLocatorByName("card_no");
		elementSendKeysJs(txtCardNo, getCellData("Grocery Order Data", 1, 13));
		
		WebElement ddnMonth = findLocatorById("month");
		selectOptionByText(ddnMonth, getCellData("Grocery Order Data", 1, 14));
		
		WebElement ddnYear = findLocatorById("year");
		selectOptionByText(ddnYear, getCellData("Grocery Order Data", 1, 15));
		
		WebElement txtCvv = findLocatorByName("cvv");
		elementSendKeysJs(txtCvv, getCellData("Grocery Order Data", 1, 16));
		
		WebElement btnPlaceOrder = findLocatorById("placeOrder");
		elementClickjs(btnPlaceOrder);
		
		try {
			
			WebElement text = findLocatorByXpath("//h5[text()='Order Details']");
			System.out.println(text);
			
			WebElement txtOrderNo = findLocatorByXpath("//p[text()='Order No: ']//span");
			String orderNo = elementGetText(txtOrderNo);
			System.out.println("Order No : "+orderNo);
			
		} catch (Exception e) {
			
			System.out.println("Exception handled");
			//login();
			
			//WebElement lnkWelcome = findLocatorByXpath("//a[contains(text(),'Welcome')]");
			//elementClick(lnkWelcome);
			
			//WebElement lnkMyAcc = findLocatorByXpath("//a[text()='My Account']");
			//elementClick(lnkMyAcc);
			
			//WebElement lnkOrders = findLocatorByXpath("//a[text()='orders']");
			//elementClick(lnkOrders);
			
			//WebElement textOrderNo = findLocatorByXpath("(//span[@class='font16 ml-2'])[1]");
			//String orderNo = elementGetText(textOrderNo);
			//System.out.println("Order No : "+orderNo);
		}
		
		
	}
	
	@Test(priority = 5,enabled = false)
	private void getOrderId() throws IOException {
		
		login();
		
		WebElement lnkWelcome = findLocatorByXpath("//a[contains(text(),'Welcome')]");
		elementClick(lnkWelcome);
		
		WebElement lnkMyAcc = findLocatorByXpath("//a[text()='My Account']");
		elementClick(lnkMyAcc);
		
		WebElement lnkOrders = findLocatorByXpath("//a[text()='orders']");
		elementClick(lnkOrders);
		
		WebElement textOrderNo = findLocatorByXpath("(//span[@class='font16 ml-2'])[1]");
		String orderNo = elementGetText(textOrderNo);
		System.out.println("Order No : "+orderNo);
		
	}
	
	@Test(priority = 6)
	private void logOutUI() {
		closeWindow();
	}
	
	@Test(priority = 7)

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
	
	@Test(priority = 8)
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
		//for (GetAllOrders data : datas) {
			//if (data.getOrder_no().equals(orderNo)) {
			//	int order_id = data.getId();
			//	System.out.println("Order Id :" + order_id);
			//	orderId = String.valueOf(order_id);
			//	break;
			//}
		//}
		
		orderNo = datas.get(0).getOrder_no();
		int order_id = datas.get(0).getId();
		orderId = String.valueOf(order_id);
		
	}
	
	@Test(priority = 9)
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
	
	@Test(priority = 10)
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

	@Test(priority = 11)
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

	@Test(priority = 12)
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

	@Test(priority = 13)
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
	
	@Test(priority = 14)
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
	
	@Test(priority = 15)
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
