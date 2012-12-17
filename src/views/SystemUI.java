/**
 * filename    : SystemUI.java
 * created     : Dec 13, 2012 (1:05:58 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package views;

import utils.*;
import views.client.CreateClientUI;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import models.Client;
import models.DataEntry;
import models.TimeSheet;

import controllers.ClientCtrl;
import controllers.TimeSheetCtrl;

@SuppressWarnings("serial")
public class SystemUI extends JFrame implements ChangeListener
{
	private boolean primaryTabActive;
	private JPanel pnlSystemLayout;
	private JPanel pnlClients;
	private JPanel pnlTimeSheet;
	private JTextField txtSearchOverview;
	private JCheckBox chkUsersSheetsOnly;
	private JList<String> lstTimeSheets;
	
	
	// Controllers
	private TimeSheetCtrl _timesheetCtrl;
	private ClientCtrl _clientCtrl;
	
	// InfoDisplay
	private String clientName;
	private String clientAddress;
	private String clientPhone;
	private String clientEmail;
	private String clientNote;
	private String caseId;
	private String sheetOwner;
	
	// Grid
	private DefaultTableModel sheetModel;
	private JTable sheetTable;
	private String[] sheetColumn;
	private DefaultTableModel clientModel;
	private JTable clientTable;
	private String[] clientColumn;

	public SystemUI()
	{
		_timesheetCtrl = new TimeSheetCtrl();
		_clientCtrl = new ClientCtrl();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/app.png")));
		setTitle(SystemInformation.systemInformation(01) + " (" + SystemInformation.systemInformation(02) +
				" - build " + SystemInformation.systemInformation(03) + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1024, 768));
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Filer");
		mnFiles.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnFiles);
		
		JMenuItem mntmPrint = new JMenuItem("Udskriv");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printFunction();
			}
		});
		mnFiles.add(mntmPrint);
		
		JSeparator separator = new JSeparator();
		mnFiles.add(separator);
		
		JMenuItem mntmLogout = new JMenuItem("Logud");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applicationLogout();
			}
		});
		mnFiles.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Afslut");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applicationExit();
			}
		});
		mnFiles.add(mntmExit);
		
		JMenu mnTimeSheet = new JMenu("Time-sag");
		mnTimeSheet.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnTimeSheet);
		
		JMenu mnClient = new JMenu("Klient");
		mnClient.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnClient);
		
		JMenuItem mntmNewClient = new JMenuItem("Ny klient");
		mntmNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createClient();
			}
		});
		mnClient.add(mntmNewClient);
		
		JMenu mnSettings = new JMenu("Indstillinger");
		mnSettings.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnSettings);
		
		JMenu mnAbout = new JMenu("Om");
		mnAbout.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnAbout);
		pnlSystemLayout = new JPanel();
		pnlSystemLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlSystemLayout);
		pnlSystemLayout.setLayout(null);
		
		JPanel pnlQuickAccess = new JPanel();
		pnlQuickAccess.setBounds(5, 0, 1014, 37);
		pnlSystemLayout.add(pnlQuickAccess);
		pnlQuickAccess.setLayout(null);
		
		JLabel lblNewTimeSheet = new JLabel("Ny time-sag");
		lblNewTimeSheet.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewTimeSheet.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewTimeSheet.setIcon(new ImageIcon(SystemUI.class.getResource("/new_timesheet.png")));
		lblNewTimeSheet.setBounds(5, 12, 95, 16);
		pnlQuickAccess.add(lblNewTimeSheet);
		
		JLabel lblNewDataEntry = new JLabel("Ny registrering");
		lblNewDataEntry.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewDataEntry.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewDataEntry.setIcon(new ImageIcon(SystemUI.class.getResource("/new_dataentry.png")));
		lblNewDataEntry.setBounds(115, 12, 114, 16);
		pnlQuickAccess.add(lblNewDataEntry);
		
		JLabel lblNewClient = new JLabel("Ny klient");
		lblNewClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createClient();
			}
		});
		lblNewClient.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewClient.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewClient.setIcon(new ImageIcon(SystemUI.class.getResource("/new_client.png")));
		lblNewClient.setBounds(244, 12, 75, 16);
		pnlQuickAccess.add(lblNewClient);
		
		JLabel lblPrintOverview = new JLabel("Udskriv");
		lblPrintOverview.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPrintOverview.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblPrintOverview.setIcon(new ImageIcon(SystemUI.class.getResource("/print_overview.png")));
		lblPrintOverview.setBounds(333, 12, 68, 16);
		pnlQuickAccess.add(lblPrintOverview);
		
		JLabel lblSortOverview = new JLabel("Sortér");
		lblSortOverview.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSortOverview.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSortOverview.setIcon(new ImageIcon(SystemUI.class.getResource("/sort_overview.png")));
		lblSortOverview.setBounds(416, 12, 60, 16);
		pnlQuickAccess.add(lblSortOverview);
		
		JLabel lblPermission = new JLabel("Rettigheder");
		lblPermission.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPermission.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblPermission.setIcon(new ImageIcon(SystemUI.class.getResource("/permission_timesheet.png")));
		lblPermission.setBounds(493, 12, 114, 16);
		pnlQuickAccess.add(lblPermission);
		
		JLabel lblSearchOverview = new JLabel("");
		lblSearchOverview.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSearchOverview.setIcon(new ImageIcon(SystemUI.class.getResource("/search_overview.png")));
		lblSearchOverview.setBounds(833, 12, 16, 16);
		pnlQuickAccess.add(lblSearchOverview);
		
		txtSearchOverview = new JTextField();
		txtSearchOverview.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSearchOverview.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtSearchOverview.setText("S\u00F8g");
			}
		});
		txtSearchOverview.setText("S\u00F8g");
		txtSearchOverview.setBounds(854, 9, 155, 22);
		pnlQuickAccess.add(txtSearchOverview);
		txtSearchOverview.setColumns(10);
		
		JPanel pnlOverviewSelection = new JPanel();
		pnlOverviewSelection.setBounds(5, 36, 215, 675);
		pnlSystemLayout.add(pnlOverviewSelection);
		pnlOverviewSelection.setLayout(null);

		// START OF TIMESHEETS PANEL
		pnlTimeSheet = new JPanel();
		pnlTimeSheet.setBounds(220, 36, 799, 675);
		pnlSystemLayout.add(pnlTimeSheet);
		pnlTimeSheet.setLayout(null);
		
		// START OF TS_INFO PANEL
		JPanel pnlTimeSheetInfo = new JPanel();
		pnlTimeSheetInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetInfo.setBackground(Color.WHITE);
		pnlTimeSheetInfo.setBounds(3, 2, 790, 86);
		pnlTimeSheet.add(pnlTimeSheetInfo);
		pnlTimeSheetInfo.setLayout(null);
		
		final JLabel lblClientName_ts = new JLabel();
		lblClientName_ts.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblClientName_ts.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblClientName_ts.setBounds(5, 5, 500, 20);
		pnlTimeSheetInfo.add(lblClientName_ts);
		
		final JLabel lblCaseId_ts = new JLabel();
		lblCaseId_ts.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaseId_ts.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblCaseId_ts.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCaseId_ts.setBounds(535, 5, 250, 20);
		pnlTimeSheetInfo.add(lblCaseId_ts);
		
		final JLabel lblClientAddress_ts = new JLabel();
		lblClientAddress_ts.setForeground(Color.GRAY);
		lblClientAddress_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientAddress_ts.setBounds(5, 25, 500, 15);
		pnlTimeSheetInfo.add(lblClientAddress_ts);
		
		final JLabel lblClientPhoneNo_ts = new JLabel();
		lblClientPhoneNo_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientPhoneNo_ts.setBounds(5, 52, 450, 15);
		pnlTimeSheetInfo.add(lblClientPhoneNo_ts);
		
		final JLabel lblClientEmail_ts = new JLabel();
		lblClientEmail_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientEmail_ts.setBounds(5, 66, 450, 15);
		pnlTimeSheetInfo.add(lblClientEmail_ts);
		
		final JLabel lblTimeSheetOwner_ts = new JLabel();
		lblTimeSheetOwner_ts.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSheetOwner_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTimeSheetOwner_ts.setBounds(485, 66, 300, 15);
		pnlTimeSheetInfo.add(lblTimeSheetOwner_ts);
		// END OF TS_INFO PANEL
		
		// START OF NOTE PANEL
		JPanel pnlTimeSheetNote = new JPanel();
		pnlTimeSheetNote.setBackground(Color.WHITE);
		pnlTimeSheetNote.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetNote.setBounds(3, 99, 790, 65);
		pnlTimeSheet.add(pnlTimeSheetNote);
		pnlTimeSheetNote.setLayout(null);
		
		final JTextArea txtNoteField = new JTextArea();
		txtNoteField.setBounds(5, 5, 780, 55);
		pnlTimeSheetNote.add(txtNoteField);	
		// END OF NOTE PANEL
		
		// START OF SHEETGRID
		JPanel pnlTimeSheetOverview = new JPanel();
		//pnlTimeSheetOverview.setBorder(new LineBorder(Color.LIGHT_GRAY));
		//pnlTimeSheetOverview.setBackground(Color.WHITE);
		pnlTimeSheetOverview.setBounds(3, 170, 790, 498);
		pnlTimeSheet.add(pnlTimeSheetOverview);

		sheetColumn = new String[]{"Påbegyndt", "Afsluttet", "Opgave", "Registrator", "Bemærkning", " "};
		
		sheetTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns == 5 || columns == 6)
					return true;
				return false;
			}
		};
		
		sheetModel = new DefaultTableModel();
		
		sheetTable.setModel(sheetModel);
		sheetTable.setFillsViewportHeight(true);
		
		JScrollPane sheetScroll = new JScrollPane(sheetTable);
		//sheetTable.setBounds(0, 0, 750, 490);
		sheetTable.setBorder(new LineBorder(Color.LIGHT_GRAY));
		sheetTable.setPreferredScrollableViewportSize(new Dimension(790, 493));
		sheetScroll.setPreferredSize(new Dimension(790, 493));
		sheetScroll.setBorder(BorderFactory.createEmptyBorder());
		pnlTimeSheetOverview.add(sheetScroll);
		// END OF SHEETGRID
		// END OF TIMESHEETS PANEL
		
		// START OF CLIENTS PANEL
		pnlClients = new JPanel();
		pnlClients.setBounds(220, 36, 799, 675);
		pnlSystemLayout.add(pnlClients);
		pnlClients.setLayout(null);
		
		// START OF CL_INFO PANEL
		JPanel pnlClientInfo = new JPanel();
		pnlClientInfo.setLayout(null);
		pnlClientInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlClientInfo.setBackground(Color.WHITE);
		pnlClientInfo.setBounds(3, 2, 790, 86);
		pnlClients.add(pnlClientInfo);
		
		JLabel lblClientName_cl = new JLabel("[swap section with db data]");
		lblClientName_cl.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblClientName_cl.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblClientName_cl.setBounds(5, 5, 500, 20);
		pnlClientInfo.add(lblClientName_cl);
		
		JLabel lblClientAddress_cl = new JLabel("[swap section with db data]");
		lblClientAddress_cl.setForeground(Color.GRAY);
		lblClientAddress_cl.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientAddress_cl.setBounds(5, 25, 500, 15);
		pnlClientInfo.add(lblClientAddress_cl);
		
		JLabel lblClientPhoneNo_cl = new JLabel("Telefon: [swap section with db data]");
		lblClientPhoneNo_cl.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientPhoneNo_cl.setBounds(5, 52, 450, 15);
		pnlClientInfo.add(lblClientPhoneNo_cl);
		
		JLabel lblClientEmail_cl = new JLabel("E-Mail: [swap section with db data]");
		lblClientEmail_cl.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientEmail_cl.setBounds(5, 66, 450, 15);
		pnlClientInfo.add(lblClientEmail_cl);
		// END OF CL_INFO PANEL
		
		// START OF CLIENTGRID
		JPanel pnlClientOverview = new JPanel();
		pnlClientOverview.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlClientOverview.setBackground(Color.WHITE);
		pnlClientOverview.setBounds(3, 99, 790, 569);
		pnlClients.add(pnlClientOverview);
		
		clientColumn = new String[]{"Sags nr", "Virksomhed", "Oprettet", "Ansvarlig", "Note", " "};
		
		clientTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
            {
                if(columns == 5 || columns == 6)
                    return true;
                return false;
            }
		};
		
		clientModel = new DefaultTableModel();
		
		clientTable.setModel(clientModel);
		clientTable.setFillsViewportHeight(true);
		// START OF CLIENTGRID
		// END OF CLIENTS PANEL

		JTabbedPane tabSelection = new JTabbedPane(JTabbedPane.TOP);
		tabSelection.setFont(new Font("Dialog", Font.PLAIN, 12));
		tabSelection.setBounds(5, 0, 202, 668);
		tabSelection.addChangeListener(this);
		pnlOverviewSelection.add(tabSelection);
		
		JPanel pnlTimeSheetTab = new JPanel();
		tabSelection.addTab("Time-sager", null, pnlTimeSheetTab, null);
		pnlTimeSheetTab.setLayout(null);
		
		chkUsersSheetsOnly = new JCheckBox("Vis kun mine sager");
		chkUsersSheetsOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUserSheetsOnly();
			}
		});
		chkUsersSheetsOnly.setFont(new Font("Dialog", Font.PLAIN, 12));
		chkUsersSheetsOnly.setBounds(5, 614, 181, 23);;
		pnlTimeSheetTab.add(chkUsersSheetsOnly);
		
		lstTimeSheets = new JList<String>();
		lstTimeSheets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addSheetData();
				lblClientName_ts.setText(clientName);
				lblClientAddress_ts.setText(clientAddress);
				lblClientPhoneNo_ts.setText("Telefon: " + clientPhone);
				lblClientEmail_ts.setText("E-Mail: " + clientEmail);
				lblCaseId_ts.setText(caseId);
				lblTimeSheetOwner_ts.setText("Ansvarlig: " + sheetOwner);
				txtNoteField.setText("Note: " + clientNote);
			}
		});
		lstTimeSheets.setListData(populateSheetList());
		lstTimeSheets.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstTimeSheets.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstTimeSheets.setBounds(5, 5, 187, 605);
		pnlTimeSheetTab.add(lstTimeSheets);
		
		JPanel pnlClientTab = new JPanel();
		tabSelection.addTab("Klienter", null, pnlClientTab, null);
		pnlClientTab.setLayout(null);
		
		JList<String> lstClients = new JList<String>();
        lstClients.setListData(populateClientList());
        lstClients.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstClients.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstClients.setBounds(5, 5, 187, 631);
		pnlClientTab.add(lstClients);
	}
	
	private void applicationExit()
	{
		int request = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil afslutte programmet?", "Afslut", JOptionPane.YES_NO_OPTION);
		if(request == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	private void applicationLogout()
	{
		int request = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil logge ud af programmet?", "Logud", JOptionPane.YES_NO_OPTION);
		if(request == JOptionPane.YES_OPTION)
			return; // logout and go to login dialog
	}
	
	private void printFunction()
	{
	}
	
	private void createClient()
	{
		CreateClientUI.createWindow();
	}
	
	private void checkUserSheetsOnly()
	{
		if(chkUsersSheetsOnly.isSelected())
		{
			lstTimeSheets.setListData(populateSheetByUser());
		}
		else
		{
			lstTimeSheets.setListData(populateSheetList());
		}
	}
	
	private String[] populateSheetList()
	{
        try
        {
            return new PopulateSheetList().doInBackground();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
	
	private String[] populateSheetByUser()
	{
		ArrayList<TimeSheet> timesheetList;
		try
		{
			timesheetList = _timesheetCtrl.getAllTimeSheetsByUser(UserSession.getLoggedInUser());
			String[] sheetNames = new String[timesheetList.size()];
			for(int i = 0; i < timesheetList.size(); i++)
				sheetNames[i] = timesheetList.get(i).getCaseId() + 
						" (" + timesheetList.get(i).getClient().getName() + ")";
			
			return sheetNames;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	private String[] populateClientList()
	{
		ArrayList<Client> clientsList;
		try
		{
			clientsList = _clientCtrl.getAllClients();
			String[] clientNames = new String[clientsList.size()];
			for(int i = 0; i < clientsList.size(); i++)
				clientNames[i] = clientsList.get(i).getName();
			
			return clientNames;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	private void addSheetData()
	{
        new AddSheetData().execute();
	}
	
	private void addClientSheetData()
	{
		// code missing
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{	
		try
		{
			if(primaryTabActive == false)
			{
				pnlClients.setVisible(false);
				pnlTimeSheet.setVisible(true);
				primaryTabActive = true;
			}
			else
			{
				pnlTimeSheet.setVisible(false);
				pnlClients.setVisible(true);
				primaryTabActive = false;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 5), "Fejl!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

    class PopulateSheetList extends SwingWorker<String[], Integer>
    {
        protected String[] doInBackground() throws Exception
        {
            ArrayList<TimeSheet> timeSheetList;
            try
            {
                timeSheetList = _timesheetCtrl.getAllTimeSheets();
                String[] sheetNames = new String[timeSheetList.size()];
                for(int i = 0; i < timeSheetList.size(); i++)
                    sheetNames[i] = timeSheetList.get(i).getCaseId() +
                            " (" + timeSheetList.get(i).getClient().getName() + ")";

                return sheetNames;
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }
    }

    class AddSheetData extends SwingWorker<Integer, Integer>
    {
        protected Integer doInBackground() throws Exception
        {
            try
            {
                int sheetId = 2;

                TimeSheet sheet = _timesheetCtrl.getTimeSheetById(sheetId);

                if(sheet != null)
                {
                    clientName = sheet.getClient().getName();
                    clientAddress = sheet.getClient().getAddress() + ", " + sheet.getClient().getCity().getZipCode() + " " + sheet.getClient().getCity().getCityName();
                    clientPhone = String.valueOf(sheet.getClient().getPhoneNo());
                    clientEmail = sheet.getClient().getEmail();
                    caseId = sheet.getCaseId();
                    sheetOwner = sheet.getUser().getFirstName() + " " + sheet.getUser().getLastName();
                    clientNote = sheet.getNote();
                    ArrayList<DataEntry> dataEntries = sheet.getDataEntries();

                    Object[][] data = {};
                    sheetModel.setDataVector(data, sheetColumn);

                    for(int i = 0; i < dataEntries.size(); i++)
                    {

                        DataEntry dataEntry = dataEntries.get(i);
                        Object[] row = new Object[]{ dataEntry.getStartDate(), dataEntry.getEndDate(), dataEntry.getTask().getTitle(),
                                dataEntry.getUser().getFirstName() + " " + dataEntry.getUser().getLastName(), dataEntry.getEntryRemark() };
                        sheetModel.addRow(row);
                    }
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return 0;
        }
    }
}