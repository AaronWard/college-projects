/**
 * @author Aaron Ward - B00079288
 * @Group 4
 * @Year 2 - Semester 2
 * 
 * @encode Receives a string, encodes and returns it
 *
 */

package cypher;

import java.nio.charset.Charset;

/**
 * This class is used to encode the scring entered by the user
 * @author aaron
 *
 */
public class Encoder
{
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) 
	{	
		byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
		
		//Concatenate to show bytes as a string
		str = (bytes + "");
	
	    return str;
	}
	
	/**
	 * Method used to return the byte array to be passed to the
	 * Decoded class, as string won't work.
	 * @param str
	 * @return
	 */
	public static byte[] returnBytes(String str)
	{
		//turns the string into a bytes array
		byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
		
		return bytes;
	}
}
