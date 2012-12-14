package utils;

import models.DataEntry;
import models.TimeSheet;

import java.util.Date;
import java.util.List;

/**
 * Created: 14-12-2012
 * @version: 0.1
 * Filename: ReportWrapper.java
 * Description:
 * @changes
 */

public class ReportWrapper
{
    private String name;
    private String address;
    private int zipCode;
    private String cityName;
    private long phoneNo;
    private String eMail;
    private String firstName;
    private String lastName;
    private String caseId;
    private Date creationDate;
    private Date editedDate;
    private String note;
    private List<DataEntry> dataEntries;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public long getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String geteMail()
    {
        return eMail;
    }

    public void seteMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCaseId()
    {
        return caseId;
    }

    public void setCaseId(String caseId)
    {
        this.caseId = caseId;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getEditedDate()
    {
        return editedDate;
    }

    public void setEditedDate(Date editedDate)
    {
        this.editedDate = editedDate;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getCityName()
    { return cityName; }
    public void setCityName(String value)
    { cityName = value; }

    public List<DataEntry> getDataEntries()
    { return dataEntries; }

    public ReportWrapper(TimeSheet timeSheet)
    {
        this.name = timeSheet.getClient().getName();
        this.address = timeSheet.getClient().getAddress();
        this.zipCode = timeSheet.getClient().getCity().getZipCode();
        this.cityName = timeSheet.getClient().getCity().getCityName();
        this.phoneNo = timeSheet.getClient().getPhoneNo();
        this.eMail = timeSheet.getClient().getEmail();
        this.firstName = timeSheet.getUser().getFirstName();
        this.lastName = timeSheet.getUser().getLastName();
        this.caseId = timeSheet.getCaseId();
        this.creationDate = timeSheet.getCreationDate();
        this.editedDate = timeSheet.getEditedDate();
        this.note = timeSheet.getNote();
        this.dataEntries = timeSheet.getDataEntries();
    }
}

