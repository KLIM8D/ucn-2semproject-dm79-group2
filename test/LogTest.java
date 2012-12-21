import controllers.LogCtrl;
import models.Client;
import models.Log;
import controllers.UserCtrl;
import models.User;
import controllers.TaskCtrl;
import models.Task;

import db.DataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created: 21-12-2012
 * @version: 0.1
 * Filename: LogTest.java
 * Description:
 * @changes
 */

public class LogTest
{
    private LogCtrl _logCtrl;
    private UserCtrl _userCtrl;
    public LogTest()
    {
    }

    @Before
    public void setUp()
    {
    	_logCtrl = new LogCtrl();
    	_userCtrl = new UserCtrl(); 
    }
    
    @Test
    public void getAllLogs() throws Exception
    {
        ArrayList<Log> logs = _logCtrl.getAllLogs();

        assertNotNull(logs);
    }
    
    @Test
    public void getLogByUser() throws Exception
    {
    	User user = _userCtrl.getUserById(1);
    	ArrayList<Log> userLogs = _logCtrl.getLogByUser(user);
        assertNotNull(userLogs);
    }
    
    @Test
    public void insertLog() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + "<br/>");
        sb.append("Java vendor: " + System.getProperty("java.vendor") + "<br/>");
        sb.append("Java version: " + System.getProperty("java.version") + "<br/>");
        sb.append("User home dir: " + System.getProperty("user.home") + "<br/>");
        User user = _userCtrl.getUserById(1);
        Log newLog = new Log(user, sb.toString(), new Exception("Test exception").getMessage(), getClass().getName(), cal.getTime());
        
        int rowsAffected = _logCtrl.insertLog(newLog);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void getLogById() throws Exception
    {
        DataAccess da = DataAccess.getInstance();
        int nextId = (int)da.getNextId("Logs");
        Log log = _logCtrl.getLogById(nextId - 1);

        assertNotNull(log);
    }
}
