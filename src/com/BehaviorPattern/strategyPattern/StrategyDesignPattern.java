package com.BehaviorPattern.strategyPattern;

import java.util.List;
import java.util.ArrayList;

/**
 * Strategy design pattern is one of the behavioral design pattern. Strategy
 * pattern is used when we have multiple algorithm for a specific task and
 * client decides the actual implementation to be used at runtime.
 * 
 * @author nitin
 *         <P>
 *         Strategy pattern is also known as <B>Policy Pattern</B>. We define
 *         multiple algorithms and let client application pass the algorithm to
 *         be used as a parameter.
 *         </p>
 *
 */

interface PaymentStrategy {
	public void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;

	public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate) {
		this.name = nm;
		this.cardNumber = ccNum;
		this.cvv = cvv;
		this.dateOfExpiry = expiryDate;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid with credit/debit card");
	}

}

class NetBankingStrategy implements PaymentStrategy {

	private String username;
	private String password;

	public NetBankingStrategy(String username, String pwd) {
		this.username = username;
		this.password = pwd;
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Net Banking.");
	}

}

class Item {
	private String upcCode;
	private int price;

	public Item(String upc, int cost) {
		this.upcCode = upc;
		this.price = cost;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public int getPrice() {
		return price;
	}
}

class ShoppingCart {

	// List of items
	List<Item> items;

	public ShoppingCart() {
		this.items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public int calculateTotal() {
		int sum = 0;
		for (Item item : items) {
			sum += item.getPrice();
		}
		return sum;
	}

	public void pay(PaymentStrategy paymentMethod) {
		int amount = calculateTotal();
		paymentMethod.pay(amount);
	}
}

public class StrategyDesignPattern {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		Item item1 = new Item("1234", 10);
		Item item2 = new Item("5678", 40);

		cart.addItem(item1);
		cart.addItem(item2);

		// pay by paypal
		cart.pay(new NetBankingStrategy("nitinkumargupta", "IamFighter700@"));

		// pay by credit card
		cart.pay(new CreditCardStrategy("Nitin Kumar Gupta", "1234567890123456", "786", "12/15"));
	}

}
