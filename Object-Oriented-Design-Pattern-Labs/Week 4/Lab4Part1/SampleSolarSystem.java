import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is a class to represent a Sample Solar system
 * This class demonstrates the use of the Singleton Pattern through the Sun class
 * The Sun class is a Singleton and the number of Sun objects cannot exceed max of Solar System
 */

public class SampleSolarSystem extends JFrame{

	static SolarSystem alienSystem, homeSystem;

	static Sun yellowSun, blueSun, greenSun;
	static Planet earth,jupiter,venus,blue_earth,blue_jupiter,blue_venus,green_earth,green_jupiter,green_venus;

	JTextArea displayArea = new JTextArea(10,50);
	JPanel displayPanel = new JPanel();
	JScrollPane displayScroll = new JScrollPane(displayArea);

	public SampleSolarSystem() {

		displayArea.setEditable(false);
		displayPanel.add(displayScroll);
		this.getContentPane().add(displayPanel);

		setSize(675,500);
		setVisible(true);
	}

	/**
	 * Main Method Sample solar system created, edit the number of Suns beyond solar system max to see
	 * Singleton in action
	 *
	 */
	public static void main(String[] args) {

		SampleSolarSystem solarSystem = new SampleSolarSystem();

		try {
			//Create solar system, restricted singleton max set in parameter
			//1 means only one Sun etc.
			alienSystem = new SolarSystem(3);
			
			
			try {
				/**
				*  First Sun Created and added to the solar system.
				*/
				yellowSun = Sun.createSun("Yellow Sun",alienSystem);
				earth = new Planet("Yellow Earth",yellowSun);
				alienSystem.addSun(yellowSun);


				/**
				* Second sun being created will trigger an exception()
				* as only one sun is allowed to be instantiated
			    * 
			    * **Uncomment code and recompile to create a second sun**
				*/
				// blueSun = Sun.createSun("Blue Sun",alienSystem);
				// blue_earth = new Planet("Blue Earth", blueSun);
				// alienSystem.addSun(blueSun);

			}
			catch (SingletonException e) {
				e.printStackTrace();
			}

			solarSystem.displayArea.append("" + alienSystem);

		} catch (SolarSystemSunLimitException e) {
			JOptionPane.showMessageDialog(solarSystem, "Attempt to exceed upper limit of Suns which has been set to " + alienSystem.getNumSuns() + "\nIncrease Number of Suns or Decrease Number of Suns added","Solar System Error",JOptionPane.ERROR_MESSAGE);
		}

	}

}
