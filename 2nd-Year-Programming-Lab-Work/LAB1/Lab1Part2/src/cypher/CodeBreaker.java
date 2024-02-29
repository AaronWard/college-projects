/**
 * @author Aaron Ward - B00079288
 * @Group 4
 * @Year 2 - Semester 2
 * 
 *
 */


package cypher;
import java.util.Scanner;

/**
 * A class that asks the user to enter a string. It is then passed to the encoder
 * class and converted to a bytes array. the bytes array is also passed to the decoder
 * class where it converted back to a string.
 * The encoded and decoded strings are then  displayed to the screen
 * @author aaron
 *
 */
public class CodeBreaker
{

	public static void main(String [] args)
	{	
		Scanner input = new Scanner(System.in);
		
		//creating an object of the encoder + decoder classes
		@SuppressWarnings("unused")
		Encoder encodeClass = new Encoder();
		Decoder decodeClass = new Decoder();
		
		
	    //**********************1****
		System.out.println("Enter a string to encode");
		String stringToEncode = input.nextLine();
		
		//Close the scanner -- Stop warning from appearing
		input.close();
		
		//Set encoded string is set to the returned value of the encode method
		//in the encoder class
		String encodedString = Encoder.encode(stringToEncode);
		byte []   bytes 	 = Encoder.returnBytes(stringToEncode);
	
			
		//print the encoded string
		System.out.println("\nEncoded String:");
		System.out.println(encodedString);
		
		//Set decoded string is set to the returned value of the decode method
		//in the decoder class
		String decodedString = decodeClass.decode(bytes);
		
		//Print The Decoded string.
		System.out.println("\nDecoded String:");
		System.out.println(decodedString);
	}
}
