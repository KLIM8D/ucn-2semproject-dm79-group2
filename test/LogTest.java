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
    public void getLogById() throws Exception
    {
    	Log log = _logCtrl.getLogById(1);

        assertNotNull(log);
    }
    
    @Test
    public void insertLog() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        
        User user = _userCtrl.getUserById(1);
        
        String userFname = user.getFirstName();
        String userLname = user.getLastName();
        String userUname = user.getUserName();
        String saltValue = user.getSaltValue();
        String userPword = null;
        Date creaDate = user.getCreationDate();
        Date editDate = user.getEditedDate();
        String userDetails = userFname + ", " + userLname + ", " + userUname + ", " + saltValue + ", " + creaDate + ", " + editDate;
        
        
        
        
        Log log = new Log(null,null,null,null,null);
        // (User user, String userDetails, String exception, String exceptionLocation, Date createdDate)
        
        Task task = null; // get a task 
        
        int rowsAffected = _logCtrl.insertLog(null);

        assertEquals(1, rowsAffected);
    }
}
