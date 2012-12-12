package controllers;

import db.DBTimeSheet;
import models.Client;
import models.TimeSheet;
import models.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: TimeSheetCtrl.java
 * Description:
 * @changes
 */

public class TimeSheetCtrl
{
    private DBTimeSheet _dbTimeSheet;
    public TimeSheetCtrl()
    {
        _dbTimeSheet = new DBTimeSheet();
    }

    /**
     * Retrieve all TimeSheet from the database
     *
     * @return ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheets() throws Exception
    {
        return _dbTimeSheet.getAllTimeSheets();
    }


    /**
     * Retrieve a specific TimeSheet by id
     *
     * @param sheetId					the id of the TimeSheet you need returned
     * @return Timesheet
     * @throws Exception
     */
    public TimeSheet getTimesheetById(int sheetId) throws Exception
    {
        return _dbTimeSheet.getTimesheetById(sheetId);
    }


    /**
     * Retrieve all TimeSheets by specific user
     *
     * @param user						the user who assigned to the TimeSheets
     * @return ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsByUser(User user) throws Exception
    {
        return _dbTimeSheet.getAllTimeSheetsByUser(user);
    }


    /**
     * Retrieve all TimeSheets by specific client
     *
     * @param client					the user who assigned to the TimeSheets
     * @return ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsByClient(Client client) throws Exception
    {
        return _dbTimeSheet.getAllTimeSheetsByClient(client);
    }


    /**
     * Inserts a new TimeSheet into the database
     *
     * @param timeSheet				the object containing the information to be stored
     * @return							returns the number of rows affected
     * @throws Exception
     */
    public int insertTimeSheet(TimeSheet timeSheet) throws Exception
    {
        return _dbTimeSheet.insertTimeSheet(timeSheet);
    }


    /**
     * Updates a TimeSheet already existing in the database
     *
     * @param timeSheet				the object containing the updated information to be stored
     * @return 						returns the number of rows affected
     * @throws Exception
     */
    public int updateTimeSheet(TimeSheet timeSheet) throws Exception
    {
        return _dbTimeSheet.updateTimeSheet(timeSheet);
    }


    /**
     * Deletes a TimeSheet from the database
     *
     * @param timeSheet 				the object containing the TimeSheet that should be deleted
     * @return							returns the number of rows affected
     * @throws Exception
     */
    public int deleteTimeSheet(TimeSheet timeSheet) throws Exception
    {
        return _dbTimeSheet.deleteTimeSheet(timeSheet);
    }


    /**
     * Retrieves all TimeSheets by user before a given date
     *
     * @param user						the user whose TimeSheets are assigned to
     * @param date						the date where all TimeSheets created before this date are returned
     * @return	ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsBeforeDataByUser(User user, Date date) throws Exception
    {
        return _dbTimeSheet.getAllTimeSheetsBeforeDataByUser(user, date);
    }


    /**
     * Retrieves all TimeSheets by user after a given date
     *
     * @param user						the user whose TimeSheets are assigned to
     * @param date						the date where all TimeSheets created after this date are returned
     * @return	ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsAfterDateByUser(User user, Date date) throws Exception
    {
        return null;
    }


    /**
     * Retrieves all TimeSheets by client before a given date
     *
     * @param client					the client whose TimeSheets are assigned to
     * @param date						the date where all TimeSheets created before this date are returned
     * @return ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsBeforeDataByClient(Client client, Date date) throws Exception
    {
        return null;
    }
}
