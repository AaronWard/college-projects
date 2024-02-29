import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;


public class ListResourceBundle extends ResourceBundle
{

	private String[] keys = {""};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public Enumeration getKeys()
	{
		return Collections.enumeration(Arrays.asList(keys));
	}
	public Object handleGetObject(String key)
	{
		return keys;
	}
}