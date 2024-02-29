import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * A class that uses reflection to investigate what is in 
 * a mystery jar file
 * @author aaron
 *
 */
public class InvestigatorClass
{

	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args)
	{
		//instance of the jar is created
		//MysteryClass mc = new MysteryClass();		
		
		//Names and types of the fields in the class
		Field [] f = MysteryClass.class.getDeclaredFields();
		
		System.out.println("***FIELD NAME AND TYPE***");
		for(int i = 0; i < f.length; i++)
		{
			System.out.println("Field Name: " + f[i].getName().toString());
			System.out.println("Field Type: " + f[i].getType().toString());
			System.out.print("\n");
		}
		
		System.out.println("\n");

		
		//Names, return types and parameters of the methods
		Method [] m = MysteryClass.class.getDeclaredMethods();
		System.out.println("***METHOD NAMES AND RETURN TYPES***");
		for(int i = 0; i < m.length; i++)
		{
			System.out.println("Method Name: " + m[i].getName());
			System.out.println("Return Type: " + m[i].getReturnType());				
		    Class[] paramTypes = m[i].getParameterTypes();

		    	//For loop to view Parameter type
		     for (int j = 0; j < paramTypes.length; j++)
		     {
		       if (j > 0)
		       System.out.print(", ");
		       System.out.print("Parameter Type: " + paramTypes[j].getName());
		     }
			
		     
		    //System.out.println("Paramater  : " + m[i].getParameterTypes().toString());
			System.out.print("\n\n");

		}
		
		try	//Try invoke the method in the jar
		{
			Object obj = 1;
			String methodName = "publicMethod";
			Method method;
			
			method = methodName.getClass().getMethod(methodName, Parameter.class);
			method.invoke(methodName, 2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

}
