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
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import utils.Logging;

public class CreateTimeSheetUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> drpClients;
	private ClientCtrl _clientCtrl;
	private DefaultComboBoxModel<String> model;
	
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
		contentPane.add(drpClients);
		model = new DefaultComboBoxModel<String>(addClients());
		drpClients.setModel(model);

		
		// addClients();

		// pane1
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
		
		
		// pane2
		panel2 = new JPanel();
		panel2.setLayout(null);
		
		tabbedPane.add("Rettigheder", panel2);		
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
}