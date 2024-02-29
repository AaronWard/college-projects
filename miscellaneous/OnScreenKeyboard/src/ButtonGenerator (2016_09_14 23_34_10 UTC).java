
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author aaron
 *
 */
public class ButtonGenerator extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	JPanel outerPanel;
	JPanel southPanel, labelPanel;
	JLabel nameLabel, passwordLabel;
	JTextField textField;
	JPasswordField	password;
	JButton button, submit,  backSpace;
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem close;
	
	char [] passwordString;


	boolean nameNull;

	String string;
	String [] letterArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
							 "K", "L", "M", "N", "N", "O", "P", "Q", "R", "S",
							 "T", "U", "V", "W", "X", "Y", "Z"};

	Icon backSpaceIcon = new ImageIcon("backSpaceIcon.png");


	//An array of colours to change the colours of the button
	Color [] colors = new Color[]
	{
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE, Color.WHITE,
		Color.ORANGE, Color.WHITE, Color.ORANGE
	};

	public static void main(String [] args)
	{
		ButtonGenerator frame = new ButtonGenerator();

		//sets the frame in the middle of the screen
		frame.setLocationRelativeTo(null);
	}
	
	public ButtonGenerator()
	{
		Container contentPane = getContentPane();

		outerPanel = new JPanel(new BorderLayout());
		southPanel = new JPanel(new GridLayout(4, 2, 5, 10));
		labelPanel = new JPanel();

		menuBar = new JMenuBar();
		menuBar.setBackground(Color.ORANGE);
		
		menu = new JMenu("Options");
		menu.setFont(new Font("", Font.PLAIN, 20));
		
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 15));
		
		menu.add(close);
		menuBar.add(menu);
		contentPane.add(menuBar);
		
		//***************************


		nameLabel = new JLabel("Enter Name: ");
		nameLabel.setFont(new Font("", Font.BOLD,20));
		labelPanel.add(nameLabel);

		textField = new JTextField(10);
		textField.setFont(new Font("", Font.PLAIN,20));
		labelPanel.add(textField);
		
		passwordLabel = new JLabel("Enter Password: ");
		passwordLabel.setFont(new Font("", Font.BOLD,20));
		labelPanel.add(passwordLabel);
		
		password = new JPasswordField(10);
		password.setFont(new Font("", Font.PLAIN,20));
		labelPanel.add(password);

		submit = new JButton("Submit");
		submit.addActionListener(this);
		labelPanel.add(submit);


		//*****************************



		for(int i = 0; i < letterArray.length; i++)
		{
			button = new JButton(letterArray[i]);
			button.setPreferredSize(new Dimension(200, 100));
			button.setFont(new Font("", Font.PLAIN, 20));
			button.setBackground(colors[i]);
			button.addActionListener(this);
			southPanel.add(button);
		}

		backSpace = new JButton(backSpaceIcon);
		backSpace.setPreferredSize(new Dimension(200, 100));
		backSpace.setBackground(Color.WHITE);
		backSpace.addActionListener(this);
		southPanel.add(backSpace);


		//***************************

		outerPanel.add(labelPanel, BorderLayout.NORTH);
		outerPanel.add(southPanel, BorderLayout.CENTER);

		contentPane.add(outerPanel);

		setJMenuBar(menuBar); 
		setTitle("Buttons Screen Keyboard");
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
	}


	public void actionPerformed(ActionEvent e)
	{

	    switch(e.getActionCommand())
		{
		case "A":
				string = textField.getText() + "A";
				textField.setText(string);
				nameNull = false;
				break;
		case "B":
				string = textField.getText() + "B";
				textField.setText(string);
				break;
		case "C":
				string = textField.getText() + "C";
				textField.setText(string);
				break;
		case "D":
				string = textField.getText() + "D";
				textField.setText(string);
				break;
		case "E":
				string = textField.getText() + "E";
				textField.setText(string);
				break;
		case "F":
				string = textField.getText() + "F";
				textField.setText(string);
				break;
		case "G":
				string = textField.getText() + "G";
				textField.setText(string);
				break;
		case "H":
				string = textField.getText() + "H";
				textField.setText(string);
				break;
		case "I":
				string = textField.getText() + "I";
				textField.setText(string);
				break;
		case "J":
				string = textField.getText() + "J";
				textField.setText(string);
				break;
		case "K":
				string = textField.getText() + "K";
				textField.setText(string);
				break;
		case "L":
				string = textField.getText() + "L";
				textField.setText(string);
				break;
		case "M":
				string = textField.getText() + "M";
				textField.setText(string);
				break;
		case "N":
				string = textField.getText() + "N";
				textField.setText(string);
				break;
		case "O":
				string = textField.getText() + "O";
				textField.setText(string);
				break;
		case "P":
				string = textField.getText() + "P";
				textField.setText(string);
				break;
		case "Q":
				string = textField.getText() + "Q";
				textField.setText(string);
				break;
		case "R":
				string = textField.getText() + "R";
				textField.setText(string);
				break;
		case "S":
				string = textField.getText() + "S";
				textField.setText(string);
				break;
		case "T":
				string = textField.getText() + "T";
				textField.setText(string);
				break;
		case "U":
				string = textField.getText() + "U";
				textField.setText(string);
				break;
		case "V":
				string = textField.getText() + "V";
				textField.setText(string);
				break;
		case "W":
				string = textField.getText() + "W";
				textField.setText(string);
				break;
		case "X":
				string = textField.getText() + "X";
				textField.setText(string);
				break;
		case "Y":
				string = textField.getText() + "Y";
				textField.setText(string);
				break;
		case "Z":
				string = textField.getText() + "Z";
				textField.setText(string);
				break;

		}

	    if(e.getSource() == backSpace)
	    {
	    	//Catches a NullPointerException if
	    	//the text field is empty
	    	try
	    	{
				if (string.length() > 0 )
				{
					//Used to delete one character from the string
					string = string.substring(0, string.length()-1);
					textField.setText(string);
				}
	    	}

			catch(NullPointerException e1)
			{
				JOptionPane.showMessageDialog(null, "There is nothing to delete", "Warning",
				    JOptionPane.WARNING_MESSAGE);
			}

	    }

		if(e.getSource() == submit)
		{


			string = textField.getText();
			passwordString = password.getPassword();
			
			//passwordString.toString();

			if(string == null || string == "")
			{
				nameNull = true;
			

				if(nameNull == true)
				{
					JOptionPane.showMessageDialog(null, "Please enter a name", "",
						JOptionPane.WARNING_MESSAGE);
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "your name is : " + string +"\n Password: " + passwordString.toString(), "",
						JOptionPane.WARNING_MESSAGE);
				textField.setText(null);
				password.setText(null);
			}	
		}
		
		if(e.getSource() == close)
		{
			int result = JOptionPane.showConfirmDialog(this, "Do you want to quit?", "", JOptionPane.ERROR_MESSAGE);
			
			//Closes the program if the user clicks yes
			if(result == JOptionPane.YES_OPTION)
			{	
				System.exit(0);
			}
		}
	}
}