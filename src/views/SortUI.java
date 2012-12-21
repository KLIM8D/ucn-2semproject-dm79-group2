package views;

import controllers.ClientCtrl;
import controllers.SearchCtrl;
import controllers.TaskCtrl;
import controllers.TimeSheetCtrl;
import models.SearchResult;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created: 21-12-2012
 * @version: 0.1
 * Filename: SortUI.java
 * Description:
 * @changes
 */

public class SortUI
{
    private TimeSheetCtrl _timeSheetCtrl;

    private static JFrame _frame;
    private static SortUI _instance;
    private JPanel pnlSearch;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new SortUI();

        return _frame;
    }

    private SortUI()
    {
    }
}
