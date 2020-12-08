package com.skew.challenge.payload;

public class Order {

	private Double quantity;
	private Double price;
	
	public Order() {
		super();
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Order [quantity=" + quantity + ", price=" + price + "]";
	}
	
}
