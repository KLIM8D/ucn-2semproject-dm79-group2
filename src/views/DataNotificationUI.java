/**
 * filename    : DataNotificationUI.java
 * created     : Dec 17, 2012 (5:17:44 PM)
 * description :
 * -------------------------------------------------------
 * @version    : 0.1
 * @changes    :
 */

package views;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DataNotificationUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public DataNotificationUI() {
		setResizable(false);
		setTitle("Vent venligst....");
		setBounds(100, 100, 310, 85);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLoadingImg = new JLabel();
        lblLoadingImg.setIcon(new ImageIcon(DataNotificationUI.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
        lblLoadingImg.setBounds(12, 12, 32, 32);
        contentPanel.add(lblLoadingImg);
        
        JLabel lblLoadingText = new JLabel("Der kommunikeres med databasen!");
        lblLoadingText.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblLoadingText.setBounds(59, 20, 294, 15);
        contentPanel.add(lblLoadingText);
	}
}