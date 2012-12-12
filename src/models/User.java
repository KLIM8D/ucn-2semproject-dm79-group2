/**
 * filename    : User.java
 * created     : Dec 11, 2012 (1:33:02 PM)
 * description : System operators object, containing
 *               single user information.
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package models;

import java.util.Date;

public class User
{
	int _userId;
	int _permissionId;
	String _firstName;
	String _lastName;
	String _userName;
	String _userPassword;
	Date _createdDate;
	Date _editedDate;

	public int getUserId()
	{ return _userId; }
	public void setUserId(int value)
	{ _userId = value; }

	public int getUserPermission()
	{ return _permissionId; }
	public void setUserPermission(int value)
	{ _permissionId = value; }

	public String getFirstName()
	{ return _firstName; }
	public void setFirstName(String value)
	{ _firstName = value; }

	public String getLastName()
	{ return _lastName; }
	public void setLastName(String value)
	{ _lastName = value; }

	public String getUserName()
	{ return _userName; }
	public void setUserName(String value)
	{ _userName = value; }

	public String getUserPassword()
	{ return _userPassword; }
	public void setUserPassword(String value)
	{ _userPassword = value; }

	public Date getCreatedDate()
	{ return _createdDate; }
	public void setCreatedDate(Date value)
	{ _createdDate = value; }

	public Date getEditedDate()
	{ return _editedDate; }
	public void setEditedDate(Date value)
	{ _editedDate = value; }

	public User(int userId, int permissionId, String firstName, String lastName, String userName, String userPassword, Date createdDate, Date editedDate)
	{
		_userId = userId;
		_permissionId = permissionId;
		_firstName = firstName;
		_lastName = lastName;
		_userName = userName;
		_userPassword = userPassword;
		_createdDate = createdDate;
		_editedDate = editedDate;
	}
}