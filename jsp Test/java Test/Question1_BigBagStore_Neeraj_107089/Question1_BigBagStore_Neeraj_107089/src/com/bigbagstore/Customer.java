package com.bigbagstore;

abstract class Customer implements Payment {
	private static int customerId = 100;
	private String customerName;
	private String customerType;

	public Customer() {
	}

	public Customer(String customerName, String customerType) {
		super();
		customerId = customerId + 1;
		this.customerName = customerName;
		this.customerType = customerType;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerType="
				+ customerType + "]";
	}

}
