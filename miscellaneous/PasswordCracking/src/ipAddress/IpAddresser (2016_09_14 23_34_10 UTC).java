package ipAddress;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import static java.lang.System.out;


/**
 * Class used to print the interfaces and IPs associated with them.
 * @author aaron
 *
 */
public class IpAddresser
{
	public static void main(String [] args) 
	{
		//Print the local ip address of the hosts machine
		try
		{
	        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
	        //loop trough the Network Interfaces
   			for(NetworkInterface netint : Collections.list(nets))
   			{
                displayInterfaceInformation(netint);
   			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}

	//Method to go trough the interfaces and print the
	//information about them.
	private static void displayInterfaceInformation(NetworkInterface nets) throws UnknownHostException 
	{
        Enumeration<InetAddress> inetAddresses = nets.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses)) 
		{
			//Print the Display name of the Interface
			System.out.printf("Display name: %s\n", nets.getDisplayName());
			
			//Print the type of of interface
			System.out.printf("Type Name: %s\n", nets.getName());
			
			//Print the IP address
            System.out.printf("Ip Address: %s\n", inetAddress); 
            out.printf("\n");
        }
	}
}
