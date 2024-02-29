import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * This class is is a button listener for when the user has entered an input
 * and clicks the parse button on the UI.
 * @author aaron
 *
 */
public class ParseButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String input = Parser.input.getText();
		
		/**
		 * Guard to make sure the user has entered 
		 * an input to be parsed
		 */
		if(input.equals("")){
			JOptionPane.showMessageDialog(null, ""
					+ "			Please enter in some text", 
					"Oops", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			//Guard incase the user uses capital letters
			input = input.toLowerCase();
			
			StringTokenizer tokens = new StringTokenizer(input);
			ValidateInput validateInput = new ValidateInput();
			validateInput.validate(tokens);
			
			//Tag the users input using the stanford library
			MaxentTagger tagger = new MaxentTagger("src/english-left3words-distsim.tagger");			
			String tagged = tagger.tagString(input);
							
			//calls the print method to print the bracketed structure to the UI
			PrintBracket printBracket = new PrintBracket();
			printBracket.print(tagged);
		}		
	}
}
