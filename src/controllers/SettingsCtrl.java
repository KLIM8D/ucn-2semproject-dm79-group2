package controllers;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.DynaBean;
import utils.Helper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 23-12-2012
 * @version: 0.1
 * Filename: SettingsCtrl.java
 * Description:
 * @changes
 */

public class SettingsCtrl
{
    private HashMap<String, String> _properties;
    public SettingsCtrl()
    {
       _properties = new HashMap<String, String>();
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
        _properties.put(propertyName, value);
    }

    public void writeFile() throws Exception
    {
        String jsonTxt = Helper.readFile(System.getProperty("user.dir") + File.separator + "system" + File.separator + "settings.json");
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        System.gc();
        for (Map.Entry<String, String> entry : _properties.entrySet())
            jsonObject.put(entry.getKey(), entry.getValue());

        Helper.writeFile(System.getProperty("user.dir") + File.separator + "system" + File.separator + "settings.json", jsonObject.toString());
    }
}
