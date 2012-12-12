package db;

import models.City;
import java.util.ArrayList;

public interface IFDBCity {
	
	/**
	 * Retrieve all Cities from the database
	 * 
	 * @return ArrayList<City>
	 * @throws Exception
	 */
	public ArrayList<City> getAllCities() throws Exception;
	
	/**
	 * Get a specific city by id
	 * 
	 * @param cityId				the id of the city you need returned
	 * @return City
	 * @throws Exception
	 */
	public City getCityById(int cityId) throws Exception;
	
	
	/**
	 * Get a specific city from zipCode
	 * 
	 * @param zipCode				the zipCode of the city you need returned			
	 * @return	City
	 * @throws Exception
	 */
	public City getCityByZipCode(int zipCode) throws Exception;
	
	/**
	 * Get a specific city by name
	 * 
	 * @param cityName				the name of the city you need returned
	 * @return City
	 * @throws Exception
	 */
	public City getCityByName(String cityName) throws Exception;
	
	/**
	 * Inserts a new city into the database
	 * 
	 * @param city				the object containing the information to be stored
	 * @return					returns the number of rows affected
	 * @throws Exception
	 */
	public int insertCity(City city) throws Exception;
	
	/**
	 * Update an existing city in the database
	 * 
	 * @param city				the object containing the updated information to be stored
	 * @return					returns the number of rows affected
	 * @throws Exception
	 */
	public int updateCity(City city) throws Exception;
	
	/**
	 * Delete an existing city in the database
	 * 
	 * @param city				the object containing the city that should be deleted
	 * @return					returns the number of rows affected
	 * @throws Exception
	 */
	public int deleteCity(City city) throws Exception;
}