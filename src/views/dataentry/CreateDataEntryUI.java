package views.dataentry;

import controllers.TimeSheetCtrl;
import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;
import utils.Logging;
import utils.UserSession;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class CreateDataEntryUI extends JFrame
{

    private static JFrame _frame;
    private static CreateDataEntryUI _instance;
    private JPanel contentPane;
    
    private TimeSheetCtrl _tsCtrl;
    private TimeSheet ts; // timesheet needs to be imported from super, when we open this class.
    
    private JComboBox<Task> drpAssignment;
    private JComboBox<Date> drpStartDate;
    private JComboBox<Date> drpEndDate;
	private JTextField txtRemark;

	public static JFrame createWindow()
    {
		if(_instance == null)
			_instance = new CreateDataEntryUI();

		return _frame;
    }

    private CreateDataEntryUI()
    {
    	createElements();	
    }

	public void createElements() 
	{
		
		_frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Ny Registrering");
		_frame.setBounds(100, 100, 450, 300);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		_frame.setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabRegistration = new JPanel();
		tabbedPane.addTab("Registrering", null, tabRegistration, null);
        tabRegistration.setLayout(null);
		
		JLabel lblAssignment = new JLabel("Opgave");
		lblAssignment.setBounds(12,12,120,15);
        tabRegistration.add(lblAssignment);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 400, 1);
        tabRegistration.add(separator);
		
		JLabel lblCreatedDate = new JLabel("P\u00E5begyndt");
		lblCreatedDate.setBounds(12,52,120,15);
        tabRegistration.add(lblCreatedDate);
		
		JLabel lblEndDate = new JLabel("Afsluttet");
		lblEndDate.setBounds(12,82,120,15);
        tabRegistration.add(lblEndDate);
		
		JLabel lblRemark = new JLabel("Bem\u00E6rkning");
		lblRemark.setBounds(12,112,120,15);
        tabRegistration.add(lblRemark);
		
		drpAssignment.setBounds(80, 10, 100, 20);
        tabRegistration.add(drpAssignment);
		
		drpStartDate.setBounds(80, 50, 100, 20);
        tabRegistration.add(drpStartDate);
		
		drpEndDate.setBounds(80, 80, 100, 20);
        tabRegistration.add(drpEndDate);
		
		txtRemark = new JTextField();
		txtRemark.setBounds(80,120,230,60);
        tabRegistration.add(txtRemark);
		txtRemark.setColumns(10);
		
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
                createDataEntry();
            }
        });
        btnCreate.setBounds(246,195,117,25);
        contentPane.add(btnCreate);

		
		JPanel tabAssignment = new JPanel();
		tabbedPane.addTab("Opgave", null, tabAssignment, null);
	}
	
	public void createDataEntry()
	{
        try
        {
            Task task = (Task)drpAssignment.getSelectedItem();
            User user = UserSession.getLoggedInUser();
            Date startDate = (Date)drpStartDate.getSelectedItem();
            Date endDate = (Date)drpEndDate.getSelectedItem();
            String entryRemark = txtRemark.getText();
            Calendar cal = Calendar.getInstance();
            Date creationDate = cal.getTime();
            Date editedDate =  cal.getTime();
            DataEntry dataEntry = new DataEntry(task, user, startDate, endDate, entryRemark, creationDate, editedDate);

            _tsCtrl.addDataEntry(ts, dataEntry);

            JOptionPane.showMessageDialog(null, "Registreringen er oprettet", "Information!", JOptionPane.INFORMATION_MESSAGE);
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
