/**
 * filename    : ErrorHandling.java
 * created     : Dec 13, 2012 (10:42:37 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package utils;

public class ErrorHandling
{
	public static String errorHandling(int code)
	{
		switch(code)
		{
		case 01:
			return "En session fejl er hændt, programmet afsluttes.";
		}
		return "En ukendt system fejl er hændt";
	}
}