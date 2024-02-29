import java.io.*;
import java.util.Date;
/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server. 
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no 
 * need to count bytes or to wait for a line feed at the end of the frame
 */
public class Package implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	// The different types of message sent by the Client
	// WHOISONLINE to receive the list of the users connected
	// MESSAGE an ordinary message
	// NUDGE is a notification alert
	// LOGOUT to disconnect from the Server
	static final int WHOISONLINE = 0, MESSAGE = 1, NUDGE = 2, LOGOUT = 3;
	private String user;
	private Date date;
	private int type;
	private String message;
	private String[] list;
	
	// constructor
	Package(Date date, String user, int type, String message) {
		this.date = date;
		this.user = user;
		this.type = type;
		this.message = message;
	}
	
	Package(Date date,String user, int type, String[] list){
		this.date = date;
		this.user = user;
		this.type = type;
		this.list = list;
	}
	
	void setDate(Date date){
		this.date = date;
	}
	
	void setList(String list[]){
		this.list = list;
	}
	
	Date getDate(){
		return date;
	}
	
	// getters
	int getType() {
		return type;
	}
	
	String getUser(){
		return user;
	}
	
	String getMessage() {
		return message;
	}
	
	String[] getList(){
		return list;
	}
}
