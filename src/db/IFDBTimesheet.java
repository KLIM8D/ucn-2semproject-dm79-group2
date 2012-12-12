package db;

import models.TimeSheet;
import models.User;
import models.Client;
import java.util.ArrayList;
import java.util.Date;

public interface IFDBTimesheet {
	
	/**
	 * Retrieve all timesheets from the database
	 * 
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheets() throws Exception;
	
	
	/**
	 * Retrieve a specific timesheet by id
	 * 
	 * @param sheetId					the id of the timesheet you need returned			
	 * @return Timesheet
	 * @throws Exception
	 */
	public TimeSheet getTimesheetById(int sheetId) throws Exception;
	
	
	/**
	 * Retrieve all timesheets by specific user
	 * 
	 * @param user						the user who assined to the timesheet
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByUser(User user) throws Exception;
	
		
	/**
	 * Retrieve all timesheets by specific client
	 * 
	 * @param client					the user who assined to the timesheet
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByClient(Client client) throws Exception;
	
	
	/**
	 * Inserts a new timesheet into the database
	 * 
	 * @param timesheet				the object containing the information to be stored
	 * @return							returns the number of rows affected
	 * @throws Exception
	 */
	public int insertTimeSheet(TimeSheet timesheet) throws Exception;
	
	
	/**
	 * Updates a timesheet already existing in the database
	 * 
	 * @param timesheet				the object containing the updated information to be stored
	 * @return 						returns the number of rows affected
	 * @throws Exception
	 */
	public int updateTimeSheet(TimeSheet timesheet) throws Exception;
	
	
	/**
	 * Deletes a timesheet from the database
	 * 
	 * @param timesheet				the object containing the timesheet that should be deleted
	 * @return							returns the number of rows affected
	 * @throws Exception			
	 */
	public int deleteTimeSheet(TimeSheet timesheet) throws Exception;
	

	/**
	 * Retrieves all timesheets by user before a given date
	 * 
	 * @param user						the user whose timesheets are assigned to
	 * @param date						the date where all timesheets created before this date are returned
	 * @return	ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsBeforeDataByUser(User user, Date date) throws Exception;
	
		
	/**
	 * Retrieves all timesheets by user after a given date
	 * 
	 * @param user						the user whose timesheets are assigned to
	 * @param date						the date where all timesheets created after this date are returned
	 * @return	ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsAfterDateByUser(User user, Date date) throws Exception;
	

	/**
	 * Retrieves all timesheets by client before a given date
	 * 
	 * @param client					the client whose timesheets are assigned to
	 * @param date						the date where all timesheets created before this date are returned
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsBeforeDataByClient(Client client, Date date) throws Exception;
	
	
	/**
	 * Retrieves all timesheets by client after a given date
	 * 
	 * @param client					the client whose timesheets are assigned to
	 * @param date						the date where all timesheets created after this date are returned
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsAfterDataByClient(Client client, Date date) throws Exception;	
}