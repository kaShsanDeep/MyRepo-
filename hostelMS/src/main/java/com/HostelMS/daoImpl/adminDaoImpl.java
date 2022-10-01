// admin implementation

package com.HostelMS.daoImpl;

import java.util.List;

import javax.persistence.Query;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.adminDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

import org.hibernate.Session;

public class adminDaoImpl implements adminDao {

	@Override
	// method to create room
	public int createRoom(room r1) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();

			String roomName = r1.getRoomName();

			room r2 = null;
			// To get data of r2 if available
			r2 = (room) ss.createQuery("from room where roomName =:roomName").setParameter("roomName", roomName)
					.uniqueResult();

			// checking the condition
			if (r2 == null) {

				ss.save(r1);
				ss.getTransaction().commit();
				return 1;
			} else {
				// throw a new exception if r2 is not null
				throw new GlobalException("room is already exist");
			}

		}

	}

	@Override
	// method to delete any user
	public int deleteUser(int uId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();

			int st = ss.createQuery("delete from user where userId =:uId").setParameter("uId", uId).executeUpdate();

			return st;

		}
	}

	@Override
	// method to allot room to user
	public int allotRoom(int rId, int uId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();
			// HQL query to alloting the room to any user
			int st = ss.createQuery("update user set userRoom_roomId =:rId where userId=:uId ").setParameter("uId", uId)
					.setParameter("rId", rId).executeUpdate();

			ss.getTransaction().commit();

			return st;
		}
	}

	@Override
	// method to declare total amount left to be paid by the user
	public int paidDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();

			int dueFee = (int) ss.createQuery("select userFee from user where userId =:uId").setParameter("uId", uId)
					.uniqueResult();

			dueFee -= amount;

			int st = ss.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();

			return st;

		}
	}

	@Override
	// method to view user all details
	public user viewUserProfile(int uId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();

			user u1 = ss.get(user.class, uId);

			return u1;
		}
	}

	@Override
	// method to add due over the user
	public int addDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			ss.beginTransaction();

			user u = ss.get(user.class, uId);
			int dueFee = u.getUserFee();
			dueFee += amount;

			// HQL Query to adding the dues to user
			int st = ss.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();

			return st;
		}
	}

	@Override
	// method to view all the users
	public List<user> viewUsers() {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			Query q = ss.createQuery("from user");
			@SuppressWarnings("unchecked")
			List<user> ul = q.getResultList();
			return ul;
		}
	}

	@Override
	public List<user> userInARoom(int rId) {
		// TODO Auto-generated method stub
		try (Session ss = HibernateUtil.getSession()) {

			Query q = ss.createQuery("from user where userRoom_roomId =:rId").setParameter("rId", rId);

			List<user> rl = q.getResultList();
			return rl;
		}
	}

	@Override
// method to view all the rooms existed in the hostel
	public List<room> viewRooms() {
		// autoclosable session object
		try (Session ss = HibernateUtil.getSession()) {
			// getting rows of a room table
			Query q = ss.createQuery("from room");
			@SuppressWarnings("unchecked")
			List<room> rl = q.getResultList();
			return rl;
		}
	}
}