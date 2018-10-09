package com.bigbagstore;

class InvalidPaymentModeException extends Exception {
	private final String ERROR_MSG = "Invalid Payment Mode Please Try Again";

	public InvalidPaymentModeException() {
	}

	@Override
	public String toString() {
		return "InvalidPaymentModeException [ERROR_MSG=" + ERROR_MSG + "]";
	}

}
