package snake;

public class Block {
	int x;
	int y;
	public Block(int xm, int ym) {
		x=xm;
		y=ym;
	}
	public Block(Block one) {
		x=one.x;
		y=one.y;
	}

	public String toString() {
		return "x= " + x + "y= " + y;
	}
	public boolean equals(Object O) {
		if(this.getClass() != O.getClass()) return false;
		else {
			Block M = (Block)O;
			if(this.x == M.x && this.y==M.y) return true;
			else return false;
		}
		
	}
}
