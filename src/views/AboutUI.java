package views;

import utils.Helper;
import utils.SystemInformation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutUI
{

	private static JFrame _frame;
	private static AboutUI _instance;
	private JPanel pnlAbout;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AboutUI();
		
		return _frame;
	}

	private AboutUI()
    {
		_frame = new JFrame();
		_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUI.class.getResource("/icons/48x48/app.png")));
		_frame.setTitle("Om " + SystemInformation.systemInformation(01));
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setSize(new Dimension(470,430));
        Helper.centerOnScreen(_frame);
		_frame.setResizable(false);
		_frame.setVisible(true);
		_frame.toFront();
		pnlAbout = new JPanel();
		pnlAbout.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlAbout.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(pnlAbout);
		
		JTabbedPane tabAbout = new JTabbedPane(JTabbedPane.TOP);
		pnlAbout.add(tabAbout, BorderLayout.CENTER);
		
		JPanel pnlSystem = new JPanel();
		tabAbout.addTab("System", null, pnlSystem, null);
		pnlSystem.setLayout(null);
		
		JLabel lblTitle = new JLabel(SystemInformation.systemInformation(01));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTitle.setBounds(12, 12, 409, 22);
		pnlSystem.add(lblTitle);
		
		JLabel lblDescription = new JLabel(SystemInformation.systemInformation(02) + " (build " + SystemInformation.systemInformation(03) + ")");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(22, 54, 399, 15);
		pnlSystem.add(lblDescription);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 43, 409, 1);
		pnlSystem.add(separator);
		
		JLabel lblDevelopers = new JLabel("Udviklet af:");
		lblDevelopers.setBounds(22, 95, 126, 15);
		pnlSystem.add(lblDevelopers);
		
		JLabel lblDev01 = new JLabel("Rasmus Meyer Lillienskjold Pedersen (1016073@ucn.dk)");
		lblDev01.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev01.setBounds(22, 110, 399, 15);
		pnlSystem.add(lblDev01);
		
		JLabel lblDev02 = new JLabel("Chris Tindb" + "\u00E6" + "k (1016273@ucn.dk)");
		lblDev02.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev02.setBounds(22, 125, 384, 15);
		pnlSystem.add(lblDev02);
		
		JLabel lblDev03 = new JLabel("Morten Klim S" + "\u00F8" + "rensen (1016053@ucn.dk)");
		lblDev03.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev03.setBounds(22, 140, 384, 15);
		pnlSystem.add(lblDev03);
		
		JLabel lblDev04 = new JLabel("Mads Nielsen (1016051@ucn.dk)");
		lblDev04.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev04.setBounds(22, 155, 384, 15);
		pnlSystem.add(lblDev04);
		
		JLabel lblGitHubTitle = new JLabel("GitHub repository:");
		lblGitHubTitle.setBounds(22, 210, 419, 15);
		pnlSystem.add(lblGitHubTitle);
		
		JLabel lblGitHubURL = new JLabel("https://github.com/KLIM8D/ucn-2semproject-dm79-group2");
		lblGitHubURL.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGitHubURL.setBounds(22, 225, 419, 15);
		pnlSystem.add(lblGitHubURL);
		
		JLabel lblIconTitle = new JLabel("Icon set (Tango)");
		lblIconTitle.setBounds(22, 273, 419, 15);
		pnlSystem.add(lblIconTitle);
		
		JLabel lblIconURL = new JLabel("http://tango.freedesktop.org/Tango_Icon_Library");
		lblIconURL.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblIconURL.setBounds(22, 288, 419, 15);
		pnlSystem.add(lblIconURL);
		
		JPanel pnlLicense = new JPanel();
		tabAbout.addTab("Licens", null, pnlLicense, null);
		pnlLicense.setLayout(null);
		
		JTextArea txtLicense = new JTextArea();
		txtLicense.setBackground(UIManager.getColor("ComboBox.background"));
		txtLicense.setWrapStyleWord(true);
		txtLicense.setLineWrap(true);
		txtLicense.setEditable(false);
		txtLicense.setText("Copyright (c) 2012 Rasmus Meyer Lillienskjold Pedersen, Chris Tindb" + "\u00E6" + "k, Morten Klim S" + "\u00F8" + "rensen, Mads Nielsen\n\nDer gives hermed tilladelse, gratis, til enhver person at f" + "\u00e5" +" en kopi af denne software og tilh" + "\u00F8" + "rende dokumentation (Time-sag Styring), til at handle med softwaren uden begr" + "\u00E6" + "nsning, herunder uden begr" + "\u00E6" + "nsning af retten til at bruge, kopiere, modificere , flette, offentligg" + "\u00F8" + "re, distribuere, underlicensere og eller s" + "\u00E6" + "lge kopier af softwaren samt at tillade de personer, som denne software tilvejebringes for at g" + "\u00F8" + "re dette, forudsat at f" + "\u00F8" + "lgende betingelser:\n\nDen over ophavsret og denne tilladelse skal indg" + "\u00e5" +" i alle kopier eller v" + "\u00E6" + "sentlige dele af softwaren.\n\nSOFTWAREN LEVERES SOM DEN ER, UDEN NOGEN FORM FOR GARANTI, DIREKTE ELLER INDIREKTE, INKLUSIVE, MEN IKKE BEGR" + "\u00c6" + "NSET TIL ANSVAR FOR SALGBARHED, EGNETHED TIL ET BESTEMT FORM" + "\u00c5" + "L OG IKKE-KR" + "\u00c6" + "NKELSE. UNDER INGEN OMST" + "\u00c6" + "NDIGHEDER SKAL FORFATTERNE ELLER OPHAVSRETTIGHAVERNE HOLDES ANSVARLIG FOR NOGEN KRAV, SKADER ELLER ANDET ANSVAR, UANSET OM AF KONTRAKT, TORT ELLER ANDET, OPST" + "\u00c5" + "ET FRA, UD AF ELLER I FORBINDELSE MED SOFTWAREN ELLER BRUGEN ELLER ANDRE FORHOLD VED SOFTWAREN.");
		txtLicense.setBounds(5, 5, 435, 355);
		pnlLicense.add(txtLicense);
		
		_frame.addWindowListener(new WindowAdapter()
        {
			public void windowClosing(WindowEvent e)
            {
				_instance = null;
				_frame.dispose();
			}
		});
	}
}