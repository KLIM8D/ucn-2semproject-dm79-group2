import controllers.ClientCtrl;
import controllers.TimeSheetCtrl;
import controllers.UserCtrl;
import db.DataAccess;
import models.Client;
import models.TimeSheet;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created: 19-12-2012
 * @version: 0.1
 * Filename: ThreadSafeTest.java
 * Description:
 * @changes
 */

public class ThreadSafeTest
{
    private TimeSheetCtrl _timeSheetCtrl;
    private ClientCtrl _clientCtrl;
    private UserCtrl _userCtrl;
    private DataAccess _da;

    public ThreadSafeTest()
    {
        _timeSheetCtrl = new TimeSheetCtrl();
        _clientCtrl = new ClientCtrl();
        _userCtrl = new UserCtrl();
        _da = DataAccess.getInstance();
    }

    public void testThreads()
    {
        for(int i = 0; i < 20; i++)
        {
            new Thread(new ThreadHandler()).start();
        }
    }

    public static void main(String[] args)
    {
        ThreadSafeTest threadSafeTest = new ThreadSafeTest();
        threadSafeTest.testThreads();
    }

    class ThreadHandler extends Thread
    {
        public void run()
        {
            Random ran = new Random();
            int choice = ran.nextInt(5);
            switch (choice)
            {
                case 0:
                    try
                    {
                        User user = _userCtrl.getUserById(1);
                        ArrayList<TimeSheet> timeSheets = _timeSheetCtrl.getAllTimeSheetsByUser(user);
                        if(timeSheets == null)
                            throw new Exception("TimeSheets is null");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try
                    {
                        ArrayList<TimeSheet> timeSheets = _timeSheetCtrl.getAllTimeSheets();
                        if(timeSheets == null)
                            throw new Exception("TimeSheets is null");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try
                    {
                         TimeSheet timeSheet = _timeSheetCtrl.getTimeSheetById(2);
                         if(timeSheet == null)
                            throw new Exception("TimeSheet is null");
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    try
                    {
                        TimeSheet timeSheet = _timeSheetCtrl.getTimeSheetByCaseId("4910");
                        if(timeSheet == null)
                            throw new Exception("TimeSheet is null");
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    try
                    {
                        int[] sheetIds = {2, 3, 4};
                        List<TimeSheet> timeSheets = _timeSheetCtrl.getAllTimeSheets(sheetIds);
                        if(timeSheets == null)
                            throw new Exception("TimeSheet is null");
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    try
                    {
                        Client client = _clientCtrl.getClientById(1);
                        List<TimeSheet> timeSheets = _timeSheetCtrl.getAllTimeSheetsByClient(client);
                        if(timeSheets == null)
                            throw new Exception("TimeSheet is null");
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    break;
            }

            System.out.println("Number of leased connections: " + _da.getTotalLeasedConnections());
            System.out.println(getName() + " is done. Random number was: " + choice);
        }
    }
}
