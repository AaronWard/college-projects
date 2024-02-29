package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 * This class, which extends JFrame, will be the users profile
 * when they log onto the system. From here they can view their courses 
 * or check the course list. They can also edit their information
 * @author aaron
 */
@SuppressWarnings("serial")
public class UserProfile extends JFrame implements ActionListener
{


	/***  Create Components ***/
	JMenu menu;
	JMenuItem close, menuLogOut, editProfile;
	JMenuBar menuBar;	
	JButton logOut;

	//JPanels
	JPanel mainPanel, northPanel, outterLeftPanel, innerLeftPanel, 
			 innerRightPanel, linePanel; 
	//JButtons
	JButton viewCourses, myCourses;
	
	JLabel profileLabel;
	JLabel nameLabel, getNameLabel, addressLabel, getAddressLabel,
		   numberLabel, getNumberLabel, studentIDLabel, getStudentIDLabel,
		   dateOfBirthLabel, getDateOfBirthLabel;

	Color babyBlue = new Color( 138, 179, 250);
	Color panelBlue = new Color(174, 222, 253);
	Font myFont= new Font("", Font.BOLD, 25);
	
	String studentID = Login.getStudentInfo(); //Gets the login instance of studentID
	String name;
	String address;
	String DOB;
	String number;
	
	/**
	 * A GUI that displays the users information on the left side of the frame. 
	 * and also provides the users to view the course list and view the courses
	 * they have already enrolled on.
	 * @throws SQLException
	 */
	public UserProfile() throws SQLException
	{
		Container contentPane = getContentPane();
		
		
		BorderLayout menuBorder = new BorderLayout();
		
		/******** MENU *********/
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(1500, 50));  
		menuBar.setBackground(Color.WHITE);
		menuBar.setLayout(menuBorder);
	
		//Create Options Menu
		menu = new JMenu("Options");
		menu.setFont(new Font("", Font.BOLD, 18));
		menu.add(Box.createHorizontalStrut(200)); //Widens the options menu
		
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 18));

		menuLogOut = new JMenuItem("Log Out");
		menuLogOut.addActionListener(this);
		menuLogOut.setFont(new Font("", Font.BOLD, 18));
		
				
		//add options menu to menuBar
		menu.add(menuLogOut);
		menu.addSeparator();
		menu.add(close);
		
		// create back button
		logOut= new JButton("Log Out");
		logOut.setFocusPainted(false);
		logOut.setBackground(new Color( 201, 226, 233));
		logOut.setFont(new Font("", Font.BOLD, 18));
		logOut.setMargin(new Insets(10, 20, 10, 30));
		logOut.addActionListener(this);
		
		menu.setMnemonic(KeyEvent.VK_O);

		
		//used to separate the components in the menu bar
		menuBar.add(menu, BorderLayout.WEST);
		menuBar.add(logOut, BorderLayout.EAST);
				
		
//************************ DATEBASE CONNECTION ************************************
		
		/**
		 * The following retrieves the information of the user from the database
		 * and casts the values to the JLabels that are displayed in the profile
		 */
		String url ="jdbc:mysql://localhost:3306/enrolmentdb?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
			
			//get a connection to the driver
		Connection dbConnection = DriverManager.getConnection(url, user, password);
			//Select all the information WHERE in the row of the users ID
		PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM student where studentID = '"
									+studentID +"'");
		ResultSet res = stmt.executeQuery();
		
		//assign the DB values to the variables to be displayed
		while (res.next()) 
        {
			name = res.getString("studentname");
			address = res.getString("studentaddress");
			number= res.getString("studentnum");
			DOB = res.getString("DOB");
        }
		
		/**
		 * Because the name textfield in the registration form uses a formatter
		 * the empty spaces are stored in the DB. The following line is used to remove
		 * all the white spaces in the name VARCHAR.
		 */
		name = name.replaceAll("\\s+$", "");
			
//***********************************************************************************		

		
		northPanel = new JPanel();
			
		profileLabel = new JLabel(name + "'s Profile");
		profileLabel.setFont(new Font("", Font.BOLD, 75));
		northPanel.add(profileLabel);

		//Main Panel	
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
	
			//Left side of the profile with users information
		innerLeftPanel = new JPanel();
		innerLeftPanel.setLayout(new GridLayout(5, 2, 0, 0));
			
			//Labels to display information
		nameLabel = new JLabel(" Name:");
		nameLabel.setFont(myFont);
		getNameLabel = new JLabel(name); //Will take the students name from the student class
		getNameLabel.setFont(myFont);
				
		addressLabel = new JLabel(" Address:");
		addressLabel.setFont(myFont);
		getAddressLabel = new JLabel("<html>" + address + "</html>");	//HTML used to wrap text
		getAddressLabel.setFont(myFont);
			
		numberLabel = new JLabel(" Number: ");
		numberLabel.setFont(myFont);
		getNumberLabel = new JLabel(number);
		getNumberLabel.setFont(myFont);
			
		dateOfBirthLabel = new JLabel(" Date of birth:");
		dateOfBirthLabel.setFont(myFont);
		getDateOfBirthLabel = new JLabel(DOB);
		getDateOfBirthLabel.setFont(myFont);
			
		studentIDLabel = new JLabel(" Student ID: ");
		studentIDLabel.setFont(myFont);
		getStudentIDLabel = new JLabel(studentID);
		getStudentIDLabel.setFont(myFont);

			//Add all the components to the panel
		innerLeftPanel.add(nameLabel);
		innerLeftPanel.add(getNameLabel);
		innerLeftPanel.add(addressLabel);
		innerLeftPanel.add(getAddressLabel);
		innerLeftPanel.add(numberLabel);
		innerLeftPanel.add(getNumberLabel);
		innerLeftPanel.add(dateOfBirthLabel);
		innerLeftPanel.add(getDateOfBirthLabel);
		innerLeftPanel.add(studentIDLabel);
		innerLeftPanel.add(getStudentIDLabel);	
		//Put a border around th information section
		innerLeftPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

		
		//************************ 
		innerRightPanel = new JPanel();
		innerRightPanel.setLayout(new GridLayout(2,1, 0, 100));

		//Buttons for the main profile 
		viewCourses = new JButton("View Course List");
		viewCourses.addActionListener(this);
		viewCourses.setFont(new Font("", Font.BOLD, 40));
		viewCourses.setBackground(Color.WHITE);
		viewCourses.setFocusPainted(false);
		
		myCourses = new JButton("My Courses");
		myCourses.addActionListener(this);
		myCourses.setFont(new Font("", Font.BOLD, 40));	
		myCourses.setBackground(Color.WHITE);
		myCourses.setFocusPainted(false);
		
		
		
		innerRightPanel.add(viewCourses);
		innerRightPanel.add(myCourses);
		
		
		//******* ADD PANELS AND SET BOUNDS **********
		
		mainPanel.add(northPanel);
		mainPanel.add(innerLeftPanel);
		mainPanel.add(innerRightPanel);
		
		northPanel.setBounds(0,0,1500, 150);	
		mainPanel.setBounds(0, 130, 1500, 600);
		innerLeftPanel.setBounds(80, 210, 500, 500);
		innerRightPanel.setBounds(900, 210, 500, 500);
		
		
		//set Colours of the backgrounds
		northPanel.setBackground(Color.WHITE);
		mainPanel.setBackground(babyBlue);
		innerLeftPanel.setBackground(panelBlue);
		innerRightPanel.setBackground(babyBlue);
		
		contentPane.add(mainPanel);
		/*********************************/
		
		setJMenuBar(menuBar);
		setTitle(" TITLE HERE ");
		setSize(1500, 900);
		//Removes windows default border
		setUndecorated(true);
		//adds black border around the frame
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * Method to paint the vertical line down the middle
	 * off the profile.
	 */
	   public void paint(Graphics g)
	   {
	        super.paint(g); 
	        Graphics2D g2 = (Graphics2D) g;
	        //Thickens the line
	        g2.setStroke(new BasicStroke(3));
	        //Set the positioning of the line
	        Line2D lin = new Line2D.Float(735, 825, 735, 240);
	        g2.draw(lin);
	    }
	
	public void actionPerformed(ActionEvent e)
	{	
		//Change font size in the JOptionPanes
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "Arial", Font.BOLD, 18)));
		
		
		//*********Menu Bar Buttons *************/
		if(e.getActionCommand() == "Log Out")
		{
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "", 
					 JOptionPane.ERROR_MESSAGE);
		
			//Logs out from the users profile if they select yes
			if(result == JOptionPane.YES_OPTION)
			{	
				//New Login form object is created
				Login loginFrame = new Login();
				loginFrame.setVisible(true);
				dispose(); // Close the registration frame
				loginFrame.setLocationRelativeTo(null);	
			}
		}
		
		//Close the program
		if(e.getSource().equals(close))
		{
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "", 
					 JOptionPane.ERROR_MESSAGE);
		
			//Closes the program if the user clicks yes
			if(result == JOptionPane.YES_OPTION)
			{	
				System.exit(0);
			}
		}
		
		//Loads the view courses frame if button is clicked
		if(e.getSource().equals(viewCourses))
		{
			ViewCourses courseFrame = new ViewCourses();
			courseFrame.setVisible(true);
			courseFrame.setLocationRelativeTo(null);
			dispose();
		}
		
		//loads the My Courses frame if the button is clicked
		if(e.getSource().equals(myCourses))
		{
			MyCourses mc = new MyCourses();
			mc.setVisible(true);
			mc.setLocationRelativeTo(null);
			dispose();
		}
	}	
}