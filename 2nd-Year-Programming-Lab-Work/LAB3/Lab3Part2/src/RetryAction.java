import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A class that extends JFrame, allows the user to enter in a string. 
 * and counts the amount of characters are in the string.
 * 
 * If the user clicks the button and the textfield is empty, a JDialogBox
 * appears requesting the user enters a string. The character length the then 
 * displayed in the frame
 * 
 * @author aaron
 * @group 4
 * @date 10-3-16
 * 
 */

public class RetryAction extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JButton stringLengthButton;
	JTextField inputField;
	JLabel outputLabel;
	JPanel panel;
	
	String stringEntered;
	
	/**
	 * new frame object is made
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new RetryAction();
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * JFrame that contains a JButton, JTextfield and a JLabel
	 * 
	 */
	public RetryAction()
	{
		Container contentPane = getContentPane();
		
		panel = new JPanel();
		
		stringLengthButton = new JButton("get string length");
		stringLengthButton.setFont(new Font("", Font.BOLD, 20));
		stringLengthButton.setBackground(Color.PINK);
		stringLengthButton.addActionListener(this);

		inputField = new JTextField(10);
		outputLabel = new JLabel("String length = ");
		outputLabel.setFont(new Font("", Font.BOLD, 20));
		
		panel.add(stringLengthButton);
		panel.add(inputField);
		panel.add(outputLabel);
		
		contentPane.add(panel);
		
		setVisible(true);
		setSize(600,200);
		setResizable(false);
	}
	
	/**
	 * performed the following instructions to change the 
	 * text displayed on the JFrame
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == stringLengthButton)
		{
			//If the text field is empty, set the string entered variable to null
			if(inputField.getText().equals(""))
			{
				stringEntered = null;
			}
			
			//if its not empty, get the text
			else
			{
				stringEntered = inputField.getText();
			}
			
			try
			{	
				int stringLength = stringEntered.length();
				
				//Change the text of the JLabel
				outputLabel.setText("String length = " + stringLength);
				inputField.setText(null);
			}
			
			//DO THIS if the field is null/empty
			catch(NullPointerException e1)
			{
				try
				{
					int dialogLength;
					
					//keep showing the dialog box if the user
					//hasn't entered a string
					do{
						String dialogString = JOptionPane.showInputDialog(null, "You Must enter a string");
						dialogLength = dialogString.length();
					
						//only do this if the user has entered something
						//into the input dialog
						if(dialogLength != 0)
						{
							outputLabel.setText("String length = " + dialogLength);
						}
					}
					while(dialogLength == 0);
				}
				
				//Stops the program from crashing if cancel is clicked 
				catch(NullPointerException e2)
				{}
			}//end of outer catch block
		}
	} //End of actionPerformed method
}
