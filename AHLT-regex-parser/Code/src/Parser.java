import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This program takes input from a user, the input is parsed and 
 * compares to the sentence "The/a man/men/woman/women bite(s)/like(s) the green dog"
 * using regular expressions
 * 
 * The Stanford CoreNLP library is used to for tagging the users input with POS tags.
 * A bracketed phrasal structure of the users input is displayed to the screen aswell as 
 * a result wether or not was the users input an acceptable regular expression.
 * @author aaron
 *
 */
@SuppressWarnings("serial")
public class Parser extends JFrame {
	public static JPanel mainPanel ;
	public JPanel mainNorthPanel;
	public JPanel mainSouthPanel;

	public static JTextField input;
	public static JTextArea output;
	public static JLabel acceptable;


	public static void main(String [] args){
		new Parser();
	}
	
	/**
	 * Constructer to initialise GUI components
	 */
	public Parser(){
		super.setTitle("Parser");
		add(getMainPanel());
		setSizes();
	}
	
	
	private void setSizes() {
		setSize(800, 300);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Main JPanel
	 * @return
	 */
	public JPanel getMainPanel(){
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(getNorthPanel(), BorderLayout.NORTH);
		mainPanel.add(getSouthPanel(), BorderLayout.SOUTH);
		return mainPanel;
	}
	
	/**
	 * Panel for text input, parse button and bracketed output
	 * @return
	 */
	public JPanel getNorthPanel(){
	
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(300, 100));
		
		input = new JTextField(50);
		output = new JTextArea();
		output.setLineWrap(true);
	
		input.setFont(new Font("Century Gothic", Font.PLAIN , 14));		
		output.setPreferredSize(new Dimension(700, 200));
		output.setFont(new Font("Century Gothic", Font.PLAIN ,22));
		output.setBackground(Color.WHITE);
		output.setEditable(false);
	
		JButton parse = new JButton("Parse text");
		parse.addActionListener(new ParseButtonListener());
		
		northPanel.add(input);
		northPanel.add(parse);
		northPanel.add(output);
		
		return northPanel;
	}
	
	/**
	 * For displaying results
	 * @return
	 */
	public JPanel getSouthPanel(){
		JPanel southPanel = new JPanel();
		acceptable = new JLabel("<html><b>Result - </b> Acceptable Regular Expression:</html>" + true);
		acceptable.setFont(new Font("Century Gothic", Font.ITALIC , 16 ));
		acceptable.setPreferredSize(new Dimension(300, 40));

		southPanel.add(acceptable);
		return southPanel;
	}
}
