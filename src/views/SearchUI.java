package views;

import controllers.ClientCtrl;
import controllers.SearchCtrl;
import controllers.TaskCtrl;
import controllers.TimeSheetCtrl;
import models.*;
import utils.ButtonColumn;
import utils.Helper;
import utils.Logging;
import views.client.EditClientUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private TimeSheetCtrl _timeSheetCtrl;
    private ClientCtrl _clientCtrl;
    private TaskCtrl _taskCtrl;

    private static JFrame _frame;
    private static SearchUI _instance;
    private JPanel pnlSearch;
    private SearchResult _result;

    // TimeSheet table
    private DefaultTableModel sheetModel;
    private JTable sheetTable;
    private String[] sheetColumn;

    // Client table
    private DefaultTableModel clientModel;
    private JTable clientTable;
    private String[] clientColumn;

    // Task table
    private DefaultTableModel taskModel;
    private JTable taskTable;
    private String[] taskColumn;

    public static JFrame createWindow(SearchResult result)
    {
        if(_instance == null)
            _instance = new SearchUI(result);

        return _frame;
    }

    private SearchUI(SearchResult result)
    {
        _result = result;
        _searchCtrl = new SearchCtrl();
        _clientCtrl = new ClientCtrl();
        _taskCtrl = new TaskCtrl();

        _frame = new JFrame();
        _frame.setTitle("S\u00F8ge resultater");
        _frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/icons/48x48/search_overview.png")));
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setBounds(0, 0, 600, 700);
        Helper.centerOnScreen(_frame);
        _frame.toFront();
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        pnlSearch = new JPanel();
        pnlSearch.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(0, 0, 600, 700);
        _frame.setContentPane(pnlSearch);

        JLabel lblTimeSheetsHeader = new JLabel("Fundne time-sager");
        lblTimeSheetsHeader.setBounds(5, 5, 180, 15);
        lblTimeSheetsHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        pnlSearch.add(lblTimeSheetsHeader);

        JLabel lblClientsHeader = new JLabel("Fundne klienter");
        lblClientsHeader.setBounds(5, 238, 120, 15);
        lblClientsHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        pnlSearch.add(lblClientsHeader);

        JLabel lblTasksHeader = new JLabel("Fundne opgaver");
        lblTasksHeader.setBounds(5, 471, 120, 15);
        lblTasksHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        pnlSearch.add(lblTasksHeader);

        //SheetTable start
        sheetColumn = new String[]{"P" + "\u00e5" + "begyndt", "Afsluttet", "Opgave", "Registrator", "Bem" + "\u00e6" + "rkning", " "};

        sheetTable = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                if(columns == 5)
                    return true;
                return false;
            }
        };

        sheetModel = new DefaultTableModel();

        sheetTable.setModel(sheetModel);
        sheetTable.setFillsViewportHeight(true);

        JScrollPane sheetDataScroll = new JScrollPane(sheetTable);
        sheetDataScroll.setBounds(5, 25, 581, 210);
        sheetTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
        sheetTable.setPreferredScrollableViewportSize(new Dimension(581,210));
        sheetDataScroll.setPreferredSize(new Dimension(581,210));
        sheetDataScroll.setBorder(BorderFactory.createEmptyBorder());

        pnlSearch.add(sheetDataScroll);

        addTimeSheetData();

        //SheetTable end

        //ClientTable start
        clientColumn = new String[]{"Navn", "Adresse", "Postn nr. / By", "Telefon", "E-mail", " "};

        clientTable = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                if(columns == 5)
                    return true;
                return false;
            }
        };

        clientModel = new DefaultTableModel();
        clientTable.setModel(clientModel);
        clientTable.setFillsViewportHeight(true);

        JScrollPane clientDataScroll = new JScrollPane(clientTable);
        clientDataScroll.setBounds(5, 255, 581, 210);
        clientTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
        clientTable.setPreferredScrollableViewportSize(new Dimension(581,210));
        clientDataScroll.setPreferredSize(new Dimension(581,210));
        clientDataScroll.setBorder(BorderFactory.createEmptyBorder());

        pnlSearch.add(clientDataScroll);

        addClientData();

        //ClientTable end

        //TaskTable start
        taskColumn = new String[]{"Titel", "Beskrivelse", " "};

        taskTable = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                if(columns == 2)
                    return true;
                return false;
            }
        };

        taskModel = new DefaultTableModel();
        taskTable.setModel(taskModel);
        taskTable.setFillsViewportHeight(true);

        JScrollPane taskDataScroll = new JScrollPane(taskTable);
        taskDataScroll.setBounds(5, 490, 581, 180);
        taskTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
        taskTable.setPreferredScrollableViewportSize(new Dimension(581, 180));
        taskDataScroll.setPreferredSize(new Dimension(581, 180));
        taskDataScroll.setBorder(BorderFactory.createEmptyBorder());

        pnlSearch.add(taskDataScroll);

        addTaskData();

        //TaskTable end

    }

    private void addTimeSheetData()
    {
        new AddSheetData().execute();
    }

    private void addClientData()
    {
        new AddClientData().execute();
    }

    private void addTaskData()
    {
        new AddTaskData().execute();
    }

    private void addButtonsToSheets(final int columnIndex)
    {
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable sheetTable = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());

                if(columnIndex == 5)
                {

                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(sheetTable, show, columnIndex);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        sheetTable.repaint();
    }

    private void addButtonsToClients(final int columnIndex)
    {
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable clientTable = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                long phoneNo = Long.parseLong(clientTable.getValueAt(row, 3).toString());
                try
                {
                    EditClientUI.createWindow(_clientCtrl.getClientByPhone(phoneNo));
                    _instance = null;
                    _frame.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(clientTable, show, columnIndex);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        clientTable.repaint();
    }

    private void addButtonsToTasks(final int columnIndex)
    {
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable tasksTable = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());

                if(columnIndex == 5)
                {

                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(taskTable, show, columnIndex);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        taskTable.repaint();
    }

    class AddSheetData extends SwingWorker<Integer, Integer>
    {
        @Override
        protected Integer doInBackground() throws Exception
        {
            try
            {
                ArrayList<TimeSheet> timeSheets = _result.getTimeSheetCollection();

                Object[][] data = {};
                sheetModel.setDataVector(data, sheetColumn);

                for(TimeSheet sheet : timeSheets)
                {
                    if(sheet != null)
                    {
                        ArrayList<DataEntry> dataEntries = sheet.getDataEntries();
                        for(int i = 0; i < dataEntries.size(); i++)
                        {
                            DataEntry dataEntry = dataEntries.get(i);
                            Object[] row = new Object[]{ dataEntry.getStartDate(), dataEntry.getEndDate(), dataEntry.getTask().getTitle(),
                                    dataEntry.getUser().getFirstName() + " " + dataEntry.getUser().getLastName(), dataEntry.getEntryRemark(), "Vis"};
                            sheetModel.addRow(row);
                        }
                    }
                }

                addButtonsToSheets(5);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

        @Override
        protected void done()
        {
            //dbInfo.dispose();
        }
    }

    class AddClientData extends SwingWorker<Integer, Integer>
    {
        @Override
        protected Integer doInBackground() throws Exception
        {
            try
            {
                ArrayList<Client> clients = _result.getClientCollection();
                Object[][] data = {};
                clientModel.setDataVector(data, clientColumn);

                for(Client client : clients)
                {
                    if(client != null)
                    {
                        Object[] row = new Object[]{ client.getName(), client.getAddress(), client.getCity().getZipCode() + " " + client.getCity().getCityName(), client.getPhoneNo(), client.getEmail(), "Vis"};
                        clientModel.addRow(row);
                    }
                }

                addButtonsToClients(5);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

        @Override
        protected void done()
        {
            //dbInfo.dispose();
        }
    }

    class AddTaskData extends SwingWorker<Integer, Integer>
    {
        @Override
        protected Integer doInBackground() throws Exception
        {
            try
            {
                ArrayList<Task> tasks = _result.getTaskCollection();
                Object[][] data = {};
                taskModel.setDataVector(data, taskColumn);

                for(Task task : tasks)
                {
                    if(task != null)
                    {
                        Object[] row = new Object[]{ task.getTitle(), task.getDescription(), "Vis"};
                        taskModel.addRow(row);
                    }
                }

                addButtonsToTasks(2);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void done()
        {
            //dbInfo.dispose();
        }
    }
}
