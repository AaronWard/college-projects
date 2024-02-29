package com.raeside.family;

import com.raeside.family.exceptions.FirstNameExistsException;
import com.raeside.family.exceptions.SurnameMismatchException;

/**
 * The driver class that is used to add members to the family.
 * The surname must be the same as "Robinson" and the first name can not already
 * exist in the family.
 * 
 * @author aaron
 * @date 10-3-16
 * @group 4
 * 
 */
public class MakeFamilyRobinson
{

	/**
	 * Main class to create person objects and pass String names to the person class
	 * @param args
	 * @throws SurnameMismatchException
	 * @throws FirstNameExistsException
	 */
	public static void main(String[] args) throws SurnameMismatchException, FirstNameExistsException 
	{
		//person objects created with names
		Person member1 = new Person("John","Robinson");
		Person member2 = new Person("Paul","Robinson");
		Person member3 = new Person("Peter","Robinson");
		Person member4 = new Person("Missy","Robinson");
		//This will invoke a FirsNameExistsException
		Person member5 = new Person("John","Robinson"); 
		
		
		//New person object created
		//This will invoke a SurnameMismatchException
		Person member6 = new Person("Aaron", "Ward");
		
		
		//Family object created
		Family robinsons = new Family("Robinson");
		
		try 
		{
			//Adding people to the family
			robinsons.addFamilyMember(member1);
			robinsons.addFamilyMember(member2);
			robinsons.addFamilyMember(member3);
			robinsons.addFamilyMember(member4);
			robinsons.addFamilyMember(member5);
		}
		
		//If the first name already exists,  exception is caught
		catch(FirstNameExistsException ex) 
		{
			ex.printStackTrace();
		}
		
		//try to add custom person to family
		try
		{
			robinsons.addFamilyMember(member6);
		}
		
		catch(SurnameMismatchException e)
		{
			e.printStackTrace();
		}

		//Family list array, populated with the members of family
		String[] familyList = robinsons.getFamilyMembers();
		
		System.out.println("\n");
		
		//Print family array to console
		for(int i=0;i<familyList.length;i++) 
		{
			System.out.println(familyList[i]);
		}
	}
}
