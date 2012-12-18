package views.timesheet;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import controllers.ClientCtrl;
import models.Client;
import controllers.UserCtrl;
import models.User;
import controllers.UserPermissionCtrl;
import models.UserPermission;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import views.dataentry.CreateDataEntryUI;
import utils.Logging;

public class CreateTimeSheetUI extends JFrame {
	
	private JPanel _contentPane;
	private JComboBox<String> _drpClients;
	private ClientCtrl _clientCtrl;
	private DefaultComboBoxModel<String> _model;

	private JComboBox<String> _drpUsers;
	private UserCtrl _userCtrl;
	private DefaultComboBoxModel<String> _modelUsers;
		
	private JComboBox<String> _drpPermissions;
	private UserPermissionCtrl _userPermissionCtrl;
	private DefaultComboBoxModel<String> _modelPermissions;
	
	private JPanel _panel1;
	private JPanel _panel2;
	private JTextField _textField;

	private CreateDataEntryUI createDataEntryUI;
	

	/**
	 * Create the frame.
	 */
	public CreateTimeSheetUI() 
	{	
		createElements();	
	}
	
	
	public void createElements()
	{
		setTitle("Ny Registrering");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 392);
		_contentPane = new JPanel();
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(_contentPane);
		_contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 392, 262);
		_contentPane.add(tabbedPane);
		
		JButton btnNewButton = new JButton("Annuller");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(302, 331, 100, 23);
		_contentPane.add(btnNewButton);
		
		JButton btnNewNste = new JButton("N\u00E6ste (1/2)");
		btnNewNste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createDataEntryUI.createWindow();
			}
		});
		btnNewNste.setBounds(195, 331, 100, 23);
		_contentPane.add(btnNewNste);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 392, 2);
		_contentPane.add(separator);
		
		JLabel lblKlient = new JLabel("Klient");
		lblKlient.setBounds(10, 11, 53, 23);
		_contentPane.add(lblKlient);
		
		_drpClients = new JComboBox<String>();
		_drpClients.setBounds(49, 11, 353, 22);
		/*
		contentPane.add(drpClients);
		model = new DefaultComboBoxModel<String>(addClients());
		drpClients.setModel(model);
		*/
		
		// addClients();
		// addUsers();

		// pane1 start
		_panel1 = new JPanel();
		_panel1.setLayout(null);
		
		JLabel label1 = new JLabel("ID");
		label1.setBounds(5, 5, 60, 23);
		_panel1.add(label1);
		
		JLabel label2 = new JLabel("Note");
		label2.setBounds(5, 30, 60, 23);
		_panel1.add(label2);
		
		// for display ClientId
		JTextPane textPane = new JTextPane();
		textPane.setBounds(45, 5, 332, 20);
		_panel1.add(textPane);
		
		// for notes
		_textField = new JTextField();
		_textField.setBounds(45, 31, 332, 195);
		_panel1.add(_textField);
		_textField.setColumns(10);
		
		tabbedPane.add("Time-Sag", _panel1);
		// pane1 end
		
		// pane2 start
		_panel2 = new JPanel();
		_panel2.setLayout(null);
		
		JLabel label3 = new JLabel("Brugere");
		label3.setBounds(5, 30, 60, 23);
		_panel2.add(label3);
		
		JLabel label4 = new JLabel("Rettigheder");
		label4.setBounds(5, 127, 96, 23);
		_panel2.add(label4);
		
		_drpUsers = new JComboBox<String>();
		_drpUsers.setBounds(111, 30, 249, 22);
		_panel2.add(_drpUsers);
		//model = new DefaultComboBoxModel<String>(addUsers());
		//drpUsers.setModel(model);
		//addUsers();
		
		_drpPermissions = new JComboBox<String>();
		_drpPermissions.setBounds(111, 127, 249, 22);
		_panel2.add(_drpPermissions);
		//_model = new DefaultComboBoxModel<String>();
		//_drpPermissions.setModel(_model);
		//addPermissions();
				
		tabbedPane.add("Rettigheder", _panel2);
		// pane2 end
	}
	
	
	public String[] addClients()
	{
		ArrayList<Client> clients;
		try
		{
			clients = _clientCtrl.getAllClients();
			String[] clientNames = new String[clients.size()];
			for (int index = 0; index < clients.size(); index++) {
				clientNames[index] = clients.get(index).getName();
			}
			return  clientNames;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
		
		return null;
	}
	
	
	public String[] addUsers()
	{
		ArrayList<User> users;
		try
		{
			users = _userCtrl.getAllUsers();
			String[] userNames = new String[users.size()];
			for (int index = 0; index < users.size(); index++) {
				String fName = users.get(index).getFirstName();
				String lName = users.get(index).getLastName();
				String fullName = fName + " " + lName;
				userNames[index] = fullName;
			}
			return userNames;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	
	public String[] addPermissions()
	{
		ArrayList<UserPermission> permissions;
		try
		{
			permissions = _userPermissionCtrl.getAllRoles();
			String[] permissionTitles = new String[permissions.size()];
			for (int index = 0; index < permissions.size(); index++) {
				permissionTitles[index] = permissions.get(index).getUserRole();
			}
			return permissionTitles;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
}