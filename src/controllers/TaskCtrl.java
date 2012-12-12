package controllers;

import java.util.ArrayList;
import models.Task;
import db.DBTask;

public class TaskCtrl
{

	public TaskCtrl()
	{
		
	}
	
	public ArrayList<Task> getAllTasks() throws Exception
	{
		DBTask DBC = new DBTask();
        return DBC.getAllTasks();
	}
	
	public Task getTaskById(int id) throws Exception
	{
		DBTask DBC = new DBTask();
		return DBC.getTaskById(id);
	}

	public Task getTaskByTitle(String title) throws Exception
	{
		DBTask DBC = new DBTask();
		return DBC.getTaskByTitle(title);
	}
	
	public int insertTask(Task task) throws Exception
	{
		DBTask DBC = new DBTask();
		return DBC.insertTask(task);
	}
	
	public int updateTask(Task task) throws Exception
	{
		DBTask DBC = new DBTask();
		return DBC.updateTask(task);
	}
	
	public int deleteTask(Task task) throws Exception
	{
		DBTask DBC = new DBTask();
		return DBC.deleteTask(task);
	}
}
