package views.dataentry;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import models.DataEntry;
import models.Task;
import models.TimeSheet;
import models.User;

import controllers.TimeSheetCtrl;
import utils.Logging;
import utils.UserSession;
import db.DataAccess;

public class CreateDataEntryUI extends JFrame {

    private static JFrame _frame;
    private static CreateDataEntryUI _instance;
    private JPanel contentPane;
    
    private TimeSheetCtrl _tsCtrl;
    private TimeSheet ts; // timesheet needs to be imported from super, when we open this class.
    
    private JComboBox<Task> drpXAssignment;
    private JComboBox<Date> drpXStartDate;
    private JComboBox<Date> drpXEndDate;
	private JTextField txtRemark;

	public static JFrame createWindow()
    {
		if(_instance == null)
			_instance = new CreateDataEntryUI();

		return _frame;
    }

    public CreateDataEntryUI()
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
		
		JPanel tabRegistrering = new JPanel();
		tabbedPane.addTab("Registrering", null, tabRegistrering, null);
		tabRegistrering.setLayout(null);
		
		JLabel lblAssignment = new JLabel("Opgave");
		lblAssignment.setBounds(12,12,120,15);
		tabRegistrering.add(lblAssignment);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 400, 1);
		tabRegistrering.add(separator);
		
		JLabel lblCreatedDate = new JLabel("P\u00E5begyndt");
		lblCreatedDate.setBounds(12,52,120,15);
		tabRegistrering.add(lblCreatedDate);
		
		JLabel lblEndDate = new JLabel("Afsluttet");
		lblEndDate.setBounds(12,82,120,15);
		tabRegistrering.add(lblEndDate);
		
		JLabel lblRemark = new JLabel("Bem\u00E6rkning");
		lblRemark.setBounds(12,112,120,15);
		tabRegistrering.add(lblRemark);
		
		drpXAssignment.setBounds(80, 10, 100, 20);
		tabRegistrering.add(drpXAssignment);
		
		drpXStartDate.setBounds(80, 50, 100, 20);
		tabRegistrering.add(drpXStartDate);
		
		drpXEndDate.setBounds(80, 80, 100, 20);
		tabRegistrering.add(drpXEndDate);
		
		txtRemark = new JTextField();
		txtRemark.setBounds(80,120,230,60);
		tabRegistrering.add(txtRemark);
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
            @SuppressWarnings("unused")
			DataAccess da = DataAccess.getInstance();
            Task task = drpXAssignment.getPrototypeDisplayValue();
            UserSession uS = new UserSession();
            User user = uS.getLoggedInUser();
            Date startDate = drpXStartDate.getPrototypeDisplayValue();
            Date endDate = drpXEndDate.getPrototypeDisplayValue();
            String entryRemark = txtRemark.getText();
            Calendar cal = Calendar.getInstance();
            Date creationDate = cal.getTime();
            Date editedDate =  cal.getTime();
            DataEntry dataEntry = new DataEntry(task, user, startDate, endDate, entryRemark, creationDate, editedDate);

            _tsCtrl.addDataEntry(ts, dataEntry);

            JOptionPane.showMessageDialog(null, "Registreringen er oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
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
