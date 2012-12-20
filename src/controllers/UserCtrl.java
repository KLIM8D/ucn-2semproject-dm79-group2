/**
 * filename    : UserCtrl.java
 * created     : Dec 12, 2012 (12:34:32 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package controllers;

import db.DBPermissionWrapper;
import db.DBUser;
import models.TimeSheet;
import models.User;
import models.UserPermission;
import utils.Helper;

import java.util.ArrayList;

public class UserCtrl
{
	public UserCtrl()
	{
		
	}
	
	public ArrayList<User> getAllUsers() throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.getAllUsers();
	}

    public ArrayList<User> getAllUsersByUserRole(UserPermission userPermission) throws Exception
    {
        DBUser dbu = new DBUser();
        return dbu.getAllUsersByUserRole(userPermission);
    }
	
	public User getUserById(int value) throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.getUserById(value);
	}
	
	public User getUserByName(String userName) throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.getUserByUserName(userName);
	}
	
	public int insertUser(User user) throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.insertUser(user);
	}
	
	public int updateUser(User user) throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.updateUser(user);
	}
	
	public int deleteUser(User user) throws Exception
	{
        DBUser dbu = new DBUser();
		return dbu.deleteUser(user);
	}

    public boolean validateUserLogin(String userName, String password) throws Exception
    {
        DBUser dbu = new DBUser();
        User user = dbu.getUserByUserName(userName.trim());

        if(user == null)
            return false;

        String saltValue = user.getSaltValue();
        String hashed = Helper.hashPassword(password, saltValue);

        return hashed.equals(user.getUserPassword());
    }

    public boolean isUserAllowed(TimeSheet sheet, String userName) throws Exception
    {
        User user = getUserByName(userName);
        DBPermissionWrapper dbPermissionWrapper = new DBPermissionWrapper();
        return dbPermissionWrapper.getPermissionByUser(sheet, user);
    }
}