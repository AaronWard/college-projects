package reflections;

/**
 * CLASS REFLECTION
 * A class to test the getName() method, .class reference and the
 * forName() method.
 * @author aaron
 *
 */
public class TestReflection
{

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException
	{
		//intance of the reflections class
		ReflectionClass r = new ReflectionClass();
		
		//1 - Print name of the class using .getClass()
		System.out.println("**Use of .getClass() method**");
		Class reflectionClass = r.getClass();
		System.out.println(reflectionClass + "\n");
		
		
		//2 - get name of the class using .class reference
		System.out.println("**Use of .class reference*");
		Class r2 = ReflectionClass.class;
		System.out.println(r2);
		
		
		//3 - use the forName() method to create and instance
		// 	  and then print the name using getName()
		String className = reflectionClass.getName().toString();
		Class r3 = Class.forName(className);
		System.out.println("\n**Use of forName() Method**");
		System.out.println(r3);
	}
}
