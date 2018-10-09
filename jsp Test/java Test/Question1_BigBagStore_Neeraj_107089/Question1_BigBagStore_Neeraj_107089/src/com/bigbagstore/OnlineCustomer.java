package com.bigbagstore;

import java.util.Scanner;

class OnlineCustomer extends Customer {
	private String discountCoupon;

	public OnlineCustomer() {
	}

	public OnlineCustomer(String customerName, String customerType, String discountCoupon) {
		super(customerName, customerType);
		this.discountCoupon = discountCoupon;
	}
	
	public String getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(String discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	@Override
	public boolean makePayment(String paymentMode) throws InvalidPaymentModeException {
		Scanner in = new Scanner(System.in);
		switch (paymentMode.trim().toLowerCase()) {
		case "internet banking":
			break;
		case "paytm":
			break;
		default:
			throw new InvalidPaymentModeException();
		}

		return true;
	}

}
