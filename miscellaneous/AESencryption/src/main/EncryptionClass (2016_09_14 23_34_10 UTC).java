package main;

import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;


/**
 * A program that intakes the users input. and uses the AES
 * encryption algorithm to cipher the text. The cipher uses a
 * randomly genorated key to prevent the occurence of a two time pad.
 * 
 * AES uses 128 bit or 16 characters for its key.
 * 
 * @author aaron
 *
 */

public class EncryptionClass
{	
	//AES name
	public static final String ENC = "AES";
	
	//Private key - must be 16 characters long
	public static final byte [] KEY = new byte[16];

	static Key key;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws NoSuchAlgorithmException
	{
		//the RNG creates random characters for the key.
		SecureRandom.getInstanceStrong().nextBytes(KEY);

		try
		{
			Scanner input = new Scanner(System.in);

			System.out.println("Enter password");  
			String password = input.nextLine();
			
			String encryptedPassword = Encrypt(password);
			System.out.println("\nEncrypted Password: " + encryptedPassword);
			
			String decryptedPassword = Decrypt(encryptedPassword);
			System.out.println("\nDecrypted Password: " + decryptedPassword);
			
			System.out.println("\nEncryption Algorithm: " + key.getAlgorithm() + "\n\nKey used: ");
			
			for(int i = 0; i < KEY.length; i++)
			{
				System.out.print(KEY[i]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	//Encrypts the password and returns to the main method
	public static String Encrypt(String p) throws Exception
	{
		//Create istance of a Key, using 128 bit key and the encryption algorithm
		key = new SecretKeySpec(KEY, ENC);
		
        //Create a cryptographic cipher using the encryption method AES
        Cipher c = Cipher.getInstance(ENC);
		
        //initialise the cipher with the 128 bit key
		c.init(Cipher.ENCRYPT_MODE, key);
		
		//encrypts the password
        byte[] encryptedValue = c.doFinal(p.getBytes());
        
        p = new BASE64Encoder().encode(encryptedValue);
        
		return p;
	}


	//Decrypted the encrypted password
	public static String Decrypt(String e) throws Exception
	{
        key = new SecretKeySpec(KEY, ENC);
        
        Cipher c = Cipher.getInstance(ENC);
        
        c.init(Cipher.DECRYPT_MODE, key);
       
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(e);
        
        byte[] decValue = c.doFinal(decordedValue);
        
        String decryptedValue = new String(decValue);
        
        return decryptedValue;
	}
}
