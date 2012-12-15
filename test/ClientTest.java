import controllers.ClientCtrl;
import db.DataAccess;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

import models.Client;
import org.junit.Before;
import org.junit.Test;

/**
 * Created: 15-12-2012
 * @version: 0.1
 * Filename: ClientTest.java
 * Description:
 * @changes
 */

public class ClientTest
{
    private ClientCtrl _clientCtrl;
    public ClientTest()
    {
    }

    @Before
    public void setUp()
    {
        _clientCtrl = new ClientCtrl();
    }

    @Test
    public void insertClient() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        Client newClient = new Client("Bente Hansen", "Blomstervej 219", _clientCtrl.getCityByZipCode(9000), 92031022,"bente@mail.dk", cal.getTime(), cal.getTime());
        int rowsAffected = _clientCtrl.insertClient(newClient);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void getClientById() throws Exception
    {
        Client client = _clientCtrl.getClientById(1);

        assertNotNull(client);
    }

    @Test
    public void getClientByPhoneNo() throws Exception
    {
        Client client = _clientCtrl.getClientByPhone(92031022);

        assertNotNull(client);
    }

    @Test
    public void getClientByName() throws Exception
    {
        Client client = _clientCtrl.getClientByName("Bente Hansen");

        assertNotNull(client);
    }

    @Test
    public void getAllClients() throws Exception
    {
        ArrayList<Client> clients = _clientCtrl.getAllClients();

        assertNotNull(clients);
    }

    @Test
    public void updateClient() throws Exception
    {
        Client client = _clientCtrl.getClientById(3);
        client.setName("Bente Nielsen");
        int rowsAffected = _clientCtrl.updateClient(client);

        assertEquals(1, rowsAffected);
    }

    /*@Test
    public void deleteClient() throws Exception
    {
        Client client = _clientCtrl.getClientById(2);
        int rowsAffected = _clientCtrl.deleteClient(client);

        assertEquals(1, rowsAffected);
    }*/

    @Test
    public void getNextId() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("Clients");
        assertTrue(id > 0);
    }
}
