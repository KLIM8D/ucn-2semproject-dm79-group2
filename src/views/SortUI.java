package views;

import controllers.TimeSheetCtrl;
import db.DataAccess;
import views.shared.DateTimePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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
    private JPanel pnlSort;
    private DateTimePanel startDate;
    private DateTimePanel endDate;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new SortUI();

        return _frame;
    }

    private SortUI()
    {
        _timeSheetCtrl = new TimeSheetCtrl();

        _frame = new JFrame();
        _frame.setTitle("Sortering");
        _frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/sort_overview.png")));
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setBounds(0, 0, 300, 170);
        _frame.toFront();
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);
        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });

        pnlSort = new JPanel();
        pnlSort.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlSort.setLayout(null);
        pnlSort.setBounds(0, 0, 300, 400);
        _frame.setContentPane(pnlSort);

        JLabel lblSortDateHeader = new JLabel("Filtre mellem oprettet dato");
        lblSortDateHeader.setBounds(5, 5, 180, 15);
        lblSortDateHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        pnlSort.add(lblSortDateHeader);

        JLabel lblStarted = new JLabel("Start dato");
        lblStarted.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblStarted.setBounds(10, 25, 70, 14);
        pnlSort.add(lblStarted);

        JLabel lblEnded = new JLabel("Slut dato");
        lblEnded.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblEnded.setBounds(10, 55, 70, 14);
        pnlSort.add(lblEnded);

        startDate = new DateTimePanel();
        JPanel pnlStartDate = startDate.buildDateTimePanel(new Date());
        pnlStartDate.setBounds(60, 20, 240, 25);
        pnlSort.add(pnlStartDate);

        endDate = new DateTimePanel();
        JPanel pnlEndDate = endDate.buildDateTimePanel(new Date());
        pnlEndDate.setBounds(60, 50, 240, 25);
        pnlSort.add(pnlEndDate);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 85, 300, 1);
        pnlSort.add(separator);


        JButton btnUpdate = new JButton("Opdater");
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SystemUI.getInstance().setSortDates(startDate.getDateChooser().getDate(), endDate.getDateChooser().getDate());
                SystemUI.getInstance().sortData();
            }
        });
        btnUpdate.setBounds(5, 100, 125, 23);
        pnlSort.add(btnUpdate);

        JButton btnCancel = new JButton("Fjern filter");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SystemUI.getInstance().setSortDates(null, null);
                SystemUI.getInstance().sortData();
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(155, 100, 125, 23);
        pnlSort.add(btnCancel);
    }
}
