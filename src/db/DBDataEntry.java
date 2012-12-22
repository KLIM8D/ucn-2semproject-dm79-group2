package db;

import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: DBDataEntry.java
 * Description:
 * @changes
 */

public class DBDataEntry implements IFDBDataEntry
{
    private DataAccess _da;
    public DBDataEntry()
    {
        _da = DataAccess.getInstance();
    }

    /**
     * Retrieve all DataEntries from database
     *
     * @return ArrayList<DataEntry>
     */
    @Override
    public ArrayList<DataEntry> getAllDataEntries() throws Exception
    {
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries");
        ResultSet dataEntries = _da.callCommandGetResultSet(query, con);

        while(dataEntries.next())
        {
            DataEntry dataEntry = buildDataEntry(dataEntries);
            returnList.add(dataEntry);
        }

        return returnList;
    }

    /**
     * Retrieve all DataEntries from database
     *
     * @param user                    the User which the DataEntries belongs to
     * @return ArrayList<DataEntry>
     */
    @Override
    public ArrayList<DataEntry> getAllDataEntriesByUser(User user) throws Exception
    {
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries WHERE userId = ?");
        query.setInt(1, user.getUserId());
        ResultSet dataEntries = _da.callCommandGetResultSet(query, con);

        while(dataEntries.next())
        {
            DataEntry dataEntry = buildDataEntry(dataEntries);
            returnList.add(dataEntry);
        }

        return returnList;
    }

    /**
     * Retrieve all DataEntries from database
     *
     * @param timeSheet               the TimeSheet which the DataEntries are associated to
     * @return ArrayList<DataEntry>
     */
    @Override
    public ArrayList<DataEntry> getAllDataEntriesByTimeSheet(TimeSheet timeSheet) throws Exception
    {
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries WHERE sheetId = ?");
        query.setInt(1, timeSheet.getSheetId());
        ResultSet dataEntries = _da.callCommandGetResultSet(query, con);

        while(dataEntries.next())
        {
            DataEntry dataEntry = buildDataEntry(dataEntries);
            returnList.add(dataEntry);
        }

        return returnList;
    }

    /**
     * Retrieve all DataEntries from database, between 2 dates
     *
     * @param timeSheet			        the TimeSheet which the DataEntries are associated to
     * @param startDate                 the startDate, where the filtering should start from
     * @param endDate                   the endDate, where the filtering should end
     * @return ArrayList<DataEntry>
     */
    @Override
    public ArrayList<DataEntry> getAllDataEntriesByTimeSheet(TimeSheet timeSheet, Date startDate, Date endDate) throws Exception
    {
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries WHERE sheetId = ? AND startDate BETWEEN ? AND ?");
        query.setInt(1, timeSheet.getSheetId());
        query.setString(2, _da.dateToSqlDate(startDate));
        query.setString(3, _da.dateToSqlDate(endDate));

        ResultSet dataEntries = _da.callCommandGetResultSet(query, con);

        while(dataEntries.next())
        {
            DataEntry dataEntry = buildDataEntry(dataEntries);
            returnList.add(dataEntry);
        }

        return returnList;
    }

    /**
     * Retrieve all DataEntries from database
     *
     * @param task                    the Task which the DataEntries are associated to
     * @return ArrayList<DataEntry>
     */
    @Override
    public ArrayList<DataEntry> getAllDataEntriesByTask(Task task) throws Exception
    {
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries WHERE taskId = ?");
        query.setInt(1, task.getTaskId());
        ResultSet dataEntries = _da.callCommandGetResultSet(query, con);

        while(dataEntries.next())
        {
            DataEntry dataEntry = buildDataEntry(dataEntries);
            returnList.add(dataEntry);
        }

        return returnList;
    }

    /**
     * Get a specific DataEntry by id
     *
     * @param value                   the id of the DataEntry you want returned
     * @return Log
     */
    @Override
    public DataEntry getDataEntryById(int value) throws Exception
    {
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM DataEntries WHERE entryId = ?");
        query.setInt(1, value);
        ResultSet entryResult = _da.callCommandGetRow(query, con);

        if(entryResult.next())
            return buildDataEntry(entryResult);

        return null;
    }

    /**
     * Inserts a new DataEntry in the database
     *
     * @param dataEntry               the object containing the information you want stored
     * @param sheetId                 the id of the TimeSheet which the DataEntry are associated to
     * @return int                    returns the number of rows affected
     */
    @Override
    public int insertDataEntry(DataEntry dataEntry, int sheetId) throws Exception
    {
        if(dataEntry == null || sheetId < 0)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO DataEntries (sheetId, taskId, userId, startDate, endDate, entryRemark, creationDate, editedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        query.setInt(1, sheetId);
        query.setInt(2, dataEntry.getTask().getTaskId());
        query.setInt(3, dataEntry.getUser().getUserId());
        query.setString(4, _da.dateToSqlDate(dataEntry.getStartDate()));
        query.setString(5, _da.dateToSqlDate(dataEntry.getEndDate()));
        query.setString(6, dataEntry.getEntryRemark());
        query.setString(7, _da.dateToSqlDate(dataEntry.getCreationDate()));
        query.setString(8, _da.dateToSqlDate(dataEntry.getEditedDate()));

        return _da.callCommand(query, con);
    }

    /**
     * Update a existing DataEntry in database
     *
     * @param dataEntry               the object containing the updated information you want stored
     * @return int                    returns the number of rows affected
     */
    @Override
    public int updateDataEntry(DataEntry dataEntry) throws Exception
    {
        if(dataEntry == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("UPDATE DataEntries SET taskId = ?, userId = ?, startDate = ?, endDate = ?, entryRemark = ?, editedDate = ? WHERE entryId = ?");
        query.setInt(1, dataEntry.getTask().getTaskId());
        query.setInt(2, dataEntry.getUser().getUserId());
        query.setString(3, _da.dateToSqlDate(dataEntry.getStartDate()));
        query.setString(4, _da.dateToSqlDate(dataEntry.getEndDate()));
        query.setString(5, dataEntry.getEntryRemark());
        query.setString(6, _da.dateToSqlDate(dataEntry.getEditedDate()));
        query.setInt(7, dataEntry.getEntryId());

        return _da.callCommand(query, con);
    }

    /**
     * Delete an existing DataEntry from the database
     *
     * @param dataEntry               the object containing the DataEntry, which should be deleted from the database
     * @return int                    returns the number of rows affected
     */
    @Override
    public int deleteDataEntry(DataEntry dataEntry) throws Exception
    {
        if(dataEntry == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM DataEntries WHERE entryId = ?");
        query.setInt(1, dataEntry.getEntryId());

        return _da.callCommand(query, con);
    }

    private DataEntry buildDataEntry(ResultSet row) throws Exception
    {
        if(row == null)
            return null;

        DBUser dbUser = new DBUser();
        DBTask dbTask = new DBTask();

        int entryId = row.getInt("entryId");
        Task task = dbTask.getTaskById(row.getInt("taskId"));
        User user = dbUser.getUserById(row.getInt("userId"));
        Date startDate = row.getDate("startDate");
        Date endDate = row.getDate("endDate");
        String entryRemark = row.getString("entryRemark");
        Date creationDate = row.getDate("creationDate");
        Date editedDate = row.getDate("editedDate");

        return new DataEntry(entryId, task, user, startDate, endDate, entryRemark, creationDate, editedDate);
    }
}
