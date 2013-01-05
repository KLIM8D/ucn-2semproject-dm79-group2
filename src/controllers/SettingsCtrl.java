package controllers;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.DynaBean;
import utils.Helper;

import java.io.File;

/**
 * Created: 23-12-2012
 * @version: 0.1
 * Filename: SettingsCtrl.java
 * Description:
 * @changes
 */

public class SettingsCtrl
{
    public SettingsCtrl()
    {
    }

    public String getProperty(String propertyName) throws Exception
    {
        String jsonTxt = Helper.readFile(System.getProperty("user.dir") + File.separator + "system" + File.separator + "settings.json");
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonTxt);

        DynaBean bean = (DynaBean) JSONSerializer.toJava( jsonObject );
        return bean.get(propertyName).toString();
    }

    public void setProperty(String propertyName, String value) throws Exception
    {
        String jsonTxt = Helper.readFile(System.getProperty("user.dir") + File.separator + "system" + File.separator + "settings.json");
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        jsonObject.put(propertyName, value);
        Helper.writeFile(System.getProperty("user.dir") + File.separator + "system" + File.separator + "settings.json", jsonObject.toString());
    }
}
