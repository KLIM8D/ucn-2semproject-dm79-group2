package controllers;

import db.DBDataEntry;
import db.DBPermissionWrapper;
import db.DBTimeSheet;
import models.Client;
import models.DataEntry;
import models.TimeSheet;
import models.User;
import models.UserPermission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private DBDataEntry _dbDataEntry;
    private DBPermissionWrapper _dbPermissionWrapper;
   
    public TimeSheetCtrl()
    {
        _dbTimeSheet = new DBTimeSheet();
        _dbDataEntry = new DBDataEntry();
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

    public List<TimeSheet> getAllTimeSheets(int[] sheetIds) throws Exception
    {
        List<TimeSheet> returnList = new ArrayList<TimeSheet>();

        for(int i = 0; i < sheetIds.length; i++)
            returnList.add(getTimeSheetById(sheetIds[i]));

        return returnList;
    }

    /**
     * Retrieve a specific TimeSheet by id
     *
     * @param sheetId					the id of the TimeSheet you need returned
     * @return TimeSheet
     * @throws Exception
     */
    public TimeSheet getTimeSheetById(int sheetId) throws Exception
    {
        return _dbTimeSheet.getTimeSheetById(sheetId);
    }
    
    /**
     * Retrieve a specific TimeSheet by caseId
     * 
     * @param caseId					the caseId of the TimeSheet you want returned
     * @return TimeSheet
     * @throws Exception
     */
    public TimeSheet getTimeSheetByCaseId(String caseId) throws Exception
    {
    	return _dbTimeSheet.getTimeSheetByCaseId(caseId);
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
     * @return						returns the number of rows affected
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
     * Retrieves all TimeSheet by user between startDate and endDate
     *
     * @param user						the user whose TimeSheets are assigned to
     * @param startDate					the first date of the date interval
     * @param endDate					the last date of the date interval
     * @return	ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsByUser(User user, Date startDate, Date endDate) throws Exception
    {
        return _dbTimeSheet.getAllTimeSheetsByUser(user, startDate, endDate);
    }

    /**
     * Retrieves all TimeSheets by client between startDate and endDate
     *
     * @param client					the client whose TimeSheets are assigned to
     * @param startDate					the first date of the date interval
     * @param endDate					the last date of the date interval
     * @return	ArrayList<TimeSheet>
     * @throws Exception
     */
    public ArrayList<TimeSheet> getAllTimeSheetsByClient(Client client, Date startDate, Date endDate) throws Exception
    {
        return _dbTimeSheet.getAllTimeSheetsByClient(client, startDate, endDate);
    }
    
    public int addDataEntry(TimeSheet timeSheet, DataEntry dataEntry) throws Exception
    {
    	return _dbDataEntry.insertDataEntry(dataEntry, timeSheet.getSheetId());
    }

    public int updateDataEntry(DataEntry dataEntry) throws Exception
    {
        return _dbDataEntry.updateDataEntry(dataEntry);
    }

    public int removeDataEntry(DataEntry dataEntry) throws Exception
    {
        return _dbDataEntry.deleteDataEntry(dataEntry);
    }
    
    public int deletePermission(TimeSheet timeSheet, User user) throws Exception
    {
    	return _dbPermissionWrapper.deletePermission(timeSheet, user);
    }
    
    public int deletePermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception
    {
    	return _dbPermissionWrapper.deletePermission(timeSheet, userPermission);
    }
    
    public int insertPermission(TimeSheet timeSheet, User user) throws Exception
    {
    	return _dbPermissionWrapper.insertPermission(timeSheet, user);
    }
    
    public int insertPermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception
    {
    	return _dbPermissionWrapper.insertPermission(timeSheet, userPermission);
    }
}
