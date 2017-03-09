
public class ConnectNPlayer extends Player {
	
	public ConnectNPlayer(String name, int turn, int timeLimit) {
		super(name, turn, timeLimit);
	}


	public Move getMove(StateTree state) {
		return Heuristics.alphaBetaSearch(state);
	}
	
	public int getTurn() {
		return this.turn;
	}

}
