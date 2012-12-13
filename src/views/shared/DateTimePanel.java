package views.shared;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created: 13-12-2012
 * @version: 0.1
 * Filename: DateTimePanel.java
 * Description:
 * @changes
 */

public class DateTimePanel
{
    public DateTimePanel()
    {
    }

    public static JPanel buildDateTimePanel(Date value)
    {
        JPanel datePanel = new JPanel();

        JDateChooser dateChooser = new JDateChooser();
        if (value != null)
            dateChooser.setDate(value);

        for (Component comp : dateChooser.getComponents())
        {
            if (comp instanceof JTextField)
            {
                ((JTextField) comp).setColumns(50);
                ((JTextField) comp).setEditable(false);
            }
        }

        datePanel.add(dateChooser);

        SpinnerModel model = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(model);
        JComponent editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(editor);

        if(value != null)
            timeSpinner.setValue(value);

        datePanel.add(timeSpinner);

        return datePanel;
    }
}
