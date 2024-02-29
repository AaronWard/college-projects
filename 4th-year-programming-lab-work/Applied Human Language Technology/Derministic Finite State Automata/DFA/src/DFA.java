import java.util.*;
import java.io.*;


class DFA {

	private Vector states;
	private Vector endStates;
	private String alphabet;
	private Vector transitions;

	public DFA(String filename) throws Exception {

		states = new Vector();
		endStates = new Vector();
		transitions = new Vector();

		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		//number of states
		String line1 = br.readLine();
		int nStates = Integer.parseInt(line1);
		for (int i=0; i<nStates; i++){
			State s = new State(i);
			states.addElement(s);
		}

		//end states
		String line2 = br.readLine();
		String[] ends = line2.split("\\s");
		for (int i=0; i<ends.length; i++){
			String e = ends[i];
			State s = getState(Integer.parseInt(e));
			endStates.addElement(s);
		}

		//alphabet
		String line3 = br.readLine();
		alphabet = line3;
		
		//transitions
		String line4 = br.readLine();
		while (line4!=null){
			//System.out.println(line4);
			String[] tr = line4.split("\\s");
			for(int i = 0; i < tr.length; i++){
				if(tr[i].length() == 0){
					tr[i] = " ";
					break;
				}
			}

			State current = getState(Integer.parseInt(tr[0]));
			State next = getState(Integer.parseInt(tr[2]));
			char s = tr[1].charAt(0);

			Transition t = new Transition(current, s, next);
			transitions.addElement(t);

			line4 = br.readLine();
		}


	}

	private State getState (int st){

		for (Enumeration e = states.elements(); e.hasMoreElements(); ){

			State s = (State) e.nextElement();
			if (s.getState() == st)
				return s;
		}

		return null;
	}


	public Transition getTransition(State current, char symbol){

		for (Enumeration e=transitions.elements(); e.hasMoreElements(); ){

			Transition t = (Transition) e.nextElement();
			if ((t.getCurrrentState()).equals (current) && t.getSymbol() == symbol)
				return t;
		}

		return null;
	}


	public State getNextState(State current, char symbol){
		Transition t = getTransition(current, symbol);
		if (t==null)
			return null;
		else
			return t.getNextState();
	}


	public boolean isEndState (State s){
		for (Enumeration e = endStates.elements(); e.hasMoreElements(); ){

			State state = (State) e.nextElement();
			System.out.println("next: "+state.toString());
			if (state.equals(s))
				return true;
		}

		System.out.println("not end state");
		return false;
	}

	public void printEndStates(){
		for (Enumeration e = endStates.elements(); e.hasMoreElements(); ){
			State state = (State) e.nextElement();
			System.out.print(state+" ");
		}
		System.out.println();
	}



	public boolean parseInput (String input){
		State currentState = getState(0);
		String currentInput = input;

		State nextState = currentState;
		char symbol;

		while (!currentInput.equals("")){

			symbol = currentInput.charAt(0);
			nextState = getNextState(currentState, symbol);


			System.out.println("Processing: " + currentState.toString()+ " " + symbol + " " +nextState.toString());
			if (currentInput.length()==0)
				currentInput = "";
			else
				currentInput = currentInput.substring(1);

			currentState = 	nextState;
		}

		System.out.println(currentState);
		return isEndState(currentState);

	}


}