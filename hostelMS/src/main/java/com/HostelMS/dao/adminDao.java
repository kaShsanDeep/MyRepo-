

package com.HostelMS.dao;

import java.util.List;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

public interface adminDao {
     public List<room>viewRooms();
     public List<user>viewUsers();
     public int createRoom (room r1)throws GlobalException;
     public int allotRoom(int rId, int uId )throws GlobalException;
     public int deleteUser(int uId);
     public int addDueAmount(int amount , int uId)throws GlobalException;
     public int paidDueAmount(int amount , int uId )throws GlobalException;
     public user viewUserProfile(int uId)throws GlobalException;
     public List<user>userInARoom(int rId)throws GlobalException;
     
}
