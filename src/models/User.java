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
	UserPermission _userPermission;
	String _firstName;
	String _lastName;
	String _userName;
	String _userPassword;
    String _saltValue;
	Date _creationDate;
	Date _editedDate;

	public int getUserId()
	{ return _userId; }
	public void setUserId(int value)
	{ _userId = value; }

	public UserPermission getUserPermission()
	{ return _userPermission; }
	public void setUserPermission(UserPermission value)
	{ _userPermission = value; }

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

    public String getSaltValue()
    { return _saltValue; }
    public void setSaltValue(String value)
    { _saltValue = value; }

	public Date getCreationDate()
	{ return _creationDate; }
	public void setCreationDate(Date value)
	{ _creationDate = value; }

	public Date getEditedDate()
	{ return _editedDate; }
	public void setEditedDate(Date value)
	{ _editedDate = value; }

    public User(UserPermission userPermission, String firstName, String lastName, String userName, String saltValue, String userPassword, Date createdDate, Date editedDate)
    {
        _userPermission = userPermission;
        _firstName = firstName;
        _lastName = lastName;
        _userName = userName;
        _userPassword = userPassword;
        _saltValue = saltValue;
        _creationDate = createdDate;
        _editedDate = editedDate;
    }

	public User(int userId, UserPermission userPermission, String firstName, String lastName, String userName, String saltValue, String userPassword, Date createdDate, Date editedDate)
	{
		_userId = userId;
		_userPermission = userPermission;
		_firstName = firstName;
		_lastName = lastName;
		_userName = userName;
		_userPassword = userPassword;
        _saltValue = saltValue;
		_creationDate = createdDate;
		_editedDate = editedDate;
	}
}