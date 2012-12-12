package models;

import java.util.Date;

public class Client 
{

	int _clientId;
	String _name;
	String _address;
	City _city;
	long _phoneNo;
	String _eMail;
	Date _creationDate;
	Date _editedDate;
	
	public int getClientId()
	{ return _clientId; }
	
	public String getName() 
	{ return _name; }
	public void setName(String value) 
	{ _name = value; }
	
	public String getAddress() 
	{ return _address; }
	public void setAddress(String value) 
	{ _address = value; }
	
	public City getCity() 
	{ return _city; }
	public void setCity(City value) 
	{ _city = value; }
	
	public long getPhoneNo() 
	{ return _phoneNo; }
	public void setPhoneNo(long value) 
	{ _phoneNo = value; }
	
	public String getEmail() 
	{ return _eMail; }
	public void setEmail(String value)
	{ _eMail = value; }
	
	public Date getCreationDate()
	{ return _creationDate; }
	public void setCreationDate(Date value)
	{ _creationDate = value; }
	
	public Date getEditedDate() 
	{ return _editedDate; }
	public void setEditedDate(Date value) 
	{ _editedDate = value; }

	public Client(String name, String address, City city, long phoneNo, String eMail, Date createdDate, Date editedDate) 
	{
		_name = name;
		_address = address;
		_city = city;
		_phoneNo = phoneNo;
		_eMail = eMail;
		_creationDate = createdDate;
		_editedDate = editedDate;
	}
	
	public Client(int clientId, String name, String address, City city, long phoneNo, String eMail, Date createdDate, Date editedDate) 
	{
		_clientId = clientId;
		_name = name;
		_address = address;
		_city = city;
		_phoneNo = phoneNo;
		_eMail = eMail;
		_creationDate = createdDate;
		_editedDate = editedDate;
	}
	
}
