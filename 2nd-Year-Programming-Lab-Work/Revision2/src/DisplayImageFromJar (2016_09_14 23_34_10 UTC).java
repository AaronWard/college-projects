
import java.awt.*;
import javax.swing.*;

/**
 * A frame that uses getClass().getResource() to view the image in
 * a jar file
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class DisplayImageFromJar extends JFrame
{
	JLabel label;
	

	public static void main(String[] args)
	{
		JFrame frame = new DisplayImageFromJar();
		frame.setLocationRelativeTo(null);

	}
	

	/**
	 * GUI frame
	 */
	public DisplayImageFromJar()
	{
		Container contentPane = getContentPane();
		
		//Path to the image in the file
		String s = "img/itb_logo.jpg";
		
		//JLabel to contain the image
		label = new JLabel();
		JPanel panel = new JPanel();
		
		displayImage(s);

		panel.add(label);		
		contentPane.add(panel);
		setVisible(true);
		setResizable(false);
		setSize(600, 400);
	}

	
	/**
	 * This method sets the image in the label
	 * @param imageName
	 */
	public void displayImage(String imageName)
	{
		ImageIcon img = new ImageIcon(getClass().getResource(imageName));
		this.label.setIcon(img);
	}
}
