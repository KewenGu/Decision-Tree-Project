/**
 * This class represents the state of a game at any time.
 * This class contains information on the board size and configuration
 * as well as whose turn it is. This class also has the ability
 * to be given a parent and children to the current state. This will help with building the tree
 * that you will min-max on. You don't have to use any of the variables given
 * if you don't want to.
 * 
 * You might want to make a whole different representation for states which
 * ok, this is just something to get you started but make sure your player
 * can still interpret the RefereeBoard objects its getting and is still
 * able to return moves.
 * 
 * @author Ethan Prihar
 */

import java.util.ArrayList;
import java.lang.Math;

public abstract class StateTree
{
	int rows, columns, winNumber, turn; // board parameters
	boolean pop1, pop2;                 // true if the player has used their pop move
	int[][] boardMatrix;                // matrix representing the board (0 = empty, 1 = player1, 2 = player2)
	StateTree parent;                   // parent state
	ArrayList<StateTree> children;      // list of children states
	
	public StateTree(int r, int c, int w, int t, boolean p1, boolean p2, StateTree p)
	{
		this.rows = r;
		this.columns = c;
		this.winNumber = w;
		this.boardMatrix = new int[rows][columns];
		this.turn = t;
		this.pop1 = p1;
		this.pop2 = p2;
		this.children = new ArrayList<StateTree>();
		if(p != null)
			this.parent = p;
	}
	public StateTree(StateTree p) {
		this.parent = p;
		this.rows = p.rows;
		this.columns = p.columns;
		this.winNumber = p.winNumber;
		this.boardMatrix = p.copyBoard();
		this.turn = p.turn;
		this.pop1 = p.pop1;
		this.pop2 = p.pop2;
		this.children = new ArrayList<StateTree>();
	}
	
	// Checks if the given move is valid in the current state
	public boolean validMove(Move move)
	{
		if(move.column >= columns || move.column < 0)
		{
//			System.out.println("That column doesn't exist.");
			return false;
		}
		if(!move.pop && boardMatrix[rows-1][move.column] != 0)
		{
//			System.out.println("That column is full.");
			return false;
		}
		if(move.pop)
		{
			if(boardMatrix[0][move.column] != turn)
			{
//				System.out.println("You can't pop a piece that isn't your own.");
				return false;
			}
			if((turn == 1 && pop1) || (turn == 2 && pop2))
			{
//				System.out.println("You can't pop a piece twice in a game.");
				return false;
			}
		}
		return true;
	}
	
	// Makes the given move on the board and updates the state
	public void makeMove(Move move)
	{
		if(move.pop)
		{
			if(turn == 1)
				pop1 = true;
			if(turn == 2)
				pop2 = true;
			for(int i=0; i<rows-1; i++)
			{
				boardMatrix[i][move.column] = boardMatrix[i+1][move.column];
			}
			boardMatrix[rows-1][move.column] = 0;
			turn = Math.abs(turn-3);
			return;
		}
		else
		{
			for(int i=0; i<rows; i++)
			{
				if(boardMatrix[i][move.column] == 0)
				{
					boardMatrix[i][move.column] = turn;
					turn = Math.abs(turn-3);
					return;
				}
			}
		}
	}
	
	// Prints the board
	public void display()
	{
		for (int i=rows-1; i>=0; i--)
		{
		    for (int j = 0; j < columns; j++)
		    {
		        System.out.print(boardMatrix[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
	
	public boolean isEmpty(){
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++){
				if(boardMatrix[i][j]!=0)
					return false;
			}
		return true;
	}
	
	public boolean isFull(){
		for(int i=0;i<columns;i++)
			for(int j=0;j<rows;j++){
				if(boardMatrix[j][i]==0)
					return false;
			}
		return true;
	}
	
	public int[][] copyBoard() {
		int[][] copy = new int[this.rows][this.columns];
//		this.display();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (this.boardMatrix[i][j] != 0)
					copy[i][j] = this.boardMatrix[i][j];
			}
		}
		return copy;
	}
}
