package omrbranch.day11;

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

public class Day11_U1101 extends BaseClass {

	String logtoken;
	int state_Id;
	int city_Id;
	String categoryId;
	String productName;
	String  productId;
	String variantId;
	String cartId;
	String addressId;
	String orderNo;
	String orderId;
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

	@Test(priority = 4)
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

		CreateOrder_Input_Pojo createOrder_Input_Pojo = new CreateOrder_Input_Pojo("debit_card", "5555555555552222",
				"visa", "2025", "08", "123");
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
		System.out.println("Order Number : " + order_no);
		orderNo = String.valueOf(order_no);

	}

	@Test(priority = 11)
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

	@Test(priority = 12)
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

	@Test(priority = 13)
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

	@Test(priority = 14)
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

	@Test(priority = 15)
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

	@Test(priority = 16)
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
	
	@Test(priority = 17)
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
