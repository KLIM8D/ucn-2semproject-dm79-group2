/**
 * filename    : Execute.java
 * created     : Dec 13, 2012 (1:08:08 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

import controllers.SettingsCtrl;
import models.User;
import models.UserPermission;
import utils.UserSession;
import views.SystemUI;

import java.util.Date;

final class Execute
{
    public static void main(String[] args)
    {
    	try
        {
            SettingsCtrl settingsCtrl = new SettingsCtrl();
    		// swap with login, when appropriate
            User user = new User(1, new UserPermission(3, "User", new Date(), new Date()), "Test", "User", "test", "1289hjusbv7f123", "testpass", new Date(), new Date());
            UserSession.setLoggedInUser(user);
            UserSession.setExportToPdf(Boolean.parseBoolean(settingsCtrl.getProperty("exportToPdf")));
            UserSession.setOutputPath(settingsCtrl.getProperty("exportPath"));
    		SystemUI window = new views.SystemUI();
    		window.setVisible(true);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}