package models;

import java.util.ArrayList;

/**
 * Created: 19-12-2012
 * @version: 0.1
 * Filename: SearchResult.java
 * Description:
 * @changes
 */

public class SearchResult
{
    private ArrayList<TimeSheet> _timeSheetCollection;
    private ArrayList<Client> _clientCollection;
    private ArrayList<Task> _taskCollection;

    public ArrayList<TimeSheet> getTimeSheetCollection()
    { return _timeSheetCollection; }
    public void setTimeSheetCollection(ArrayList<TimeSheet> value)
    { _timeSheetCollection = value; }

    public ArrayList<Client> getClientCollection()
    { return _clientCollection; }
    public void setClientCollection(ArrayList<Client> value)
    { _clientCollection = value; }

    public ArrayList<Task> getTaskCollection()
    { return _taskCollection; }
    public void setTaskCollection(ArrayList<Task> value)
    { _taskCollection = value; }

    public SearchResult()
    {
        _timeSheetCollection = new ArrayList<TimeSheet>();
        _clientCollection = new ArrayList<Client>();
        _taskCollection = new ArrayList<Task>();
    }

    public void addTimeSheet(TimeSheet timeSheet)
    {
        _timeSheetCollection.add(timeSheet);
    }

    public void addClient(Client client)
    {
        _clientCollection.add(client);
    }

    public void addTask(Task task)
    {
        _taskCollection.add(task);
    }
}
