package com.HostelMS.serviceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.HostelMS.dao.userDao;
import com.HostelMS.daoImpl.userDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.userdashboard;

public class userdashboardImpl implements userdashboard {
	// declaring static objects to use in entire class
	static Logger lg = Logger.getLogger(userdashboardImpl.class);
	static Scanner sk = new Scanner(System.in);
	static userdashboardImpl udl = new userdashboardImpl();
	static userDao dao = new userDaoImpl();
	static int userId;

	// view room details of the user
	@Override
	public void viewRoom() {
		
		user u1 = dao.viewRoom(userId);                 // calling dao
		lg.info("Hello " + u1.getUserName() + " your room number is" + u1.getUserRoom().getRoomId() + " room name is "
				+ u1.getUserRoom().getRoomName() + " and it is " + u1.getUserRoom().getRoomType() + " room");
	}


	@Override
	public void viewDueAmmount() {
		
		int amount = dao.viewDueAmmount(userId);    // calling dao
		lg.info("your fee due is :" + amount);
	}

	// viewProfile with toString
	@Override
	public void viewProfile() {

		user u1 = dao.viewProfile(userId);
		lg.info(u1);

	}

	// to change phone number
	@Override
	public void changePhonenumber() {
		lg.info("Enter New Phone number");
		String phone = sk.next();
		
		int st = dao.changePhonenumber(userId, phone);
		
		if (st == 1) {
			lg.info("Phone number updated");
		}
	}

	
	@Override
	public void changePassword() throws GlobalException {

		lg.info("Enter OLD Password");
		String oldpwd = sk.next();
		
		lg.info("Enter New Password");
		String newpwd = sk.next();
		
		
		int st = dao.changePassword(userId, oldpwd, newpwd);

		if (st == 1) {
			lg.info("password changed");
		}
	}

	@Override
	public void dashboard() throws GlobalException {
		lg.info("\t\t\t userdashboard   ");

		int x = 0;
		while (x < 6) {
			// user can select operation
			lg.info("\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");

			x = sk.nextInt();

			switch (x) {

			case 1 -> udl.viewRoom();

			case 2 -> udl.viewDueAmmount();

			case 3 -> udl.viewProfile();

			case 4 -> udl.changePhonenumber();

			case 5 -> udl.changePassword();
			}
		}
	}

}