/**
 * filename    : SystemInformation.java
 * created     : Dec 13, 2012 (1:41:30 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package utils;

public class SystemInformation
{
	private static String _systemTitle = "Time-sag Styring";
	private static String _systemDescription = "UCN-DM79, Gruppe 2";
	private static String _systemBuild = "13122012";
	
	public static String systemInformation(int code)
	{
		switch(code)
		{
		case 01:
			return _systemTitle;
		case 02:
			return _systemDescription;
		case 03:
			return _systemBuild;
		}
		return null;
	}
}
