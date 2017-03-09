
public class Evaluation {
	public StateTree board;
	public int player;
	public int NCount = 0;
	public int NMinusOneCount = 0;
	public int NMinusTwoCount = 0;
	
	private static final int Nval = 2000;
	private static final int NMinusOneVal = 1500;
	private static final int NMinusTwoVal = 100;
	
	
	public Evaluation(StateTree board){
		this.board = board;
		this.player = board.turn;
		evaluateBoardState();
	}

	
	public static int calculateScore(StateTree board){
		int score = 0;
		Evaluation playerEval = new Evaluation(board);
		Board opponentBoard = new Board(board);
		opponentBoard.turn = 3 - opponentBoard.turn;
		Evaluation opponentEval = new Evaluation(opponentBoard);
		
		score = playerEval.NCount * Nval + 
				playerEval.NMinusOneCount * NMinusOneVal +
                playerEval.NMinusTwoCount * NMinusTwoVal -
                opponentEval.NCount * Nval -
                opponentEval.NMinusOneCount * NMinusOneVal -
                opponentEval.NMinusTwoCount * NMinusTwoVal;
		
		   if (board.turn == 1){
			   if (Feature.Feature1(board.boardMatrix) >= 0){
				   score = score + 500;
				   if (Feature.Feature2(board.boardMatrix) >=0){
					   score = score + 200;
					   if (Feature.Feature3(board.boardMatrix) >= 0){
						   score = score + 400;
						   if (Feature.Feature4(board.boardMatrix) >= 0){
							   score = score + 1000;
							   if (Feature.Feature5(board.boardMatrix) >= 0){
								   score = score + 600;
							   }
							   else score = score - 600;
						   }
						   else score = score - 1000;
					   }
					   else score = score - 400;
				   }
				   else score = score - 200;
			   }
			   else score = score - 500;
		   }
		   else {
				   if (Feature.Feature1(board.boardMatrix) >= 0){
					   score = score - 500;
					   if (Feature.Feature2(board.boardMatrix) >=0){
						   score = score - 200;
						   if (Feature.Feature3(board.boardMatrix) >= 0){
							   score = score - 400;
							   if (Feature.Feature4(board.boardMatrix) >= 0){
								   score = score - 1000;
								   if (Feature.Feature5(board.boardMatrix) >= 0){
									   score = score - 600;
								   }
								   else score = score + 600;
							   }
							   else score = score + 1000;
						   }
						   else score = score + 400;
					   }
					   else score = score + 200;
				   }
				   else score = score + 500;
		   }
		   
		return score;
	}
	
	public void evaluateBoardState(){
		int[][] B = board.boardMatrix;
		int numCols = board.columns;
		int numRows = board.rows;
        int N = board.winNumber;
        

        checkHorizontal(B, N, numCols, numRows);
        checkVertical(B, N, numCols, numRows);
        checkDiagonal1(B, N, numCols, numRows);
        checkDiagonal2(B, N, numCols, numRows);
		

	}
	
	public void checkHorizontal(int[][] B, int N, int numCols, int numRows){
		int i, j, count;
		for (i = 0; i < numRows; i++){
			count = 0;
			for (j=0; j<numCols; j++){
				if(B[i][j] == this.player){
					count ++;
					 if ((j < (numCols - 1) && B[i][j+1] != this.player) || (j == numCols - 1))
	                        countN(count, N);
				}
			}
		}
	}
	
    public void checkVertical(int[][] B, int N, int numCols, int numRows) {
        int i, j, count;
        for (i = 0; i < numCols; i++) {
            count = 0;
            for (j = 0; j < numRows; j++) {

                if (B[j][i] == this.player) {
                    count++;
                    if ((j < (numRows - 1) && B[j+1][i] != this.player) || (j == numRows - 1))
                        countN(count, N);
                }
            }
        }
    }
    
    public void checkDiagonal1(int[][] B, int N, int numCols, int numRows) {
        int i, j, k, m, n, count;
        j = 0;
        k = 0;

        for (i = 0; i < numCols + numRows - 1; i++) {

            m = j;
            n = k;
            count = 0;

            while (m >= 0 && n < numRows) {

                if (B[n][m] == this.player) {
                    count++;
                    if ((m > 0 && n < numRows-1 &&B[n+1][m-1] != this.player) || (m == 0 && n == numRows-1))
                        countN(count, N);
                }
                m--;
                n++;
            }
            if (j == numCols-1)
                k++;
            if (j >= 0 && j < numCols-1)
                j++;
        }
    }
    
    public void checkDiagonal2(int[][] B, int N, int numCols, int numRows) {
        int i, j, k, m, n, count;
        j = 0;
        k = numRows-1;

        for (i = 0; i < numCols + numRows - 1; i++) {

            m = j;
            n = k;
            count = 0;

            while (m >= 0 && n >= 0) {

                if (B[n][m] == this.player) {
                    count++;
                    if ((m > 0 && n > 0 && B[n-1][m-1] != this.player) || (m == 0 && n == 0))
                        countN(count, N);
                }
                m--;
                n--;
            }
            if (j == numCols-1)
                k--;
            if (j >= 0 && j < numCols-1)
                j++;
        }
    }
    
	
	
	 public void countN(int count, int N) {
	        if (count == N)
	            this.NCount++;
	        else if (count == N-1)
	            this.NMinusOneCount++;
	        else if (count == N-2)
	            this.NMinusTwoCount++;
	        count = 0;
	    }
	
}
