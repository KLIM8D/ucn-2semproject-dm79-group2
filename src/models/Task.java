package models;

/**
 * Created: 11-12-2012
 * Filename: Task.java
 * Description:
 */

public class Task
{
    private int _taskId;
    private String _title;
    private String _description;

    public int getTaskId()
    { return _taskId; }

    public String getTitle()
    { return _title; }
    public void setTitle(String value)
    { _title = value; }

    public String getDescription()
    { return _description; }
    public void setDescription(String value)
    { _description = value; }


    public Task(String title, String description)
    {
        _title = title;
        _description = description;
    }

    public Task(int taskId, String title, String description)
    {
        _taskId = taskId;
        _title = title;
        _description = description;
    }
}
