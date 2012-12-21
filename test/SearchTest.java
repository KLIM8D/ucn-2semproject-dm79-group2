import controllers.SearchCtrl;
import models.SearchResult;
import db.DataAccess;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created: 21-12-2012
 * @version: 0.1
 * Filename: ClientTest.java
 * Description:
 * @changes
 */

public class SearchTest
{
    private SearchCtrl _searchCtrl;
    public SearchTest()
    {
    }

    @Before
    public void setUp()
    {
    	_searchCtrl = new SearchCtrl();
    }


    @Test
    public void searchResult() throws Exception
    {
    	String searchString = "Mogens";
    	_searchCtrl.search(searchString);
    }
}
