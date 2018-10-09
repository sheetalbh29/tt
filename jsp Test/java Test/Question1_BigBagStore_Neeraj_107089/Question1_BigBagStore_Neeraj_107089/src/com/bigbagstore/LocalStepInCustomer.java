package com.bigbagstore;

import java.util.Scanner;

class LocalStepInCustomer extends Customer {

	private int maxCreditLimit;

	public LocalStepInCustomer() {
	}

	public LocalStepInCustomer(String customerName, String customerType, int maxCreditLimit) {
		super(customerName, customerType);
		this.maxCreditLimit = maxCreditLimit;
	}

	public int getMaxCreditLimit() {
		return maxCreditLimit;
	}

	public void setMaxCreditLimit(int maxCreditLimit) {
		this.maxCreditLimit = maxCreditLimit;
	}

	@Override
	public boolean makePayment(String paymentMode) throws InvalidPaymentModeException {
		Scanner in = new Scanner(System.in);
		switch (paymentMode.trim().toLowerCase()) {
		case "internet banking":
			break;
		case "paytm":
			break;
		case "cash":
			break;
		case "credit":
			break;
		default:
			throw new InvalidPaymentModeException();
		}
		return true;
	}
}
