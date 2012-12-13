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

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SystemUI extends JFrame implements ChangeListener
{
	private boolean _activeSession;
	private int _selectedIndex;
	private JPanel _pnlSystemLayout;
	private JPanel _pnlClients;
	private JPanel _pnlTimeSheet;
	private JTextField _txtSearchOverview;

	@SuppressWarnings("rawtypes")
	public SystemUI() {
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
		
		JMenu mnTimeSheet = new JMenu("Time-sag");
		mnTimeSheet.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnTimeSheet);
		
		JMenu mnClient = new JMenu("Klient");
		mnClient.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnClient);
		
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
		
		JList lstClients = new JList();
		lstClients.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstClients.setBounds(5, 5, 187, 631);
		pnlClientTab.add(lstClients);
		
		_pnlTimeSheet = new JPanel();
		_pnlTimeSheet.setBounds(220, 36, 799, 675);
		_pnlSystemLayout.add(_pnlTimeSheet);
		_pnlTimeSheet.setVisible(true);
		_pnlTimeSheet.setLayout(null);
		
		JPanel pnlTimeSheetInfo = new JPanel();
		pnlTimeSheetInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTimeSheetInfo.setBackground(Color.WHITE);
		pnlTimeSheetInfo.setBounds(3, 2, 790, 86);
		_pnlTimeSheet.add(pnlTimeSheetInfo);
		pnlTimeSheetInfo.setLayout(null);
		
		JLabel lblClientName = new JLabel("[swap section with db data]");
		lblClientName.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblClientName.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblClientName.setBounds(5, 5, 500, 20);
		pnlTimeSheetInfo.add(lblClientName);
		
		JLabel lblCaseId = new JLabel("[swap section with db data]");
		lblCaseId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaseId.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblCaseId.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCaseId.setBounds(535, 5, 250, 20);
		pnlTimeSheetInfo.add(lblCaseId);
		
		JLabel lblClientAddres = new JLabel("[swap section with db data]");
		lblClientAddres.setForeground(Color.GRAY);
		lblClientAddres.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientAddres.setBounds(5, 25, 500, 15);
		pnlTimeSheetInfo.add(lblClientAddres);
		
		JLabel lblClientPhoneNo = new JLabel("Telefon: [swap section with db data]");
		lblClientPhoneNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientPhoneNo.setBounds(5, 52, 450, 15);
		pnlTimeSheetInfo.add(lblClientPhoneNo);
		
		JLabel lblClientEmail = new JLabel("E-Mail: [swap section with db data]");
		lblClientEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblClientEmail.setBounds(5, 66, 450, 15);
		pnlTimeSheetInfo.add(lblClientEmail);
		
		JLabel lblTimeSheetOwner = new JLabel("Ansvarlig: [swap section with db data]");
		lblTimeSheetOwner.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSheetOwner.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTimeSheetOwner.setBounds(485, 66, 300, 15);
		pnlTimeSheetInfo.add(lblTimeSheetOwner);
		
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
		
		_pnlClients = new JPanel();
		_pnlClients.setBounds(220, 36, 799, 675);
		_pnlSystemLayout.add(_pnlClients);
		_pnlClients.setVisible(false);
		_pnlTimeSheet.setLayout(null);
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		try
		{
			if(_activeSession == false)
				_activeSession = true;

			else if(_activeSession == true)
				if(_selectedIndex == 0)
				{
					_pnlTimeSheet.setVisible(false);
					_pnlClients.setVisible(true);
					_selectedIndex = 1;
				}
				else
				{
					_pnlClients.setVisible(false);
					_pnlTimeSheet.setVisible(true);
					_selectedIndex = 0;
				}

			else
			{
				JOptionPane.showMessageDialog(null, ErrorHandling.errorHandling(01), "Fejl!", JOptionPane.ERROR_MESSAGE);
				System.exit(EXIT_ON_CLOSE);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ErrorHandling.errorHandling(99) + ", programmet afsluttes.", "Fejl!", JOptionPane.ERROR_MESSAGE);
			System.exit(EXIT_ON_CLOSE);
		}
	}
}