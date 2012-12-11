package models;

/**
 * Created: 11-12-2012
 * Filename: City.java
 * Description:
 * @changes
 */

public class City
{
    private int _cityId;
    private int _zipCode;
    private String _cityName;

    public int getCityId()
    { return _cityId; }

    public int getZipCode()
    { return _zipCode; }
    public void setZipCode(int value)
    { _zipCode = value; }

    public String getCityName()
    { return _cityName; }
    public void setCityName(String value)
    { _cityName = value; }

    public City(int zipCode, String cityName)
    {
        _zipCode = zipCode;
        _cityName = cityName;
    }

    public City(int cityId, int zipCode, String cityName)
    {
        _cityId = cityId;
        _zipCode = zipCode;
        _cityName = cityName;
    }
}
