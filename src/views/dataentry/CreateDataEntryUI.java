package views.dataentry;

import controllers.TaskCtrl;
import controllers.TimeSheetCtrl;
import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;
import utils.Helper;
import utils.Logging;
import utils.UserSession;
import views.SystemUI;
import views.shared.DateTimePanel;
import views.timesheet.CreateTimeSheetUI;

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

public class CreateDataEntryUI
{

    private static JFrame _frame;
    private static CreateDataEntryUI _instance;
    private static boolean _existingCase; 
    private JPanel contentPane;
    
	private JTextPane txtRemark;
	private JTextField txtTitle;
	private JTextPane txtDesc;
	
    private TimeSheetCtrl _timesheetCtrl;
    private TaskCtrl _taskCtrl;
    private static TimeSheet _timeSheet; // timesheet needs to be imported from super, when we open this class.
    
    private JComboBox<String> drpTask;
    private DateTimePanel startDate;
    private DateTimePanel endDate;
	private DefaultComboBoxModel<String> _model;

    private JPanel pnlTask;

	public static JFrame createWindowForNewCase(TimeSheet timeSheet)
    {
		if(_instance == null)
		{
            _timeSheet = timeSheet;
            _instance = new CreateDataEntryUI();
		}
			
		return _frame;
    }
	
	public static JFrame createWindowForExistingCase(TimeSheet timeSheet)
	{
		if(_instance == null)
		{
			_existingCase = true;
			_timeSheet = timeSheet;
			_instance = new CreateDataEntryUI();
		}
		
		return _frame;
	}

	private CreateDataEntryUI() 
	{
		_timesheetCtrl = new TimeSheetCtrl();
		_taskCtrl = new TaskCtrl();
		
		_frame = new JFrame();
		_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/icons/48x48/new_dataentry.png")));
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _frame.setTitle("Ny Registrering");
		_frame.setSize(new Dimension(338,335));
        Helper.centerOnScreen(_frame);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);
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
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(null);
		_frame.setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10,10,315,245);
		contentPane.add(tabbedPane);
		
		JPanel pnlDataEntry = new JPanel();
		pnlDataEntry.setFont(new Font("Dialog", Font.PLAIN, 12));
		tabbedPane.addTab("Registrering", null, pnlDataEntry, null);
		pnlDataEntry.setLayout(null);
		
		JLabel lblTask = new JLabel("Opgave:");
		lblTask.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTask.setBounds(10,12,70,16);
		pnlDataEntry.add(lblTask);
		
		drpTask = new JComboBox<String>();
		drpTask.setFont(new Font("Dialog", Font.PLAIN, 12));
		drpTask.setBackground(Color.WHITE);
		drpTask.setBounds(95,12,205,22);
		pnlDataEntry.add(drpTask);
		_model = new DefaultComboBoxModel<String>(populateTaskList());
        drpTask.setModel(_model);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10,43,290,2);
		pnlDataEntry.add(separator);
		
		JLabel lblStarted = new JLabel("P\u00E5begyndt:");
		lblStarted.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStarted.setBounds(10,57,86,16);
		pnlDataEntry.add(lblStarted);
		
		JLabel lblEnded = new JLabel("Afsluttet:");
		lblEnded.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblEnded.setBounds(10,85,70,16);
		pnlDataEntry.add(lblEnded);
		
		JLabel lblRemark = new JLabel("Bem\u00E6rkning:");
		lblRemark.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRemark.setBounds(10,114,99,16);
		pnlDataEntry.add(lblRemark);

        startDate = new DateTimePanel();
        JPanel pnlStartDate = startDate.buildDateTimePanel(new Date());
        pnlStartDate.setBounds(90,49,215,25);
		pnlDataEntry.add(pnlStartDate);

        endDate = new DateTimePanel();
        JPanel pnlEndDate = endDate.buildDateTimePanel(new Date());
        pnlEndDate.setBounds(90,78,215,25);
        pnlDataEntry.add(pnlEndDate);
        
        txtRemark = new JTextPane();
		txtRemark.setBounds(95,114,205,95);
		txtRemark.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlDataEntry.add(txtRemark);
		
		pnlTask = new JPanel();
		pnlTask.setFont(new Font("Dialog", Font.PLAIN, 12));
		tabbedPane.addTab("Opgave", null, pnlTask, null);
		pnlTask.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTitle.setBounds(10,14,57,14);
		pnlTask.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDescription.setBounds(10,42,84,14);
		pnlTask.add(lblDescription);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(95,12,205,20);
		pnlTask.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtDesc = new JTextPane();
		txtDesc.setBounds(95,43,205,132);
		txtDesc.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlTask.add(txtDesc);
		
		JButton btnCreateTask = new JButton("Opret ny Opgave");
        btnCreateTask.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(txtTitle.getSelectionEnd() != 0 & txtDesc.getSelectionEnd() != 0)
            	{
            		createTask();
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "Begge felter skal benyttes.", "Fejl!", JOptionPane.WARNING_MESSAGE);
            	}
            }
        });
		btnCreateTask.setBounds(95,186,205,23);
		pnlTask.add(btnCreateTask);
		
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		btnCancel.setBounds(200,267,125,23);
		contentPane.add(btnCancel);
		
        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createDataEntry();
            }
        });
		btnCreate.setBounds(63,267,125,23);
		contentPane.add(btnCreate);
	}
	
	private String[] populateTaskList()
	{
		try
		{
			return new PopulateTaskList().doInBackground();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(ex, 99), "Fejl!", JOptionPane.ERROR_MESSAGE);
		}

        return null;
	}
	
	private void createDataEntry()
	{
		new CreateDataEntry().execute();
	}
	
	private void createTask()
	{
		new CreateTask().execute();
	}
	
	class PopulateTaskList extends SwingWorker<String[], Integer>
	{
		@Override
		protected String[] doInBackground() throws Exception
		{
			ArrayList<Task> tasks;
			try
			{
				tasks = _taskCtrl.getAllTasks();
				String[] taskTitles = new String[tasks.size()];
				for(int i = 0; i < tasks.size(); i++)
					taskTitles[i] = tasks.get(i).getTitle();
				
				return taskTitles;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
			}
			
			return null;
		}
	}
	
	class CreateDataEntry extends SwingWorker<Integer, Integer>
	{
		@Override
		protected Integer doInBackground() throws Exception
		{
			try
			{ 
				Task task = _taskCtrl.getTaskByTitle((String) drpTask.getSelectedItem());
				User user = UserSession.getLoggedInUser();
		        Date chosenStartDate = startDate.getDateChooser().getDate();
		        Date chosenEndDate = endDate.getDateChooser().getDate();
		        String entryRemark = txtRemark.getText();
		        Calendar cal = Calendar.getInstance();
		        Date creationDate = cal.getTime();
		        Date editedDate =  cal.getTime();
		        DataEntry dataEntry = new DataEntry(task, user, chosenStartDate, chosenEndDate, entryRemark, creationDate, editedDate);
		        
		        if(_existingCase != true)
		        	_timesheetCtrl.insertTimeSheet(_timeSheet);
		        
		        _timesheetCtrl.addDataEntry(_timesheetCtrl.getTimeSheetByCaseId(_timeSheet.getCaseId()), dataEntry);

		        JOptionPane.showMessageDialog(null, "Indtastningerne er blevet oprettet.", "Information!", JOptionPane.INFORMATION_MESSAGE);
		        _instance = null;
		        _frame.dispose();
		        
		        if(_existingCase != true)
		        	CreateTimeSheetUI.createWindow().dispose();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
			}
			
			return null;
		}
	}
	
	class CreateTask extends SwingWorker<Integer, Integer>
	{
		@Override
		protected Integer doInBackground() throws Exception
		{
			try
			{
				String taskTitle = txtTitle.getText();
				String taskDesc = txtDesc.getText();
				Task task = new Task(taskTitle, taskDesc);
				
				_taskCtrl.insertTask(task);
				
				JOptionPane.showMessageDialog(null, "Opgavetype oprettet.", "Information!", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.ERROR_MESSAGE);
			}
			
			return null;
		}
	}
}