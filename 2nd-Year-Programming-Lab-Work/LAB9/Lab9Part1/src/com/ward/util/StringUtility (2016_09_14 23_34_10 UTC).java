package com.ward.util;

public class StringUtility
{
	
	/**
	 * param string is passed, and value is computed
	 * @param s
	 * @return
	 */
	public static int getSumOfAsciiValues(String s)
	{
		s.toCharArray();
		int value = 0;	//initialised at 0

		for(int i = 0; i < s.length(); i++)
		{
			value += s.charAt(i);
		}
	
		return value;
	}
	
	/**
	 * param string is passed, and value of ascii characters
	 * multiplied is returned
	 * @param s
	 * @return
	 */
	public static int getProductOfAsciiValues(String s)
	{
		
		s.toCharArray();
		int value = 0;

		for(int i = 0; i < s.length(); i++)
		{	
			value = s.charAt(i) * s.charAt(i);
		}
	
		return value;
	}
	
}
