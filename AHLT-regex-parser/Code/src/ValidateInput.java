import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Used to validate wether or not the users input is an acceptable 
 * input according to the regular expression.
 * @author aaron
 *
 */
public class ValidateInput {

	public void validate(StringTokenizer tokens){
	
		Scanner fileReaderScan = null;
		ArrayList<String> categoryList = new ArrayList<String>();

		/**
		 * traverse each token from the input
		 */
		while(tokens.hasMoreTokens()){
			//Read the lexicon file
			fileReaderScan = new Scanner(getClass().getResourceAsStream("lexicon.txt"));
			String thisToken = tokens.nextToken();
			
			/**
			 * Traverse lines in the lexicon.txt
			 */
			while(fileReaderScan.hasNextLine())
			{	
					//Tokenize the words of each line in the lexicon.txt
					String scan = fileReaderScan.nextLine().toString();
					StringTokenizer lexiconTokens = new StringTokenizer(scan);

					/**
					 * traverse the tokens and add them to the list of POS categories
					 */
					while(lexiconTokens.hasMoreTokens()){
						if(thisToken.equals(lexiconTokens.nextToken())){
							String thisLexToken = lexiconTokens.nextToken();
							categoryList.add(thisLexToken);
						}	
					}
			}
		}
		fileReaderScan.close();
		
		// Add each POS to a concatenated string
		String posTags = "";
		for(int i = 0; i < categoryList.size(); i++){
			posTags += categoryList.get(i) + " ";
		}
		System.out.println(posTags);
		/**
		 * if valid then print true, else print false
		 */
		if(posTags.equals("DT NN VBZ DT JJ NN ") || posTags.equals("DT NNS IN DT JJ NN ") ||
			posTags.equals("DT NNS VBZ DT JJ NN ") || posTags.equals("DT NN IN DT JJ NN ")){
			Parser.acceptable.setText("<html>Result - Acceptable Regular Expression: <b>True</b></html>" );
		}
		else{
			Parser.acceptable.setText("<html>Result - Acceptable Regular Expression: <b>False</b></html>" );
		}
	}
}
