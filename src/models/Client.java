package models;

import java.util.Date;

public class Client {

	int _clientId;
	String _name;
	String _address;
	City _city;
	long _phoneNo;
	String _eMail;
	Date _createdDate;
	Date _editedDate;
	
	public String getName() 
	{ return _name; }
	
	public void setName(String name) 
	{ _name = name; }
	
	public String getAddress() 
	{ return _address; }
	
	public void setAddress(String address) 
	{ _address = address; }
	
	public City getCity() 
	{ return _city; }
	
	public void setCity(City city) 
	{ _city = city; }
	
	public long getPhoneNo() 
	{ return _phoneNo; }
	
	public void setPhoneNo(long phoneNo) 
	{ _phoneNo = phoneNo; }
	
	public String getEmail() 
	{ return _eMail; }
	
	public void setEmail(String eMail)
	{ _eMail = eMail; }
	
	public Date getCreatedDate() 
	{ return _createdDate; }
	
	public void setCreatedDate(Date createdDate) 
	{ _createdDate = createdDate; }
	
	public Date getEditedDate() 
	{ return _editedDate; }
	
	public void setEditedDate(Date editedDate) 
	{ _editedDate = editedDate; }

	public Client(String name, String address, City city, long phoneNo, String eMail, Date createdDate, Date editedDate) 
	{
		//super();
		_name = name;
		_address = address;
		_city = city;
		_phoneNo = phoneNo;
		_eMail = eMail;
		_createdDate = createdDate;
		_editedDate = editedDate;
	}
	
	public Client(int clientId, String name, String address, City city, long phoneNo, String eMail, Date createdDate, Date editedDate) 
	{
		//super();
		_clientId = clientId;
		_name = name;
		_address = address;
		_city = city;
		_phoneNo = phoneNo;
		_eMail = eMail;
		_createdDate = createdDate;
		_editedDate = editedDate;
	}
	
}
