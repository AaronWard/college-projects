import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



/**
 * JFrame class that has 2 inner classes, one extending JPanel and
 * another implementing actionListener
 * 
 * A JButton in on inner class has the actionListerner class added to it
 * to change the text in a text field when it is clicked
 * 
 * The action listener is added with an anonymous instance of the action
 * listener class
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class FormFrame extends JFrame
{
	public static void main(String[] args)
	{
		JFrame frame = new FormFrame();
		frame.setLocationRelativeTo(null);
	}	
	public FormFrame()
	{
		Container contentPane = getContentPane();
		
		//New registerPanel object
		RegisterPanel p = new RegisterPanel();
		
			//Anonymous instance of a class
		p.button.addActionListener(new SubmitResponder(p));
		p.button.setFont(new Font("", Font.BOLD, 20));	
		
		
		p.text.setEditable(false);
		p.text.setFont(new Font("", Font.BOLD, 20));	
		
		//Add components of p to p
		p.add(p.button);
		p.add(p.text);
		
		contentPane.add(p);
		setSize(500, 100);
		setVisible(true);
	}
	
	/**
	 * inner Class that extends JPanel and has 
	 * @author aaron
	 *
	 */
	static class RegisterPanel extends JPanel
	{
		JTextField text = new JTextField(10);
		JButton button = new JButton("Submit");
	}
	
	/**
	 * An action listener inner class that implements the 
	 * changing of the text field
	 * @author aaron
	 *
	 */
	class SubmitResponder implements ActionListener
	{

	    private final RegisterPanel rp;

	    /**
	     * Constructor for SubmitResponder to take rp
	     * @param rp
	     */
	    public SubmitResponder(RegisterPanel rp)
	    {
	        this.rp = rp;
	    }
	    
	    //Change text of the textfield
	    public void actionPerformed(ActionEvent e) 
	    {
	    	rp.text.setText("Submit Complete");
	    	FormFrame.this.setTitle("Submitted Completed!!!");
	    }
	}
}
