package controllers;

import db.DBLog;
import models.Log;
import models.User;

import java.util.ArrayList;

public class LogCtrl
{

	public LogCtrl()
	{
		
	}
	
	public ArrayList<Log> getAllLogs() throws Exception
	{
		DBLog DBL = new DBLog();
        return DBL.getAllLogs();
	}
	
	public ArrayList<Log> getLogByUser(User user) throws Exception
	{
		DBLog DBL = new DBLog();
		return DBL.getAllLogsByUser(user);
	}
	
	public Log getLogById(int id) throws Exception
	{
		DBLog DBL = new DBLog();
		return DBL.getLogById(id);
	}
	
	public int insertLog(Log task) throws Exception
	{
		DBLog DBL = new DBLog();
		return DBL.insertLog(task);
	}
}