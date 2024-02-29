import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

/**
 * @author Aaron
 *
 */
@SuppressWarnings({"serial"})

public class ButtonTranslator extends JFrame implements ActionListener
{
	JButton one, two, three, translate;
	JPanel panel;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new ButtonTranslator();
		frame.setLocationRelativeTo(null);
	}
	
	public ButtonTranslator()
	{
		Container contentPane = getContentPane();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 40, 30));
		
		//create resource bundle and French locale
		Locale locale = new Locale("fr", "FR");
		ResourceBundle resource = ListResourceBundle.getBundle("ListResourceBundle",locale); //Create resource bundle

		
		//Create buttons
		one = new JButton(resource.getString("One"));
		two = new JButton(resource.getString("Two"));
		three = new JButton(resource.getString("Three"));
		translate = new JButton("Translate Two French");
		translate.addActionListener(this);
		
		
		//Add buttons to panel
		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(translate);
		
		contentPane.add(panel);
		
		setSize(500,200);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		//if the translate button is pressed 
		if(e.getSource() == translate)
		{
			//one.setText(););

		}
	}

}
