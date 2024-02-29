package com.raeside.family.exceptions;

/**
 * A custom made exception that 
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class FirstNameExistsException extends Exception 
{	
	/**
	 * 
	 * @param message
	 */
	public FirstNameExistsException(String message)
	{
		super(message);
		
		//use fill stack trace to suppress lines
		fillInStackTrace();
	}
	
	/**
	 * Used to suppress red writing when fillStackTrace is used
	 */
	public Throwable fillInStackTrace() {
        return null;
    } 
	
}
