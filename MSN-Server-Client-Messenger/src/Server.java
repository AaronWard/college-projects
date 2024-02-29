import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Server {
	// a unique ID for each connection
	private static int uniqueId;
	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> clientList;
	// if I am in a GUI
	private ServerGui gui;
	// to display time
	private SimpleDateFormat dateFormat;
	// the port number to listen for connection
	private int port;
	// the boolean that will be turned of to stop the server
	private boolean keepGoing;
	
	//List of users online
	private String[] onlineList;
	
	//debug is enabled
	public int DEBUG = 1;
	
	/**
	 * Server Constructor - this is used to set up the server
	 * @param port - this is the port to listen on
	 * @param gui - this is the link to the ServerGui
	 */
	public Server(int port, ServerGui gui, int DEBUG) {
		this.DEBUG  = DEBUG;
		if(DEBUG == 1)
			System.out.println("SERVER: CONSTRUCTOR");
		// GUI or not
		this.gui = gui;
		// the port
		this.port = port;
		// to display hh:mm:ss
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		// ArrayList for the Client list
		clientList = new ArrayList<ClientThread>();
	}
	
	public void start() {
		keepGoing = true;
		/* create socket server and wait for connection requests */
		try 
		{
			if(DEBUG == 1)
				System.out.println("SERVER: STARTED");
			// the socket used by the server
			ServerSocket serverSocket = new ServerSocket(port);

			//Infinite loop to wait for connections
			while(keepGoing) 
			{				
				Socket socket = serverSocket.accept();  	// accept connection
				// if I was asked to stop
				if(!keepGoing)
					break;
				ClientThread thread = new ClientThread(socket);  // make a thread of it
				clientList.add(thread);									// save it in the ArrayList
				thread.start();
			}
			//keep going = false
			
			// I was asked to stop
			try {
				if(DEBUG == 1)
					System.out.println("SERVER: STOPPED");
				serverSocket.close();
				for(int i = 0; i < clientList.size(); ++i) {
					ClientThread thread = clientList.get(i);
					try {
					thread.input.close();
					thread.output.close();
					thread.socket.close();
					}
					catch(IOException ioE) {
						// not much I can do
					}
				}
			}
			catch(Exception e) {
				gui.appendEventsAreaWithString("Exception closing the server and clients: " + e);
			}
		}
		// something went bad
		catch (IOException e) {
            gui.appendEventsAreaWithString(" Exception on new ServerSocket: " + e);
		}
	}		
    //when server is stopped
	protected void stop() {
		keepGoing = false;
		// connect to myself as Client to exit statement 
		// Socket socket = serverSocket.accept();
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {
			// nothing I can really do
			System.out.println("SERVER : SOCKET UNAVAILBlE");
		}
	}

	//send package to all users
	private synchronized void sendPackage(Package packageObject) {
		
		if(DEBUG == 1)
			System.out.println("SERVER: SENDING PACKAGE");
		
		onlineList = new String[clientList.size()];
		
		for(int i = 0; i < clientList.size(); ++i) {
			onlineList[i] = clientList.get(i).username;
		}
		
		packageObject.setList(onlineList);
		
		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for(int i = clientList.size(); --i >= 0;) {
			ClientThread client = clientList.get(i);
			// try to write to the Client if it fails remove it from the list
			if(!client.sendObject(packageObject)) {
				
				clientList.remove(i);
				onlineList = new String[clientList.size()];
				for(int x = 0; x < clientList.size(); ++x) {
					onlineList[x] = clientList.get(x).username;
				}
				//packageObject.setList(clientList);
				gui.appendEventsAreaWithString("Disconnected Client " + client.username + " removed from list.");
			}
		}
		gui.appendOnlineAreaWithStringArray(onlineList);
	}

	// for a client logging off: using the LOGOUT package
	synchronized void remove(int id) {
		// scan the array list until we found the Id
		for(int i = 0; i < clientList.size(); ++i) {
			ClientThread client = clientList.get(i);
			// found it
			if(client.id == id) {
				if(DEBUG ==1){
					System.out.println("SERVER: REMOVE CLIENT");
				}
				gui.appendEventsAreaWithString(client.username + " left the room.");
				clientList.remove(i);
				onlineList = new String[clientList.size()];
				for(int x = 0; x < clientList.size(); ++x) {
					onlineList[x] = clientList.get(x).username;
				}
				gui.appendOnlineAreaWithStringArray(onlineList);
				return;
			}
		}
	}

	//One instance of this thread will run for each client 
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream input;
		ObjectOutputStream output;
		// my unique id (easier for deconnection)
		int id;
		// the Username of the Client
		String username;
		// the only type of message a will receive
		Package packageObject;
		// the date I connect
		String date;

		// Constructor
		ClientThread(Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;
			
			if(DEBUG == 1)
				System.out.println("CLIENT THREAD " + id + ": STARTED");
			
			//Creating both Object Stream 
			try
			{
				// create output first
				output = new ObjectOutputStream(socket.getOutputStream());
				input  = new ObjectInputStream(socket.getInputStream());
				
				// read the username
				username = (String) input.readObject();
				if(DEBUG ==1){
					System.out.println("CLIENT THREAD: " + username + "");
				}
				
				sendPackage(new Package(new Date(), username, Package.WHOISONLINE, ""));
				gui.appendEventsAreaWithString(username + " joined the chat room");
			}
			catch (IOException e) {
				gui.appendEventsAreaWithString("Exception creating new Input/output Streams: " + e);
				return;
			}
			// have to catch ClassNotFoundException
			// but I read a String, I am sure it will work
			catch (ClassNotFoundException e) {
			}
            date = new Date().toString() + "\n";
		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			boolean keepGoing = true;
			while(keepGoing) {
				// read a String (which is an object) passed on login
				try {
					packageObject = (Package) input.readObject();
					if(DEBUG == 1)
						System.out.println("CLIENT THREAD " + id + " : RECIEVED OBJECT");
				}
				//catch if there is a sudden user disconenct
				catch (IOException e) {
					gui.appendEventsAreaWithString(username + " has lost connection.");
					break;				
				}
				catch(ClassNotFoundException e2) {
					break;
				}
				//switch state for client actions
				switch(packageObject.getType()) {
				
				case Package.LOGOUT:
					if(DEBUG == 1)
						System.out.println("CLIENT THREAD " + id + " : RECIEVED LOGOUT");
					sendPackage(packageObject);
					gui.appendOnlineAreaWithStringArray(onlineList);
					keepGoing = false;
					break;
				case Package.MESSAGE:
					if(DEBUG == 1)
						System.out.println("CLIENT THREAD " + id + " : RECIEVED MESSAGE");
					gui.appendEventsAreaWithMessage(packageObject);
					sendPackage(packageObject);
					break;
				case Package.NUDGE:
					if(DEBUG == 1)
						System.out.println("CLIENT THREAD " + id + " : RECIEVED NUDGE");
					gui.appendEventsAreaWithNudge(packageObject);
					sendPackage(packageObject);
					break;
				case Package.WHOISONLINE:
					if(DEBUG == 1)
						System.out.println("CLIENT THREAD " + id + " : RECIEVED WHOISON");
					gui.appendEventsAreaWithWhoIsOnline(packageObject);
					sendPackage(packageObject);
					gui.appendOnlineAreaWithStringArray(onlineList);
					break;
				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}
		
		// try to close everything
		private void close() {
			// try to close the connection
			if(DEBUG ==1){
				System.out.println("CLIENT THREAD " + id + ": CLOSE EVERYTHING");
			}
			try {
				if(output != null) output.close();
			}
			catch(Exception e) {}
			try {
				if(input != null) input.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		/*
		 * send a Package to the Client output stream
		 */
		private boolean sendObject(Package packageObject) {
			// if Client is still connected send the message to it
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				if(DEBUG ==1){
					System.out.println("CLIENT THREAD " + id + ": SEND OBJECT");
				}
				output.writeObject(packageObject);
			}
			// if an error occurs, do not abort just inform the user
			catch(IOException e) {
				gui.appendEventsAreaWithString("Error sending message to " + username);
				gui.appendEventsAreaWithString(e.toString());
			}
			return true;
		}
	}
}

