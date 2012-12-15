package db;

import models.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DBCity implements IFDBCity
{
	private DataAccess _da;
	public DBCity()
	{
		_da = DataAccess.getInstance();
	}

	/**
	 * Get all the cities from the database
	 * 
	 * @return ArrayList<City>
	 */
	@Override
	public ArrayList<City> getAllCities() throws Exception
    {
		ArrayList<City> returnList = new ArrayList<City>();
		
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Cities");
		_da.setSqlCommandText(query);
		ResultSet cities = _da.callCommandGetResultSet();
		
		while (cities.next()) 
		{
			City city = buildCity(cities);
			returnList.add(city);
		}

		return returnList;
	}


	/**
	 * Retrieve a specific city from cityId
	 */
	@Override
	public City getCityById(int cityId) throws Exception
    {
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Cities WHERE cityId = ?");
		query.setInt(1, cityId);
		_da.setSqlCommandText(query);
		ResultSet cityResult = _da.callCommandGetRow();
		cityResult.next();
		
		return buildCity(cityResult); 
	}

		
	/**
	 * Retrieve a specific city from zipCode
	 */
	@Override
	public City getCityByZipCode(int zipCode) throws Exception
    {
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Cities WHERE zipCode = ?");
		query.setInt(1, zipCode);
		_da.setSqlCommandText(query);
		ResultSet cityResult = _da.callCommandGetRow();
		cityResult.next();
		
		return buildCity(cityResult);
	}
	
	
	/**
	 * Retrieve a specific city from name
	 */
	@Override
	public City getCityByName(String cityName) throws Exception
    {
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Cities WHERE cityName = ?");
		query.setString(1, cityName);
		_da.setSqlCommandText(query);
		ResultSet cityResult = _da.callCommandGetRow();
		cityResult.next();
		
		return buildCity(cityResult);
	}

	
	/**
	 * Insert a new City to the database
	 */
	@Override
	public int insertCity(City city) throws Exception
    {
		if(city == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Cities (cityName, zipCode) VALUES (?, ?)");
		query.setString(1, city.getCityName());
		query.setInt(2, city.getZipCode());
		_da.setSqlCommandText(query);
		
		return _da.callCommand();
	}

	
	/**
	 * Update a city that already exists in the database
	 */
	@Override
	public int updateCity(City city) throws Exception
    {
		if (city == null)
			return 0;
		
		if (getCityById(city.getCityId()) == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE Cities SET cityName = ?, zipCode = ? WHERE cityId = ?");
        query.setString(1, city.getCityName());
        query.setInt(2, city.getZipCode());
		query.setInt(3, city.getCityId());
		_da.setSqlCommandText(query);
		
		return _da.callCommand();
	}

	
	/**
	 * Delete city that already exists in the database
	 */
	@Override
	public int deleteCity(City city) throws Exception
    {
		if (city == null)
			return 0;
		
		if (getCityById(city.getCityId()) == null)
			return 0;
		
		int rowsAffected = 0;
		PreparedStatement query = _da.getCon().prepareStatement("SELECT FROM Cities WHERE zipCode = ?");
		query.setInt(1, city.getZipCode());
		_da.setSqlCommandText(query);
		rowsAffected += _da.callCommand();
		
		return rowsAffected;
	}
	
	
	private City buildCity(ResultSet row) throws Exception
	{
		if (row == null)
			return null;
		
		int cityId = row.getInt("cityId");
		int zipCode = row.getInt("zipCode");
		String cityName = row.getString("cityName");
		
		return new City(cityId, zipCode , cityName);
	}
}