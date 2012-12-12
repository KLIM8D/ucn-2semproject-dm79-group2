/**
 * filename    : IFDBUser.java
 * created     : Dec 11, 2012 (6:17:15 PM)
 * description : Interface class for DBUser
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package db;

import models.User;

import java.util.ArrayList;

public interface IFDBUser
{
	/**
	 * Retrieve all roles from database
	 * 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getAllUsers() throws Exception;
	
	/**
	 * Retrieve a specific user by it's id
	 * 
	 * @param value				the value of the id you need returned
	 * @return User
	 */
	public User getUserById(int value) throws Exception;
	
	/**
	 * Insert a user into the database
	 * 
	 * @param user				the object that contains the data you want stored
	 * @return int				returns the number of rows affected
	 */
	public int insertUser(User user) throws Exception;
	
	/**
	 * Update an existing user in the database
	 * 
	 * @param user					the object containing the updated information you want stored
	 * @return int					returns the number of rows effected
	 */
	public int updateUser(User user) throws Exception;
	
	/**
	 * Delete an existing user from the database
	 * 
	 * @param user					the object containing the role which is going to be delete from the database
	 * @return int					returns the number of rows affected
	 */
	public int deleteUser(User user) throws Exception;
}