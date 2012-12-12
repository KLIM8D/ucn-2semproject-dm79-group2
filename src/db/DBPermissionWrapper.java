package db;

import models.TimeSheet;
import models.User;
import models.UserPermission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: DBPermissionWrapper.java
 * Description:
 * @changes
 */

public class DBPermissionWrapper implements IFDBPermissionWrapper
{
    private DataAccess _da;
    public DBPermissionWrapper()
    {
        _da = DataAccess.getInstance();
    }

    /**
     * Retrieve Users permission to access the TimeSheet
     *
     * @return boolean              returns true if the User are granted access to the TimeSheet
     */
    @Override
    public boolean getPermissionByUser(TimeSheet timeSheet, User user) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 1 AND permissionValue = ?");
        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());
        _da.setSqlCommandText(query);
        ResultSet userResult = _da.callCommandGetResultSet();

        return userResult.next();
    }

    /**
     * Retrieve User permission by the users role for the TimeSheet
     *
     * @return boolean              returns true if the User are granted access to the TimeSheet
     */
    @Override
    public boolean getPermissionByUserRole(TimeSheet timeSheet, UserPermission userPermission) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 2 AND permissionValue = ?");
        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());
        _da.setSqlCommandText(query);
        ResultSet userResult = _da.callCommandGetResultSet();

        return userResult.next();
    }

    /**
     * Inserts a new Permission in the database
     *
     * @param timeSheet the TimeSheet object which the user should grant access to
     * @param user      the object containing the user information
     * @return int                    returns the number of rows affected
     */
    @Override
    public int insertPermission(TimeSheet timeSheet, User user) throws Exception
    {
        if(timeSheet == null || user == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO PermissionWrapper (sheetId, permissionType, permissionValue) VALUES (?, 1, ?)");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Inserts a new Permission in the database
     *
     * @param timeSheet      the TimeSheet object which the user should grant access to
     * @param userPermission the object containing the userPermission(userRole) information
     * @return int                    returns the number of rows affected
     */
    @Override
    public int insertPermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception
    {
        if(timeSheet == null || userPermission == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO PermissionWrapper (sheetId, permissionType, permissionValue) VALUES (?, 2, ?)");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Remove a users permission for a TimeSheet
     *
     * @param timeSheet the TimeSheet object which the user should grant access to
     * @param user      the object containing the user information
     * @return int                     returns the number of rows affected
     */
    @Override
    public int deletePermission(TimeSheet timeSheet, User user) throws Exception
    {
        if(timeSheet == null || user == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 1 AND permissionValue = ?");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Remove all users permission for a TimeSheet
     *
     * @param timeSheet      the TimeSheet object which the user should grant access to
     * @param userPermission the object containing the userPermission(userRole) information
     * @return int                     returns the number of rows affected
     */
    @Override
    public int deletePermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception
    {
        if(timeSheet == null || userPermission == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 2 AND permissionValue = ?");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }
}
