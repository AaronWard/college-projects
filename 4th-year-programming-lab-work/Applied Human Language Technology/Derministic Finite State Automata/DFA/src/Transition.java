class Transition {

	private State currentState;
	private State nextState;
	private char symbol;

	public Transition (State current, char s, State next){
		currentState = current;
		nextState = next;
		symbol = s;
	}

	public State getCurrrentState(){
		return currentState;
	}

	public State getNextState(){
		return nextState;
	}

	public char getSymbol(){
		return symbol;
	}
}
