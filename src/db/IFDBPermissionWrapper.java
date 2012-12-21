package db;

import models.TimeSheet;
import models.User;
import models.UserPermission;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: IFDBPermissionWrapper.java
 * Description:
 * @changes
 */

public interface IFDBPermissionWrapper
{
    /**
     * Retrieve Users permission to access the TimeSheet
     *
     * @return boolean              returns true if the User are granted access to the TimeSheet
     */
    public boolean getPermissionByUser(TimeSheet timeSheet, User user) throws Exception;

    /**
     * Retrieve User permission by the users role for the TimeSheet
     *
     * @return boolean              returns true if the User are granted access to the TimeSheet
     */
    public boolean getPermissionByUserRole(TimeSheet timeSheet, UserPermission userPermission) throws Exception;

    /**
     * Inserts a new Permission in the database
     *
     * @param timeSheet		        the TimeSheet object which the user should grant access to
     * @param user		            the object containing the user information
     * @return int			        returns the number of rows affected
     */
    public int insertPermission(TimeSheet timeSheet, User user) throws Exception;

    /**
     * Inserts a new Permission in the database
     *
     * @param timeSheet		        the TimeSheet object which the user should grant access to
     * @param userPermission        the object containing the userPermission(userRole) information
     * @return int			        returns the number of rows affected
     */
    public int insertPermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception;

    /**
     * Remove a users permission for a TimeSheet
     *
     * @param timeSheet		        the TimeSheet object which the user should grant access to
     * @param user		            the object containing the user information
     * @return int 			        returns the number of rows affected
     */
    public int deletePermission(TimeSheet timeSheet, User user) throws Exception;

    /**
     * Remove all users permission for a TimeSheet
     *
     * @param timeSheet		        the TimeSheet object which the user should grant access to
     * @param userPermission		the object containing the userPermission(userRole) information
     * @return int 			        returns the number of rows affected
     */
    public int deletePermission(TimeSheet timeSheet, UserPermission userPermission) throws Exception;

    /**
     * Remove all permissions for a TimeSheet
     *
     * @param timeSheet      the TimeSheet object which the user should grant access to
     * @return int                     returns the number of rows affected
     */
    public int deleteAllPermissions(TimeSheet timeSheet) throws Exception;
}
