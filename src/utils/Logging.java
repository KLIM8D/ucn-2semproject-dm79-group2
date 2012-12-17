package utils;

import db.DBLog;
import models.Log;
import models.User;

import java.util.Calendar;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: Logging.java
 * Description:
 * @changes
 */

public class Logging
{
    public Logging()
    {
    }

    public static String handleException(Exception ex, int returnMessage)
    {
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + "<br/>");
        sb.append("Java vendor: " + System.getProperty("java.vendor") + "<br/>");
        sb.append("Java version: " + System.getProperty("java.version") + "<br/>");
        sb.append("User home dir: " + System.getProperty("user.home") + "<br/>");
        User user = UserSession.getLoggedInUser();
        Log newLog = new Log(user, sb.toString(), ex.getStackTrace().toString(), ex.getClass().getName(), cal.getTime());

        try
        {
            DBLog dbLog = new DBLog();
            dbLog.insertLog(newLog);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            System.out.println("System tried to log this exception:");
            ex.printStackTrace();
        }

        return messages(returnMessage);
    }

    private static String messages(int messageIndex)
    {
        switch (messageIndex)
        {
            case 0:
                return "Kunne ikke hente den n\u00F8dvendige data. Pr\u00F8v igen senere eller kontakt support";
            case 1:
                return "Det var pt. ikke muligt at oprette et produkt. Pr\u00F8v igen senere eller kontakt support";
            case 2:
                return "Det var pt. ikke muligt at opdatere produktet. Pr\u00F8v igen senere eller kontakt support";
            case 3:
                return "Det var pt. ikke muligt at oprette en produkt kategori. Pr\u00F8v igen senere eller kontakt support";
            case 4:
                return "Det var pt. ikke muligt at opdatere produkt kategorien. Pr\u00F8v igen senere eller kontakt support";
            case 5:
            	return "En ukendt system fejl er hændt, programmet afsluttes.";
            default:
                return "Der skete en fejl i udf\u00F8rselen af denne handling. Kontakt support";
        }
    }
}
