package db;

import models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBTask implements IFDBTask
{
    private DataAccess _da;
	public DBTask()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all tasks information from database
	 *
	 * @return ArrayList<Task>
	 */
    @Override
	public ArrayList<Task> getAllTasks() throws Exception
	{
		ArrayList<Task> returnList = new ArrayList<Task>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tasks");

        ResultSet tasks = _da.callCommandGetResultSet(query, con);

        while(tasks.next())
        {
            Task task = buildTask(tasks);
            returnList.add(task);
        }

		return returnList;
	}
	
	/**
	 *  Retrieve specific task record by id
	 *  
	 *  @param id					the id of the record you wish to return
	 *  @return Task
	 */
    @Override
	public Task getTaskById(long id) throws Exception
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tasks WHERE taskId = ?");
        query.setLong(1, id);

        ResultSet taskResult = _da.callCommandGetRow(query, con);
        if(taskResult.next())
            return buildTask(taskResult);

        return null;
	}
	
	/**
	 * Retrieve specific task by name
	 * 
	 * @param title					the name of the record you wish to return
	 * @return Task
	 */
    @Override
	public Task getTaskByTitle(String title) throws Exception
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tasks WHERE title = ?");
        query.setString(1, title);

        ResultSet taskResult = _da.callCommandGetRow(query, con);
        if(taskResult.next())
            return buildTask(taskResult);

        return null;
	}

    /**
     * Using a wildcard search to, retrieve all Tasks by the searchString.
     *
     * @param searchString				what you want to search for
     * @return ArrayList<Task>
     * @throws Exception
     */
    @Override
    public ArrayList<Task> searchForTasks(String searchString) throws Exception
    {
        ArrayList<Task> returnList = new ArrayList<Task>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tasks WHERE title LIKE '%?%' OR description LIKE '%?%'");
        query.setString(1, searchString);
        query.setString(2, searchString);

        ResultSet tasks = _da.callCommandGetResultSet(query, con);

        while (tasks.next())
        {
            Task task = buildTask(tasks);
            returnList.add(task);
        }

        return returnList;
    }

    /**
     * Inserts a new task in the database
     *
     * @param task				the object containing the information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int insertTask(Task task) throws Exception
	{
        if(task == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO Tasks (title, description) VALUES (?, ?)");

        query.setString(1, task.getTitle());
        query.setString(2, task.getDescription());

        return _da.callCommand(query, con);
	}

    /**
     * Update a existing task in database
     *
     * @param task 				the object containing the updated information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int updateTask(Task task) throws Exception
	{
		if(task == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("UPDATE Tasks SET title = ?, description = ? WHERE taskId = ?");
        query.setString(1, task.getTitle());
        query.setString(2, task.getDescription());
        query.setLong(3, task.getTaskId());

        return _da.callCommand(query, con);
	}

    /**
     * Delete an existing task from the database
     *
     * @param task 		the object containing the task which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    @Override
    public int deleteTask(Task task) throws Exception
    {
        if(task == null)
            return 0;
        
        if(getTaskById(task.getTaskId()) == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM Tasks WHERE taskId = ?");
        query.setLong(1, task.getTaskId());

        return _da.callCommand(query, con);
    }

	private Task buildTask(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
        int taskId = row.getInt("taskId");
        String title = row.getString("title");
        String description = row.getString("description");

        return new Task(taskId, title, description);
	}
}