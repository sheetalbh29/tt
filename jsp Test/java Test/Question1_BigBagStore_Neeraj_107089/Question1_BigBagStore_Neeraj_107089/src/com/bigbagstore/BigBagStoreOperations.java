package com.bigbagstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class BigBagStoreOperations {
	private static Map<Integer, Customer> customerList = new HashMap<>();

	public int checkUser(String username) {
		int userID = 0;
		Set<Integer> id = customerList.keySet();
		for (Integer key : id) {
			if (customerList.get(key).getCustomerName().equalsIgnoreCase(username.trim())) {
				userID = key;
			}
		}
		return userID;
	}

	public void payment(int id) throws InvalidPaymentModeException {
		String paymentMode;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter amount to be paid by user : ");
		int amount = Integer.parseInt(in.nextLine());
		if (customerList.get(id).getCustomerType().equals("Online Customer")) {
			OnlineCustomer oc1 = (OnlineCustomer) customerList.get(id);
			amount = amount - Integer.parseInt(oc1.getDiscountCoupon());
			System.out.println("Choose payment options from : \nIntenet Banking \t PayTm");
			paymentMode = in.nextLine();
			OnlineCustomer oc = new OnlineCustomer();
			boolean flag = oc.makePayment(paymentMode);
			if (flag) {
				fileWrite(customerList.get(id).getCustomerName(), amount, paymentMode);
			}
		} else {
			LocalStepInCustomer lc1 = (LocalStepInCustomer) customerList.get(id);
			System.out.println("Choose payment options from : \nIntenet Banking \t PayTm \t Cash \t Credit");
			paymentMode = in.nextLine();
			if (lc1.getMaxCreditLimit() < amount && paymentMode.trim().equalsIgnoreCase("credit")) {
				System.out.println("Credit option is not available");
				return;
			}
			LocalStepInCustomer lc = new LocalStepInCustomer();
			boolean flag = lc.makePayment(paymentMode);
			if (flag) {
				fileWrite(customerList.get(id).getCustomerName(), amount, paymentMode);
			}
		}

	}

	public void addCustomerToMap(Customer c) {
		int customerID = c.getCustomerId();
		customerList.put(customerID, c);
		System.out.println("User added user ID is : " + customerID);
	}

	public int countCustomers(String customerType) {
		int count = 0;
		Set<Integer> id = customerList.keySet();
		for (Integer key : id) {
			if (customerType.equalsIgnoreCase(customerList.get(key).getCustomerType())) {
				count++;
			}
		}
		return count;
	}

	void fileWrite(String path, int amount, String paymentMode) {
		Date d = new Date();
		SimpleDateFormat fd = new SimpleDateFormat("dd-MM-yyyy");
		String date = fd.format(d);
		String record = "";
		try {
			FileWriter fw = new FileWriter(new File(path));
			BufferedWriter bw = new BufferedWriter(fw);
			record = "File Name    : " + path + "\n" + "Date of Bill : " + date + "\n" + "Amount       : "
					+ Integer.toString(amount) + "\n" + "Payment Mode : " + paymentMode;
			bw.write(record);
			System.out.println("\nPayment Done...\n");
			bw.close();
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
