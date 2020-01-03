package com.revature.model;

public class BankAccount extends User {
	
	private long id;
	private double balance;
	
	private User user;

	public BankAccount() {
		super();
	}

	public BankAccount(long id, double balance, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.user = user;
	}
	
	
	public BankAccount(double balance, String userName) {
		super();
		this.balance = balance;
		this.username = userName;
	}

	
	public BankAccount(double balance, User user) {
		super();
		this.balance = balance;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", balance=" + balance + ", user=" + user + "]";
	}
	
}