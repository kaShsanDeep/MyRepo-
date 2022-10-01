package com.HostelMS.serviceImpl;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Scanner;
import com.HostelMS.dao.adminDao;
import com.HostelMS.model.room;
import com.HostelMS.model.user;
import com.HostelMS.daoImpl.adminDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.service.admindashboard;

public class admindashboardImpl implements admindashboard {
	static admindashboard adl = new admindashboardImpl();
	static adminDao dao = new adminDaoImpl();
	static Logger lg = Logger.getLogger(admindashboardImpl.class);
	static Scanner sc = new Scanner(System.in);

	@Override
	public void dashboard() throws GlobalException {
		lg.info("\t\t\t\twelcome to Admin dashboard   ");
		int x = 0;
		while (x < 10) {
			lg.info("\n\tPress 1 : To create room \t\tPress 2 :To allot room to users \n\tPress 3 :To view rooms  \t\tPress 4 :To view users \n\tPress 5 :To view users in room \t\tPress 6 :To view user profile \n\tPress 7 :To add dues \t\t\tPress 8 :To show dues to paid \n\tPress 9 :To delete a user");
			x = sc.nextInt();

			switch (x) {

			case 1 -> adl.createRoom();
			case 2 -> adl.allotRoom();
			case 3 -> adl.viewRooms();
			case 4 -> adl.viewUsers();
			case 5 -> adl.userInARoom();
			case 6 -> adl.viewUserProfile();
			case 7 -> adl.addDueAmount();
			case 8 -> adl.paidDueAmount();
			case 9 -> adl.deleteUser();

			}
		}

	}

	@Override
	public void viewRooms() {

		List<room> st = dao.viewRooms();
		for (room r : st) {

			lg.info("\tRoom Id Is :" + r.getRoomId() + "\tRoom Name Is :" + r.getRoomName() + "\tRoom Type Is :"
					+ r.getRoomType());
		}

	}

	@Override
	public void viewUsers() {
		// TODO Auto-generated method stub
		List<user> st = dao.viewUsers();
		for (user u : st) {
			lg.info("\tUser id is :" + u.getUserId() + "\tUser Name is :" + u.getUserName() + "\tUser password :"
					+ u.getUserPassword() + "\tUser addres is :" + u.getUserAddress() + "\tUser fee is :"
					+ u.getUserFee() + "\tUser phone is :" + u.getUserPhone() + "\tUser Role is :" + u.getUserRole());
		}
	}

	@Override
	public void userInARoom() {
		// TODO Auto-generated method stub
		lg.info("enter room id :");
		int rId = sc.nextInt();
		List<user> st = dao.userInARoom(rId);
		for (user u : st) {

			lg.info("\tUser id is:" + u.getUserId() + "\tUser Name is:" + u.getUserName() + "\tUser addres is : "
					+ u.getUserAddress() + "\tUser fee is :" + u.getUserFee() + "\tUser phone is :" + u.getUserPhone()
					+ "\tUser Role is :" + u.getUserRole());
		}

	}

	@Override
	public void createRoom() {
		// TODO Auto-generated method stub

		try {
			lg.info(" enter room details ");
			lg.info(" enter room name :");
			String rName = sc.next();
			lg.info(" enter room id :");
			int rId = sc.nextInt();

			lg.info("enter room type :");
			String rType = sc.next();
			room r1 = new room();

			r1.setRoomId(rId);
			r1.setRoomName(rName);
			r1.setRoomType(rType);

			int st = dao.createRoom(r1);
			if (st == 1) {

				lg.info("room created successfully ;)");
			}
		}

		catch (Exception e) {

			lg.info(e.getMessage());
		}
	}

	@Override
	public void allotRoom() throws GlobalException {
		// TODO Auto-generated method stub
		lg.info("---Alloting room to users---");
		lg.info("enter user id :");
		int uId = sc.nextInt();
		lg.info("enter room id :");
		int rId = sc.nextInt();
		int st = dao.allotRoom(rId, uId);

		if (st == 1) {

			lg.info("room has been alloted ;) ");
		} else {

			throw new GlobalException("user/room id is invalid");
		}

	}

	@Override
	public void deleteUser() throws GlobalException {
		// TODO Auto-generated method stub

		lg.info("enter user id :");
		int uId = sc.nextInt();
		int st = dao.deleteUser(uId);
		if (st == 1) {

			lg.info("User Deleted Successfully ;(");
		} else {

			throw new GlobalException("user does'nt exist");
		}

	}

	@Override
	public void addDueAmount() throws GlobalException {
		// TODO Auto-generated method stub
		lg.info("enter amount which u want to add");
		int amount = sc.nextInt();

		lg.info("enter user id :");
		int uId = sc.nextInt();
		int st = dao.addDueAmount(amount, uId);

		if (st == 1) {

			lg.info("Due added successfully");

		} else {
			throw new GlobalException("user not found");
		}

	}

	@Override
	public void viewUserProfile() {
		// TODO Auto-generated method stub
		lg.info("enter user id ");
		int uId = sc.nextInt();
		user u = dao.viewUserProfile(uId);

		lg.info(u);

	}

	@Override
	public void paidDueAmount() throws GlobalException {
		// TODO Auto-generated method stub
		lg.info("enter amount which is already paid by user");
		int amount = sc.nextInt();

		lg.info("enter user id :");
		int uId = sc.nextInt();
		int st = dao.paidDueAmount(amount, uId);
		if (st == 1) {

			lg.info("final due is analizd successfull");

		} else {

			throw new GlobalException("User not found :( ");
		}
	}

}