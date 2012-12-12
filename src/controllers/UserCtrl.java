/**
 * filename    : UserCtrl.java
 * created     : Dec 12, 2012 (12:34:32 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package controllers;

import db.DBUser;
import models.User;

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
	
	public User getUserById(int value) throws Exception
	{
		DBUser dbu = new DBUser();
		return dbu.getUserById(value);
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
}