import controllers.SearchCtrl;
import models.SearchResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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
    	SearchResult searchResult = _searchCtrl.search(searchString);
        assertNotNull(searchResult);
    }
}
