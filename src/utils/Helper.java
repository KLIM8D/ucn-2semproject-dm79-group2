package utils;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Helper 
{
	
	public static void checkIfInt(JTextField data) 
	{
		try 
		{
			Integer.parseInt(data.getText());
		}
		catch (NumberFormatException err) 
		{
			JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
			data.setText(null);
		}
	}
	
	public static void checkIfLong(JTextField data) 
	{
		try 
		{
			Long.parseLong(data.getText());
		}
		catch (NumberFormatException err) 
		{
			JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
			data.setText(null);
		}
	}

    public static void checkIfDouble(JTextField data)
    {
        try
        {
            Double.parseDouble(data.getText());
        }
        catch (NumberFormatException err)
        {
            JOptionPane.showMessageDialog(null, "Dette felt kan kun indeholde decimal tal", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(Helper.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
            data.setText(null);
        }
    }

    public static int showConfirmDialog(String objectName)
    {
        int option = JOptionPane.showConfirmDialog (null, String.format("Er du sikker p" + "\u00e5" + " du vil slette dette %s?", objectName));
        if (option == JOptionPane.YES_OPTION )
            return 1;

        return 0;
    }
	
	//Added because of stupid a bug with setLocationRelativeTo(null) for some JFrames or JDialogs
	public static void centerOnScreen(final Component c)
	{
	    final int width = c.getWidth();
	    final int height = c.getHeight();
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screenSize.width / 2) - (width / 2);
	    int y = (screenSize.height / 2) - (height / 2);
	    c.setLocation(x, y);
	}

    public static <E> ArrayList<E> makeCollection(Iterable<E> iter)
    {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter)
            list.add(item);
        return list;
    }

    public static int selectDropdownIndex(JComboBox<String> dropdown, String findValue)
    {
        int drpIndex = 0;
        for (int i=0; i< dropdown.getItemCount(); i++)
        {
            String currentValue = dropdown.getItemAt(i);
            if(findValue.equalsIgnoreCase(currentValue))
            {
                drpIndex = i;
                break;
            }
        }
        return drpIndex;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String createSalt(User user)
    {

        Calendar currentDate = Calendar.getInstance();
        String combined = user.getFirstName() + user.getLastName() + user.getUserId() + currentDate.getTimeInMillis();

        return createHash(combined);
    }

    public static String hashPassword(String password, String salt)
    {
        return createHash(password + salt);
    }

    public static String createHash(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(input.getBytes());

            StringBuffer sb = new StringBuffer();
            for(byte b : hash)
            {
                sb.append(Integer.toHexString(b & 0xff));
            }

            return sb.toString();
        }
        catch(Exception e)
        {
            Logging.handleException(e, 0);
        }

        return "";
    }

    public static String readFile(String path) throws IOException
    {
        FileInputStream stream = new FileInputStream(new File(path));
        try
        {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            fc.close();

            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally
        {
            stream.close();
        }
    }

    public static void writeFile(String filePath, String content) throws Exception
    {
        FileWriter fStream = new FileWriter(filePath);
        BufferedWriter out = new BufferedWriter(fStream);
        try
        {
            out.write(content);
        }
        finally
        {
            out.close();
            fStream.close();
        }
    }
}
