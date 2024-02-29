package com.raeside.family.exceptions;

/**
 * A Custom made exception used if the surname entered does not match the family
 * name.
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class SurnameMismatchException extends Exception
{
	/**
	 * Takes surname as a parameter.
	 * @param surname
	 */
	public SurnameMismatchException(String surname)
	{
		super(surname);
		
		//use Fill stack trace to suppress lines
		fillInStackTrace();
	}
	
	public Throwable fillInStackTrace() {
        return null;
    } 

}
