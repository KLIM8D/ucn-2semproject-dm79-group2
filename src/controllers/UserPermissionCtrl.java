/**
 * filename    : UserPermissionCtrl.java
 * created     : Dec 12, 2012 (12:17:18 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package controllers;

import db.DBUserPermission;
import models.UserPermission;

import java.util.ArrayList;

public class UserPermissionCtrl
{
	public UserPermissionCtrl()
	{
		
	}
	
	public ArrayList<UserPermission> getAllRoles() throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.getAllRoles();
	}
	
	public UserPermission getRoleById(int value) throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.getRoleById(value);
	}
	
	public int insertRole(UserPermission userPermission) throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.insertRole(userPermission);
	}
	
	public int updateRole(UserPermission userPermission) throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.updateRole(userPermission);
	}
	
	public int deleteRole(UserPermission userPermission) throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.deleteRole(userPermission);
	}
	
	public UserPermission getPermissionByTitle(String title) throws Exception
	{
		DBUserPermission dbup = new DBUserPermission();
		return dbup.getPermissionByTitle(title);
	}
}