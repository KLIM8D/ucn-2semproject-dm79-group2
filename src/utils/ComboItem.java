package utils;

/**
 * Created: 19-12-2012
 * @version: 0.1
 * Filename: ComboItem.java
 * Description:
 * @changes
 */

public class ComboItem
{
    private String _value;
    private String _displayText;

    public String getValue()
    { return _value; }
    public void setValue(String value)
    { _value = value; }

    public String getDisplayText()
    { return _displayText; }
    public void setDisplayText(String value)
    { _displayText = value; }

    public ComboItem(String value, String displayText)
    {
        _value = value;
        _displayText = displayText;
    }
}
