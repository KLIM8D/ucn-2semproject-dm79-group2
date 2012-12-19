package views.dataentry;

import controllers.TaskCtrl;
import controllers.TimeSheetCtrl;
import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;
import utils.Logging;
import utils.UserSession;
import views.dataentry.CreateDataEntryUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class CreateDataEntryUI
{

    private static JFrame _frame;
    private static CreateDataEntryUI _instance;
    private JPanel contentPane;
    
	private JTextField txtRemark;
	private JTextField txtTitle;
	private JTextField txtDescription;
	
    private TimeSheetCtrl _tsCtrl;
    private TaskCtrl _taCtrl;
    private static TimeSheet ts; // timesheet needs to be imported from super, when we open this class.
    
    private JComboBox<Task> drpXTask;
    private JComboBox<Date> drpXStarted;
    private JComboBox<Date> drpXEnded;

	public static JFrame createWindow(TimeSheet timeSheet)
    {
		if(_instance == null)
		{
			_instance = new CreateDataEntryUI();
			ts = timeSheet;
		}
			
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
		_frame.setBounds(100, 100, 300, 307);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		_frame.setContentPane(contentPane);
		
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 239);
		contentPane.add(tabbedPane);
		
		JPanel dataEntry = new JPanel();
		tabbedPane.addTab("Registrering", null, dataEntry, null);
		dataEntry.setLayout(null);
		
		JLabel lblTask = new JLabel("Opgave");
		lblTask.setBounds(10, 11, 66, 14);
		dataEntry.add(lblTask);
		
		drpXTask = new JComboBox();
		drpXTask.setBounds(80, 8, 121, 20);
		dataEntry.add(drpXTask);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 268, 1);
		dataEntry.add(separator);
		
		JLabel lblStarted = new JLabel("P\u00E5begyndt");
		lblStarted.setBounds(10, 50, 66, 14);
		dataEntry.add(lblStarted);
		
		JLabel lblEnded = new JLabel("Afsluttet");
		lblEnded.setBounds(10, 75, 66, 14);
		dataEntry.add(lblEnded);
		
		JLabel lblRemark = new JLabel("Bem\u00E6rkning");
		lblRemark.setBounds(10, 100, 66, 14);
		dataEntry.add(lblRemark);
		
		txtRemark = new JTextField();
		txtRemark.setBounds(80, 100, 190, 100);
		dataEntry.add(txtRemark);
		txtRemark.setColumns(10);
		
		drpXStarted = new JComboBox();
		drpXStarted.setBounds(80, 47, 121, 20);
		dataEntry.add(drpXStarted);
		
		drpXEnded = new JComboBox();
		drpXEnded.setBounds(80, 72, 121, 20);
		dataEntry.add(drpXEnded);
		
		JPanel Task = new JPanel();
		tabbedPane.addTab("Opgave", null, Task, null);
		Task.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 57, 14);
		Task.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Beskrivelse");
		lblDescription.setBounds(10, 45, 65, 14);
		Task.add(lblDescription);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(72, 11, 201, 20);
		Task.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(72, 42, 201, 136);
		Task.add(txtDescription);
		txtDescription.setColumns(10);
		
		JButton btnCreateTask = new JButton("Opret ny Opgave");
        btnCreateTask.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createTask();
            }
        });
		btnCreateTask.setBounds(156, 183, 117, 23);
		Task.add(btnCreateTask);
		
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		btnCancel.setBounds(185, 241, 89, 23);
		contentPane.add(btnCancel);
		
        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createDataEntry();
            }
        });
		btnCreate.setBounds(86, 241, 89, 23);
		contentPane.add(btnCreate);
	}
	
	public void createDataEntry()
	{
        try
        {
            Task task = (Task)drpXTask.getSelectedItem();
            User user = UserSession.getLoggedInUser();
            Date startDate = (Date)drpXStarted.getSelectedItem();
            Date endDate = (Date)drpXEnded.getSelectedItem();
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
	
	public void createTask()
	{
        try
        {
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            Task task = new Task(title, description);

            _taCtrl.insertTask(task);

            JOptionPane.showMessageDialog(null, "Opgavetypen er oprettet", "Information!", JOptionPane.INFORMATION_MESSAGE);
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