package models;

import java.util.Date;

/**
 * Created: 11-12-2012
 * Filename: DataEntry.java
 * Description:
 */

public class DataEntry
{

    private int _entryId;
    private Task _task;
    private User _user;
    private Date _startDate;
    private Date _endDate;
    private String _entryRemark;
    private Date _creationDate;
    private Date _editedDate;

    public int getEntryId(){ return _entryId; }

    public Task getTask()
    { return _task; }
    public void setTask(Task value)
    { _task = value; }

    public Date getStartDate()
    { return _startDate; }
    public void setStartDate(Date value)
    { _startDate = value; }

    public Date getEndDate()
    { return _endDate; }
    public void setEndDate(Date value)
    { _endDate = value; }

    public String getEntryRemark()
    { return _entryRemark; }
    public void setEntryRemark(String value)
    { _entryRemark = value; }

    public Date getCreationDate()
    { return _creationDate; }
    public void set_creationDate(Date value)
    { _creationDate = value; }

    public Date getEditedDate()
    { return _editedDate; }
    public void setEditedDate(Date value)
    { _editedDate = value; }

    public DataEntry(Task task, User user, Date startDate, Date endDate, String entryRemark, Date creationDate, Date editedDate)
    {
        _task = task;
        _user = user;
        _startDate = startDate;
        _endDate = endDate;
        _entryRemark = entryRemark;
        _creationDate = creationDate;
        _editedDate = editedDate;
    }

    public DataEntry(int entryId, Task task, User user, Date startDate, Date endDate, String entryRemark, Date creationDate, Date editedDate)
    {
        _entryId = entryId;
        _task = task;
        _user = user;
        _startDate = startDate;
        _endDate = endDate;
        _entryRemark = entryRemark;
        _creationDate = creationDate;
        _editedDate = editedDate;
    }
}
