

public class Feature {

	/* Feature 1
	 * Which player has more pieces in center rows */
	public static int Feature1 (int [][] board){
		int p1cnt = 0;
		int p2cnt = 0;
		for (int l = 3; l <5 ;l++ ){
			for(int m = 0; m< 7; m++){
				if (board[l][m] == 1){
					p1cnt++;
				}
				else if (board[l][m] == 2){
					p2cnt ++;
				}
			}
		}
		return p1cnt - p2cnt;
	}

	/* Feature 2
	 * Which player has a piece at the bottom left corner of the board */
	public static int Feature2(int [][] board){
		if (board[0][0] == 1)
		return 1;
		else if(board [0][0] == 2){
			return -1;
		}
		else return 0;
	}

	/* Feature 3
	 * Which player has more pieces in the center column */
	public static int Feature3(int[][] board){
		int p1cnt = 0;
		int p2cnt = 0;
		for (int n = 0; n < 6; n++){
			if (board[n][3]==1){
				p1cnt ++;
			}
			else if (board[n][3]==2){
				p2cnt ++;
			}
		}
		return p1cnt - p2cnt;
	}

	/* Feature 4
	 * Which player has more pieces in the bottom row */
	public static int Feature4(int[][] board){
		int p1cnt = 0;
		int p2cnt = 0;
		for (int n = 0; n < 6; n++){
			if (board[n][0]==1){
				p1cnt ++;
			}
			else if (board[n][0]==2){
				p2cnt ++;
			}
		}
		return p1cnt - p2cnt;
	}

	/* Feature 5
	 * Which player has more 2 pieces in a row */
	public static int Feature5(int[][] board){
		int p1cnt = 0;
		int p2cnt = 0;
		int p1ver = 0;
		int p2ver = 0;
		int p1dia = 0;
		int p2dia = 0;
		int p1sum = 0;
		int p2sum = 0;

		// Horizontal connection
		// 6 rows
		for (int o = 0; o < 6; o++){
			// 7 columns
			for (int p = 0; p< 6; p++){
				if(board[o][p] == 1 && board[o][p+1] == 1){
					p1cnt ++;
				}
				else if (board[o][p] == 2 && board[o][p+1] == 2){
					p2cnt ++;
				}
			}
		}

		// Vertical Connection
		// 6 rows
		for (int o = 0; o < 5; o++){
			// 7 columns
			for (int p = 0; p< 7; p++){
				if(board[o][p] == 1 && board[o+1][p] == 1){
					p1ver ++;
				}
				else if (board[o][p] == 2 && board[o+1][p] == 2){
					p2ver ++;
				}
			}
		}

		// Diagonal Connection 1 (0,0 and 1,1)
		// 6 rows
				for (int o = 0; o < 5; o++){
					// 7 columns
					for (int p = 0; p< 6; p++){
						if(board[o][p] == 1 && board[o+1][p+1] == 1){
							p1dia ++;
						}
						else if (board[o][p] == 2 && board[o+1][p+1] == 2){
							p2dia ++;
						}
					}
				}

		for (int q = 1;  q < 6; q++){
			for (int r = 0; r < 6; r++){
				if(board[q][r] == 1 && board[q-1][r+1] == 1){
					p1dia ++;
				}
				else if (board[q][r] == 2 && board[q-1][r+1] == 2){
					p2dia ++;
				}

			}
		}


		p1sum = p1cnt + p1ver + p1dia;
		p2sum = p2cnt + p2ver + p2dia;
		return p1sum - p2sum;
	}
}
