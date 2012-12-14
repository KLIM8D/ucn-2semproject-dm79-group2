package views.client;

import controllers.ClientCtrl;
import db.DataAccess;
import models.City;
import models.Client;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
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
    private JTextField txtCountry;
    private JTextField txtDiscount;
    private Checkbox chkIsBusiness;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CreateClientUI();

        return _frame;
    }

    public CreateClientUI()
    {
        createElements();
    }

    private void createElements()
    {
        _cliCtrl = new ClientCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Opret ny klient");
        _frame.setBounds(0,0,509,270);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblClientName = new JLabel("Navn:");
        lblClientName.setBounds(12,12,120,15);
        contentPane.add(lblClientName);
        
        JLabel lblClientAddress = new JLabel("Adresse:");
        lblClientAddress.setBounds(12,37,120,15);
        contentPane.add(lblClientAddress);
        
        JLabel lblClientZipCode = new JLabel("Postnummer:");
        lblClientZipCode.setBounds(12,62,120,15);
        contentPane.add(lblClientZipCode);
        
        JLabel lblClientCity = new JLabel("By");
        lblClientCity.setBounds(222,62,120,15);
        contentPane.add(lblClientCity);
        
        JLabel lblClientNumber = new JLabel("Telefonnummer:");
        lblClientNumber.setBounds(12,87,120,15);
        contentPane.add(lblClientNumber);
        
        JLabel lblClientEmail = new JLabel("Email:");
        lblClientEmail.setBounds(244,87,120,15);
        contentPane.add(lblClientEmail);
                
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
                {
                	Helper.checkIfInt(txtZipCode);
                	
                	if(txtZipCode.getCursor() == null)
                	{
                		try
                		{
                			txtCity.setText(_cliCtrl.getCityByZipCode(Integer.parseInt(txtZipCode.getText())).getCityName());
                		}
                        catch (Exception error)
                        {
                            error.printStackTrace();
                            JOptionPane.showMessageDialog(null, Logging.handleException(error, 1), "Forkert postnummer", JOptionPane.WARNING_MESSAGE);
                        }
                	}
                }
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
        txtPhoneNo.setBounds(142,85,75,19);
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

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createClient();
            }
        });
        btnCreate.setBounds(246,195,117,25);
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

    private void createClient()
    {
        try
        {
            @SuppressWarnings("unused")
			DataAccess da = DataAccess.getInstance();
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
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 1), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}