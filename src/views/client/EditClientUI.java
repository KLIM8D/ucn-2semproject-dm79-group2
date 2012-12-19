package views.client;

import controllers.ClientCtrl;
import models.City;
import models.Client;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;
import views.SystemUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class EditClientUI
{
    private static JFrame _frame;
    private static EditClientUI _instance;
    private JPanel contentPane;

    //Controllers
    private ClientCtrl _cliCtrl;
    private Client _client;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;

    public static JFrame createWindow(Client data)
    {
        if(_instance == null)
            _instance = new EditClientUI(data);

        return _frame;
    }

    private EditClientUI(Client data)
    {
    	_client = data;
        createElements();
    }

    private void createElements()
    {
    	_cliCtrl = new ClientCtrl();

        _frame = new JFrame();
        _frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/new_client.png")));
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setTitle("Rediger kunde");
        _frame.setBounds(0,0,509,175);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblClientName = new JLabel("Navn:");
        lblClientName.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientName.setBounds(5, 7, 120, 15);
        contentPane.add(lblClientName);
        
        JLabel lblClientAddress = new JLabel("Adresse:");
        lblClientAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientAddress.setBounds(5, 32, 120, 15);
        contentPane.add(lblClientAddress);
        
        JLabel lblClientZipCode = new JLabel("Postnummer:");
        lblClientZipCode.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientZipCode.setBounds(5, 57, 120, 15);
        contentPane.add(lblClientZipCode);
        
        JLabel lblClientCity = new JLabel("By");
        lblClientCity.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientCity.setBounds(222, 57, 120, 15);
        contentPane.add(lblClientCity);
        
        JLabel lblClientNumber = new JLabel("Telefonnummer:");
        lblClientNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientNumber.setBounds(5, 82, 120, 15);
        contentPane.add(lblClientNumber);
        
        JLabel lblClientEmail = new JLabel("Email:");
        lblClientEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientEmail.setBounds(244, 82, 120, 15);
        contentPane.add(lblClientEmail);
                
        txtName = new JTextField();
        txtName.setBounds(142, 5, 355, 19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(142, 30, 355, 19);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);
        
        txtZipCode = new JTextField();
        txtZipCode.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if (txtZipCode.getText().length() > 0)
                {
                    Helper.checkIfInt(txtZipCode);
                }
                
                if(txtZipCode.getText().length() > 3)
                {
                	lookupCity();
                }
            }
        });

        txtZipCode.setDocument(new JTextFieldLimit(5));
        txtZipCode.setBounds(142, 55, 50, 19);
        contentPane.add(txtZipCode);
        txtZipCode.setColumns(10);
        
        txtCity = new JTextField();
        txtCity.setBounds(265, 55, 232, 19);
        contentPane.add(txtCity);
        txtCity.setColumns(10);
        txtCity.setEditable(false);
        
        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(142, 80, 75, 19);
        txtPhoneNo.setDocument(new JTextFieldLimit(9));
        contentPane.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(305, 80, 192, 19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(375, 115, 120, 25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opdater");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateClient();
            }
        });
        btnCreate.setBounds(246,115,120,25);
        contentPane.add(btnCreate);
        
        addData(_client);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }
    
    private void lookupCity()
    {
    	try
        {
        	if(_cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText())).getCityName().equals(""))
        	{
        		throw new Exception();
        	}
            txtCity.setText(_cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText())).getCityName());
        }
        catch (Exception error)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(error, 1), "Ukendt postnummer", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateClient()
    {
    	try
    	{
    		if(Long.parseLong(txtPhoneNo.getText()) != _client.getPhoneNo())
    		{
    			if(_cliCtrl.getClientByPhone(Long.parseLong(txtPhoneNo.getText())) != null)
    			{
    				throw new Exception();
    			}
    		}
    	}
    	catch (Exception e)
    	{
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 1), "Telefonnummeret eksisterer for en anden klient!", JOptionPane.ERROR_MESSAGE);
    	}
    	try
    	{    		
    		String name = txtName.getText();
    		String address = txtAddress.getText();
    		City city = _cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText()));
    		long phoneNo = Long.parseLong(txtPhoneNo.getText());
    		String eMail = txtEmail.getText();
            Calendar cal = Calendar.getInstance();
            Date creationDate = _client.getCreationDate();
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
    
    private void addData(Client client)
    {
    	try
    	{
    		txtName.setText(client.getName());
   			txtAddress.setText(client.getAddress());
   			City city = client.getCity();
   			txtZipCode.setText(String.valueOf(city.getZipCode()));
   			txtCity.setText(city.getCityName());
    		txtPhoneNo.setText(String.valueOf(client.getPhoneNo()));  
    		txtEmail.setText(client.getEmail());
    	}
    	catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 1), "Fejl", JOptionPane.ERROR_MESSAGE);
        }
    }
}
