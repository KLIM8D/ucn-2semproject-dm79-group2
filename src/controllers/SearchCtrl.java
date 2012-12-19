package controllers;

import db.DBClient;
import db.DBTask;
import db.DBTimeSheet;
import models.SearchResult;

/**
 * Created: 19-12-2012
 * @version: 0.1
 * Filename: SearchCtrl.java
 * Description:
 * @changes
 */

public class SearchCtrl
{
    private DBTimeSheet _dbTimeSheet;
    private DBClient _dbClient;
    private DBTask _dbTask;

    public SearchCtrl()
    {
        _dbTimeSheet = new DBTimeSheet();
        _dbClient = new DBClient();
        _dbTask = new DBTask();
    }

    public SearchResult search(String searchString) throws Exception
    {
        if(searchString == null || searchString.length() < 0)
            return null;

        SearchResult results = new SearchResult();

        results.setTimeSheetCollection(_dbTimeSheet.searchForTimeSheets(searchString));
        results.setClientCollection(_dbClient.searchForClients(searchString));
        results.setTaskCollection(_dbTask.searchForTasks(searchString));

        return results;
    }

    public void testSearch(String searchString) throws Exception
    {
        SearchResult result = search(searchString);

        System.out.println("TimeSheet collection size: " + result.getTimeSheetCollection().size());
        System.out.println("Client collection size: " + result.getClientCollection().size());
        System.out.println("Task collection size: " + result.getTaskCollection().size());
    }
}
