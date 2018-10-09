package com.bigbagstore;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BigBagStoreApp {
	Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;
		BigBagStoreApp obj = new BigBagStoreApp();
		BigBagStoreOperations bbs = new BigBagStoreOperations();
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("Select Option \n1.Store Customer \t\t 2.Count No of Online Customers \n"
					+ "3.Count No of Local Customers \t 4.Make Payment \t 5.Exit");
			choice = Integer.parseInt(in.nextLine());
			switch (choice) {
			case 1:
				obj.addCustomer();
				break;
			case 2:
				System.out.println("No of Online Customers are : " + bbs.countCustomers("Online Customer"));
				break;
			case 3:
				System.out.println("No of Online Customers are : " + bbs.countCustomers("Local Customer"));
				break;
			case 4:
				obj.makePayment();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
			}
		}

	}

	public void addCustomer() {
		String name;
		int id, choice;
		int flag = 0;
		System.out.println("Enter the User name : ");
		name = in.nextLine();
		while (flag == 0) {
			System.out.println("Select the user type : 1.Local Step In \t 2.Online \t 3.Exit");
			choice = Integer.parseInt(in.nextLine());
			switch (choice) {
			case 1:
				addLocalCustomer(name);
				flag = 1;
				break;
			case 2:
				addOnlineCustomer(name);
				flag = 1;
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Selection");
			}
		}

	}

	public void addLocalCustomer(String name) {
		String customerType = "Local Customer";
		int maxCreditLimit;
		System.out.println("Enter max credit Limit for customer : ");
		maxCreditLimit = Integer.parseInt(in.nextLine());
		LocalStepInCustomer lc = new LocalStepInCustomer(name, customerType, maxCreditLimit);
		BigBagStoreOperations bbs = new BigBagStoreOperations();
		bbs.addCustomerToMap(lc);
	}

	public void addOnlineCustomer(String name) {
		String customerType = "Online Customer", discountCoupon;
		System.out.println("Enter discount coupon amount for customer  :");
		discountCoupon = in.nextLine();
		OnlineCustomer oc = new OnlineCustomer(name, customerType, discountCoupon);
		BigBagStoreOperations bbs = new BigBagStoreOperations();
		bbs.addCustomerToMap(oc);
	}

	public void makePayment() {
		String username;
		System.out.println("Enter UserName : ");
		username = in.nextLine();
		BigBagStoreOperations bbs = new BigBagStoreOperations();
		int userID = bbs.checkUser(username);
		if (userID != 0) {
			try {
				bbs.payment(userID);
			} catch (InvalidPaymentModeException e) {
				System.err.println(e);
				return;
			}
		} else {
			System.out.println("Username Does not exists please check");
			return;
		}
	}
}
