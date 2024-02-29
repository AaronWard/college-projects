
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

/**
 * A GUI frame with 4 JButtons that have been purposly hardcoded to 
 * invoke 4 kinds of Exceptions. IOException, MalformedURLException, NullPointerException
 * and a general Exception. A JOptionPane appears for display what exceptions have been
 * caught. A Finally block is then called after the user clicks OK on the caught exceptions
 * JOptionPane
 * @author aaron
 * @Group 4
 * @date 8-3-16
 *
 */
public class TestFourExceptionsGUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel outerPanel, innerPanel;
	JButton[] button  = new JButton[4];
	String [] buttonNames = {"Test IOException", "Test URL Exception",
				"Test Null Pointer Exception", "Test General Exception"};

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		TestFourExceptionsGUI frame = new TestFourExceptionsGUI();
		
		//Sets the frame in the middle of screen
		frame.setLocationRelativeTo(null);
	}
	
	
	public TestFourExceptionsGUI() 
	{
		Container contentPane = getContentPane();
		
		outerPanel = new JPanel(new BorderLayout());
		innerPanel = new JPanel(new GridLayout(2,2,40,40));
		innerPanel.setBackground(Color.BLACK);
		
		
		//use the buttons array to generate buttons and 
		// user the buttonNames array to fill the buttons with text
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(buttonNames[i]);
			button[i].setFont(new Font("", Font.PLAIN, 20));
			button[i].setBackground(Color.PINK);
			button[i].addActionListener(this);
			innerPanel.add(button[i]);
		}
		
		
		outerPanel.add(innerPanel);
		contentPane.add(outerPanel, BorderLayout.CENTER);
		
		
		setVisible(true);
		setSize(800,600);
		setResizable(false);		
	}
	
	/**
	 * method that implements all the exceptions
	 * @param string
	 * @param fileName
	 * @param url
	 * @param generalExceptionActivated
	 */
	@SuppressWarnings("resource")
	public void testExceptions(String string, String fileName,String url,
								boolean generalExceptionActivated)
	{
		try
		{
			//Null string potential error 
			string.toCharArray();
			//Unknown filename potential error 
			new FileReader(fileName); 
			//Badly written URL potential error
			new URL(url); 
			
			//Potential error 
			if(generalExceptionActivated) 
			{ 
				this.clone(); //Will be caught as a general error! 
			}
		}
		
		//Malformed goes before IOException because the IOException
		//Handles it already, so it would make it redundant
		catch(MalformedURLException e) 
		{
			//URL is spells ht//www. etc.. so this will display
			JOptionPane.showMessageDialog(null,"a URL has been badly written. " + e.getMessage());
		}
		catch(IOException e)
		{
			//Whatever.txt doesn't exist, so this is called
			JOptionPane.showMessageDialog(null, "An IO Exception has been caught. " + e.getMessage());
		}
		
		catch(NullPointerException e)
		{
			//string is null, so this will be displayed
			JOptionPane.showMessageDialog(null, "A null pointer exception has been caught. " + e.getMessage());
		}
		
		catch(Exception e)
		{
			//this will display because the has been an exception in the code
			JOptionPane.showMessageDialog(null, "Some unidentified exception has been caught. " + e.getMessage());
		}
		
		//This will display regardless
		finally 
		{
			JOptionPane.showMessageDialog(null, "The Finally block has been called");
		}
	}

	
	/**
	 * Action listener method to invoke testException method
	 * when the button is clicked
	 */
	public void actionPerformed(ActionEvent e)
	{	
		//if the button of index 0 in the JButton array is clicked
		if(e.getSource()== button[0])
		{	
			//parameters are passed to the method
			testExceptions("hi", "Whatever.txt", "http://www.itb.ie", false);
		}
		
		if(e.getSource()== button[1])
		{
			testExceptions("hi", "Real.txt", "ht//www.itb.ie", false);
		}
		
		if(e.getSource()== button[2])
		{
			testExceptions(null, "Real.txt", "http://www.itb.ie", false);
		}
		
		if(e.getSource()== button[3])
		{
			testExceptions("hi", "Real.txt", "http://www.itb.ie", true);
		}
	}
}
