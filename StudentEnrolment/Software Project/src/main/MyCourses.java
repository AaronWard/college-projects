package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.sql.*;


/**
 * This frame retrieves the users courses that they have enrolled in. 
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class MyCourses extends JFrame implements ActionListener
{
	
	/***  Create Components ***/
	JMenu menu, logOutMenu;
	JMenuItem close, menuBack;
	JMenuBar menuBar;	

	JButton back;

	JPanel mainPanel, northPanel, myCourseListPanel, descriptionPanel;
	
	JTextArea descriptionTextArea;
	
	//Variables that passed from previous frames
	String studentID = Login.getStudentInfo();
	String courseID = ViewCourses.getCurrentCourseClicked();
	String courseName = ViewCourses.getCourseName();
	String course1;
	JList<String> myCourseList = new JList<String>(new DefaultListModel<String>());
	
	Color babyBlue = new Color( 138, 179, 250);

	@SuppressWarnings("rawtypes")
	DefaultListModel listModel = new DefaultListModel();
	
	/********************************************/
	
	/**
	 * Users Courses will be displayed here.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyCourses()
	{
		//Connection variables are declared for database
		String url ="jdbc:mysql://localhost:3306/enrolmentdb?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		
		//Check if the user has already enrolled in that course
		try
		{
			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement myStatement = dbConnection.createStatement();
			
			ResultSet myRes = myStatement.executeQuery("SELECT courseid FROM courses where studentid =" + studentID);
			
			//Count the amount of columns applicable to that user, and print out their courses
			ResultSetMetaData rsmd = myRes.getMetaData();
		    int columnsCount= rsmd.getColumnCount();
			
			while(myRes.next())
			{
				//Gets the users courses and iterates trought the rows in
				//the column to the same amount of the column count
				course1 = myRes.getString ("courseid");
				for(int i = 0; i < columnsCount; i++)
				{
					switch(course1)
					{
						case "001": 
							listModel.addElement("<html>- B.SC in Computer Science(Honours)</html>");
							break;
						case "002":
							listModel.addElement("<html>- Bachelor of Business (Honours) in Digital Marketing</html>");
							break;
						case "003":
							listModel.addElement("<html>- Bachelor of Business (Honours</html>");
							break;
						case "004":
							listModel.addElement("<html>- Bachelor of Engineering (Honours) in Mechatronic Engineering</html>");
							break;
						case "005":
							listModel.addElement("<html>- Bachelor of Arts (Honours) in Sports Management</html>");
							break;
						case "006":
							listModel.addElement("<html>- Bachelor of Arts (Honours) in Early Childhood and Education</html>");
							break;
						case "007":
							listModel.addElement("<html>- Bachelor of Arts (Honours) in Social Studies in Social Care</html>");
							break;
					}
				}	
			}
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		
		//If the user hasnt enrolled in any course
		if(listModel.isEmpty())
		{
			listModel.addElement("      ** YOU HAVE NOT ENROLLED IN ANY COURSES. **");
		}

		myCourseList = new JList(listModel);
		myCourseList.setFont(new Font("Calbiri", Font.BOLD, 40));
		myCourseList.setFixedCellHeight(100);
		
		
		//**************************************************
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
		
		//Closes the programs
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 18));

		menuBack = new JMenuItem("Back");
		menuBack.addActionListener(this);
		menuBack.setFont(new Font("", Font.BOLD, 18));
		
				
		//add options menu to menuBar
		menu.add(menuBack);
		menu.addSeparator();
		menu.add(close);
		
		// create back button
		back = new JButton("Back");
		back.setBackground(new Color( 201, 226, 233));
		back.setFont(new Font("", Font.BOLD, 18));
		back.setMargin(new Insets(10, 20, 10, 40));
		back.addActionListener(this);
		back.setFocusable(false);
		
		menu.setMnemonic(KeyEvent.VK_O);
		
		
		//used to separate the components in the menu bar
		menuBar.add(menu, BorderLayout.WEST);
		menuBar.add(back, BorderLayout.EAST);
				
	
		/*********************************/
		
		northPanel = new JPanel(new BorderLayout());
		JLabel northLabel = new JLabel("<html>The following list displays the courses you have enrolled in.<br> To enrol"
				+ " in more courses, please visit the course list.</html>");
		northLabel.setFont(new Font("", Font.PLAIN, 25));
		northPanel.add(northLabel, BorderLayout.SOUTH);

		
		/*********************************/
		
		
		//Panel for coourse names with a JList
		//When the course is selected the description will appear
		//on the description panel.
		myCourseListPanel = new JPanel(new BorderLayout());

		
		myCourseListPanel.add(myCourseList, BorderLayout.CENTER);
		myCourseListPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		
		//************************************
		
		//Panel Will display the description off the course when it it selected by the user.
		descriptionPanel = new JPanel(new BorderLayout());
		descriptionPanel.setVisible(false);
		descriptionPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setFont(new Font("", Font.BOLD, 15));
	
		
		descriptionPanel.add(descriptionTextArea, BorderLayout.CENTER);
		
		//***********************************
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		
		//add Panels
		mainPanel.add(northPanel);
		mainPanel.add(myCourseListPanel);
		mainPanel.add(descriptionPanel);
		
		//Change colours of panels
		northPanel.setBackground(Color.WHITE);
		mainPanel.setBackground(babyBlue);
		
		//Sound Bounds
		northPanel.setBounds(0,0,1500,150);
		mainPanel.setBounds(0,150,1500, 0);
		myCourseListPanel.setBounds(130, 225, 1200, 500);
		
		
		contentPane.add(mainPanel);
		
		/*********************************/
		setJMenuBar(menuBar);
		setSize(1500, 900);
		//Removes windows default border
		setUndecorated(true);
		//adds black border around the frame
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		setVisible(true);
		setResizable(false);
	}

	
	public void actionPerformed(ActionEvent e)
	{	
		//Change font size in the JOptionPanes
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "Arial", Font.BOLD, 18))); 
		
		/****** MENU BUTTONS ******/
		if(e.getActionCommand().equals("Back"))
		{
			//Load profile and handles a SQL exception
			try
			{
				UserProfile p = new UserProfile();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				dispose();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		
		if(e.getSource().equals(close))
		{
			//
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "", 
					 JOptionPane.ERROR_MESSAGE);
		
			//Closes the program if the user clicks yes
			if(result == JOptionPane.YES_OPTION)
			{	
				System.exit(0);
			}
		}
	}
}