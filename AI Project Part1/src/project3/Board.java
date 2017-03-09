package project3;

import java.util.ArrayList;
import java.util.List;

// Board class to hold the board state
public class Board {
	private static final int rows = 6;
	private static final int cols = 7;
	private int[][] board;
	private int winPlayer;
	
	public Board(int[][] board, int winPlayer) {
		this.board = board;
		this.winPlayer = winPlayer;
	}
	
	public int[][] getBoard() {
		return this.board;
	}
	
	public int getWinPlayer() {
		return this.winPlayer;
	}
	
	// Construct a board from input string
	public static Board readLineToBoard(String line) {
		int len = rows * cols + 1;
		List<Integer> intList = new ArrayList<Integer>();
		for (char ch : line.toCharArray()) {
			if ((ch >= '0') && (ch <= '9')) {
				intList.add(Character.getNumericValue(ch));
			}
		}
		if (intList.size() != len) {
			return null;
		}
		
		int winPlayer = intList.get(len - 1);
		intList.remove(len - 1);
		int[][] board = new int[rows][cols];
		
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				board[j][i] = intList.get(i * rows + j);
			}
		}
		
		return new Board(board, winPlayer);
	}
	
	// Print the board
	public void printBoard()
	{
		for (int i = rows - 1; i >= 0; i--)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        System.out.print(this.board[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
}
