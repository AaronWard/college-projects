import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.BoxLayout;

public class StartupGui extends JFrame implements ActionListener {
	
	//set defualt host to localhost 127.0.0.1
	public static String host = "localhost";
	//store username
	public static String username;
	//set port to 1500
	final public static int port = 1500; 
	//set debug to 0 meaning no debugging
	public int DEBUG = 0;
	
	//create buttoms
	JButton serverButton = new JButton("Server");
	JButton clientButton = new JButton("Client");
	JButton bothButton = new JButton("Open Both");
	JButton settingsButton = new JButton("Settings");
	JButton debugButton = new JButton("Enable Debug");
	
	//create settings frame and buttons
	JFrame settingsFrame;
	JTextField ipAddress;
	JButton useLocalHost;
	JButton useIp;
	
	//new image
	ImageIcon ico = new ImageIcon("images/msn4.png");
	
	boolean serverExists;
	
	
    

	public StartupGui(){
		if(DEBUG == 1)
			System.out.println("START UP GUI: CONSTRUCTOR");
		Container c = getContentPane();
		
		//set background to container
	    setContentPane(new JLabel(new ImageIcon("images/background.png")));
		
	    //declare buttons and add lisenters
		debugButton.addActionListener(this);
		getContentPane().setLayout(null);
		serverButton.setBounds(144, 53, 96, 40);
		getContentPane().add(serverButton);
		clientButton.setBounds(252, 53, 96, 40);
		getContentPane().add(clientButton);
		settingsButton.setBounds(252, 106, 96, 40);
		getContentPane().add(settingsButton);
		bothButton.setBounds(144, 103, 96, 43);
		getContentPane().add(bothButton);
		bothButton.addActionListener(this);
		settingsButton.addActionListener(this);
		clientButton.addActionListener(this);
		serverButton.addActionListener(this);
		
		setTitle("ITB Messenger");
		setSize(500,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//this method is for when a user clicks on settings
	public void displaySettings(){
		settingsFrame = new JFrame();
		JPanel panel = new JPanel();
		JLabel serverLabel = new JLabel("Server: ");
		JLabel serverNameLabel = new JLabel("");
		JLabel debugLabel = new JLabel("Debug: ");
		JLabel localHostLabel = new JLabel("localhost");
		useLocalHost = new JButton("Use Local host");
		ipAddress = new JTextField(16);
		useIp = new JButton("Use Ip Address");
		
		
		serverNameLabel.setText(host);
		useLocalHost.addActionListener(this);
		useIp.addActionListener(this);
		
		panel.setLayout(new GridLayout(4,2));
		settingsFrame.getContentPane().add(panel);
		
		panel.add(serverLabel);
		panel.add(serverNameLabel);

		panel.add(localHostLabel);
		panel.add(useLocalHost);
		
		panel.add(ipAddress);
		panel.add(useIp);
		
		panel.add(debugLabel);
		panel.add(debugButton);
		
		settingsFrame.setVisible(true);
		settingsFrame.setSize(350, 200);
		settingsFrame.setLocationRelativeTo(null);
	}

	private void setHost() {
		this.host = host;
		
	}

	
	public static void main(String[] args){
		//run gui
		StartupGui run = new StartupGui();
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		//for client log in
		String message = "Please enter your username";
		String message1	= "(No name name entry will be assigned Anonymous)";
		String title = "Please Log in";
		
		//if statements for button selection
		if(e.getSource() == serverButton){
			new ServerGui(1500, DEBUG);
			debugButton.setEnabled(false);
			bothButton.setEnabled(false);
			serverButton.setEnabled(false);
		}
		else if(e.getSource() == clientButton){
			username = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, ico, null, null);
			if(username.isEmpty())
				username = "Anonymous";
			try {
				new ClientGui(host, port, username, DEBUG);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			debugButton.setEnabled(false);
		}
		else if(e.getSource() == bothButton){
			new ServerGui(port, DEBUG);
			username = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, ico, null, null);
			if(username.isEmpty())
				username = "Anonymous";
			try {
				new ClientGui(host,  port, username, DEBUG);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //Call to new Class
			debugButton.setEnabled(false);
			bothButton.setEnabled(false);
			serverButton.setEnabled(false);
		}
		else if(e.getSource() == settingsButton){
			displaySettings();
		}
		else if(e.getSource() == useIp){
			host = ipAddress.getText().toString();
			settingsFrame.setVisible(false);
			serverButton.setEnabled(false);
		}
		else if(e.getSource() == useLocalHost){
			host = "localhost";
			settingsFrame.setVisible(false);
			serverButton.setEnabled(true);
		}
		else{
			if(DEBUG == 0){
				DEBUG = 1;
				debugButton.setText("Disable Debug");
				settingsFrame.setVisible(false);
			}
			else{
				DEBUG = 0;
				debugButton.setText("Enable Debug");
				settingsFrame.setVisible(false);
			}
			
		}
	}
}
