package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.MaskFormatter;
import java.sql.*;


/**
 * This frame allows the user to either log into their profile should they have one already
 * or the user can choose to register with the system.
 * @author aaron
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{
	/***  Create Components ***/
	JMenu menu;
	JMenuItem close, menuReset, menuBack, menuRegister;
	JMenuBar menuBar;	
	
	
	JLabel userIDLabel, passwordLabel, imageLabel;
	JTextField userIDField;
	JPasswordField passwordField;
	
	JButton logIn, register;
	JPanel mainPanel, formPanel, inputGrid, imagePanel, buttonsPanel;
	
	//Color for the backgrounds
	Color babyBlue = new Color(138, 179, 250);
	
	static String userID;
	
	//********************************************
	
	public static void main(String [] args)
	{
		JFrame frame = new Login();
		//set the frame to the centre of the screen
		frame.setLocationRelativeTo(null);	
		}
	
	/**
	 * This method contains the components for the
	 * log in menu
	 */
	public Login()
	{
		Container contentPane = getContentPane();
		
		/******** MENU *********/
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(1500, 50));  
		menuBar.setBackground(Color.WHITE);
		menuBar.setLayout(new BorderLayout());
	
		//Create Options Menu
		menu = new JMenu("Options");
		menu.setFont(new Font("", Font.BOLD, 18));
		menu.add(Box.createHorizontalStrut(200)); //Widens the options menu
		
		//Create reset fields option
		menuReset = new JMenuItem("Reset");
		menuReset.addActionListener(this);
		menuReset.setFont(new Font("", Font.BOLD, 18));
		
		//Create close program option
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 18));
				
		//add options menu to menuBar
		menu.add(menuReset);
		menu.addSeparator();
		menu.add(close);
		menuBar.add(menu, BorderLayout.WEST);		

		//Mnemomic set for shortcut access to menu
		menu.setMnemonic(KeyEvent.VK_O);

		
		/******* MAIN PANEL *******/		
		
		/**
		 * Image panel is used to diaplay the the ITB Logo at the top of
		 * the frame
		 */
		imagePanel = new JPanel();
		
		//Get the image using getClas() and getResource so it works in the JAR file
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/itb_logo.jpg"));
		Image img = icon.getImage(); 
		
		//Change the dimensions of the image
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics(); 
		g.drawImage(img, 100, 80, 300, 150, null); 
		ImageIcon newIcon = new ImageIcon(bi);

		imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(500, 250));
		imageLabel.setIcon(newIcon);
		imagePanel.add(imageLabel);	
		
		
		//******************************************
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null); //set to null so setBounds can be used
		
		//**Create components for the form**/	
		formPanel = new JPanel();		
		
		// panel for basic info 
		inputGrid = new JPanel();
		inputGrid .setLayout(new GridLayout(2, 2, 10, 80));
		inputGrid .setBackground(babyBlue);
		
			//For users name
		userIDLabel = new JLabel("   Enter your user ID :");
		userIDLabel.setFont(new Font("", Font.BOLD,30));
		/**
		 * Add formatter to name text field to ensure illegal characters,like
		 * number and punctuation, aren't used.
		 */
		try
		{
				//Maximum of 4 numbers are allowed in the field
			MaskFormatter idFormatter = new MaskFormatter("****");
			idFormatter.setValidCharacters("1234567890");
			userIDField = new JFormattedTextField(idFormatter);;
		}
		catch(ParseException e)
		{ 
			e.printStackTrace();  
		}


			//for user's password
		passwordLabel = new JLabel("Enter your password:");
		passwordLabel.setFont(new Font("", Font.BOLD, 30));
		passwordField = new JPasswordField();

		
		   //Add the components to the form grid
		inputGrid.add(userIDLabel);
		inputGrid.add(userIDField);
		inputGrid.add(passwordLabel);
		inputGrid.add(passwordField);	
		
		
		
	  /******** BUTTONS PANEL ************/
		JPanel buttonPanel = new JPanel();
			
		/**
		 * Login button submits the information entered by the
		 * user.
		 */
		logIn = new JButton("Log In");
		logIn.addActionListener(this);
		logIn.setBackground(Color.WHITE);
		logIn.setFocusPainted(false);
		logIn.setFont(new Font("", Font.BOLD, 25));
		logIn.setPreferredSize(new Dimension(350, 50));
		
		/**
		 * The register button opens the Register frame so the user
		 * can fill the form, and receive an account
		 */
		register = new JButton("Register");
		register.addActionListener(this);
		register.setBackground(Color.WHITE);
		register.setFocusPainted(false);
		register.setFont(new Font("", Font.BOLD, 25));
		register.setPreferredSize(new Dimension(350, 50));
		
		buttonPanel.add(logIn);
		buttonPanel.add(register);
		
		
		//*****ADDING PANELS AND SETTING BOUNDS ********
		
		/**
		 * panels are added to the content pane 
		 * and bounds are set for panels with the layout set
		 * to null.
		 */
		formPanel.add(inputGrid);
		mainPanel.add(buttonPanel);
	
			//Setting bounds for panels
		imagePanel.setBounds(0,0,1500, 250);
		formPanel.setBounds(0, 350,1500, 600);
		buttonPanel.setBounds(360, 640,800, 120);

		mainPanel.add(imagePanel);
		mainPanel.add(formPanel);
			
			//Setting colours to panels
		imagePanel.setBackground(Color.WHITE);
		mainPanel.setBackground(babyBlue);
		formPanel.setBackground(babyBlue);
		buttonPanel.setBackground(babyBlue);
		
		contentPane.add(mainPanel);
		//*********************************
		setJMenuBar(menuBar);
		setSize(1500, 900);
		setUndecorated(true);	//Removes windows default border
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); //adds black border around the frame
		setVisible(true);
	}
	
	
	/**
	 * Allows logic to be performed when the buttons are
	 * selected by the user.
	 */
	public void actionPerformed(ActionEvent e)
	{	
		//Set the size of the font displayed in a JOptionPane
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "Arial", Font.BOLD, 18))); 

		//********** Menu Bar Buttons ***********/
			//Closes the program when clicked
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
			userIDField.setText(null);
			passwordField.setText(null);
		}
		
		
		
		//******* Form Buttons ***********/	
		
		/**
		 * Should check if the userID is valid.
		 * Should check if the password is valid
		 * Should prompt the user if the ID doesnt Exist
		 * or the password is incorrect
		 * Should show warning message if the user clicks log in
		 * and doesnt enter the fields
		 * 
		 */
		if(e.getActionCommand().equals("Log In"))
		{
				//To check if the fields arent left null when Log in is clicked
			if(userIDField.getText().equals("    "))
			{
				JOptionPane.showMessageDialog(null, "Please enter all fields to log in.", "Woops",
											JOptionPane.ERROR_MESSAGE);
				
			}
				//if the password field is left null
			else if(passwordField.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(null, "Please enter all fields to log in.", "Woops",
											JOptionPane.ERROR_MESSAGE);
				
			}
				//Retrieve data from the DB
			else
			{
				/** CREATE DATABASE CONNECTION **/
				String url ="jdbc:mysql://localhost:3306/enrolmentdb?autoReconnect=true&useSSL=false";
				String user = "root";
				String password = "root";
				
				try
				{
					userID = userIDField.getText();
					String userPassword = String.valueOf(passwordField.getPassword());
				    String user1="";
				    String pass1="";
				    
					Connection dbConnection = DriverManager.getConnection(url, user, password);	
					//Select if the users ID and Password match that of one from the DB
					PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM student where studentID='"+
														userID+"' && password='"+userPassword+"'");
			        ResultSet res = stmt.executeQuery();

					while (res.next()) 
		            {
						//variables are set to compare with the users input
		                user1 = res.getString("studentID");
		                pass1 = res.getString("password");
		            }
		            
		            	//If the users ID and password match
		            if (userID.equals(user1) && userPassword.equals(pass1))
		            {
		            	//Load the users Profile if successful
		                UserProfile profile = new UserProfile();
		                profile.setVisible(true);
		                profile.setLocationRelativeTo(null);
		                dispose();
		            }
		            
		            //If the user doesnt enter the correct information
		            else
		            {
			            JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
			            userIDField.setText(null);
			            passwordField.setText(null);
		            }
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}	// End of try
		}    	// End of login ActionEvent

		/**
		 * Display the registration form and dispose the current form
		 * if the register button is clicked.
		 */
		if(e.getActionCommand() == "Register")
		{
			//Create new Registration form instance
			RegistrationForm registrationFormFrame = new RegistrationForm();
			
			registrationFormFrame.setVisible(true);
			registrationFormFrame.setLocationRelativeTo(null);
			dispose();// Kills current frame
		}
	}
	/*******************************************/
	
	/**
	 * Returns this instance of the users ID to the profile
	 * @return String
	 */
	public static String getStudentInfo()
	{
		return userID;
	}
}
	
	
