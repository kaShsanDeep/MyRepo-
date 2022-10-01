// Hostel Management System 
// using with JPA, HIBERNATE, MySQL
//@Sandeep kumar

package com.HostelMS;

import java.util.Scanner;
import com.HostelMS.serviceImpl.loginregisterimpl;
import org.apache.log4j.Logger;
import com.HostelMS.service.loginregister;
import com.HostelMS.exception.GlobalException;

public class App {
	// main method
	static Logger lg = Logger.getLogger(App.class);
	// loggers for output

	public static void main(String[] args) 
			throws GlobalException {
		Scanner sc = new Scanner(System.in);
		loginregister loginreg = new loginregisterimpl();
		// object of log-in-register-impl()

		lg.info("\t\t\t\t\t Hostel_Management System  ");
		lg.info("\n Press 1 for Login \nPress 2 for Registeration ");
		int x = sc.nextInt();

		
		switch (x) {
		case 1 -> loginreg.login();
		case 2 -> loginreg.register();
		}
		sc.close();
	}
}