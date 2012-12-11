/**
 * filename    : User.java
 * created     : Dec 11, 2012 (1:33:02 PM)
 * description : User role object, containing the single
 *               users role (admin, superuser etc.).
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package models;

import java.util.Date;

public class UserPermission
{
	private int _permissionId;
	private String _userRole;
	private Date _createdDate;
	private Date _editedDate;
	
	public int getPermissionId()
	{ return _permissionId; }
	public void setPermissionId(int value)
	{ _permissionId = value; }
	
	public String getUserRole()
	{ return _userRole; }
	public void setUserRole(String value)
	{ this._userRole = value; }

	public Date getCreatedDate()
	{ return _createdDate; }
	public void setCreatedDate(Date value)
	{ _createdDate = value; }

	public Date getEditedDate()
	{ return _editedDate; }
	public void setEditedDate(Date value)
	{ _editedDate = value; }

	public UserPermission(int permissionId, String userRole, Date createdDate, Date editedDate)
	{
		_permissionId = permissionId;
		_userRole = userRole;
		_createdDate = createdDate;
		_editedDate = editedDate;
	}	
}