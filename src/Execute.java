/**
 * filename    : Execute.java
 * created     : Dec 13, 2012 (1:08:08 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

import controllers.SettingsCtrl;
import utils.UserSession;
import views.LoginUI;

final class Execute
{
    public static void main(String[] args)
    {
    	try
        {
            SettingsCtrl settingsCtrl = new SettingsCtrl();
            UserSession.setExportToPdf(Boolean.parseBoolean(settingsCtrl.getProperty("exportToPdf")));
            UserSession.setOutputPath(settingsCtrl.getProperty("exportPath"));
            new LoginUI();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}