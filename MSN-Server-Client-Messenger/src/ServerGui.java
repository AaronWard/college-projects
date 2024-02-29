import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

/*
 * The server as a GUI
 */
public class ServerGui extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 1L;
	// the stop and start buttons
	private JButton startButton, stopButton;
	//JTextArea for the events log and online pane
	private JTextArea event, online;
	//JLabel for status bar
	private JLabel statusLabel;
	//Assignment Management Port
	private int port;
	//Server Object
	private Server server;
	//Logging time stamp String
	private String timeStamp;
	
	public int DEBUG = 1;
	
	
	//Construct the window and receive the port to open on
	ServerGui(int port, int DEBUG) {
		
		this.DEBUG = DEBUG;
		this.port = port;
		
		//Setup GUI
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//Instantiate Server Object
		server = null;
		
		
		//Setup the North Panel
		JPanel north = new JPanel();
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		north.add(startButton);
		north.add(stopButton);
		c.add(north, BorderLayout.NORTH);
				
		
		//Setup Center Panel
		JPanel centre = new JPanel(new GridLayout(2,1));

		//Events Log
		JPanel eventsPanel = new JPanel(new BorderLayout());
		eventsPanel.add(new JLabel("Events Log"), BorderLayout.PAGE_START);
		
		event = new JTextArea(10,20);
		event.setLineWrap(true);
		event.setEditable(false);
		
		JScrollPane eventsPane = new JScrollPane(event);
		eventsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		eventsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		eventsPanel.add(eventsPane, BorderLayout.CENTER);
		centre.add(eventsPanel);
		
		
		//Online Users List
		JPanel onlinePanel = new JPanel(new BorderLayout());
		onlinePanel.add(new JLabel("Online Users"), BorderLayout.PAGE_START);
		
		online = new JTextArea(10,20);
		online.setLineWrap(true);
		online.setEditable(false);
		
		JScrollPane onlinePane = new JScrollPane(online);
		onlinePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		onlinePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		onlinePanel.add(onlinePane, BorderLayout.CENTER);
		centre.add(onlinePanel);
		
		
		//Add centre pane to GUI
		c.add(centre, BorderLayout.CENTER);
		
		
		//Setup status panel
		JPanel status = new JPanel();
		statusLabel = new JLabel("", SwingConstants.CENTER);
		status.add(statusLabel);
		c.add(status, BorderLayout.PAGE_END);
		
		
		//Create a new Server & start as thread
		server = new Server(port, this, DEBUG);
		new ServerRunning().start();
		startButton.setEnabled(false);
		stopButton.setEnabled(true);
		updateStatusLabelWithMessage("start");
		
		
		//Window Properties
		setTitle("CDR Messenger Server");
		addWindowListener(this);
		setSize(400, 600);
		setVisible(true);

	}//END Constructor
	
	/**
	 * Append Online Area with list from packageObject
	 * @param packageObject
	 */
	public void appendOnlineAreaWithStringArray(String[] list) {
		if(DEBUG == 1){
			System.out.println("list is: ");
		}
		
		String onlineUsers = new String();
		if(list != null){
			for(int i = 0; i <list.length; i++){
				if(DEBUG == 1){
					System.out.println("list " + i + ": " + list[i]);
				}
				onlineUsers += list[i] + "\n";
			}
		}
		online.setText(onlineUsers);
		
		if(!online.getText().isEmpty()){
			online.setCaretPosition(online.getText().length() - 1);	
		}
	}
	

	/**
	 * Append the Events Text Area with a String
	 * @param message
	 */
	public void appendEventsAreaWithString(String message) {
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
		
		event.append(timeStamp + ": " + message + "\n");
		event.setCaretPosition(event.getText().length() - 1);
	}
	
	public void appendEventsAreaWithNudge(Package packageObject) {

		String message = timeStamp + ": " + packageObject.getUser() + " sent a nudge \n";
				
		event.append(message);
		event.setCaretPosition(event.getText().length() - 1);
	}
	
	/**
	 * Append the Events Text Area with a list from packageObject
	 * @param packageObject
	 */
	public void appendEventsAreaWithLogin(Package packageObject) {
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
		//String message = timeStamp + ": " + packageObject.getUser() + ": HAS LOGGED IN";
				
		event.append(packageObject.getMessage());
		event.setCaretPosition(event.getText().length() - 1);
		
	}
	
	public void appendEventsAreaWithMessage(Package packageObject) {
		if(DEBUG == 1) {
			System.out.println("SERVER GUI: write an event for message");
		}
		
		String message = timeStamp + ": " + packageObject.getUser() + " sent a message \n";
				
		event.append(message);
		event.setCaretPosition(event.getText().length() - 1);
	}
	


	public void appendEventsAreaWithWhoIsOnline(Package packageObject) {
		if(DEBUG == 1) {
			System.out.println("SERVER GUI: write an event for message");
		}
		
		String message = timeStamp + ": " + packageObject.getUser() + " checked who is online \n";
				
		event.append(message);
		event.setCaretPosition(event.getText().length() - 1);
	}
	
	/*
	 * Update status bar message with current status
	 * @param String
	 */
	public void updateStatusLabelWithMessage(String message) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

		if(message.equals("start")) {
			statusLabel.setText("Server Started on port " + port);
			appendEventsAreaWithString("Server Started on port " + port);
			statusLabel.setForeground(Color.GREEN);
		}
		else if (message.equals("stop")) {
			statusLabel.setText("Server Stopped");
			statusLabel.setForeground(Color.RED);
		}
	}
	

	//Run when Start/Stop button clicked
	public void actionPerformed(ActionEvent e) {
		
		//If server is running
		if(server != null) {
			server.stop();
			server = null;
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			updateStatusLabelWithMessage("stop");
		}
		//If server isn't running
		else {
			//Create a new Server
			server = new Server(this.port, this, DEBUG);
			//Start new Thread
			new ServerRunning().start();
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			updateStatusLabelWithMessage("start");
		}
	}

	/*
	 * If the user click the X button to close the application
	 * I need to close the connection with the server to free the port
	 */
	public void windowClosing(WindowEvent e) {
		// if my Server exist
		if(server != null) {
			try {
				server.stop();			// ask the server to close the connection
			}
			catch(Exception eClose) {
			}
			server = null;
		}
		// dispose the frame
		dispose();
		System.exit(0);
	}
	
	// I can ignore the other WindowListener method
	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	
	/*
	 * A thread to run the Server
	 */
	class ServerRunning extends Thread {
		public void run() {
			if(DEBUG == 1){
				System.out.println("SERVER RUNNING: STARTED");
			}
			server.start();         // should execute until if fails
			// the server failed
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			appendEventsAreaWithString("Server Stopped!");
			server = null;
		}
	}
}
