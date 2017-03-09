
public class Board extends StateTree {

	public Board(int r, int c, int w, int t, boolean p1, boolean p2, StateTree p)
	{
		super(r, c, w, t, p1, p2, p);
	}
	
	public Board(StateTree p) {
		super(p);
	}
}
