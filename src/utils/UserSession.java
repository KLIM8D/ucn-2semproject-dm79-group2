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
    private static boolean _printToPdf;
    private static String _outputPath;

    public static User getLoggedInUser()
    { return _loggedInUser; }
    public static void setLoggedInUser(User value)
    { _loggedInUser = value; }

    public static boolean getPrintToPdf()
    { return _printToPdf; }
    public static void setPrintToPdf(boolean value)
    { _printToPdf = value; }

    public static String getOutputPath()
    { return _outputPath; }
    public static void setOutputPath(String value)
    { _outputPath = value; }
}
