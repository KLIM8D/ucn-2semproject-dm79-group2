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
    private static boolean _exportToPdf;
    private static String _outputPath;

    public static User getLoggedInUser()
    { return _loggedInUser; }
    public static void setLoggedInUser(User value)
    { _loggedInUser = value; }

    public static boolean getExportToPdf()
    { return _exportToPdf; }
    public static void setExportToPdf(boolean value)
    { _exportToPdf = value; }

    public static String getOutputPath()
    { return _outputPath; }
    public static void setOutputPath(String value)
    { _outputPath = value; }
}
