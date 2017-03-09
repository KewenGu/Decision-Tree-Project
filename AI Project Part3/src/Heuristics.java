import java.util.Random;


public class Heuristics {
	
	/* Defines the weight for several discs in chain, 
	 * e.g. 4 in a line, 3 in a line etc. in connect 4*/

    private static final int iterations = 3;
	
    public static Move alphaBetaSearch(StateTree board) {
    	Action nextAction;
    	if(board.isEmpty()){
    		Random randomGenerator = new Random();
    		int randomInt1 = randomGenerator.nextInt(3) + 2;
    		return new Move(false, randomInt1);
    	}
    	
    	nextAction = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, iterations);
    	while (!board.validMove(nextAction.nextMove)) {
    		Random randomGenerator = new Random();
    		int randomInt2 = randomGenerator.nextInt(board.columns);
    		nextAction = new Action(board, false, randomInt2);
    	}
    	
//    	boolean pop = false;
//    	if (board.turn == 1)
//    		pop = board.pop1;
//    	else if (board.turn == 2)
//    		pop = board.pop2;
//    	if (!pop)
//    		verifyPopOut(board, nextAction);
    	
    	return nextAction.nextMove;
    }
    	
    public static Action maxValue(StateTree board, int alpha, int beta, int moveCol, int iter) {
    	if (iter <= 0 || board.isFull())
    		return new Action(board, false, moveCol);
    	
		Board b = null;
    	Action value = new Action(Integer.MIN_VALUE);
    	Action maxResult;
    	int i;
    	for (i = 0; i < board.columns; i++) {
    		if (board.validMove(new Move(false, i))) {
	    		b = new Board(board);
	//    		board.children.add(b);
	    		if (b.validMove(new Move(false, i))) {
	    			b.makeMove(new Move(false, i));
	//    			b.display();
	    			maxResult = minValue(b, alpha, beta, i, iter--);
	    			if (Math.abs(value.evalScore) < Math.abs(maxResult.evalScore))
	    				value = maxResult;
	    			if (Math.abs(value.evalScore) >= beta)
	    				return value;
	    			alpha = Math.max(alpha, Math.abs(value.evalScore));
	    		}
    		}
    	}
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(board.columns);
    	return new Action(b, false, randomInt);
	}
    
    public static Action minValue(StateTree board, int alpha, int beta, int moveCol, int iter) {
		if (iter <= 0 || board.isFull())
			return new Action(board, false, moveCol);
		
    	Board b = null;
    	Action value = new Action(Integer.MAX_VALUE);
    	Action minResult;
    	int i;
    	for (i = 0; i < board.columns; i++) {
    		if (board.validMove(new Move(false, i))) {
	    		b = new Board(board);
	//    		board.children.add(b);
	    		if (b.validMove(new Move(false, i))) {
	    			b.makeMove(new Move(false, i));
	//    			b.display();
	    			minResult = maxValue(b, alpha, beta, i, iter--);
	    			if (Math.abs(value.evalScore) > Math.abs(minResult.evalScore))
	    				value = minResult;
	    			if (Math.abs(value.evalScore) <= alpha)
	    				return value;
	    			beta = Math.min(beta, Math.abs(value.evalScore));
	    		}
    		}
    	}
    	return value;
    }
    
    public static void verifyPopOut(StateTree board, Action nextAction) {
    	Action popAction;
    	Board b = new Board(board);
    	for (int i = 0; i < b.columns; i++) {
    		if (b.boardMatrix[0][i] == b.turn) {
    			popAction = new Action(b, true, i);
	    		if (b.validMove(popAction.nextMove)) {
	    			b.makeMove(popAction.nextMove);
	    			popAction = maxValue(b, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, iterations-1);
	    		}
	    		if ((Math.abs(popAction.evalScore) >= 300) && (Math.abs(popAction.evalScore) > Math.abs(nextAction.evalScore)))
    				nextAction = popAction;
    		}
    	}
    }

}
