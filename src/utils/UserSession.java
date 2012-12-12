package utils;

import models.User;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: UserSession.java
 * Description:
 * @changes
 */

public class UserSession
{
    private static User _loggedInUser;

    public static User getLoggedInUser()
    { return _loggedInUser; }
    public static void setLoggedInUser(User value)
    { _loggedInUser = value; }
}
