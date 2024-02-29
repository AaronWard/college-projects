package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This frame provides a list of courses to choose from. When clicked, a description of 
 * the course appears along with a JButton. Once the button is clicked the user is
 * brought to the Payments page.
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class ViewCourses extends JFrame implements ActionListener, ListSelectionListener
{
	/***  Create Components ***/
	JMenu menu, logOutMenu;
	JMenuItem close, menuReset, menuBack;
	JMenuBar menuBar;	
	JButton back;

	String courseSelected;
	
	JPanel mainPanel, northPanel, courseListPanel, descriptionPanel;
	JLabel courseListLabel, courseDescriptionLabel;
	
		//Array of names for the courses to polulate the Course list
	String [] courseNames = {"B.SC in Computer Science(Honours)", 
							 "Bachelor of Business (Honours) in Digital Marketing",
							 "Bachelor of Business (Honours)",
							 "Bachelor of Engineering (Honours) in Mechatronic Engineering",
							 "Bachelor of Arts (Honours) in Sports Management",
							 "Bachelor of Arts (Honours) in Early Childhood and Education",
							 "Bachelor of Arts (Honours) in Social Studies in Social Care"
							 };
	
	//on the right side of the frame, the text area is setVisble(False)
	//And is only set to true when the user wants to view a course.
	JTextArea descriptionTextArea;
	JButton enrol;
	
	
	static String courseID = "";
	static String courseName = ""; 
	String fileName = "";
	
	JList<String> courseList;
	
	String course1;
	String student1;
	String studentID = Login.getStudentInfo();

	//Font size for the discription
	Font desFont= new Font("", Font.BOLD, 20);
	Color babyBlue = new Color( 138, 179, 250);

	
	/********************************************/
	public ViewCourses()
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
		back.setFocusPainted(false);
		
		
		//used to separate the components in the menu bar
		menuBar.add(menu, BorderLayout.WEST);
		menuBar.add(back, BorderLayout.EAST);
				
	
		/*********************************/
		
		northPanel = new JPanel(new BorderLayout());
		
		courseListLabel = new JLabel("       Course List");
		courseListLabel.setFont(new Font("", Font.BOLD, 60));
		
		courseDescriptionLabel = new JLabel("Course Description        ");
		courseDescriptionLabel.setFont(new Font("", Font.BOLD, 60));
		
		northPanel.add(courseListLabel, BorderLayout.WEST);
		northPanel.add(courseDescriptionLabel, BorderLayout.EAST);
		
		/*********************************/
		
		
		//Panel for coourse names with a JList
		//When the course is selected the description will appear
		//on the description panel.
		courseListPanel = new JPanel(new BorderLayout());
		courseList = new JList<String>(courseNames);
		courseList.addListSelectionListener(this);
			//Add Spacing to the rows in the JList
		courseList.setFixedCellHeight(70);
		courseList.setFont(new Font("", Font.BOLD, 14));
		
		courseListPanel.add(courseList, BorderLayout.CENTER);
		courseListPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		//************************************
		
		//Panel Will display the description off the course when it it selected by the user.
		descriptionPanel = new JPanel(new BorderLayout());
		descriptionPanel.setVisible(false);
		descriptionPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setFont(new Font("", Font.BOLD, 15));
		
		enrol = new JButton("Enrol in this course");
		enrol.addActionListener(this);
		enrol.setBackground(Color.WHITE);
		enrol.setVisible(false);
		
		descriptionPanel.add(descriptionTextArea, BorderLayout.CENTER);
		descriptionPanel.add(enrol, BorderLayout.SOUTH);
		

		
		//*************************/
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		
		//add Panels
		mainPanel.add(northPanel);
		mainPanel.add(courseListPanel);
		mainPanel.add(descriptionPanel);
		
		//Change colours of panels
		northPanel.setBackground(Color.WHITE);
		mainPanel.setBackground(babyBlue);
		
		//Sound Bounds
		northPanel.setBounds(0,0,1500,150);
		mainPanel.setBounds(0,150,1500, 0);
		courseListPanel.setBounds(100, 225, 450, 500);
		descriptionPanel.setBounds(900, 225, 450, 500);
		
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
	
	/**
	 * Paints a horizontal line down the middle of the frame
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
		
		//********** Menu Buttons ********
		
		//If the user wants to go back to their profile
		if(e.getActionCommand().equals("Back"))
		{
			UserProfile profile;
			try
			{	
				//Create a new User Profile frame
				profile = new UserProfile();
				profile.setVisible(true);
				dispose();
				profile.setLocationRelativeTo(null);
			}
			//Catch any exception
			catch (Exception e1)
			{
				e1.printStackTrace();
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
		
		
		
	//************** Course buttons *****************/
		if(e.getSource().equals(enrol))
		{	
			//Brings the user to the Make Payments frame to complete the enrolment.
				MakePayment p;
				try
				{
					p = new MakePayment();
					p.setVisible(true);
					p.setLocationRelativeTo(null);
					dispose();
			
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}

		}
	
	/**
	 * Depending on what the user selects, a string variable is sent to the
	 * setTextArea method, which will use that string in the .TXT file path
	 * to set the text of the text area.
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		int selectionNum = courseList.getSelectedIndex();
		//Used to repaint the graphics line so the description panel
		//doesn't cover it.
		super.repaint();
		
		//Switch statement to pass strings to method
		switch(selectionNum)
		{
			case 0: fileName = "computer_science";
					//name of the course is passed to the MakePayments class
					courseName = courseNames[0];
					//Filename is passed to the setTextArea
					setTextArea(fileName);
					courseID ="001";
					break;
					
			case 1: fileName = "business";
					courseName = courseNames[1];
					setTextArea(fileName);
					courseID ="002";
					break;
					
			case 2: fileName = "business2";
					courseName = courseNames[2];
					setTextArea(fileName);
					courseID ="003";
					break;
					
			case 3: fileName = "engineering";
					courseName = courseNames[3];
					setTextArea(fileName);
					courseID ="004";
					break;
					
			case 4: fileName = "sport";
					courseName = courseNames[4];
					setTextArea(fileName);
					courseID ="005";
					break;
					
			case 5: fileName = "early";
					courseName = courseNames[5];
					setTextArea(fileName);
					courseID ="006";
					break;	
					
			case 6: 
					fileName = "social";
					courseName = courseNames[6];
					setTextArea(fileName);
					courseID ="007";
					break;				
		}
	}	//End of valuesChanges Method
	
	
	
	/**
	 * This method is used to read the file, with the course name passed
	 * from the valueChanged method.
	 * @param courseName
	 */
	@SuppressWarnings("resource")
	public void setTextArea(String courseName)
	{	
		descriptionPanel.setVisible(true);
		descriptionTextArea.setFont(new Font("Calbiri", Font.BOLD, 16));
		enrol.setVisible(true);
		enrol.setFont(new Font("", Font.BOLD, 22));

		//User getClass and getResource to access txt files in the Jar
		Scanner fileReaderScan = new Scanner(getClass().getResourceAsStream("/courseInfo/" + courseName + ".txt"));
		String storeAll = "";
		
			//while loop to read trough lines of the text file
		while(fileReaderScan.hasNextLine())
		{	
			String temp = fileReaderScan.nextLine() + "\n";
			storeAll += temp;
		}
		descriptionTextArea.setText(storeAll);		
	} //end of setTextArea method
	
	
	
	/********************************************/
	
	/**
	 * Pass the course that the user has clicked to
	 * the payments class
	 * @return
	 */
	public static String getCurrentCourseClicked()
	{
		return courseID;
	}
	
	/**
	 * Course name is sent to the MakePayments class
	 * @return
	 */
	public static String getCourseName()
	{
		return courseName;
	}	
}
	


