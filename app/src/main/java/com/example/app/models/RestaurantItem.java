package com.example.app.models;

public class RestaurantItem {
    public float price;
	public String itemName;
	public String restaurant;
	public String imageLink;
    public String restaurantLink;
	
	public RestaurantItem(float price, String item, String restaurantName, String restaurantLink, String img) {
		this.price = price;
		this.itemName = item;
		this.restaurant = restaurantName;
		this.restaurantLink = restaurantLink;
		this.imageLink = img;
	}
}
