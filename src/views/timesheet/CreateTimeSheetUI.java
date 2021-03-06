package views.timesheet;

import controllers.ClientCtrl;
import controllers.TimeSheetCtrl;
import controllers.UserCtrl;
import controllers.UserPermissionCtrl;
import models.Client;
import models.TimeSheet;
import models.User;
import models.UserPermission;
import utils.Helper;
import utils.Logging;
import utils.UserSession;
import views.SystemUI;
import views.dataentry.CreateDataEntryUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateTimeSheetUI
{
    private static JFrame _frame;
    private static CreateTimeSheetUI _instance;
	private JPanel contentPane;
	private JList<String> lstGroup;
	private JList<String> lstUser;
	private JComboBox<String> drpClients;
	private DefaultComboBoxModel<String> mdlClient;
	private JPanel pnlTimeSheet;
	private JPanel pnlPermission;
	private JTextPane txtNotes;
    private JTextField txtCaseId;
    
	// Controllers
	private ClientCtrl _clientCtrl;
	private UserCtrl _userCtrl;
	private UserPermissionCtrl _userPermissionCtrl;

    public static CreateTimeSheetUI getInstance()
    { return _instance; }
	
    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CreateTimeSheetUI();

        return _frame;
    }
	
	private CreateTimeSheetUI()
	{
        _clientCtrl = new ClientCtrl();
        _userCtrl = new UserCtrl();
        _userPermissionCtrl = new UserPermissionCtrl();
        
		_frame = new JFrame();
		_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/icons/48x48/new_timesheet.png")));
		_frame.setTitle("Ny time-sag");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setSize(new Dimension(415,397));
        _frame.setVisible(true);
        _frame.setResizable(false);
        Helper.centerOnScreen(_frame);
		_frame.toFront();
		_frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		contentPane = new JPanel();
        contentPane.setBounds(0, 0, 415, 397);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);

		_frame.setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10,55,392,262);
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(tabbedPane);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		btnCancel.setBounds(276,329,125,23);
		contentPane.add(btnCancel);
		
		JButton btnNext = new JButton("N\u00E6ste (1/2)");
		btnNext.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(txtCaseId.getSelectionEnd() != 0)
            	{
            		createTimeSheet();
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "Der skal indtastes sags nummer.", "Fejl!", JOptionPane.WARNING_MESSAGE);
            	}
            }
        });
		btnNext.setBounds(139,329,125,23);
		contentPane.add(btnNext);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10,45,392,2);
		contentPane.add(separator);
		
		JLabel lblKlient = new JLabel("Klient");
		lblKlient.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblKlient.setBounds(10,12,53,23);
		contentPane.add(lblKlient);
		
		drpClients = new JComboBox<String>();
		drpClients.setBounds(55, 12, 347, 22);
		drpClients.setFont(new Font("Dialog", Font.PLAIN, 12));
		drpClients.setBackground(Color.WHITE);
		contentPane.add(drpClients);
		mdlClient = new DefaultComboBoxModel<String>(populateClientList());
        drpClients.setModel(mdlClient);

		// pane1 start
        pnlTimeSheet = new JPanel();
        pnlTimeSheet.setLayout(null);
		
		JLabel lblCaseId = new JLabel("Sags Nr.:");
		lblCaseId.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCaseId.setBounds(5,11,60,20);
		pnlTimeSheet.add(lblCaseId);
		
		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNote.setBounds(5,43,60,20);
		pnlTimeSheet.add(lblNote);
		
		// for display ClientId
		txtCaseId = new JTextField();
		txtCaseId.setBounds(67,12,310,20);
		pnlTimeSheet.add(txtCaseId);
		
		// for notes
		txtNotes = new JTextPane();
		txtNotes.setBounds(67,44,310,182);
		pnlTimeSheet.add(txtNotes);
        txtNotes.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		tabbedPane.add("Time-Sag", pnlTimeSheet);
		
		pnlPermission = new JPanel();
		pnlPermission.setLayout(null);
				
		tabbedPane.add("Rettigheder", pnlPermission);
		
		JPanel pnlGroup = new JPanel();
		pnlGroup.setBounds(10,11,179,212);
		pnlGroup.setBorder(BorderFactory.createTitledBorder(null, "Gruppe", 0, 0, new Font("Dialog", Font.PLAIN, 12)));
		pnlGroup.setFont(new Font("Dialog", Font.PLAIN, 12));
		pnlPermission.add(pnlGroup);
		pnlGroup.setLayout(null);
		
		lstGroup = new JList<String>();		
		populateUserGroups();
		lstGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstGroup.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstGroup.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstGroup.setBounds(13,17,153,182);
		JScrollPane groupListScroll = new JScrollPane(lstGroup);
		pnlGroup.add(groupListScroll);
		pnlGroup.add(lstGroup);
		
		JPanel pnlUser = new JPanel();
		pnlUser.setBorder(BorderFactory.createTitledBorder(null, "Bruger", 0, 0, new Font("Dialog", Font.PLAIN, 12)));
		pnlUser.setBounds(197,11,179,212);
		pnlPermission.add(pnlUser);
		pnlUser.setLayout(null);
		
		lstUser = new JList<String>();		
		populateUserList();
		lstUser.setFont(new Font("Dialog", Font.PLAIN, 12));
		lstUser.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lstUser.setBounds(13,17,153,182);
		JScrollPane userListScroll = new JScrollPane(lstUser);
		pnlUser.add(userListScroll);
		pnlUser.add(lstUser);
	}

	public void createTimeSheet()
	{
		String caseId = txtCaseId.getText();
        User user = UserSession.getLoggedInUser();
        long clientPhone = Long.parseLong(drpClients.getSelectedItem().toString().substring(drpClients.getSelectedItem().toString().indexOf("(") + 1,
                drpClients.getSelectedItem().toString().indexOf(")")));

        Client client = null;
        try
        {
            client = _clientCtrl.getClientByPhone(clientPhone);  
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
        }
        String note = txtNotes.getText();
        Calendar cal = Calendar.getInstance();
        Date creationDate = cal.getTime();
        Date editedDate =  cal.getTime();
        
		TimeSheet timeSheet = new TimeSheet(caseId, user, client, note, creationDate, editedDate);
		CreateDataEntryUI.createWindowForNewCase(timeSheet);
	}

    public void createPermissions(TimeSheet sheet)
    {
        try
        {
            TimeSheetCtrl timeSheetCtrl = new TimeSheetCtrl();
            for(String roleName : lstGroup.getSelectedValuesList())
            {
                timeSheetCtrl.insertPermission(sheet, _userPermissionCtrl.getPermissionByTitle(roleName));
            }
            for(String userName : lstUser.getSelectedValuesList())
            {
                timeSheetCtrl.insertPermission(sheet, _userCtrl.getUserByName(userName));
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
        }


    }
	
	private String[] populateClientList()
	{
		try
		{
			return new PopulateClientList().doInBackground();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	private void populateUserList()
	{
		try
		{
			new PopulateUserList().execute();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void populateUserGroups()
	{
		try
		{
			new PopulateGroupList().execute();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	class PopulateClientList extends SwingWorker<String[], Integer>
	{
		@Override
		protected String[] doInBackground() throws Exception
		{
			ArrayList<Client> clients;
			try
			{
				clients = _clientCtrl.getAllClients();
				String[] clientNames = new String[clients.size()];
				for(int i = 0; i < clients.size(); i++)
					clientNames[i] = clients.get(i).getName() + " (" + clients.get(i).getPhoneNo() +")";
				
				return clientNames;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
			}
			
			return null;
		}
	}

    class PopulateUserList extends SwingWorker<Integer, Integer>
    {
        private DefaultListModel<String> userNames;

        @Override
        protected Integer doInBackground() throws Exception
        {
            ArrayList<User> userList;
            try
            {
                userList = _userCtrl.getAllUsers();
                userNames = new DefaultListModel<String>();
                for(int i = 0; i < userList.size(); i++)
                    userNames.add(i, userList.get(i).getUserName());

                return 1;
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

        @Override
        protected void done()
        {
            lstUser.setModel(userNames);
        }
    }

    class PopulateGroupList extends SwingWorker<Integer, Integer>
    {
        private DefaultListModel<String> groupNames;

        @Override
        protected Integer doInBackground() throws Exception
        {
            ArrayList<UserPermission> groupList;
            try
            {
                groupList = _userPermissionCtrl.getAllRoles();
                groupNames = new DefaultListModel<String>();
                for(int i = 0; i < groupList.size(); i++)
                    groupNames.add(i, groupList.get(i).getUserRole());

                return 1;
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl!", JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

        @Override
        protected void done()
        {
            lstGroup.setModel(groupNames);
        }
    }
}