package com.HostelMS.daoImpl;

import org.hibernate.Session;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.userDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public class userDaoImpl implements userDao {

	@Override
	public user viewRoom(int uId) {
		// getting user to print room details
		try (Session ss = HibernateUtil.getSession()) {

			user u2 = ss.get(user.class, uId);
			return u2;
		}

	}

	// getting profile of the user
	@Override
	public user viewProfile(int uId) {
		try (Session ss = HibernateUtil.getSession()) {

			user u2 = ss.get(user.class, uId);
			return u2;
		}
	}

	// updating password if current password is correct
	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {

		try (Session ss = HibernateUtil.getSession()) {
			ss.beginTransaction();
			user u1 = ss.get(user.class, uId);
			if (u1.getUserPassword().equals(oldPwd)) {
				int status = ss.createQuery("update user set userPassword=:newPwd where userId=:uId")
						.setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ss.getTransaction().commit();
				return status;
			} else {
				throw new GlobalException("To update password you have to enter current password");
			}
		}
	}

	// getting dueamount
	@Override
	public int viewDueAmmount(int uId) {
		// TODO Auto-generated method stub

		try (Session ss = HibernateUtil.getSession()) {

			int amount = (int) ss.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId)
					.uniqueResult();
			return amount;
		}
	}

	@Override
	public int changePhonenumber(int uId, String phone) {
		// TODO Auto-generated method stub

		try (Session ss = HibernateUtil.getSession()) {
			ss.beginTransaction();
			int status = ss.createQuery("update user set userPhone=:phone where userId=:uId")
					.setParameter("phone", phone).setParameter("uId", uId).executeUpdate();
			ss.getTransaction().commit();
			return status;
		}
	}
}