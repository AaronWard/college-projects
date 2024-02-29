package com.raeside.family;

/**
 * A person class with the first name and surnname attributes.
 * Constructor and getters used to set and pass values.
 * @author aaron
 *
 */
public class Person 
{
	//Initialise first name and surname
	private String firstName;
	private String familyName;
	
	//Constructor method that takes names as a parameter
	Person(String firstName, String familyName)
	{
		//reference to this class
		this.firstName  = firstName;
		this.familyName = familyName;
	}
	
	//returns first name to family class
	public String getFirstName()
	{
		return firstName;
	}
	
	//returns surname to family class
	public String getFamilyName()
	{		
		return familyName;
	}

}

