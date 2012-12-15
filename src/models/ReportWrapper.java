package models;

import java.util.ArrayList;
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
    private String _name;
    private String _address;
    private int _zipCode;
    private String _cityName;
    private long _phoneNo;
    private String _eMail;
    private String _firstName;
    private String _lastName;
    private String _caseId;
    private Date _creationDate;
    private Date _editedDate;
    private String _note;
    private List<SubReportWrapper> _dataEntries;

    public String getName()
    { return _name; }

    public String getAddress()
    { return _address; }

    public int getZipCode()
    { return _zipCode; }

    public long getPhoneNo()
    { return _phoneNo; }

    public String geteMail()
    { return _eMail; }

    public String getFirstName()
    { return _firstName; }

    public String getLastName()
    { return _lastName; }

    public String getCaseId()
    { return _caseId; }

    public Date getCreationDate()
    { return _creationDate; }

    public Date getEditedDate()
    { return _editedDate; }

    public String getNote()
    { return _note; }

    public String getCityName()
    { return _cityName; }

    public List<SubReportWrapper> getDataEntries()
    { return _dataEntries; }

    public ReportWrapper(TimeSheet timeSheet)
    {
        _name = timeSheet.getClient().getName();
        _address = timeSheet.getClient().getAddress();
        _zipCode = timeSheet.getClient().getCity().getZipCode();
        _cityName = timeSheet.getClient().getCity().getCityName();
        _phoneNo = timeSheet.getClient().getPhoneNo();
        _eMail = timeSheet.getClient().getEmail();
        _firstName = timeSheet.getUser().getFirstName();
        _lastName = timeSheet.getUser().getLastName();
        _caseId = timeSheet.getCaseId();
        _creationDate = timeSheet.getCreationDate();
        _editedDate = timeSheet.getEditedDate();
        _note = timeSheet.getNote();
        _dataEntries = new ArrayList<SubReportWrapper>();

        for(DataEntry dataEntry : timeSheet.getDataEntries())
            _dataEntries.add(new SubReportWrapper(dataEntry));
    }
}

