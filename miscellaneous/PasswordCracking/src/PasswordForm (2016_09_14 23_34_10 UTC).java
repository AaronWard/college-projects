import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


/*
 * The purpose of this program is to display the method of hashing in java.
 * it involves a simple GUI that takes input of a username and a password, chosen by the user.
 * The password is hashed using an MD5, SHA (etc) algorithm and stored into a DB.
 * 
 * The char [] from the JPassword frield is converted to string type, and is then converted
 * to bytes in order to be hexed by the algorithm. An enhanced for loop is then used
 * to go trought the digested hex code and convert it to string.
 * 
 * The StringBuffered hashed string is then sent to the DB along with the username.
 * 
 * ALGORITHM FOR THIS:
 * 	try
	{
		bytesOfMessage = unchar.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(unchar.getBytes());
		byte [] b = md.digest();
		
		//conver to hex format
		StringBuffer sb = new StringBuffer();
		for(byte b1 : b)
		{
			sb.append(Integer.toHexString(b1 & 0xFF).toString());
		}
	}
 * 
 * 
 */


@SuppressWarnings("serial")
public class PasswordForm extends JFrame implements ActionListener 
{
	JButton submit; 
	JButton showHash;
	JTextField username;
	JPasswordField passwordField;
	String [] hashType = {"SHA-256", "MD5"} ;
	StringBuffer sb;
	JComboBox cb;
	
	public static void main(String [] args)
	{
		PasswordForm p = new PasswordForm();
		p.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	PasswordForm()
	{
		Container contentPane = getContentPane();
		
		//Create components for frame
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());
		
		
		//form section
		JPanel north = new JPanel();
		
		cb = new JComboBox(hashType);
		username = new JTextField(20);
		passwordField = new JPasswordField(20);
		
		north.add(cb);
		north.add(username);
		north.add(passwordField);
		
		//submit section
		JPanel south = new JPanel();
		submit = new JButton("submit");
		submit.setFont(new Font("", Font.BOLD, 24));
		submit.addActionListener(this);
		
		showHash = new JButton("Show your hash");
		showHash.setFont(new Font("", Font.BOLD, 24));
		showHash.addActionListener(this);
		
		south.add(submit);
		south.add(showHash);
		
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(south, BorderLayout.SOUTH);
		
		contentPane.add(frame);
		
		
		setVisible(true);
		setSize(600, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{				
		
		//Connection variables are declared for database
			String url ="jdbc:mysql://localhost:3306/passworddb?autoReconnect=true&useSSL=false";
			String user = "root";
			String password = "root";
			Connection dbConnection;
			try
			{
				dbConnection = DriverManager.getConnection(url, user, password);

	
				
			    //If the user wanted to submit the username and password
				if(e.getSource().equals(submit))
				{
					String name = username.getText();
					char [] pass = passwordField.getPassword();
					passwordHasher(pass); //Send the password to the hash method
					
					try
					{
						Statement myStatement = dbConnection.createStatement();
						
						
						//Update database
						String sqlStatement = "INSERT INTO user"
											+ "(username, password)"
											+ "VALUES ('" + name + "', '" + sb + "')";
																
						myStatement.executeUpdate(sqlStatement);
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
				
				if(e.getSource().equals(showHash))
				{
					String name = username.getText();
					
					try
					{
						try
						{
							PreparedStatement stmt = dbConnection.prepareStatement("SELECT password FROM user where "
									+ "username ='" + name+ "'");
		
							ResultSet res = stmt.executeQuery();
				
							String pass1 = null;
							while (res.next()) 
				            {
				                pass1 = res.getString("password");
					            if(pass1.equals(null))
								{
						        
						            JOptionPane.showMessageDialog(this,"no username found","Error",JOptionPane.ERROR_MESSAGE);
								}
					            if(cb.getSelectedItem().equals("SHA-256"))
								{
						            JOptionPane.showMessageDialog(this,"Youre " + cb.getSelectedItem() + " hashed password is: \n" + pass1,
						            							"Error",JOptionPane.ERROR_MESSAGE);
						            break;
								}
					            if(cb.getSelectedItem().equals("MD5"))
								{
						            JOptionPane.showMessageDialog(this,"Youre " + cb.getSelectedItem() + " hashed password is: \n" + pass1,
						            							"Error",JOptionPane.ERROR_MESSAGE);
						            break;
								}
				            }
							
							
							
						}
						catch(NullPointerException e2)
						{
							e2.printStackTrace();
						}
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
			catch (SQLException e3)
			{
				e3.printStackTrace();
			}		
	}	
	
	//Method that hashes the password
	public StringBuffer passwordHasher(char[] pass)
	{
		String unchar = "";

		System.out.println("\nPassword:" + pass.toString() + "\n");		
		
		for (int i = 0; i < pass.length; i++) 
		{
			unchar += pass[i];
		}
		System.out.println("unhashed password: " + unchar);
		
		@SuppressWarnings("unused")
		byte[] bytesOfMessage;
		
		
		try
		{	// 1 - change string to bytes array
			bytesOfMessage = unchar.getBytes("UTF-8");
			
			// 2 -  Cryptographic hash generated as MD5 or SHA-1
			MessageDigest md = MessageDigest.getInstance((String) cb.getSelectedItem());
			
			// 3 - updates the input into the digest
			md.update(unchar.getBytes());
			
			// 4 - new bytes array created from digest
			byte [] b = md.digest();
			sb = new StringBuffer();
			
			// 5 - Enhanced for loop to sort trough bytes array and
			//     convert to hex values
			for(byte b1 : b)
			{
				sb.append(Integer.toHexString(b1 & 0xFF).toString());
			}
			
			System.out.println("\n" + cb.getSelectedItem() + " Hashed Password: " + sb);				
		}
		//Incase an non supported hash type is in the digest
		catch (UnsupportedEncodingException | NoSuchAlgorithmException e2)
		{
			e2.printStackTrace();
		}
		return sb;
	}
}
