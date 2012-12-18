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

import utils.Logging;

public class CreateTimeSheetUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> drpClients;
	private ClientCtrl _clientCtrl;
	private DefaultComboBoxModel<String> model;

	
	private JComboBox<String> drpUsers;
	private UserCtrl userCtrl;
	private DefaultComboBoxModel<String> modelUsers;
	
	
	private JComboBox<String> drpPermissions;
	private UserPermissionCtrl userPermissionCtrl;
	private DefaultComboBoxModel<String> modelPermissions;
	
	
	
	
	private JPanel panel1;
	private JPanel panel2;
	private JTextField textField;


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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 392, 262);
		contentPane.add(tabbedPane);
		
		JButton btnNewButton = new JButton("Annuller");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(302, 331, 100, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewNste = new JButton("N\u00E6ste (1/2)");
		btnNewNste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewNste.setBounds(195, 331, 100, 23);
		contentPane.add(btnNewNste);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 392, 2);
		contentPane.add(separator);
		
		JLabel lblKlient = new JLabel("Klient");
		lblKlient.setBounds(10, 11, 53, 23);
		contentPane.add(lblKlient);
		
		drpClients = new JComboBox<String>();
		drpClients.setBounds(49, 11, 353, 22);
		/*
		contentPane.add(drpClients);
		model = new DefaultComboBoxModel<String>(addClients());
		drpClients.setModel(model);
		*/
		
		// addClients();
		// addUsers();

		// pane1 start
		panel1 = new JPanel();
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("ID");
		label1.setBounds(5, 5, 60, 23);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Note");
		label2.setBounds(5, 30, 60, 23);
		panel1.add(label2);
		
		// for display ClientId
		JTextPane textPane = new JTextPane();
		textPane.setBounds(45, 5, 332, 20);
		panel1.add(textPane);
		
		// for notes
		textField = new JTextField();
		textField.setBounds(45, 31, 332, 195);
		panel1.add(textField);
		textField.setColumns(10);
		
		tabbedPane.add("Time-Sag", panel1);
		// pane1 end
		
		// pane2 start
		panel2 = new JPanel();
		panel2.setLayout(null);
		
		JLabel label3 = new JLabel("Brugere");
		label3.setBounds(5, 30, 60, 23);
		panel2.add(label3);
		
		JLabel label4 = new JLabel("Rettigheder");
		label4.setBounds(5, 127, 96, 23);
		panel2.add(label4);
		
	
		
		drpUsers = new JComboBox<String>();
		drpUsers.setBounds(111, 30, 249, 22);
		panel2.add(drpUsers);
		//model = new DefaultComboBoxModel<String>(addUsers());
		//drpUsers.setModel(model);
		//addUsers();
		
		drpPermissions = new JComboBox<String>();
		drpPermissions.setBounds(111, 127, 249, 22);
		panel2.add(drpPermissions);
		//model = new DefaultComboBoxModel<String>();
		//drpPermissions.setModel(model);
		//addPermissions();
				
		tabbedPane.add("Rettigheder", panel2);
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
			users = userCtrl.getAllUsers();
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
			permissions = userPermissionCtrl.getAllRoles();
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