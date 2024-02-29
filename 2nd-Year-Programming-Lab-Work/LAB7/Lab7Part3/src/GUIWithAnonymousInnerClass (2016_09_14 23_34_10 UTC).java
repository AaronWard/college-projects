import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A GUI class that does the same are part 2, but uses an
 * anonymous inner class for the action listener
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class GUIWithAnonymousInnerClass extends JFrame
{
	JLabel label;
	JButton button;
	JPanel panelOne, panelTwo;

	
	public static void main(String [] args)
	{
		JFrame frame = new GUIWithAnonymousInnerClass();
		frame.setLocationRelativeTo(null);
	}
	
	public GUIWithAnonymousInnerClass()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		panelOne = new JPanel();
		panelTwo = new JPanel();

			//Create components
		label = new JLabel("Label");		
		button = new JButton("Inner Class Button");
		
			//Add listener with an anonymous inner class
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{			
				if(e.getSource() == button)
				{
					//Change the text of the label
					GUIWithAnonymousInnerClass.this.label.setText("<html>Outter class Label set by inner class <br>" + 
															this.getClass().getName() + "</html>");
					GUIWithAnonymousInnerClass.this.label.setFont(new Font("", Font.BOLD, 20));
				}
			}
			
	
		});

		
		panelOne.add(label);
		panelTwo.add(button);
		
		contentPane.add(panelOne);
		contentPane.add(panelTwo);
		
		
		setVisible(true);
		setSize(500, 200);
	}
}
