import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * GUI class with an inner class that implements action listener to change
 * the text of a label when the button is clicked
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class GUIWithInnerClass extends JFrame
{
	JLabel label;
	JButton button;
	JPanel panelOne, panelTwo;

	
	public static void main(String [] args)
	{
		JFrame frame = new GUIWithInnerClass();
		frame.setLocationRelativeTo(null);
	}
	
	public GUIWithInnerClass()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		
		//1 -  Label in the outter class 
		panelOne = new JPanel();
		panelTwo = new JPanel();

			//Instance of inner class
		ButtonResponder br = new ButtonResponder();
			//Create components
		label = new JLabel("Label");		
		button = new JButton("Inner Class Button");
		
			//Add listener to ButtonResponder Class
		button.addActionListener(br);

		
		panelOne.add(label);
		panelTwo.add(button);
		
		contentPane.add(panelOne);
		contentPane.add(panelTwo);
		
		
		setVisible(true);
		setSize(500, 300);
	}
	
	/**
	 * Inner class that implements action listener
	 * @author aaron
	 *
	 */
	class ButtonResponder implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			if(e.getSource() == button)
			{
				//Change the text of the label
				GUIWithInnerClass.this.label.setText("<html>Outter class Label set by inner class <br>" + 
														this.getClass().getName() + "</html>");
				GUIWithInnerClass.this.label.setFont(new Font("", Font.BOLD, 20));
			}
		}
		
	}
}
