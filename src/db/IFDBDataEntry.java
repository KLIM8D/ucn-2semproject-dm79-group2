package db;

import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;

import java.util.ArrayList;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: IFDBDataEntry.java
 * Description:
 * @changes
 */

public interface IFDBDataEntry
{
    /**
     * Retrieve all DataEntries from database
     *
     * @return ArrayList<DataEntry>
     */
    public ArrayList<DataEntry> getAllDataEntries() throws Exception;

    /**
     * Retrieve all DataEntries from database
     *
     * @param user			        the User which the DataEntries belongs to
     * @return ArrayList<DataEntry>
     */
    public ArrayList<DataEntry> getAllDataEntriesByUser(User user) throws Exception;

    /**
     * Retrieve all DataEntries from database
     *
     * @param timeSheet			        the TimeSheet which the DataEntries are associated to
     * @return ArrayList<DataEntry>
     */
    public ArrayList<DataEntry> getAllDataEntriesByTimeSheet(TimeSheet timeSheet) throws Exception;

    /**
     * Retrieve all DataEntries from database
     *
     * @param task			        the Task which the DataEntries are associated to
     * @return ArrayList<DataEntry>
     */
    public ArrayList<DataEntry> getAllDataEntriesByTask(Task task) throws Exception;

    /**
     * Get a specific DataEntry by id
     *
     * @param value			        the id of the DataEntry you want returned
     * @return Log
     */
    public DataEntry getDataEntryById(int value) throws Exception;

    /**
     * Inserts a new DataEntry in the database
     *
     * @param dataEntry		        the object containing the information you want stored
     * @return int			        returns the number of rows affected
     */
    public int insertDataEntry(DataEntry dataEntry, int sheetId) throws Exception;

    /**
     * Update a existing DataEntry in database
     *
     * @param dataEntry				the object containing the updated information you want stored
     * @return int  				returns the number of rows affected
     */
    public int updateDataEntry(DataEntry dataEntry) throws Exception;

    /**
     * Delete an existing DataEntry from the database
     *
     * @param dataEntry		the object containing the DataEntry, which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    public int deleteDataEntry(DataEntry dataEntry) throws Exception;
}
