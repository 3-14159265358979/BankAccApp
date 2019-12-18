package entities;

import structures.Account;

public class Customer extends Person{
	
	private Account acc;
	
	public Customer(String username, String pass, Account acc) {
		super(username, pass);
		this.acc = acc;
	}
	
	public void deposit(double amount) {
		acc.deposit(amount);
	}
	
	public boolean withdraw(double amount) {
		return acc.withdraw(amount);
	}
	
	public boolean onlinePurchase(double amount) {
		return acc.onlinePurchase(amount);
	}
	
	public Account getAccount() {
		return acc;
	}

	

}
