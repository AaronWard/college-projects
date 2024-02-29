class State {

	private int state;

	public State(int s){
		state = s;
	}

	public int getState(){
		return state;
	}

	public void setState(int s){
		state = s;
	}
	
	public boolean equals (State s){
		return (s.getState() == state);
	}
	
	public String toString(){
		return ""+state;
	}	
}