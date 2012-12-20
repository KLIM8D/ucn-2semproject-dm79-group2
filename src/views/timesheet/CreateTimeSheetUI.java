package views.timesheet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import controllers.ClientCtrl;
import models.Client;
import models.TimeSheet;
import controllers.UserCtrl;
import models.User;
import controllers.UserPermissionCtrl;
import models.UserPermission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import views.SystemUI;
import views.dataentry.CreateDataEntryUI;
import utils.Logging;
import utils.UserSession;
import javax.swing.JList;

public class CreateTimeSheetUI
{
    private static JFrame _frame;
    private static CreateTimeSheetUI _instance;
	private JPanel _contentPane;
	
	private JList<String> lstGroup;
	private JList<String> lstUser;

	
	private JComboBox<String> _drpClients;
	private DefaultComboBoxModel<String> _model;
	
	private JPanel _timeSheetPanel;
	private JPanel _permissionPanel;
	private JTextPane txtNotes;
    private JTextField txtCaseId;
    
	// Controllers
	private ClientCtrl _clientCtrl;
	private UserCtrl _userCtrl;
	private UserPermissionCtrl _userPermissionCtrl;
	
    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CreateTimeSheetUI();

        return _frame;
    }
	/**
	 * Create the frame.
	 */
	private CreateTimeSheetUI() 
	{	
		createElements();	
	}
	
	
	public void createElements()
	{
        _clientCtrl = new ClientCtrl();
        _userCtrl = new UserCtrl();
        _userPermissionCtrl = new UserPermissionCtrl();
        
		_frame = new JFrame();
		_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/new_timesheet.png")));
		_frame.setTitle("Ny Registrering");
		_frame.setVisible(true);
		_frame.setResizable(false);
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(100, 100, 415, 392);
		_contentPane = new JPanel();
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		_frame.setContentPane(_contentPane);
		_contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 392, 262);
		_contentPane.add(tabbedPane);
		
		JButton btnNewButton = new JButton("Annuller");
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		btnNewButton.setBounds(302, 331, 100, 23);
		_contentPane.add(btnNewButton);
		
		JButton btnNewNste = new JButton("N\u00E6ste (1/2)");
		btnNewNste.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createTimeSheet();
            }
        });
		btnNewNste.setBounds(195, 331, 100, 23);
		_contentPane.add(btnNewNste);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 392, 2);
		_contentPane.add(separator);
		
		JLabel lblKlient = new JLabel("Klient");
		lblKlient.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblKlient.setBounds(15, 11, 53, 23);
		_contentPane.add(lblKlient);
		
		_drpClients = new JComboBox<String>();
		_drpClients.setBounds(55, 11, 347, 22);
		_contentPane.add(_drpClients);
		_model = new DefaultComboBoxModel<String>(addClients());
        _drpClients.setModel(_model);

		//addClients();
		// addUsers();

		// pane1 start
		_timeSheetPanel = new JPanel();
		_timeSheetPanel.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblID.setBounds(5, 5, 60, 23);
		_timeSheetPanel.add(lblID);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNote.setBounds(5, 30, 60, 23);
		_timeSheetPanel.add(lblNote);
		
		// for display ClientId
		txtCaseId = new JTextField();
		txtCaseId.setBounds(45, 5, 332, 20);
		_timeSheetPanel.add(txtCaseId);
		
		// for notes
		txtNotes = new JTextPane();
		txtNotes.setBounds(45, 31, 332, 195);
		_timeSheetPanel.add(txtNotes);
        txtNotes.setBorder(new LineBorder(Color.LIGHT_GRAY));
		//_txtNotes.setColumns(10);
		
		tabbedPane.add("Time-Sag", _timeSheetPanel);
		
		_permissionPanel = new JPanel();
		_permissionPanel.setLayout(null);
		//_model = new DefaultComboBoxModel<String>();
		//_drpPermissions.setModel(_model);
		//addPermissions();
				
		tabbedPane.add("Rettigheder", _permissionPanel);
		
		JPanel groupPanel = new JPanel();
		groupPanel.setBounds(10, 11, 179, 212);
		groupPanel.setBorder(BorderFactory.createTitledBorder("Gruppe"));
		_permissionPanel.add(groupPanel);
		groupPanel.setLayout(null);
		
		lstGroup = new JList<String>();		
		lstGroup.setListData(populateGroupList());
		lstGroup.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstGroup.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstGroup.setBounds(13, 17, 153, 182);
		//JScrollPane groupListScroll = new JScrollPane(lstGroup);
		//groupPanel.add(groupListScroll);
		groupPanel.add(lstGroup);
		
		JPanel userPanel = new JPanel();
		userPanel.setBorder(BorderFactory.createTitledBorder("Bruger"));
		userPanel.setBounds(197, 11, 179, 212);
		_permissionPanel.add(userPanel);
		userPanel.setLayout(null);
		
		lstUser = new JList<String>();		
		lstUser.setListData(populateUserList());
		lstUser.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstUser.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstUser.setBounds(13, 17, 153, 182);
		JScrollPane userListScroll = new JScrollPane(lstUser);
		groupPanel.add(userListScroll);
		userPanel.add(lstUser);
	}
	
	
	public String[] addClients()
	{
		ArrayList<Client> clients;
		try
		{
			clients = _clientCtrl.getAllClients();
			String[] clientNames = new String[clients.size()];
			for (int index = 0; index < clients.size(); index++)
				clientNames[index] = clients.get(index).getName() + " (" + clients.get(index).getPhoneNo() +")";

			return clientNames;
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
	
	public void createTimeSheet()
	{
		String caseId = txtCaseId.getSelectedText();
        User user = UserSession.getLoggedInUser();
        long clientPhone = Long.parseLong(_drpClients.getSelectedItem().toString().substring(_drpClients.getSelectedItem().toString().indexOf("(") + 1,
                _drpClients.getSelectedItem().toString().indexOf(")")));

        Client client = null;
        try
        {
            client = _clientCtrl.getClientByPhone(clientPhone);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String note = txtNotes.getText();
        Calendar cal = Calendar.getInstance();
        Date creationDate = cal.getTime();
        Date editedDate =  cal.getTime();
        
		TimeSheet ts = new TimeSheet(caseId, user, client, note, creationDate, editedDate);
		CreateDataEntryUI.createWindow(ts);
	}
	
	public String[] populateGroupList()
	{
        try
        {
            return new PopulateGroupList().doInBackground();
        }
        catch(Exception ex)
        {
        	JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
        }

        return null;
	}
	
	public String[] populateUserList()
	{
        try
        {
            return new PopulateUserList().doInBackground();
        }
        catch(Exception ex)
        {
        	JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
        }

        return null;
	}
	
	class PopulateUserList extends SwingWorker<String[], Integer>
    {
    	@Override
        protected String[] doInBackground() throws Exception
        {
            ArrayList<User> userList;
            try
            {
                userList = _userCtrl.getAllUsers();
                String[] userNames = new String[userList.size()];
                for(int i = 0; i < userList.size(); i++)
                    userNames[i] = userList.get(i).getFirstName() + " " + userList.get(i).getLastName();

                return userNames;
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }
    }
	
	class PopulateGroupList extends SwingWorker<String[], Integer>
    {
    	@Override
        protected String[] doInBackground() throws Exception
        {
            ArrayList<UserPermission> groupList;
            try
            {
                groupList = _userPermissionCtrl.getAllRoles();
                String[] groupNames = new String[groupList.size()];
                for(int i = 0; i < groupList.size(); i++)
                    groupNames[i] = groupList.get(i).getUserRole();

                return groupNames;
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }
    }
}