package com.HostelMS.serviceImpl;


import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.HostelMS.App;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.daoImpl.hostelMSDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.admindashboard;
import com.HostelMS.service.loginregister;
import com.HostelMS.service.userdashboard;

public class loginregisterimpl implements loginregister {
	static Logger lg=Logger.getLogger(App.class);
	static Scanner sk=new Scanner(System.in);
	static hostelMSDao dao=new hostelMSDaoImpl();
	
	//registration method
	public void register() throws GlobalException{
		lg.info("welcome to registeration");
		lg.info("Enter Username");
		String uname=sk.next();
		lg.info("Create Password");
		String upwd=sk.next();
		lg.info("Enter Phone number");
		String uphone=sk.next();
		lg.info("Enter Address");
		String uaddress=sk.next();
		
		user u1=new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);
		//regular expressions to check data correctness
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			//saving the user details
			int status=dao.registration(u1);
			//log.info(status);
			if(status==1) {
				lg.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
		else {
			throw new GlobalException("Invalid data");
		}
}

	public void login()throws GlobalException {
		lg.info("  welcome ;) to Login  ");
		
		lg.info("Enter username");
		String username=sk.next();
		lg.info("Enter password");
		String password=sk.next();
		//checking login
		user u1=dao.login(username, password);
		//success message
		lg.info("Hello "+u1.getUserName()+" Login Success");
		userdashboard udash=new userdashboardImpl();
		admindashboard adash=new admindashboardImpl();
		
		
		
		
		if(u1.getUserRole().equals("student")) {
			udash.dashboard();
		}
		else if(u1.getUserRole().equals("admin")) {
			adash.dashboard();
		}
	}

}