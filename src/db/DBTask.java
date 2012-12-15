package db;

import models.Task;

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

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Tasks");
        _da.setSqlCommandText(query);
        ResultSet tasks = _da.callCommandGetResultSet();

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
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Tasks WHERE taskId = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet taskResult = _da.callCommandGetRow();
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
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Tasks WHERE title = ?");
        query.setString(1, title);
        _da.setSqlCommandText(query);
        ResultSet taskResult = _da.callCommandGetRow();
        if(taskResult.next())
            return buildTask(taskResult);

        return null;
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

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Tasks (title, description) VALUES (?, ?)");

        query.setString(1, task.getTitle());
        query.setString(2, task.getDescription());
        _da.setSqlCommandText(query);

        return _da.callCommand();
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

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Tasks SET title = ?, description = ? WHERE taskId = ?");
        query.setString(1, task.getTitle());
        query.setString(2, task.getDescription());
        query.setLong(3, task.getTaskId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
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

        int rowsAffected = 0;
        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Tasks WHERE taskId = ?");
        query.setLong(1, task.getTaskId());
        _da.setSqlCommandText(query);
        rowsAffected += _da.callCommand();

        return rowsAffected;
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