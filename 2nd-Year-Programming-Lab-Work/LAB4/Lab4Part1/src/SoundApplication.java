import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * audio class that plays the sound when the JButton is clicked.
 * @author aaron
 *
 */
public class SoundApplication
{	
		//Variables declared for audio name and URL
		AudioClip audio;
		String soundName = "bottle-open.wav";
		URL soundURL;

		/**
		 * @throws MalformedURLException
		 */
		public void start() throws MalformedURLException
		{
			//set the URL to the current directory
			soundURL = new URL("file:" + System.getProperty("user.dir") + "/" + soundName);
			
			//Print the address of the file being played, convert URL to string
	        System.out.println("Fetch " + soundURL.toString());
	        
	        //Create new audio clip
			audio = Applet.newAudioClip(soundURL);
			
			//Play the audio clip
			audio.play();
		}
}