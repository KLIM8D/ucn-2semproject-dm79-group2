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

public class CreateClientUI
{
    private static JFrame _frame;
    private static CreateClientUI _instance;
    private JPanel contentPane;

    //Controllers
    private ClientCtrl _cliCtrl;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CreateClientUI();

        return _frame;
    }

    private CreateClientUI()
    {
        createElements();
    }

    private void createElements()
    {
        _cliCtrl = new ClientCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/icons/48x48/new_client.png")));
        _frame.setTitle("Opret ny klient");
        _frame.setSize(new Dimension(509,186));
        _frame.setResizable(false);
        _frame.setVisible(true);
        Helper.centerOnScreen(_frame);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblClientName = new JLabel("Navn:");
        lblClientName.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientName.setBounds(5,7,120,15);
        contentPane.add(lblClientName);
        
        JLabel lblClientAddress = new JLabel("Adresse:");
        lblClientAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientAddress.setBounds(5,32,120,15);
        contentPane.add(lblClientAddress);
        
        JLabel lblClientZipCode = new JLabel("Postnummer:");
        lblClientZipCode.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientZipCode.setBounds(5,57,120,15);
        contentPane.add(lblClientZipCode);
        
        JLabel lblClientCity = new JLabel("By:");
        lblClientCity.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientCity.setBounds(232,57,36,15);
        contentPane.add(lblClientCity);
        
        JLabel lblClientNumber = new JLabel("Telefonnummer:");
        lblClientNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientNumber.setBounds(5,82,120,15);
        contentPane.add(lblClientNumber);
        
        JLabel lblClientEmail = new JLabel("Email:");
        lblClientEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblClientEmail.setBounds(232,84,55,15);
        contentPane.add(lblClientEmail);
                
        txtName = new JTextField();
        txtName.setBounds(142,5,355,19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(142,30,355,19);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);
        
        txtZipCode = new JTextField();
        txtZipCode.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtZipCode.getText().length() > 0)
                {
                	Helper.checkIfInt(txtZipCode);
                }
                if(txtZipCode.getText().length() > 2)
                {
                	lookupCity();
                }
            }
        });
        txtZipCode.setDocument(new JTextFieldLimit(4));
        txtZipCode.setBounds(142,55,50,19);
        contentPane.add(txtZipCode);
        txtZipCode.setColumns(10);
        
        txtCity = new JTextField();
        txtCity.setBounds(275,55,222,19);
        contentPane.add(txtCity);
        txtCity.setColumns(10);
        txtCity.setEditable(false);
        
        txtPhoneNo = new JTextField();
        txtPhoneNo.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtPhoneNo.getText().length() > 0)
                    Helper.checkIfLong(txtPhoneNo);
            }
        });
        txtPhoneNo.setBounds(142,80,75,19);
        txtPhoneNo.setDocument(new JTextFieldLimit(8));
        contentPane.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(275,80,222,19);
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
        btnCancel.setBounds(375,115,120,25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createClient();
            }
        });
        btnCreate.setBounds(246,115,120,25);
        contentPane.add(btnCreate);

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
            JOptionPane.showMessageDialog(null, Logging.handleException(error, 9), "Ukendt postnummer", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void createClient()
    {
    	try
    	{
        	if(_cliCtrl.getClientByPhone(Long.parseLong(txtPhoneNo.getText())) != null)
        	{
        		throw new Exception();
        	}	
    	}
    	catch (Exception e)
    	{
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 10), "Telefonnummeret eksisterer for en anden klient!", JOptionPane.WARNING_MESSAGE);
    	}
    	
        try
        {        	
            String name = txtName.getText();
            String address = txtAddress.getText();
            City city = _cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText()));
            long phoneNo = Long.parseLong(txtPhoneNo.getText());
            String eMail = txtEmail.getText();
            Calendar cal = Calendar.getInstance();
            Date creationDate = cal.getTime();
            Date editedDate =  cal.getTime();
            Client client = new Client(name, address, city, phoneNo, eMail, creationDate, editedDate);

            _cliCtrl.insertClient(client);

            JOptionPane.showMessageDialog(null, "Klienten er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            _instance = null;
            _frame.dispose();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 9), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}