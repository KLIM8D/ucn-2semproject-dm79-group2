/**
 * filename    : DBUserPermission.java
 * created     : Dec 11, 2012 (5:05:06 PM)
 * description : Methods for DB interaction
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package db;

import models.UserPermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBUserPermission implements IFDBUserPermission
{
	private DataAccess _da;
	public DBUserPermission()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all roles from database
	 * 
	 * @return ArrayList<UserPermission>
	 */
	@Override
	public ArrayList<UserPermission> getAllRoles() throws Exception
	{
		ArrayList<UserPermission> returnList = new ArrayList<UserPermission>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM UserPermissions");

		ResultSet roleResult = _da.callCommandGetResultSet(query, con);
		
		while(roleResult.next())
		{
			UserPermission userPermission = buildRoles(roleResult);
			returnList.add(userPermission);
		}
		
		return returnList;
	}
	
	/**
	 * Retrieve a specific role by it's id
	 * 
	 * @param value				the value of the id you need returned
	 * @return UserPermission
	 */
	@Override
	public UserPermission getRoleById(int value) throws Exception
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM UserPermissions WHERE permissionId = ?");
		query.setInt(1, value);

		ResultSet roleResult = _da.callCommandGetRow(query, con);
		if(roleResult.next())
			return buildRoles(roleResult);
		
		return null;
	}
	
	/**
	 * Inserts a role into the database
	 * 
	 * @param userPermission	the object that contains the data you want stored
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int insertRole(UserPermission userPermission) throws Exception
	{
		if(userPermission == null)
			return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO UserPermissions (userRole, creationDate, editedDate " +
																"VALUES(?, ?, ?)");
		
		query.setString(1, userPermission.getUserRole());
		query.setString(2,  _da.dateToSqlDate(userPermission.getCreationDate()));
		query.setString(3, _da.dateToSqlDate(userPermission.getEditedDate()));
		
		return _da.callCommand(query, con);
	}
	
	/**
	 * Updates an existing role in the database
	 * 
	 * @param userPermission	the object containing the data you want to update
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int updateRole(UserPermission userPermission) throws Exception
	{
		if(userPermission == null)
			return 0;
		
		if(getRoleById(userPermission.getPermissionId()) == null)
			return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("UPDATE UserRoles SET userRole = ?, " +
																"editedDate = ? WHERE permissionId = ?");
		
		query.setString(1, userPermission.getUserRole());
		query.setString(2, _da.dateToSqlDate(userPermission.getEditedDate()));
		query.setInt(3, userPermission.getPermissionId());
		
		return _da.callCommand(query, con);
	}
	
	/**
	 * Deletes an existing role from the database
	 * 
	 * @param userPermission	the object containing the role which is going to be deleted
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int deleteRole(UserPermission userPermission) throws Exception
	{
		if(userPermission == null)
			return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM UserPermissions WHERE permissionId = ?");
		
		query.setInt(1, userPermission.getPermissionId());
		
		return _da.callCommand(query, con);
	}
	
	private UserPermission buildRoles(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
		int permissionId = row.getInt("permissionId");
		String userRole = row.getString("userRole");
		Date creationDate = row.getDate("creationDate");
		Date editedDate = row.getDate("editedDate");
		
		return new UserPermission(permissionId, userRole, creationDate, editedDate);
	}
}