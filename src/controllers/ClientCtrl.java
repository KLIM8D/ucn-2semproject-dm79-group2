package controllers;

import java.util.ArrayList;
import models.Client;
import models.City;
import db.DBClient;
import db.DBCity;

public class ClientCtrl
{

	public ClientCtrl()
	{
		
	}
	
	public ArrayList<Client> getAllClients() throws Exception
	{
		DBClient DBC = new DBClient();
        return DBC.getAllClients();
	}
	
	public Client getClientById(int id) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.getClientById(id);
	}
	
	public Client getClientByPhone(long phoneNo) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.getClientByPhone(phoneNo);
	}
	
	public Client getClientByName(String name) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.getClientByName(name);
	}
	
	public City getCityById(int id) throws Exception
	{
		DBCity DBC = new DBCity();
		return DBC.getCityById(id);
	}
	
	public int insertClient(Client client) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.insertClient(client);
	}
	
	public int updateClient(Client client) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.updateClient(client);
	}
	
	public int deleteClient(Client client) throws Exception
	{
		DBClient DBC = new DBClient();
		return DBC.deleteClient(client);
	}
}
