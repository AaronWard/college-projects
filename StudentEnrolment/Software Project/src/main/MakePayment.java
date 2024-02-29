package main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

/**
 * This frame will consist of a form that allows the user to enter the bank details.
 * The user must complete the form correctly before enrolling on a course. 
 * One the user has enrolled, their information will be sent to the DB.
 * @author aaron
 */
@SuppressWarnings("serial")
public class MakePayment extends JFrame implements ActionListener
{
	
	/***  Create Components ***/
	JMenu menu, logOutMenu;
	JMenuItem close, menuReset, menuBack;
	JMenuBar menuBar;	
	JButton back;
	
	JPanel mainPanel,northPanel,  formPanel, gridPanel, cardButtonPanel,   submitButtonPanel, checkBoxPanel;
	
	JDateChooser expiryDate;
	JButton visaCardButton, masterCardButton, submit, reset;
	
	JLabel cardTypeLabel, cardNumberLabel, expiryLabel, nameOnCardLabel, tcLabel,
			ccv2Label;
	JCheckBox checkBox;
	
	JFormattedTextField cardNumberTextField;
	JFormattedTextField nameOnCardTextField;
	JFormattedTextField ccv2TextField;
	
	//used to check if the user has selected a card type
	Boolean visaSelected = false;
	Boolean masterSelected = false;
	
	String course1 = "";
	String student1 = "";
	
	Color babyBlue = new Color( 138, 179, 250);
	Font payFont = new Font("", Font.BOLD, 35);
	
	//Variables that passed from previous frames
	String studentID = Login.getStudentInfo();
	String courseID = ViewCourses.getCurrentCourseClicked();
	String courseName = ViewCourses.getCourseName();
	
	/********************************************/

	/**
	 * A GUI that contains a form for the user to pay their fees
	 * @throws SQLException
	 */
	public MakePayment() throws SQLException
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
		
			//Create option menu items
		menuReset = new JMenuItem("Reset");
		menuReset.addActionListener(this);
		menuReset.setFont(new Font("", Font.BOLD, 18));
		
		close = new JMenuItem("Close Program");
		close.addActionListener(this);
		close.setFont(new Font("", Font.BOLD, 18));

		menuBack = new JMenuItem("Back");
		menuBack.addActionListener(this);
		menuBack.setFont(new Font("", Font.BOLD, 18));
		
			//add options menu to menuBar
		menu.add(menuReset);
		menu.addSeparator();
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
		northPanel = new JPanel();
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		
		formPanel = new JPanel();
		formPanel.setLayout(new BorderLayout()); //
		
		JLabel paymentLabel = new JLabel("Payments Form");
		paymentLabel.setFont(new Font("", Font.BOLD, 75));
		northPanel.add(paymentLabel);
		
		
		/*********************************/

		
		JLabel descriptionLabel = new JLabel("                      "
											+ "                 "
											+ " Please fill out the form to enroll in " + courseName +".");
		descriptionLabel.setFont(new Font("", Font.BOLD, 23));
		formPanel.add(descriptionLabel);
		
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(5, 2, 1, 35));
		gridPanel.setBorder(BorderFactory.createEmptyBorder(75, 200, 250,300));
		
		//1
		cardTypeLabel = new JLabel("Select Card Type:");
		cardTypeLabel.setFont(payFont);

		//JButtons to choose what card type you are using
		visaCardButton = new JButton("Visa");
		visaCardButton.setPreferredSize(new Dimension(243, 38));
		visaCardButton.setFont(new Font("", Font.ITALIC, 20));
		visaCardButton.setBackground(Color.WHITE);
		visaCardButton.setFocusPainted(false);
		visaCardButton.addActionListener(this);
	
		masterCardButton = new JButton("MasterCard");
		masterCardButton.setBackground(Color.WHITE);
		masterCardButton.setFont(new Font("", Font.ITALIC, 20));
		masterCardButton.setPreferredSize(new Dimension(243, 38));
		masterCardButton.setFocusPainted(false);
		masterCardButton.addActionListener(this);
	
		
		cardButtonPanel = new JPanel(new FlowLayout());
		cardButtonPanel.setBackground(babyBlue);
		cardButtonPanel.add(visaCardButton);
		cardButtonPanel.add(masterCardButton);
		
		
		//2
		cardNumberLabel = new JLabel("Number of card: ");
		cardNumberLabel.setFont(payFont);
		try
		{
			//Only allows numbers to be entered
			MaskFormatter numFormatter = new MaskFormatter("********************");
			numFormatter.setValidCharacters("1234567890 ");
			cardNumberTextField = new JFormattedTextField(numFormatter);
		}
		catch(ParseException e)
		{ 
			e.printStackTrace();  
		}
		

		//3
		nameOnCardLabel = new JLabel("Name on card: ");
		nameOnCardLabel.setFont(payFont);
		try
		{
			MaskFormatter nameFormatter = new MaskFormatter("********************");
			nameFormatter.setValidCharacters("abcdefghijklmnopqrstuvwxyz "
											+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ-");
			nameOnCardTextField = new JFormattedTextField(nameFormatter);
		}
		catch(ParseException e)
		{ 
			e.printStackTrace();  
		}
		

		//4
		ccv2Label = new JLabel("CCV2:");
		ccv2Label.setFont(payFont);
		try
		{
			MaskFormatter ccv2Formatter = new MaskFormatter("***");
			ccv2Formatter.setValidCharacters("1234567890 ");
			ccv2TextField = new JFormattedTextField(ccv2Formatter);
		}
		catch(ParseException e)
		{ 
			e.printStackTrace();  
		}

		//5
		expiryLabel = new JLabel("Expiry date:");
		expiryLabel.setFont(payFont);
		expiryDate = new JDateChooser();
		expiryDate.setFont(new Font("", Font.PLAIN, 15));

		
		//6
		JPanel checkBoxPanel = new JPanel(new FlowLayout());
		checkBoxPanel.setBackground(babyBlue);
		tcLabel = new JLabel("<html>By clicking this you agree to terms and condition:</html>");
		tcLabel.setFont(new Font("", Font.ITALIC, 14));

			//Add all the components to the grid
		gridPanel.add(cardTypeLabel);
		gridPanel.add(cardButtonPanel);
		gridPanel.add(cardNumberLabel);
		gridPanel.add(cardNumberTextField);
		gridPanel.add(nameOnCardLabel);
		gridPanel.add(nameOnCardTextField);
		gridPanel.add(ccv2Label);
		gridPanel.add(ccv2TextField);
		gridPanel.add(expiryLabel);
		gridPanel.add(expiryDate);

		
			//Add label and grid panel to the form
		formPanel.add(descriptionLabel, BorderLayout.NORTH);
		formPanel.add(gridPanel, BorderLayout.SOUTH);
		
		
		
	  //************* Buttons Panel *****************/
		JPanel buttonPanel = new JPanel();
		
			//Sends information to the DB
		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setBackground(Color.WHITE);
		submit.setFont(payFont);
		submit.setFocusPainted(false);
		submit.setPreferredSize(new Dimension(350, 50));
		
			//Clears all fields if the user wants.
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setBackground(Color.WHITE);
		reset.setFont(payFont);
		reset.setFocusPainted(false);
		reset.setPreferredSize(new Dimension(350, 50));
		
		buttonPanel.add(submit);
		buttonPanel.add(reset);
	
		
		//*****************************************//		
		formPanel.add(gridPanel);
		mainPanel.add(buttonPanel);

	
		//Setting bounds for panels
		northPanel.setBounds(0,30,1500, 130);
		formPanel.setBounds(0, 175,1500, 700);
		buttonPanel.setBounds(360, 700,800, 120);


		mainPanel.add(northPanel);
		mainPanel.add(formPanel);
		
		northPanel.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);
		gridPanel.setBackground(babyBlue);
		formPanel.setBackground(babyBlue);
		buttonPanel.setBackground(babyBlue);

		
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

		//********** Menu Buttons ********
		if(e.getActionCommand().equals("Back"))
		{
			try
			{	
				//Brings the user back to the ViewCourses page
				ViewCourses v = new ViewCourses();
				v.setVisible(true);
				v.setLocationRelativeTo(null);
				dispose();
			}
			//Catch any exception
			catch (Exception e1)
			{
				e1.printStackTrace();
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
				nameOnCardTextField.setText	(null);
				cardNumberTextField.setText (null);
				ccv2TextField.setText		(null);
				expiryDate.setDate 			(null);
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
		
		
		//********************************/
		
		if(e.getSource().equals(masterCardButton))
		{
			visaSelected = false;
			masterSelected = true;
			visaCardButton.setContentAreaFilled(false);
			masterCardButton.setContentAreaFilled(true);
		}
		if(e.getSource().equals(visaCardButton))
		{
			visaSelected = true;
			masterSelected = false;
			visaCardButton.setContentAreaFilled(true);
			masterCardButton.setContentAreaFilled(false);
		}
		
		/*
		 * Checks are put in place to see if the user has completed the form correctly
		 */
		if(e.getSource().equals(submit))
		{
			if(visaSelected.equals(false) && masterSelected.equals(false))
			{
				JOptionPane.showMessageDialog(null, "You must select a card type", 
						"Woops", JOptionPane.ERROR_MESSAGE);			
			}
			
			else if(nameOnCardTextField.getText().equals(null))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
			else if(nameOnCardTextField.getText().equals("                    "))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(cardNumberTextField.getText().equals("                    "))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
			else if(ccv2TextField.equals("   "))
			{
				JOptionPane.showMessageDialog(null, "All fields must be complete to submit.", 
						"Woops", JOptionPane.ERROR_MESSAGE);
			}
			
			
			//if the user enters the information in the form correctly
			else
			{
				String name = nameOnCardTextField.getText();
				name = name.replaceAll("\\s+$", "");
				
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
					
					
					ResultSet myRes = myStatement.executeQuery("SELECT * FROM courses where studentid =" + studentID);
					while(myRes.next())
					{
						course1 = myRes.getString("courseid");
						student1 = myRes.getString("studentid");
					}
					
					//Check if the user hasnt already inrolled in this course
					if(course1.equals(courseID) && this.studentID.equals(student1))
					{
						JOptionPane.showMessageDialog(null,"You have already enrolled in this course",
											"Woop", JOptionPane.ERROR_MESSAGE);
					}
					
					//If they havent already enrolled, then enrollment goes as planned
					else
					{
						
						int result = JOptionPane.showConfirmDialog(this, "Do you agree to the terms and conditions?",
													"Terms and conditions", JOptionPane.ERROR_MESSAGE);
						
						//If the user agrees to the terms and conditions
						//the DB is updated and theyre brought to the main profile
						if(result == JOptionPane.YES_OPTION)
						{
							/******** Update Database ***********/
							String sqlStatement = "INSERT INTO courses"
												+ "(courseid, coursename, studentid)"
												+ "VALUES ('" + courseID +"', '" + courseName + "', '" + studentID + "')";
							
							myStatement.executeUpdate(sqlStatement);
							//When the user successfully registers and the DB has been updated
							JOptionPane.showMessageDialog(null,"<html>You have just enrolled in " + courseName + ".<br> "
									+ "  A fee"
									+ " of €50 will be billed to " + name +".</html>", "Enrolment Confirmed", JOptionPane.ERROR_MESSAGE);
							
							
							//New Login form object is created
							UserProfile profile;
							try
							{
								profile = new UserProfile();
								profile.setVisible(true);
								dispose(); // Close the registration frame
								profile.setLocationRelativeTo(null);		
							}
							catch (SQLException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						//If they dont, they are brought to the course list
						else
						{
							ViewCourses vc = new ViewCourses();
							vc.setVisible(true);
							dispose(); // Close the registration frame
							vc.setLocationRelativeTo(null);
						}
					}
				}	
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		} //end of action performed method
	}
}
