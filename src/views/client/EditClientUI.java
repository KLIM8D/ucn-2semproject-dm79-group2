package views.client;

import controllers.ClientCtrl;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;
import views.SystemUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import models.City;
import models.Client;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class EditClientUI
{
    private static JFrame _frame;
    private static EditClientUI _instance;
    private JPanel contentPane;

    //Controllers
    private ClientCtrl _cliCtrl;
    private long _phoneNo;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;

    public static JFrame createWindow(long data)
    {
        if(_instance == null)
            _instance = new EditClientUI(data);

        return _frame;
    }

    public EditClientUI(long data)
    {
    	_phoneNo = data;
        createElements();
    }

    private void createElements()
    {
    	_cliCtrl = new ClientCtrl();

        _frame = new JFrame();
        _frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/new_client.png")));
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Rediger kunde");
        _frame.setBounds(0,0,509,270);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblSupplierName = new JLabel("Navn:");
        lblSupplierName.setBounds(12,12,120,15);
        contentPane.add(lblSupplierName);
        
        JLabel lblSupplierAddress = new JLabel("Adresse:");
        lblSupplierAddress.setBounds(12,37,120,15);
        contentPane.add(lblSupplierAddress);
        
        JLabel lblSupplierZipCode = new JLabel("Postnummer:");
        lblSupplierZipCode.setBounds(12,62,120,15);
        contentPane.add(lblSupplierZipCode);
        
        JLabel lblSupplierCity = new JLabel("By");
        lblSupplierCity.setBounds(222,62,120,15);
        contentPane.add(lblSupplierCity);
        
        JLabel lblSupplierNumber = new JLabel("Telefonnummer:");
        lblSupplierNumber.setBounds(12,87,120,15);
        contentPane.add(lblSupplierNumber);
        
        JLabel lblSupplierEmail = new JLabel("Email:");
        lblSupplierEmail.setBounds(244,87,120,15);
        contentPane.add(lblSupplierEmail);
                
        txtName = new JTextField();
        txtName.setBounds(142,10,350,19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(142,35,350,19);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);
        
        txtZipCode = new JTextField();
        txtZipCode.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtZipCode.getText().length() > 0)
                    Helper.checkIfInt(txtZipCode);
            }
        });
        txtZipCode.setDocument(new JTextFieldLimit(5));
        txtZipCode.setBounds(142,60,50,19);
        contentPane.add(txtZipCode);
        txtZipCode.setColumns(10);
        
        txtCity = new JTextField();
        txtCity.setBounds(265,60,227,19);
        contentPane.add(txtCity);
        txtCity.setColumns(10);
        
        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(142,85,75,19);
        txtPhoneNo.setEditable(false);
        txtPhoneNo.setDocument(new JTextFieldLimit(9));
        contentPane.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(305,85,187,19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(12,135,480,1);
        contentPane.add(separator);
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(375,195,117,25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opdater");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateClient();
            }
        });
        btnCreate.setBounds(246,195,117,25);
        contentPane.add(btnCreate);
        
        addData(_phoneNo);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }
    
    private void updateClient()
    {
    	try
    	{
    		String name = txtName.getText();
    		String address = txtAddress.getText();
    		City city = _cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText()));
    		long phoneNo = _phoneNo;
    		String eMail = txtEmail.getText();
            Calendar cal = Calendar.getInstance();
            Date creationDate = _cliCtrl.getClientByPhone(_phoneNo).getCreationDate();
            Date editedDate =  cal.getTime();
    		Client client = new Client(name, address, city, phoneNo, eMail, creationDate, editedDate);
    		
    		_cliCtrl.updateClient(client);
    		JOptionPane.showMessageDialog(null, "Kunden er nu opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    		_instance = null;
    		_frame.dispose();
    	}
    	catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 2), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
		
    }
    
    private void addData(long phoneNo)
    {
    	try
    	{
    		Client client = _cliCtrl.getClientByPhone(phoneNo);
    		if(client != null)
    		{
    			txtName.setText(client.getName());
    			txtAddress.setText(client.getAddress());
    			City city = client.getCity();
    			txtZipCode.setText(String.valueOf(city.getZipCode()));
    			txtCity.setText(city.getCityName());
    			txtPhoneNo.setText(String.valueOf(client.getPhoneNo()));
    			txtEmail.setText(client.getEmail());
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "Der skete en fejl i hentning af klient information", "Fejl", JOptionPane.WARNING_MESSAGE);
    		}
    	}
    	catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 1), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
