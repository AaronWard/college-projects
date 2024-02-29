/**
 * 
 * 
 * @author aaron
 *
 */
package encoder;
import java.nio.charset.Charset;

public class encoding
{
	//Charset variable is made
	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	/**
	 * A encoder method that converts the string parameter
	 * to a bytes array. Then the bytes array is concatinated to a string
	 * type. and is returns to the CodeFrame Class.
	 * @param str
	 * @return encoded string
	 */
	public String encodeMethod(String str) 
	{	
		//converts string to byte array
		byte[] bytes = str.getBytes(UTF8_CHARSET);
		
		//Concat to show bytes as a string
		String strOne = bytes + "";
	
	    return strOne;
	}
}
