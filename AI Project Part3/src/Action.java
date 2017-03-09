
public class Action {
	Move nextMove;
	int evalScore;
	
	public Action(StateTree board, boolean pop, int moveCol) {
		this.nextMove = new Move(pop, moveCol);
		this.evalScore = Evaluation.calculateScore(board);
	}
	
	public Action(int score) {
		this.evalScore = score;
		this.nextMove = new Move(false, Integer.MAX_VALUE);
	}
}
