package com.bigbagstore;

public interface Payment {
	public boolean makePayment(String paymentMode) throws InvalidPaymentModeException;
}
