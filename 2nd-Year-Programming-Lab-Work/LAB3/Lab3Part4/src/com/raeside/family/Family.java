package com.raeside.family;

import java.util.Vector;
import com.raeside.family.exceptions.FirstNameExistsException;
import com.raeside.family.exceptions.SurnameMismatchException;

/**
 * The family class with logic added to ensure that the requirements 
 * are met before the new person can be added to the family.
 * @author aaron
 *
 */
public class Family 
{

		@SuppressWarnings("rawtypes")
		private Vector familyMembers = new Vector();
		private String familyName;
		
		public Family(String familyName) 
		{
			this.familyName = familyName;
		}
		
		/**
		 * 
		 * @param member object passed from the MakeFamilyRobinson class
		 * @throws FirstNameExistsException
		 */
		@SuppressWarnings("unchecked") 
		public void addFamilyMember(Person member) throws FirstNameExistsException, SurnameMismatchException
		{
			//If the name is equal to robinson
			if(member.getFamilyName().equalsIgnoreCase("Robinson"))
			{
				//And the name is a new first name
				if(newFirstName(member.getFirstName())) 
				{
					//then add the member to the family
					familyMembers.addElement(member);
				}
			
				//If its not new, then throw a FirstNameExistsException
				else
				{	
					throw new FirstNameExistsException("This family already has a " + member.getFirstName());
				}
			}
			
			//if the surname is not equal to Robinson
			else
			{
				throw new SurnameMismatchException(member.getFamilyName() + " cant join the Robinson Family");
			}
		}
		
		/**
		 *  
		 * @return String array
		 */
		public String[] getFamilyMembers()
		{
			
			String [] names = new String[familyMembers.size()];
			
			for(int i=0; i < familyMembers.size(); i++) 
			{
				names[i]=((Person)familyMembers.elementAt(i)).getFirstName() + " " + ((Person)familyMembers.elementAt(i)).getFamilyName();
			}
			return names;
		}
		
		/**
		 * 
		 * @param firstName
		 * @return Boolean
		 */
		public boolean newFirstName(String firstName)
		{
			for(int i=0; i < familyMembers.size(); i++)
			{
				if(((Person)familyMembers.elementAt(i)).getFirstName().equals(firstName))
				{
					return false; //name is not new to the family
				}
			}
			return true; //the name was not found
		}
		
		/**
		 * 
		 * @param familyName
		 * @return
		 */
		public boolean correctFamilyName(String familyName) 
		{
			
			if(familyName.equals(this.familyName)) 
			{
				return true; //family name is correct
			}
			return false; //person's family name doesn't match
			
		}
}
