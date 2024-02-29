package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;

/**
 * This frame will provide a form for the user to enter in their details
 * It has a submit button that will generate their new ID and a reset button
 * that will set the text in the fields to null so they can start the form
 * again. 
 * @author Aaron Ward - B00079288
 */
@SuppressWarnings({ "serial"})
public class RegistrationForm extends JFrame implements ActionListener
{
	/***  Create Components (From top to bottom) ***/
	JMenu menu, logOutMenu;
	JMenuItem close, menuReset, menuBack;
	JMenuBar menuBar;	
	
	JPanel outterPanel,northPanel,  mainPanel, innerGrid, outterGrid;
	JPanel formPanel;

	JTextField addressTextField;
	JFormattedTextField nameTextField, numberTextField;
	JPasswordField passwordField, passwordFieldTwo;
	
	JLabel welcomeLabel,  descriptionLabel;
	JLabel nameLabel, addressLabel, numberLabel, emailLabel;
	JLabel dateOfBirth, password1, password2;
	
	JButton submit, reset, back;
	
	JDateChooser datePicker;
	
	Color babyBlue = new Color( 138, 179, 250);
	Font regFont = new Font("", Font.BOLD, 26);
	
	//********************************************/
	

	public RegistrationForm()
	{
		Container contentPane = getContentPane();
		
		
		/******** MENU BAR *********/
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(1500, 50));  
		menuBar.setBackground(Color.WHITE);
		menuBar.setLayout(new BorderLayout());
	
		//Create Options Menu
		menu = new JMenu("Options");
		menu.setFont(new Font("", Font.BOLD, 18));
		menu.add(Box.createHorizontalStrut(200)); //Widens the options menu
		
		//Create option menu items
		menuReset = new JMenuItem("Reset");
		menuReset.addActionListener(this);
		menuReset.setFont(new Font("", Font.BOLD, 18));
			
			//Closes system when clicked
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 18));
			
			//Returns to login form
		menuBack = new JMenuItem("Back");
		menuBack.addActionListener(this);
		menuBack.setFont(new Font("", Font.BOLD, 18));
		
				
			// add options menu to menuBar
		menu.add(menuReset);
		menu.addSeparator();
		menu.add(menuBack);
		menu.addSeparator();
		menu.add(close);
		
			//Back button is created
		back = new JButton("Back");
		back.setFocusPainted(false);
		back.setBackground(Color.WHITE);
		back.setFont(new Font("", Font.BOLD, 18));
		back.setMargin(new Insets(10, 20, 10, 30));
		back.addActionListener(this);
		
			//Shortcut to options menu
		menu.setMnemonic(KeyEvent.VK_O);

		
		//border layout used to separate the components in the menu bar
		menuBar.add(menu, BorderLayout.WEST);
		menuBar.add(back, BorderLayout.EAST);
		
	
		
		
		//*********************************/		

		/**
		 * This panel will display the title 
		 */
		northPanel = new JPanel();
		
		welcomeLabel = new JLabel("Registration form");
		welcomeLabel.setFont(new Font("", Font.BOLD, 75));
		northPanel.add(welcomeLabel);	
		
		
		//*********** MAIN PANEL *************/
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null); //set to null so setBounds can be used

		//***Form Panel ****/	
		formPanel = new JPanel();		
		
			//A label telling the user to enter in the fields
		descriptionLabel = new JLabel("Please fill out this form and submit to register.");
		descriptionLabel.setFont(new Font("", Font.BOLD, 45));
		formPanel.add(descriptionLabel);
				
	
		//Panel with a grid layout for the form
		innerGrid = new JPanel();
		innerGrid.setLayout(new GridLayout(3, 2, 10, 80));
		innerGrid .setBackground(babyBlue);
		
			//For users name
		nameLabel = new JLabel("Enter full name  :");
		nameLabel.setFont(regFont);
			//Add formatter to name text field to ensure illegal characters like
			//number and punctuation aren't used.
		try
		{
			MaskFormatter nameFormatter = new MaskFormatter("********************");
			nameFormatter.setValidCharacters("abcdefghijklmnopqrstuvwxyz "
											+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ-");
			nameTextField = new JFormattedTextField(nameFormatter);
		}
		catch(ParseException e)
		{ 
			e.printStackTrace();  
		}
			
		//For user's address
		addressLabel = new JLabel("Enter home address:");
		addressLabel.setFont(regFont);
		addressTextField = new JTextField();
		
		//for users number
		numberLabel = new JLabel("Enter phone number:");
		numberLabel.setFont(regFont);
			//Only allows numbers in the field
		try
		{
			MaskFormatter formatter = new MaskFormatter("**********");//Allows 10 digits in the field
			formatter.setValidCharacters("0123456789 "); 			  //allows number from 0 to 9 in the field
			//Adds formatter to the JTextField
			numberTextField = new JFormattedTextField(formatter);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		

		
		//Date of birth of the user
		dateOfBirth = new JLabel("Date of birth: ");
		dateOfBirth.setFont(regFont);
		datePicker = new JDateChooser();
		datePicker.setFont(new Font("", Font.PLAIN, 13));
			
		//for user's password
		password1 = new JLabel("Enter a password:");
		password1.setFont(regFont);
		passwordField = new JPasswordField();

			//validate password
		password2 = new JLabel("Re-enter password:");
		password2.setFont(regFont);
		passwordFieldTwo = new JPasswordField();
		
		
		//Add the components to the form grid
		innerGrid.add(nameLabel);
		innerGrid.add(nameTextField);
		innerGrid.add(addressLabel);
		innerGrid.add(addressTextField);
		innerGrid.add(numberLabel);	
		innerGrid.add(numberTextField);
		innerGrid.add(password1);
		innerGrid.add(passwordField);
		innerGrid.add(dateOfBirth);
		innerGrid.add(datePicker);
		innerGrid.add(password2);
		innerGrid.add(passwordFieldTwo);
	
		
		
	  //************* Buttons Panel *****************/
		JPanel buttonPanel = new JPanel();
		
			//Sends information to the DB
		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setBackground(Color.WHITE);
		submit.setFont(regFont);
		submit.setFocusPainted(false);
		submit.setPreferredSize(new Dimension(350, 50));
		
			//Clears all fields if the user wants.
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setBackground(Color.WHITE);
		reset.setFont(regFont);
		reset.setFocusPainted(false);
		reset.setPreferredSize(new Dimension(350, 50));
		
		buttonPanel.add(submit);
		buttonPanel.add(reset);


		
		//*******ADD PANELS AND SET BOUNDS*************/

		/**
		 * The panels are added to the content pane and
		 * bounds are set for the panels with a layout set to 
		 * null.
		 */
		formPanel.add(innerGrid);
		mainPanel.add(buttonPanel);
	
		//Setting bounds for panels
		northPanel.setBounds(0,30,1500, 130);
		formPanel.setBounds(0, 175,1500, 800);
		buttonPanel.setBounds(360, 670,800, 120);
		
		formPanel.setBorder(BorderFactory.createEmptyBorder(75, 200, 250,200));

		mainPanel.add(northPanel);
		mainPanel.add(formPanel);
			
			//Adding colours to the panels
		northPanel.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);
		formPanel.setBackground(babyBlue);
		buttonPanel.setBackground(babyBlue);
		
		
		contentPane.add(mainPanel);
		/*********************************/
		setJMenuBar(menuBar);
		setSize(1500, 900);
		setUndecorated(true);//Removes windows default border
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));//adds black border around the frame
		setVisible(true);
	}
		
	
	public void actionPerformed(ActionEvent e)
	{
		//Change font size in the JOptionPanes
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "Arial", Font.BOLD, 18))); 

		
		/** Menu Bar Buttons **/
		if(e.getSource() == close)
		{
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "", 
						 JOptionPane.ERROR_MESSAGE);
			
			//Closes the program if the user clicks yes
			if(result == JOptionPane.YES_OPTION)
			{	
				System.exit(0);
			}
		}
		
		//If the reset button is clicked in the menu or the form
		if(e.getActionCommand() == "Reset")
		{
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all fields?", "", 
						 JOptionPane.ERROR_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION)
			{	
				//clear all fields
				nameTextField.setText	(null);
				addressTextField.setText(null);
				numberTextField.setText	(null);
				datePicker.setDate 		(null);
				passwordField.setText	(null);
				passwordFieldTwo.setText(null);
			}
		}
		
		//If back is clicked in the menu or the button
		//on the menu bar - The log in form appears
		if(e.getActionCommand() == "Back")
		{
			Login loginFrame = new Login();
			
			loginFrame.setVisible(true);
			loginFrame.setLocationRelativeTo(null);
			dispose();
		}
		
		
		
	/****************************************/
		
		/** Form Buttons **/
		if(e.getSource() == submit)
		{	
			/**
			 * The following code checks that the user hasn't left
			 * any of the feilds blank. And prompts them with a warning if
			 * they have.
			 */
			if(nameTextField.getText().equals("                    ")) //Space in between brackets because of Formatter
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
				
			else if(numberTextField.getText().equals("          "))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(addressTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
		
			/**
			 * If the users password do not match, a warning appears to prompt the user
			 * to enter again. the fields are also set to null
			 */
			else if (!Arrays.equals(passwordField.getPassword(), passwordFieldTwo.getPassword()))
			{
				JOptionPane.showMessageDialog(null, "Passwords do not match.", "Woops", JOptionPane.ERROR_MESSAGE);
				
					//set the text to null if this error occurs 
				passwordField.setText(null);
				passwordFieldTwo.setText(null);
			}
			
			//So promt the user if the password fields are empty
			else if(passwordField.getPassword().length == 0 || passwordFieldTwo.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(null, "Password field must not be empty", "Woops", JOptionPane.ERROR_MESSAGE);
				
					//set the text to null if this error occurs 
				passwordField.setText(null);
				passwordFieldTwo.setText(null);
			}
			
			/**
			 * If the user completes the form successfully
			 * the following code is executed.
			 */
			else
			{	
				//A new random ID is generated for the user.
				//ID is displayed to user.
				String studentID = new IDGenerator().generator();
				
				
				//Variables are set for sql statements
				String name = nameTextField.getText();
				String address = addressTextField.getText();
				String phoneNumber = numberTextField.getText();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		        String DOB = format.format(this.datePicker.getDate());
				String userPassword = String.valueOf(passwordField.getPassword());
				
				//Connection variables are declared for database
				String url ="jdbc:mysql://localhost:3306/enrolmentdb?autoReconnect=true&useSSL=false";
				String user = "root";
				String password = "root";
				
				try
				{
					//1 - get a connection to the driver
					Connection dbConnection = DriverManager.getConnection(url, user, password);
					//2 - create a statement
					Statement myStatement = dbConnection.createStatement();
					
					
					//Update database
					String sqlStatement = "INSERT INTO student"
										+ "(studentid, studentname, studentaddress, studentnum, password, DOB)"
										+ "VALUES ('" + studentID + "', '" + name +"', '" + address + "', '" + phoneNumber + 
										"', '"+ userPassword + "', '" + DOB + "')";
					
					myStatement.executeUpdate(sqlStatement);
				
					/**
					 * Used to get the information from the database and display it to the console.
					 * Prints out all that infomation in the table WHERE the student id is the same
					 * as the student ID in this instance.
					 */
					ResultSet myRes = myStatement.executeQuery("SELECT * FROM student WHERE studentid = " + studentID);
				    ResultSetMetaData rsmd = myRes.getMetaData();
				    int columnsNumber = rsmd.getColumnCount();
					 
				    //Print out the contents of the table to the console
				    while (myRes.next()) 
					 {
				        for (int i = 1; i < columnsNumber; i++) 
				        {
				            System.out.print("");
				            String columnValue = myRes.getString(i);
				            System.out.println(rsmd.getColumnName(i) + ": " + columnValue);
				        }
					  }
				}		//End of TRY
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				
				//When the user successfully registers and the DB has been updated
				JOptionPane.showMessageDialog(null,"You have succesfully registered. Your new student ID is: "
						+ studentID, "Congratulations " + nameTextField.getText(), JOptionPane.ERROR_MESSAGE);
				
				
				//New Login form object is created
				Login loginFrame = new Login();
				loginFrame.setVisible(true);
				dispose(); // Close the registration frame
				loginFrame.setLocationRelativeTo(null);	
			}
		}
	}
}