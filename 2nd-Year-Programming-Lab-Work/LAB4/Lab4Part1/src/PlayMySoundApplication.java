/**
 * 
 * @author aaron
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.*;


@SuppressWarnings("serial")
public class PlayMySoundApplication extends JFrame implements ActionListener
{
	JPanel panel;
	JButton playButton;

	public static void main(String [] args)
	{
		JFrame frame = new PlayMySoundApplication();
		frame.setLocationRelativeTo(null);
		
	}
	
	/**
	 * Gui class with a JButton to play the sound
	 */
	public PlayMySoundApplication()
	{
		Container contentPane = getContentPane();
		panel = new JPanel();
		
		//button to play sound
		playButton = new JButton("Click to play sound");
		playButton.addActionListener(this);
				
		panel.add(playButton);
		contentPane.add(panel);		
		
		setTitle("Play Sound");
		setResizable(false);
		setVisible(true);
		setSize(300,80);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		//if the button is clicked
		if(e.getSource() == playButton)
		{
			//Sound object is made
			SoundApplication sound = new SoundApplication();
			try
			{
				//play sound if button is clicked
				sound.start();
			}
			catch (MalformedURLException e1)
			{
				//e1.printStackTrace();
			}
		}
	}
}	

