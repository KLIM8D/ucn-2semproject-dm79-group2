package db;

import models.Client;
import models.TimeSheet;
import models.User;

import java.util.ArrayList;
import java.util.Date;

public interface IFDBTimeSheet
{
	
	/**
	 * Retrieve all TimeSheets from the database
	 * 
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheets() throws Exception;
	
	
	/**
	 * Retrieve a specific TimeSheet by id
	 * 
	 * @param sheetId					the id of the TimeSheet you need returned
	 * @return TimeSheet
	 * @throws Exception
	 */
	public TimeSheet getTimeSheetById(int sheetId) throws Exception;

    /**
     * Retrieve a specific TimeSheet by id, between 2 dates
     *
     * @param sheetId					the id of the TimeSheet you need returned
     * @param startDate                 the startDate, where the filtering should start from
     * @param endDate                   the endDate, where the filtering should end
     * @return TimeSheet
     * @throws Exception
     */
    public TimeSheet getTimeSheetById(int sheetId, Date startDate, Date endDate) throws Exception;
	
	/**
	 * Retrieve a specific TimeSheet by caseId
	 * 
	 * @param caseId					the caseId of the TimeSheet you want returned
	 * @return
	 * @throws Exception
	 */
	public TimeSheet getTimeSheetByCaseId(String caseId) throws Exception;

    /**
     * Retrieve a specific TimeSheet by caseId, between 2 dates
     *
     * @param caseId					the caseId of the TimeSheet you want returned
     * @param startDate                 the startDate, where the filtering should start from
     * @param endDate                   the endDate, where the filtering should end
     * @return TimeSheet
     * @throws Exception
     */
    public TimeSheet getTimeSheetByCaseId(String caseId, Date startDate, Date endDate) throws Exception;
	
	/**
	 * Retrieve all TimeSheets by specific user
	 * 
	 * @param user						the user who assigned to the TimeSheet
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByUser(User user) throws Exception;
		
	/**
	 * Retrieve all TimeSheets by specific client
	 * 
	 * @param client					the user who assigned to the TimeSheet
	 * @return ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByClient(Client client) throws Exception;
	
	
	/**
	 * Inserts a new TimeSheet into the database
	 * 
	 * @param timeSheet				    the object containing the information to be stored
	 * @return							returns the number of rows affected
	 * @throws Exception
	 */
	public int insertTimeSheet(TimeSheet timeSheet) throws Exception;
	
	
	/**
	 * Updates a TimeSheet already existing in the database
	 * 
	 * @param timeSheet				the object containing the updated information to be stored
	 * @return 						returns the number of rows affected
	 * @throws Exception
	 */
	public int updateTimeSheet(TimeSheet timeSheet) throws Exception;
	
	
	/**
	 * Deletes a TimeSheet from the database
	 * 
	 * @param timeSheet				    the object containing the TimeSheet that should be deleted
	 * @return							returns the number of rows affected
	 * @throws Exception			
	 */
	public int deleteTimeSheet(TimeSheet timeSheet) throws Exception;
	

	/**
	 * Retrieves all TimeSheets by user between startDate and endDate
	 * 
	 * @param user						the user whose TimeSheets are assigned to
	 * @param startDate					the first date of the date interval
	 * @param endDate					the last date of the date interval
	 * @return	ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByUser(User user, Date startDate, Date endDate) throws Exception;


	/**
	 * Retrieves all TimeSheets by client between startDate and endDate
	 * 
	 * @param client					the client whose TimeSheets are assigned to
	 * @param startDate					the first date of the date interval
	 * @param endDate					the last date of the date interval
	 * @return	ArrayList<TimeSheet>
	 * @throws Exception
	 */
	public ArrayList<TimeSheet> getAllTimeSheetsByClient(Client client, Date startDate, Date endDate) throws Exception;

    /**
     * Using a wildcard search to, retrieve all TimeSheets by the searchString.
     *
     * @param searchString				what you want to search for
     * @return ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> searchForTimeSheets(String searchString) throws Exception;
}