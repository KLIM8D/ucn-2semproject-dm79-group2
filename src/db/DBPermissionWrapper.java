package db;

import models.TimeSheet;
import models.User;
import models.UserPermission;

import java.sql.Connection;
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
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 1 AND permissionValue = ?");
        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());
        ResultSet userResult = _da.callCommandGetResultSet(query, con);

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
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 2 AND permissionValue = ?");
        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());
        ResultSet userResult = _da.callCommandGetResultSet(query, con);

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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO PermissionWrapper (sheetId, permissionType, permissionValue) VALUES (?, 1, ?)");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());

        return _da.callCommand(query, con);
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO PermissionWrapper (sheetId, permissionType, permissionValue) VALUES (?, 2, ?)");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());

        return _da.callCommand(query, con);
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 1 AND permissionValue = ?");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, user.getUserId());

        return _da.callCommand(query, con);
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

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM PermissionWrapper WHERE sheetId = ? AND permissionType = 2 AND permissionValue = ?");

        query.setInt(1, timeSheet.getSheetId());
        query.setInt(2, userPermission.getPermissionId());

        return _da.callCommand(query, con);
    }

    /**
     * Remove all permissions for a TimeSheet
     *
     * @param timeSheet      the TimeSheet object which the user should grant access to
     * @return int                     returns the number of rows affected
     */
    @Override
    public int deleteAllPermissions(TimeSheet timeSheet) throws Exception
    {
        if(timeSheet == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM PermissionWrapper WHERE sheetId = ?");
        query.setInt(1, timeSheet.getSheetId());

        return _da.callCommand(query, con);
    }
}
