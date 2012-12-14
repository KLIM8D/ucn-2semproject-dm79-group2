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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import models.Client;

import controllers.ClientCtrl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SystemUI extends JFrame implements ChangeListener
{
	private boolean _primaryTabActive;
	private JPanel _pnlSystemLayout;
	private JPanel _pnlClients;
	private JPanel _pnlTimeSheet;
	private JTextField _txtSearchOverview;
	
	// Controllers
	private ClientCtrl _clientCtrl;
	
	// Elements
	private DefaultListModel<String> clientModel;

	@SuppressWarnings("rawtypes")
	public SystemUI()
	{
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
		_pnlSystemLayout = new JPanel();
		_pnlSystemLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_pnlSystemLayout);
		_pnlSystemLayout.setLayout(null);
		
		JPanel pnlQuickAccess = new JPanel();
		pnlQuickAccess.setBounds(5, 0, 1014, 37);
		_pnlSystemLayout.add(pnlQuickAccess);
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
		
		_txtSearchOverview = new JTextField();
		_txtSearchOverview.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				_txtSearchOverview.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				_txtSearchOverview.setText("S\u00F8g");
			}
		});
		_txtSearchOverview.setText("S\u00F8g");
		_txtSearchOverview.setBounds(854, 9, 155, 22);
		pnlQuickAccess.add(_txtSearchOverview);
		_txtSearchOverview.setColumns(10);
		
		JPanel pnlOverviewSelection = new JPanel();
		pnlOverviewSelection.setBounds(5, 36, 215, 675);
		_pnlSystemLayout.add(pnlOverviewSelection);
		pnlOverviewSelection.setLayout(null);

		_pnlTimeSheet = new JPanel();
		_pnlTimeSheet.setBounds(220, 36, 799, 675);
		_pnlSystemLayout.add(_pnlTimeSheet);
		_pnlTimeSheet.setLayout(null);
		
		JPanel pnlTimeSheetInfo = new JPanel();
		pnlTimeSheetInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetInfo.setBackground(Color.WHITE);
		pnlTimeSheetInfo.setBounds(3, 2, 790, 86);
		_pnlTimeSheet.add(pnlTimeSheetInfo);
		pnlTimeSheetInfo.setLayout(null);
		
		JLabel lblClientName_ts = new JLabel("[swap section with db data]");
		lblClientName_ts.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblClientName_ts.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblClientName_ts.setBounds(5, 5, 500, 20);
		pnlTimeSheetInfo.add(lblClientName_ts);
		
		JLabel lblCaseId_ts = new JLabel("[swap section with db data]");
		lblCaseId_ts.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaseId_ts.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblCaseId_ts.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCaseId_ts.setBounds(535, 5, 250, 20);
		pnlTimeSheetInfo.add(lblCaseId_ts);
		
		JLabel lblClientAddress_ts = new JLabel("[swap section with db data]");
		lblClientAddress_ts.setForeground(Color.GRAY);
		lblClientAddress_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientAddress_ts.setBounds(5, 25, 500, 15);
		pnlTimeSheetInfo.add(lblClientAddress_ts);
		
		JLabel lblClientPhoneNo_ts = new JLabel("Telefon: [swap section with db data]");
		lblClientPhoneNo_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientPhoneNo_ts.setBounds(5, 52, 450, 15);
		pnlTimeSheetInfo.add(lblClientPhoneNo_ts);
		
		JLabel lblClientEmail_ts = new JLabel("E-Mail: [swap section with db data]");
		lblClientEmail_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientEmail_ts.setBounds(5, 66, 450, 15);
		pnlTimeSheetInfo.add(lblClientEmail_ts);
		
		JLabel lblTimeSheetOwner_ts = new JLabel("Ansvarlig: [swap section with db data]");
		lblTimeSheetOwner_ts.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSheetOwner_ts.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTimeSheetOwner_ts.setBounds(485, 66, 300, 15);
		pnlTimeSheetInfo.add(lblTimeSheetOwner_ts);
		
		JPanel pnlTimeSheetNote = new JPanel();
		pnlTimeSheetNote.setBackground(Color.WHITE);
		pnlTimeSheetNote.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetNote.setBounds(3, 99, 790, 65);
		_pnlTimeSheet.add(pnlTimeSheetNote);
		pnlTimeSheetNote.setLayout(null);
		
		JTextArea txtNoteField = new JTextArea();
		txtNoteField.setText("Note: [swap section with db data]");
		txtNoteField.setBounds(5, 5, 780, 55);
		pnlTimeSheetNote.add(txtNoteField);
		
		JPanel pnlTimeSheetOverview = new JPanel();
		pnlTimeSheetOverview.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetOverview.setBackground(Color.WHITE);
		pnlTimeSheetOverview.setBounds(3, 176, 790, 492);
		_pnlTimeSheet.add(pnlTimeSheetOverview);
		
		_pnlClients = new JPanel();
		_pnlClients.setBounds(220, 36, 799, 675);
		_pnlSystemLayout.add(_pnlClients);
		_pnlClients.setLayout(null);
		
		JPanel pnlClientInfo = new JPanel();
		pnlClientInfo.setLayout(null);
		pnlClientInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlClientInfo.setBackground(Color.WHITE);
		pnlClientInfo.setBounds(3, 2, 790, 86);
		_pnlClients.add(pnlClientInfo);
		
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
		
		JPanel pnlClientOverview = new JPanel();
		pnlClientOverview.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlClientOverview.setBackground(Color.WHITE);
		pnlClientOverview.setBounds(3, 99, 790, 569);
		_pnlClients.add(pnlClientOverview);
		
		JTabbedPane tabSelection = new JTabbedPane(JTabbedPane.TOP);
		tabSelection.setFont(new Font("Dialog", Font.PLAIN, 12));
		tabSelection.setBounds(5, 0, 202, 668);
		tabSelection.addChangeListener(this);
		pnlOverviewSelection.add(tabSelection);
		
		JPanel pnlTimeSheetTab = new JPanel();
		tabSelection.addTab("Time-sager", null, pnlTimeSheetTab, null);
		pnlTimeSheetTab.setLayout(null);
		
		JCheckBox chkUsersSheetsOnly = new JCheckBox("Vis kun mine sager");
		chkUsersSheetsOnly.setFont(new Font("Dialog", Font.PLAIN, 12));
		chkUsersSheetsOnly.setBounds(5, 614, 181, 23);
		pnlTimeSheetTab.add(chkUsersSheetsOnly);
		
		JList lstTimeSheets = new JList();
		lstTimeSheets.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstTimeSheets.setBounds(5, 5, 187, 605);
		pnlTimeSheetTab.add(lstTimeSheets);
		
		JPanel pnlClientTab = new JPanel();
		tabSelection.addTab("Klienter", null, pnlClientTab, null);
		pnlClientTab.setLayout(null);
		
		JList<String> lstClients = new JList<String>();
		lstClients.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstClients.setBounds(5, 5, 187, 631);
		pnlClientTab.add(lstClients);
		clientModel = new DefaultListModel<String>();
		lstClients.setModel(clientModel);
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
			System.out.println(ex);
		}
		
		return null;
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{	
		try
		{
			if(_primaryTabActive == false)
			{
				_pnlClients.setVisible(false);
				_pnlTimeSheet.setVisible(true);
				_primaryTabActive = true;
			}
			else
			{
				_pnlTimeSheet.setVisible(false);
				_pnlClients.setVisible(true);
				_primaryTabActive = false;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 5), "Fejl!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}