package com.ward.testUtil;

import com.ward.util.*;

/**
 * A tester class that sends a string to a method in the utility class
 * and a returned int with the value of the ascii values is set
 * then printed to the console
 * @author aaron
 *
 */
public class TestStringUtility
{

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		StringUtility util = new StringUtility();
		
			//Sum is printed
		int a = util.getSumOfAsciiValues("Aaron");
		System.out.println("**Sum of ascii values**");
		System.out.println(a);
			
			//Product is printed
		int b = util.getProductOfAsciiValues("hi");
		System.out.println("**Product of ascii values**");
		System.out.println(b);	
	}

}
