/**
 * filename    : LoginUI.java
 * created     : Dec 23, 2012 (4:03:24 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package views;

import controllers.UserCtrl;
import utils.Helper;
import utils.Logging;
import utils.UserSession;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI
{
	private static JFrame _frame;
	private JTextField txtUserName;
	private JPasswordField pfdUserPassword;
    private UserCtrl _userCtrl;
	
	public LoginUI()
	{
        _userCtrl = new UserCtrl();
		_frame = new JFrame("Time-sag Styring, Login");
		_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginUI.class.getResource("/icons/48x48/app.png")));
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(new Dimension(400,135));
		_frame.setResizable(false);
        _frame.setVisible(true);
		Helper.centerOnScreen(_frame);
		
		JPanel pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5,5,5,5));
		pnlContent.setLayout(null);
		_frame.setContentPane(pnlContent);
		
		JLabel lblLoginIcon = new JLabel();
		lblLoginIcon.setIcon(new ImageIcon(LoginUI.class.getResource("/icons/48x48/app_login.png")));
		lblLoginIcon.setBounds(12,8,48,48);
		pnlContent.add(lblLoginIcon);
		
		JLabel lblUserName = new JLabel("Brugernavn:");
		lblUserName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUserName.setBounds(75,12,105,15);
		pnlContent.add(lblUserName);
		
		JLabel lblUserPassword = new JLabel("Adgangskode:");
		lblUserPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUserPassword.setBounds(75,35,105,15);
		pnlContent.add(lblUserPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(185,10,200,19);
		pnlContent.add(txtUserName);
		
		pfdUserPassword = new JPasswordField();
		pfdUserPassword.setBounds(185,34,200,19);
		pnlContent.add(pfdUserPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				systemLogin();
			}
		});
		btnLogin.setBounds(123,65,125,25);
		pnlContent.add(btnLogin);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				System.exit(0);
			}
		});
		btnCancel.setBounds(260,65,125,25);
		pnlContent.add(btnCancel);
	}
	
	private void systemLogin()
	{
		try
        {
			if(txtUserName.getText().trim().length() != 0)
			{
				if(pfdUserPassword.getPassword().length != 0)
				{
					String userName = txtUserName.getText();
                    String userPassword = "";
                    for(char c : pfdUserPassword.getPassword())
                        userPassword += c;

					boolean success = _userCtrl.validateUserLogin(userName, userPassword);
					if(success)
					{
                        UserSession.setLoggedInUser(_userCtrl.getUserByName(userName));
                        new views.SystemUI().setVisible(true);
                        _frame.dispose();
					}
					else
					{
                        //wrong password, unknown user name (case 8)
                        JOptionPane.showMessageDialog(null, Logging.messages(8), "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					//no entry in password field (case 7)
                    JOptionPane.showMessageDialog(null, Logging.messages(7), "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				//no entry in user name field (case 06)
                JOptionPane.showMessageDialog(null, Logging.messages(6), "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex,5), "Fejl", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}