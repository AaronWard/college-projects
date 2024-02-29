
package GUI;
import encoder.encoding;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A class that extends JFrame with a text fields and a button
 * that encodes the text entered by the user. When the button is clicked, the encoded
 * version of the string is displayed in the next field Field
 * @author aaron
 *
 */

@SuppressWarnings("serial")
public class CodeFrame extends JFrame implements ActionListener
{	

	//Declaring global variables
	encoding encode = new encoding(); 
	
	JPanel     panel;
	JLabel 	   labelOne, labelTwo;
	JTextField textFieldOne, textFieldTwo;
	JButton    button;
	String     wordEntered;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{

		JFrame codeFrame = new CodeFrame();
		
		//Make the frame appear in the centre of the screen
		codeFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * A frame is made with two labels and 2 text field
	 * and a button to encode 
	 */
	public CodeFrame()
	{
		Container contentPane = getContentPane();
		panel		  = new JPanel();
		panel.setLayout(new GridLayout(3, 3, 40, 30));
		
		//Create Components
		labelOne = new JLabel("     Set Text To Encode");
		labelTwo = new JLabel("   The Endcoded text is");
		
		//Change size of Font 
		labelOne.setFont(new Font("", Font.BOLD, 24));
		labelTwo.setFont(new Font("", Font.BOLD, 24));
		
		textFieldOne = new JTextField();
		textFieldTwo = new JTextField();
		
		button = new JButton("Encode");
		button.addActionListener(this); //add the action listener to the button
		button.setFont(new Font("", Font.PLAIN, 24));
		
		//Adding components to the panel.
		panel.add(labelOne);
		panel.add(labelTwo);
		panel.add(textFieldOne);
		panel.add(textFieldTwo);
		panel.add(button);
		
		
		contentPane.add(panel);
		
		setVisible(true);
		setSize(700, 300);
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) 
	{	
		//If the button is clicked
		if(e.getSource() == button)
		{	
			//If the user doesn't enter a string 
			if(textFieldOne.getText().equals(""))
			{	
				textFieldTwo.setText("You must enter a word to encode");
			}
			else
			{
				//Get the text of the first text field
				wordEntered = textFieldOne.getText();
				//Send the text to the encode method in the encoding class
				wordEntered = encode.encodeMethod(wordEntered);
				//Set the text of the encoded string to the second text field
				textFieldTwo.setText(wordEntered);
			}
		}
	} //End of action performed method

}
