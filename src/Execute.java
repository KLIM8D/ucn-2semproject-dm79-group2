/**
 * filename    : Execute.java
 * created     : Dec 13, 2012 (1:08:08 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

import views.SystemUI;

final class Execute
{
    public static void main(String[] args)
    {
    	try
        {
    		// swap with login, when appropriate
    		SystemUI window = new views.SystemUI();
    		window.setVisible(true);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}