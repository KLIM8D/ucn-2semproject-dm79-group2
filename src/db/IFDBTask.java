package db;

import models.Task;
import java.util.ArrayList;

public interface IFDBTask {
	
	/**
	 * Retrieve all tasks from database
	 *
	 * @return ArrayList<Task>
	 */
	public ArrayList<Task> getAllTasks() throws Exception;
	
	/**
	 * Get specific task by id
	 * 
	 * @param id					the id of the task you need returned
	 * @return Task
	 */
	public Task getTaskById(long id) throws Exception;
	
	/**
	 * Get specific task by title
	 * 
	 * @param title					the title of the task you need returned
	 * @return Task
	 */
	public Task getTaskByTitle(String title) throws Exception;
	
	/**
	 * Inserts a new task in the database
	 * 
	 * @param task					the object containing the information you want stored
	 * @return						returns the number of rows affected
	 */
	public int insertTask(Task task) throws Exception;
	
	/**
	 * Update a existing Task in database
	 * 
	 * @param task 					the object containing the updated information you want stored
	 * @return						returns the number of rows affected
	 */
	public int updateTask(Task task) throws Exception;

    /**
     * Delete an existing task from the database
     *
     * @param task 			the object containing the task which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    public int deleteTask(Task task) throws Exception;

}
