//interface is declare to achiecve abstraction



package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public interface hostelMSDao {

	public int registration(user u1) throws GlobalException;

	public user login(String username, String password)throws GlobalException;

}
