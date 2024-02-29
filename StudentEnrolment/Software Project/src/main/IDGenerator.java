package main;

import java.util.Random;
/**
 * This class is used to generate an ID when the user has submitted 
 * the form
 * @author aaron
 */
public class IDGenerator
{
	public String generator()
	{
		Random random = new Random();
		String idString ="";
		
		//Array of size 4
		int [] idArray = new int [4];
		
		for(int i = 0; i < idArray.length; i++)
		{
			//Populate the array with a random number
			idArray[i] = random.nextInt(9)+1;
			//Concatenate the indices in the array to form
			//a string for the ID
			idString += idArray[i] +"";
		}
		return idString;
	}
}
