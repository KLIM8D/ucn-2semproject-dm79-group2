package views;

import controllers.SearchCtrl;
import models.SearchResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created: 20-12-2012
 * @version: 0.1
 * Filename: SearchUI.java
 * Description:
 * @changes
 */

public class SearchUI
{
    private SearchCtrl _searchCtrl;

    private static JFrame _frame;
    private static SearchUI _instance;
    private JPanel pnlSearch;

    public static JFrame createWindow(SearchResult result)
    {
        if(_instance == null)
            _instance = new SearchUI(result);

        return _frame;
    }

    private SearchUI(SearchResult result)
    {
        _searchCtrl = new SearchCtrl();

        _frame = new JFrame();
        _frame.setTitle("S\u00F8ge resultater");
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setBounds(0, 0, 600, 700);
        _frame.setAlwaysOnTop(true);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);
        pnlSearch = new JPanel();
        pnlSearch.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlSearch.setLayout(new BorderLayout(0, 0));
        pnlSearch.setBounds(0, 0, 600, 700);
        _frame.setContentPane(pnlSearch);

        JLabel lblTimeSheetsHeader = new JLabel("Fundne time-sager");
        lblTimeSheetsHeader.setBounds(5, 5, 399, 15);
        pnlSearch.add(lblTimeSheetsHeader);

        JLabel lblClientsHeader = new JLabel("Fundne klienter");
        lblClientsHeader.setBounds(5, 238, 399, 15);
        pnlSearch.add(lblClientsHeader);

        JLabel lblTasksHeader = new JLabel("Fundne opgaver");
        lblTasksHeader.setBounds(5, 671, 399, 15);
        pnlSearch.add(lblTasksHeader);


    }
}
