package db;

import models.Client;

import java.util.ArrayList;

public interface IFDBClient
{
	
	/**
	 * Retrieve all clients from database
	 *
	 * @return ArrayList<Client>
	 */
	public ArrayList<Client> getAllClients() throws Exception;
	
	/**
	 * Get specific client by id
	 * 
	 * @param id					the id of the client you need returned
	 * @return Client
	 */
	public Client getClientById(int id) throws Exception;
	
	/**
	 * Get specific client by phone number
	 * 
	 * @param phoneNo				the phone number of the client you need returned
	 * @return Client
	 */
	public Client getClientByPhone(long phoneNo) throws Exception;
	
	/**
	 * Get specific client by name
	 * 
	 * @param name					the name of the client you need returned
	 * @return Client
	 */
	public Client getClientByName(String name) throws Exception;
	
	/**
	 * Inserts a new client in the database
	 * 
	 * @param client				the object containing the information you want stored
	 * @return						returns the number of rows affected
	 */
	public int insertClient(Client client) throws Exception;
	
	/**
	 * Update a existing client in database
	 * 
	 * @param client 				the object containing the updated information you want stored
	 * @return						returns the number of rows affected
	 */
	public int updateClient(Client client) throws Exception;

    /**
     * Delete an existing client from the database
     *
     * @param client 		the object containing the client which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    public int deleteClient(Client client) throws Exception;

}
