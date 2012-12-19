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

import java.sql.Connection;
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users");

		ResultSet userResult = _da.callCommandGetResultSet(query, con);
		
		while(userResult.next())
		{
			User user = buildUsers(userResult);
			returnList.add(user);
		}
		
		return returnList;
	}

    /**
     * Retrieve all users from database by user role
     *
     * @return ArrayList<User>
     */
    @Override
    public ArrayList<User> getAllUsersByUserRole(UserPermission userPermission) throws Exception
    {
        ArrayList<User> returnList = new ArrayList<User>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE permissionId = ?");
        query.setInt(1, userPermission.getPermissionId());

        ResultSet userResult = _da.callCommandGetResultSet(query, con);

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
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE userId = ?");
		query.setInt(1, value);

		ResultSet userResult = _da.callCommandGetRow(query, con);

		if(userResult.next())
			return buildUsers(userResult);
		
		return null;
	}

    /**
     * Retrieve a specific user by it's id
     *
     * @param userName				the value of the id you need returned
     * @return User
     */
    @Override
    public User getUserByUserName(String userName) throws Exception
    {
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE userName = ?");
        query.setString(1, userName);

        ResultSet userResult = _da.callCommandGetRow(query, con);

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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO Users (permissionId, firstName, lastName, " +
																"userName, userPassword, saltValue, creationDate, editedDate) " +
																"VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

		query.setInt(1, user.getUserPermission().getPermissionId());
		query.setString(2, user.getFirstName());
		query.setString(3, user.getLastName());
		query.setString(4, user.getUserName());
		query.setString(5, user.getUserPassword());
        query.setString(6, user.getSaltValue());
		query.setString(7, _da.dateToSqlDate(user.getCreationDate()));
		query.setString(8, _da.dateToSqlDate(user.getEditedDate()));

		return _da.callCommand(query, con);
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("UPDATE Users SET permissionId = ? , firstName = ?, lastName = ?, " +
																"userName = ?, userPassword = ?, editedDate = ? " +
																"WHERE userId = ?");
		
		query.setInt(1, user.getUserPermission().getPermissionId());
		query.setString(2, user.getFirstName());
		query.setString(3, user.getLastName());
		query.setString(4, user.getUserName());
		query.setString(5, user.getUserPassword());
		query.setString(6, _da.dateToSqlDate(user.getEditedDate()));
		query.setInt(7, user.getUserId());
		
		return _da.callCommand(query, con);
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM Users WHERE userId = ?");
		query.setInt(1, user.getUserId());
		
		return _da.callCommand(query, con);
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
        String saltValue = row.getString("saltValue");
		Date creationDate = row.getDate("creationDate");
		Date editedDate = row.getDate("editedDate");
		
		return new User(userId, userPermission, firstName, lastName, userName, saltValue, userPassword, creationDate, editedDate);
	}
}