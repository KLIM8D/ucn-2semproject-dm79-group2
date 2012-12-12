/**
 * filename    : DBUser.java
 * created     : Dec 11, 2012 (6:31:42 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package db;

import models.User;
import models.UserPermission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBUser implements IFDBUser
{
	private DataAccess _da;
	public DBUser()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all users from database
	 * 
	 * @return ArrayList<User>
	 */
	@Override
	public ArrayList<User> getAllUsers() throws Exception
	{
		ArrayList<User> returnList = new ArrayList<User>();
		
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Users");
		_da.setSqlCommandText(query);
		ResultSet userResult = _da.callCommandGetResultSet();
		
		while(userResult.next())
		{
			User user = buildUsers(userResult);
			returnList.add(user);
		}
		
		return returnList;
	}
	
	/**
	 * Retrieve a specific user by it's id
	 * 
	 * @param value				the value of the id you need returned
	 * @return User
	 */
	@Override
	public User getUserById(int value) throws Exception
	{
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM User WHERE userId = ?");
		query.setInt(1, value);
		_da.setSqlCommandText(query);
		ResultSet userResult = _da.callCommandGetRow();
		if(userResult.next())
			return buildUsers(userResult);
		
		return null;
	}
	
	/**
	 * Inserts a user into the database
	 * 
	 * @param user				the object that contains the data you want stored
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int insertUser(User user) throws Exception
	{
		if(user == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Users (permissionId, firstName, lastName, " +
																"userName, userPassword, creationDate, editedDate " +
																"VALUES(?, ?, ?, ?, ?, ?, ?)");

		query.setInt(1, user.getUserPermission().getPermissionId());
		query.setString(2, user.getFirstName());
		query.setString(3, user.getLastName());
		query.setString(4, user.getUserName());
		query.setString(5, user.getUserPassword());
		query.setDate(6, (java.sql.Date)user.getCreatedDate());
		query.setDate(7, (java.sql.Date)user.getEditedDate());
		_da.setSqlCommandText(query);
		
		return _da.callCommand();
	}
	
	/**
	 * Updates an existing user in the database
	 * 
	 * @param user				the object containing the data you want to update
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int updateUser(User user) throws Exception
	{
		if(user == null)
			return 0;
		
		if(getUserById(user.getUserId()) == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE Users SET permissionId = ? , firstName = ?, lastName = ?, " +
																"userName = ?, userPassword = ?, creationDate = ?, editedDate = ? " +
																"WHERE userId = ?");
		
		query.setInt(1, user.getUserPermission().getPermissionId());
		query.setString(2, user.getFirstName());
		query.setString(3, user.getLastName());
		query.setString(4, user.getUserName());
		query.setString(5, user.getUserPassword());
		query.setDate(6, (java.sql.Date)user.getCreatedDate());
		query.setDate(7, (java.sql.Date)user.getEditedDate());
		query.setInt(8, user.getUserId());
		_da.setSqlCommandText(query);
		
		return _da.callCommand();
	}
	
	/**
	 * Deletes an existing user from the database
	 * 
	 * @param user				the object containing the role which is going to be deleted
	 * @return int				returns the number of rows affected
	 */
	@Override
	public int deleteUser(User user) throws Exception
	{
		if(user == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Users WHERE userID = ?");
		
		query.setInt(1, user.getUserId());
		_da.setSqlCommandText(query);
		
		return _da.callCommand();
	}
	
	private User buildUsers(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
		DBUserPermission dbUserPermission = new DBUserPermission();
		
		int userId = row.getInt("userId");
		UserPermission userPermission = dbUserPermission.getRoleById(row.getInt("permissionId"));
		String firstName = row.getString("firstName");
		String lastName = row.getString("lastName");
		String userName = row.getString("userName");
		String userPassword = row.getString("userPassword");
		Date creationDate = row.getDate("creationDate");
		Date editedDate = row.getDate("editedDate");
		
		return new User(userId, userPermission, firstName, lastName, userName, userPassword, creationDate, editedDate);
	}
}