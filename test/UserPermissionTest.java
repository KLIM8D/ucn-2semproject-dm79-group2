import controllers.UserPermissionCtrl;
import db.DataAccess;
import models.UserPermission;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created: 21-12-2012
 * @version: 0.1
 * Filename: UserPermissionTest.java
 * Description:
 * @changes
 */

public class UserPermissionTest
{
    private UserPermissionCtrl _userPermissionCtrl;
    public UserPermissionTest()
    {
    }

    @Before
    public void setUp()
    {
        _userPermissionCtrl = new UserPermissionCtrl();
    }

    @Test
    public void insertUserPermission() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        UserPermission userPermission = new UserPermission( 4, "Trainee", cal.getTime(), cal.getTime()); 
        int rowsAffected = _userPermissionCtrl.insertRole(userPermission);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void getUserPermissionById() throws Exception
    {
        DataAccess da = DataAccess.getInstance();
        long id = da.getNextId("UserPermissions");
    	UserPermission userPermission = _userPermissionCtrl.getRoleById((int) id - 1);

        assertNotNull(userPermission);
    }

    @Test
    public void getUserPermissionByTitle() throws Exception
    {
    	String title = "Trainee";
    	UserPermission userPermission = _userPermissionCtrl.getPermissionByTitle(title);
    	
        assertNotNull(userPermission);
    }

    @Test
    public void getAllUserPermissions() throws Exception
    {
        ArrayList<UserPermission> roles = _userPermissionCtrl.getAllRoles();

        assertNotNull(roles);
    }
    
    @Test
    public void updateUserPermission() throws Exception
    {
        DataAccess da = DataAccess.getInstance();
        long id = da.getNextId("UserPermissions");
        UserPermission userPermission = _userPermissionCtrl.getRoleById((int)id - 1);
        userPermission.setUserRole("Bogholder");
        int rowsAffected = _userPermissionCtrl.updateRole(userPermission);

        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void deleteUserPermission() throws Exception
    {
    	DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("UserPermissions");
        UserPermission userPermission = _userPermissionCtrl.getRoleById((int)id - 1);
        int rowsAffected = _userPermissionCtrl.deleteRole(userPermission);

        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void getNextId() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("UserPermissions");
        assertTrue(id > 0);
    }
}