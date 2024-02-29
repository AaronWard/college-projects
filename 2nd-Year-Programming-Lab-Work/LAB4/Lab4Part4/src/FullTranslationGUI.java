import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A class that has a JButton, JCombo box and text area. When the button
 * is clicked the text area displays all the avaiable locales.
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class FullTranslationGUI extends JFrame implements ActionListener
{
	
	JButton listLocalesButton;
	JTextArea textArea;
	@SuppressWarnings("rawtypes")
	JComboBox combo;
	
	String [] comboOptions = {"English", "French"};
	
	JPanel panel;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{
		JFrame frame = new FullTranslationGUI();
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Gui class to implement components required.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FullTranslationGUI()
	{
		Container contentPane = getContentPane();
		
		panel = new JPanel();
		
		//**************
		
		combo = new JComboBox(comboOptions);
		combo.addActionListener(this);
				
		listLocalesButton = new JButton("List all Locales");
		listLocalesButton.addActionListener(this);
		
		textArea = new JTextArea(10, 40);

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);     
        
        //add a new scroller to the text are
        JScrollPane sp = new JScrollPane(textArea);  
        //Wrap a border around the text area
        sp.setBorder(BorderFactory.createTitledBorder("Available Locales"));
        //
        sp.setViewportView(textArea);
	    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    
		//***************/
		
		panel.add(combo);
		panel.add(listLocalesButton);
		panel.add(sp);
		
		
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		
		setVisible(true);
		setSize(800, 300);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		//get the item selected by the user in the combobox
		String comboAnswer = (String) combo.getSelectedItem();
		
		//If the button is clicked
		if(e.getSource().equals(listLocalesButton))
		{
			//To check which item was selected on the JComboBox
			switch(comboAnswer)
			{
				case "English":
					//
						//textArea.setText("WHAT THE FUCK IS GOING ON??");
						break;
						
				case "French":
					//	
						break;
			}
		}
	}
}
