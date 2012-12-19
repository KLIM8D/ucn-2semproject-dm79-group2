package db;

import models.City;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBClient implements IFDBClient
{
    private DataAccess _da;
	public DBClient()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all clients information from database
	 *
	 * @return ArrayList<Client>
	 */
    @Override
	public ArrayList<Client> getAllClients() throws Exception
	{
		ArrayList<Client> returnList = new ArrayList<Client>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Clients");
        ResultSet clients = _da.callCommandGetResultSet(query, con);

        while(clients.next())
        {
            Client client = buildClient(clients);
            returnList.add(client);
        }

		return returnList;
	}
	
	/**
	 *  Retrieve specific client record by id
	 *  
	 *  @param id					the id of the record you wish to return
	 *  @return Client
	 */
    @Override
	public Client getClientById(int id) throws Exception
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Clients WHERE clientId = ?");
        query.setInt(1, id);
        ResultSet clientResult = _da.callCommandGetRow(query, con);
        if(clientResult.next())
            return buildClient(clientResult);

        return null;
	}
    
	/**
	 * Get specific client by phone number
	 * 
	 * @param phoneNo				the phone number of the client you need returned
	 * @return Client
	 */
	@Override
	public Client getClientByPhone(long phoneNo) throws Exception 
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Clients WHERE phoneNo = ?");
        query.setLong(1, phoneNo);
        ResultSet clientResult = _da.callCommandGetRow(query, con);
        if(clientResult.next())
            return buildClient(clientResult);

        return null;
	}
	
	/**
	 * Retrieve specific client by name
	 * 
	 * @param name					the name of the record you wish to return
	 * @return Client
	 */
    @Override
	public Client getClientByName(String name) throws Exception
	{
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Clients WHERE name = ?");
        query.setString(1, name);
        ResultSet clientResult = _da.callCommandGetRow(query, con);
        if(clientResult.next())
            return buildClient(clientResult);

        return null;
	}

    /**
     * Using a wildcard search to, retrieve all Clients by the searchString.
     *
     * @param searchString				what you want to search for
     * @return ArrayList<Client>
     * @throws Exception
     */
    @Override
    public ArrayList<Client> searchForClients(String searchString) throws Exception
    {
        ArrayList<Client> returnList = new ArrayList<Client>();

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Clients WHERE name LIKE ? OR phoneNo LIKE ?");
        query.setString(1, "%" + searchString + "%");
        query.setString(2, "%" + searchString + "%");

        ResultSet clients = _da.callCommandGetResultSet(query, con);

        while (clients.next())
        {
            Client client = buildClient(clients);
            returnList.add(client);
        }

        return returnList;
    }

    /**
     * Inserts a new client in the database
     *
     * @param client				the object containing the information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int insertClient(Client client) throws Exception
	{
        if(client == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("INSERT INTO Clients (name, address, cityId, phoneNo, eMail, creationDate, editedDate) VALUES (?, ?, ?, ?, ?, ?, ?)");

        query.setString(1, client.getName());
        query.setString(2, client.getAddress());
        query.setInt(3, client.getCity().getCityId());
        query.setLong(4, client.getPhoneNo());
        query.setString(5, client.getEmail());
        query.setString(6, _da.dateToSqlDate(client.getCreationDate()));
        query.setString(7, _da.dateToSqlDate(client.getEditedDate()));

        return _da.callCommand(query, con);
	}

    /**
     * Update a existing client in database
     *
     * @param client 				the object containing the updated information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int updateClient(Client client) throws Exception
	{
		if(client == null)
            return 0;

        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("UPDATE Clients SET cityId = ?, name = ?, address = ?, phoneNo = ?, eMail = ?, editedDate = ? WHERE clientId = ?");
        query.setInt(1, client.getCity().getCityId());
        query.setString(2, client.getName());
        query.setString(3, client.getAddress());
        query.setLong(4, client.getPhoneNo());
        query.setString(5, client.getEmail());
        query.setString(6, _da.dateToSqlDate(client.getEditedDate()));
        query.setInt(7, client.getClientId());

        return _da.callCommand(query, con);
	}

    /**
     * Delete an existing client from the database
     *
     * @param client 		the object containing the client which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    @Override
    public int deleteClient(Client client) throws Exception
    {
        if(client == null)
            return 0;
        
        if(getClientById(client.getClientId()) == null)
            return 0;

        int rowsAffected = 0;
        Connection con = _da.getCon();
        PreparedStatement query = con.prepareStatement("DELETE FROM Clients WHERE clientId = ?");
        query.setLong(1, client.getClientId());
        rowsAffected += _da.callCommand(query, con);

        return rowsAffected;
    }

	private Client buildClient(ResultSet row) throws Exception
	{
		if(row == null)
			return null;

        DBCity dbc = new DBCity();

        int clientId = row.getInt("clientId");
        City city = dbc.getCityById(row.getInt("cityId"));
        String name = row.getString("name");
        String address = row.getString("address");
        long phoneNo = row.getLong("phoneNo");
        String eMail = row.getString("eMail");
        Date creationDate = row.getDate("creationDate");
        Date editedDate = row.getDate("editedDate");

        return new Client(clientId, name, address, city, phoneNo, eMail, creationDate, editedDate);
	}
}