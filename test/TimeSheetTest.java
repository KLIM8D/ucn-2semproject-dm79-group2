import db.DataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

import models.TimeSheet;
import models.Client;
import models.User;

import controllers.TimeSheetCtrl;
import controllers.ClientCtrl;
import controllers.UserCtrl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created: 15-12-2012
 * @version: 0.1
 * Filename: ClientTest.java
 * Description:
 * @changes
 */

public class TimeSheetTest
{
    private TimeSheetCtrl _timeSheetCrtl;
    private UserCtrl _userCtrl;
    private ClientCtrl _clientCtrl;
    
    public TimeSheetTest()
    {
    }

    @Before
    public void setUp()
    {
    	_timeSheetCrtl = new TimeSheetCtrl();
    	_userCtrl = new UserCtrl();
    	_clientCtrl = new ClientCtrl();
    }

    @Test
    public void insertTimeSheet() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        User newUser = _userCtrl.getUserById(1);
        Client newClient = _clientCtrl.getClientById(1);
                                 
        TimeSheet newTimeSheet = new TimeSheet("L-445521", newUser, newClient ,"Der skal flere noter på", cal.getTime(), cal.getTime());
        int rowsAffected = _timeSheetCrtl.insertTimeSheet(newTimeSheet);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void getTimeSheetBySheetId() throws Exception
    {
        TimeSheet timeSheet = _timeSheetCrtl.getTimeSheetById(2);

        assertNotNull(timeSheet);
    }
    
    @Test
    public void getTimeSheetByCaseId() throws Exception
    {
        TimeSheet timeSheet = _timeSheetCrtl.getTimeSheetByCaseId("4422");

        assertNotNull(timeSheet);
    }
    
    @Test
    public void getTimeSheetsByUser() throws Exception
    {
    	User user = _userCtrl.getUserById(1);
    	ArrayList<TimeSheet> timeSheets;
    	timeSheets = _timeSheetCrtl.getAllTimeSheetsByUser(user);
    }
    
    @Test
    public void getTimeSheetsByClient() throws Exception
    {
    	Client client = _clientCtrl.getClientById(1);
    	ArrayList<TimeSheet> timeSheets;
    	timeSheets = _timeSheetCrtl.getAllTimeSheetsByClient(client);
    }
       
    @Test
    public void deleteTimeSheet() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("TimeSheets");
        TimeSheet timeSheet = _timeSheetCrtl.getTimeSheetByCaseId("L-445521");
        int rowsAffected = _timeSheetCrtl.deleteTimeSheet(timeSheet);

        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void updateTimeSheet() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("TimeSheets");
        TimeSheet timeSheet = _timeSheetCrtl.getTimeSheetByCaseId("L-445521");
        //client.setName("");
        int rowsAffected = _timeSheetCrtl.updateTimeSheet(timeSheet);

        assertEquals(1, rowsAffected);
    }
}
