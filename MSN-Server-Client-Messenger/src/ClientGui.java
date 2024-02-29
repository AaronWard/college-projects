import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



//this class creates client Gui
 
public class ClientGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// DEBUG - Used for debugging the program
	public int DEBUG;
	
	//Menu Bar for the Gui
	private JMenuBar menuBar = new JMenuBar();
		
	// Labels for the Gui
	private JLabel labelMessage, labelServer, labelPort, labelStatus;
	// Used to hold the users input
	private JTextField messageField;
	// Buttons for the Gui
	private JButton login, logout, whoIsOnline, nudge, send;
	// Area to store the Chat room messages
	private JTextArea chatArea;
	// List of online users
	private JList onlineList;
	
	// Connected to the Server
	private boolean connected;
	
	// Skip the first Who is online Package
	private boolean show;
	private ImageIcon ico = new ImageIcon("images/MSN3.png");
	
	// An instance of the Client
	private Client client;

	// Default setting for the Client
	private String host;
	private int port;
	private String username;
	
	// Default settings for the Font
	private Font myFont;
	private String fontName = "Sherif";
	private int style = Font.PLAIN;
	private int size = 12;
	
	// Default settings for the nudge
	private int nudgeCount = 0;
	private int sleepTime = 0;
	private String track;
	private String track1;
	private String track2;

	//Logging time stamp String
	private String timeStamp;	
	
	/**
	 * Constructor to build the Gui
	 * 
	 * @param host - the host name.
	 * @param port - the port number.
	 * @param username - the Clients username.
	 * @param DEBUG - Debug number.
	 * @throws InterruptedException 
	 */
	ClientGui(String host, int port, String username, int DEBUG) throws InterruptedException {
		this.host = host;
		this.port = port;
		this.username = username;
		this.DEBUG = DEBUG;

		//Get the main Content Page
		Container c = getContentPane();
		
		//Set up the Four panels
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel messagePanel = new JPanel(new BorderLayout());
		JPanel actionPanel = new JPanel(new GridLayout(4,2));
		JPanel buttonPanel = new JPanel(new GridLayout(1,5));
		
		//Setup messagePanel
		chatArea = new JTextArea();
		messagePanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
		chatArea.setEditable(false);
				
		//Setup actionPanel
		labelMessage = new JLabel();
		messageField = new JTextField();
		messageField.addActionListener(this);
		actionPanel.add(labelMessage);
		actionPanel.add(messageField);
		
		//Create the 5 buttons
		login = new JButton("Login");
		logout = new JButton("Logout");
		whoIsOnline = new JButton("Who's Online");
		nudge = new JButton("Nudge");
		send = new JButton("Send");
		
		//Set up Buttons for Login
		login.setEnabled(true);
		logout.setEnabled(false);		
		whoIsOnline.setEnabled(false);
		nudge.setEnabled(false);
		send.setEnabled(false);
		
		// Add the Action Listener
		login.addActionListener(this);
		logout.addActionListener(this);
		whoIsOnline.addActionListener(this);
		nudge.addActionListener(this);
		send.addActionListener(this);
		
		//Adding buttons to button panel
		buttonPanel.add(login);
		buttonPanel.add(logout);
		buttonPanel.add(whoIsOnline);
		buttonPanel.add(nudge);
		buttonPanel.add(send);
		
		//add button panel to action panel
		actionPanel.add(buttonPanel);
		
		labelStatus = new JLabel("", SwingConstants.CENTER);
 		actionPanel.add(labelStatus);
		
		//Add all components to the mainPanel
		mainPanel.add(messagePanel, BorderLayout.CENTER);
		mainPanel.add(actionPanel, BorderLayout.SOUTH);
		
		//Add mainPanel to the GUI
		c.add(mainPanel);

		//Window properties
		setTitle("ITB Messenger - " + username);
		setSize(600, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		messageField.requestFocus();
		
	
		login();
		
	}

	// called by the GUI is the connection failed
	// we reset our buttons, label, textfield
	void connectionFailed() {
		//set instance of the client to null
		client = null;
		
		//append the gui
		chatArea.setText("Please Log in \n\n");		
		labelStatus.setText("Offline");
		labelStatus.setForeground(Color.RED);
		
		// enable the login button
		login.setEnabled(true);
		
		// disable the other 4 button
		logout.setEnabled(false);
		whoIsOnline.setEnabled(false);
		nudge.setEnabled(false);
		send.setEnabled(false);

		connected = false;
	}
	
	private void login() throws InterruptedException {
		// try creating a new Client with GUI
		client = new Client(host, port, username, this, DEBUG);
		// test if we can start the Client
		if(!client.start())
			return;
		
		if(DEBUG == 1)
			System.out.println("CLIENT GUI: CLIENT LOGGED IN");
		
		//We don't want to see this first Who is online package but we want the result
		show = false;
		client.sendPackage(new Package(new Date(), username, Package.WHOISONLINE, ""));	
				
		messageField.setText("");
		labelMessage.setText("Enter your message below");
		labelStatus.setText("Online");
		labelStatus.setForeground(Color.GREEN);;
		connected = true;
		
		// disable login button
		login.setEnabled(false);
		
		// enable the 4 buttons
		logout.setEnabled(true);
		whoIsOnline.setEnabled(true);
		nudge.setEnabled(true);
		send.setEnabled(true);
		
		track1 = "src/sounds/newalert.wav";


		Thread audio = new Thread() {
		    public void run() {
		    	File Sound = new File (track1);
				 try{
					 Clip clip = AudioSystem.getClip();
					 clip.open(AudioSystem.getAudioInputStream(Sound));
					 clip.start();
					 
					 Thread.sleep(clip.getMicrosecondLength()/1000);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
		    }
		};
		
		audio.start();
		
		audio.join();
	}
		
	
	//Button or JTextField clicked
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		// if it is the Logout button
		if(o == logout) {
			if(DEBUG ==1){
				System.out.println("\n\nCLIENT GUI: LOGOUT PRESSED");
			}
			client.sendPackage(new Package(new Date(), username, Package.LOGOUT, ""));
			return;
		}
		// if it the who is online button
		if(o == whoIsOnline) {
			if(DEBUG ==1){
				System.out.println("\n\nCLIENT GUI: WHO IS ONLINE PRESSED");
			}
			show = true;
			client.sendPackage(new Package(new Date(), username, Package.WHOISONLINE, ""));				
			return;
		}
		// if it is the nudge button
		if(o == nudge) {
			if(DEBUG ==1){
				System.out.println("\n\nCLIENT GUI: NUDGE PRESSED");
			}
			client.sendPackage(new Package(new Date(), username, Package.NUDGE, ""));				
			return;
		}

		// if it is the send button
		if(o == send) {
			if(connected){
				if(DEBUG ==1){
					System.out.println("\n\nCLIENT GUI: SEND PRESSED ");
				}
				nudgeCount = 0;
				
				//Send as long as messageField is not empty
				if(!messageField.getText().isEmpty()) {
					client.sendPackage(new Package(new Date(), username, Package.MESSAGE, messageField.getText()));				
					messageField.setText("");
				}
				return;
			}
		}
		
		// if return is hit to send
		if(o == messageField) {
			if(connected){
				if(DEBUG ==1){
					System.out.println("\n\nCLIENT GUI: RETURN HIT ");
				}
				nudgeCount = 0;

				//Send as long as messageField is not empty
				if(!messageField.getText().isEmpty()) {
					client.sendPackage(new Package(new Date(), username, Package.MESSAGE, messageField.getText()));				
					messageField.setText("");
				}
				return;
			}
		}
		
		if(o == login) {
			if(DEBUG ==1){
				System.out.println("\n\nCLIENT GUI: LOGIN PRESSED");
			}
			try {
				login();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Append Chat Area with Message from Package
	 * @param packageObject
	 * @throws InterruptedException 
	 */
	public void appendChatAreaWithMessage(Package packageObject) throws InterruptedException {
		if(DEBUG == 1)
			System.out.println("CLIENT GUI: DISPLAY MESSAGE");
		
		//Update current time
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

		String message = timeStamp + ": " + packageObject.getUser() + ": " + emojiFixer(packageObject.getMessage() + "\n");
		
		chatArea.append(message);
		chatArea.setCaretPosition(chatArea.getText().length() - 1);
		
		track = "src/sounds/online.wav";


		Thread audio = new Thread() {
		    public void run() {
		    	File Sound = new File (track);
				 try{
					 Clip clip = AudioSystem.getClip();
					 clip.open(AudioSystem.getAudioInputStream(Sound));
					 clip.start();
					 
					 Thread.sleep(clip.getMicrosecondLength()/1000);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
		    }
		};
		
		audio.start();
		
		audio.join();
	}

	/**
	 * This is the nudge plug-in incorporated into the clientGui
	 * 
	 * @param nudgeCount - holds count of the nudges
	 * @throws InterruptedException
	 *
	 */
	public void simulateNudge(Package packageObject) throws InterruptedException {
		//update current time to show time of nudge
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
		//shows message that user sent a nudge
		String message = timeStamp + ": " + packageObject.getUser() + ": sent a nudge\n";
		
		//add message to chat area
		chatArea.append(message);
		chatArea.setCaretPosition(chatArea.getText().length() - 1);
		
		//nudgeCount += 1;
		sleepTime = 100;
		track2 = "src/sounds/nudge.wav";

		if(DEBUG ==1){
			System.out.println("CLIENT GUI: APPLY NUDGE TO GUI: " );
		}
		
				
		Thread audio = new Thread() {
		    public void run() {
		    	File Sound = new File (track2);
				 try{
					 Clip clip = AudioSystem.getClip();
					 clip.open(AudioSystem.getAudioInputStream(Sound));
					 clip.start();
					 
					 Thread.sleep(clip.getMicrosecondLength()/1000);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
		    }
		};
		
		Thread lightShow = new Thread(){
			public void run() {
		    	
		    	chatArea.setBackground(Color.BLACK);
		    	//chatArea.setText(Color.WHITE);
		    	try {
		    		Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	chatArea.setBackground(Color.WHITE);
		    	try {
		    		Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
		};
		
		audio.start();
		lightShow.start();
		
		audio.join();
		lightShow.join();
		
	}
	
	public void appendChatAreaWithLogout(Package packageObject) {
		//Update current time
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

		String message = timeStamp + ": " + packageObject.getUser() + ": has left the chat\n";
		chatArea.append(message);
		chatArea.setCaretPosition(chatArea.getText().length() - 1);
		
	}
	
	public void appendChatAreaWithString(String message) {
		chatArea.append(message + "\n");
		chatArea.setCaretPosition(chatArea.getText().length() - 1);
	}
	
	/**
	 * Append Chat Area With Exception
	 * @param message
	 */
	public void appendStatusWithException(String message) {
		labelStatus.setText(message);
		labelStatus.setForeground(Color.RED);
	}
	
	public void startChatWithString(String message) {
		
		chatArea.setText(message + "\n");
		chatArea.setCaretPosition(chatArea.getText().length() - 1);
	}

	/**
	 * Append Online Area With List
	 * @param packageObject
	 */
	public void createOnlineUsersList(Package packageObject) {
		String list[] = packageObject.getList();
		if(DEBUG ==1){
			System.out.println("CLIENT GUI: LIST OF ONLINE USERS");
			for(int i = 0; i <list.length ;i++){
				System.out.println(list[i]);
			}
		}
		String strlist = "";
		for(int i = 0; i <list.length ;i++){
			strlist += list[i] + "\n";
		}
		if(show == true){
			JFrame frame = new JFrame();

			JOptionPane.showMessageDialog(frame, strlist,"Online Users", 1 , ico);
			show = false;
		} 
	}

	/**
	 * emojiFixer - checks incoming message and alters it if it contains symbols for emojis
	 * @param message - message is to be checked for characters that represent emojis
	 * @return the message with the emojis replacing the text
	 */
	private String emojiFixer(String message){
		String temp;
		
		String loveHeart = "\u2764";
		String happyFace = "\u263a";
		String peace = "\u270c";
		String sadFace = "\u2639";
		String crossBones = "\u2620";

		
		temp = message.replaceAll("<3", loveHeart);
		message = temp;
		
		temp = message.replaceAll(":P", peace);
		message = temp;
		
		temp = message.replaceAll(":[)]", happyFace);
		message = temp;
		
		temp = message.replaceAll(":[(]", sadFace);
		message = temp;
		
		temp = message.replaceAll(":cb", crossBones);
		message = temp;
		
		
		return message;
	}
	
	
}
