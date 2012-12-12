package db;

import models.Log;
import models.User;

import java.util.ArrayList;

/**
 * Created: 12-12-2012
 * @version: 0.1
 * Filename: IFDBLog.java
 * Description:
 * @changes
 */

public interface IFDBLog
{
    /**
     * Retrieve all log data from database
     *
     * @return ArrayList<Log>
     */
    public ArrayList<Log> getAllLogs() throws Exception;

    /**
     * Get a specific log entry by user
     *
     * @param user			        the user which the exception occurred to
     * @return ArrayList<Log>
     */
    public ArrayList<Log> getAllLogsByUser(User user) throws Exception;

    /**
     * Get a specific log entry by id
     *
     * @param value			        the id of the log entry you want returned
     * @return Log
     */
    public Log getLogById(int value) throws Exception;

    /**
     * Inserts a new log entry in the database
     *
     * @param logEntry		        the object containing the information you want stored
     * @return int			        returns the number of rows affected
     */
    public int insertLog(Log logEntry) throws Exception;
}
