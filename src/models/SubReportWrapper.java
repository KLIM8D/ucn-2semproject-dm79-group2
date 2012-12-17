package models;

import java.util.Date;

/**
 * Created: 15-12-2012
 * @version: 0.1
 * Filename: SubReportWrapper.java
 * Description:
 * @changes
 */

public class SubReportWrapper
{
    private String _taskName;
    private String _firstName;
    private String _lastName;
    private Date _startDate;
    private Date _endDate;
    private String _entryRemark;

    public String getTaskName()
    { return _taskName; }

    public String getFirstName()
    { return _firstName; }

    public String getLastName()
    { return _lastName; }

    public Date getStartDate()
    { return _startDate; }

    public Date getEndDate()
    { return _endDate; }

    public String getEntryRemark()
    { return _entryRemark; }

    public SubReportWrapper(DataEntry dataEntry)
    {
        _taskName = dataEntry.getTask().getTitle();
        _firstName = dataEntry.getUser().getFirstName();
        _lastName = dataEntry.getUser().getLastName();
        _startDate = dataEntry.getStartDate();
        _endDate = dataEntry.getEndDate();
        _entryRemark = (dataEntry.getEntryRemark() != null ? dataEntry.getEntryRemark() : "");
    }
}
