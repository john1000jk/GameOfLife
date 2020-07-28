package board.GameOfLife;

import board.Board;

public class GameOfLifeViewEvent {
	public enum GVEType {STEP, RESET};
	
	private GVEType type;
	
	protected GameOfLifeViewEvent(GVEType t) {
		type = t;
	}
	
	public GVEType getType() {
		return type;
	}
}

class GVEStep extends GameOfLifeViewEvent {
	protected GVEStep() {
		super(GVEType.STEP);
	}
}

class GVEReset extends GameOfLifeViewEvent {
	
	private Board b;
	
	protected GVEReset(Board b) {
		super(GVEType.RESET);
		
		this.b = b;
	}
	
	public Board getBoard() {
		return b;
	}
}