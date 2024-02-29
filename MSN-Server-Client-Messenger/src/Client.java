import java.net.*;
import java.io.*;
import java.util.*;


public class Client  {

	// Object Input Stream to handle the sending of Package
	private ObjectInputStream input;		// to read from the socket
	private ObjectOutputStream output;		// to write on the socket
	private Socket socket;

	// if I use a GUI or not
	private ClientGui gui;
	Package packageObject;
	
	// the server, the port and the username
	private String server;
	private String username;
	private int port;
	
	
	// int DEBUG  Used for debugging the program
	public int DEBUG;

	//	 Constructor call when used from the ClientGui
	Client(String server, int port, String username, ClientGui gui, int DEBUG) {
		if(DEBUG == 1)
			System.out.println("CLIENT: CONSTRUCTOR");
		this.server = server;
		this.port = port;
		this.username = username;
		this.gui = gui;
		this.DEBUG = DEBUG;
	}
	
	
	// start client
	 
	public boolean start() {
		// try to connect to the server
		try {
			if(DEBUG == 1)
				System.out.println("CLIENT: STARTING SOCKET");
			socket = new Socket(server, port);
		} 
		// if it failed not much I can so
		catch(Exception ec) {
			if(DEBUG == 1)
				System.out.println("CLIENT: SOCKET FAILED");
			gui.appendStatusWithException("Please check if the server is running");
			return false;
		}
		
		if(DEBUG == 1)
			System.out.println("CLIENT: SOCKET STARTED ");
		
		String msg = "Welcome to ITB Messenger " + username ;
		gui.startChatWithString(msg);
	
		/* Creating both Object Stream */
		try
		{
			if(DEBUG ==1){
				System.out.println("CLIENT: GETTING OBJECT INPUT STREAM");
			}
			input  = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			gui.appendStatusWithException("Exception creating new Input/output Streams");
			return false;
		}

		// creates the Thread to listen from the server 
		new ListenFromServer().start();
		
		// Send our username to the server this is the only message that we
		// will send as a String. 
		// All other messages will be Package objects
		try
		{
			output.writeObject(username);
		}
		catch (IOException eIO) {
			gui.appendStatusWithException("Could not Login");
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}
	
	
    //To send a message to the server
	void sendPackage(Package packageObject) {
		try {
			if(DEBUG == 1)
				System.out.println("CLIENT: SENDING PACKAGE");
			output.writeObject(packageObject);
		}
		catch(IOException e) {
			gui.appendStatusWithException("Could not Send Package");
		}
	}

	
	//to close connection or if connectioon fails
	 
	private void disconnect() {
		if(DEBUG == 1)
			System.out.println("CLIENT: DISCONNECTING");
		try { 
			if(input != null) input.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(output != null) output.close();
		}
		catch(Exception e) {} // not much else I can do
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do
		
		gui.connectionFailed();
			
	}
	
	
	//listens from server passes packet object
	class ListenFromServer extends Thread {
		
		public void run() {
			while(true) {
				try {
					//if(DEBUG == 1)
					//	System.out.println("LISTEN FROM SERVER: STARTED");
					
					Object object = input.readObject();
					
					if (object instanceof Package){
					    Package packageObject = (Package) object; 
					
					    if(DEBUG == 1)
							System.out.println("LISTEN FROM SERVER: RECIEVED PACKAGE");
						
					    if(packageObject.getType() == Package.LOGOUT){
							gui.appendChatAreaWithLogout(packageObject);
						}
					    if(packageObject.getType() == Package.WHOISONLINE){
							gui.createOnlineUsersList(packageObject);
						}
						else if(packageObject.getType() == Package.MESSAGE){
							gui.appendChatAreaWithMessage(packageObject);
						}
						else if(packageObject.getType() == Package.NUDGE){
							gui.simulateNudge(packageObject);
						}
					}
					else{
						try {
							String message = (String) input.readObject();
							if(DEBUG ==1){
								System.out.println("LISTEN FROM SERVER: RECIEVED MESSAGE");
							}
							gui.appendChatAreaWithString(message);
						}
						catch(IOException e) {
							gui.connectionFailed();
							break;
						}
						// can't happen with a String object but need the catch anyhow
						catch(ClassNotFoundException e2) {
							gui.connectionFailed();
							break;
						}
					}
				}
				catch(IOException e) {
					if(DEBUG == 1)
						System.out.println("LISTEN FROM SERVER: SERVER CLOSED CONNECTION");
					gui.connectionFailed();
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch(ClassNotFoundException e2) {
					if(DEBUG == 1)
						System.out.println("LISTEN FROM SERVER: CRASH DUE TO NOT USING STRING");
					gui.connectionFailed();
					break;
				} catch (InterruptedException e3) {
					e3.printStackTrace();
				}
			}
		}
	}
}
