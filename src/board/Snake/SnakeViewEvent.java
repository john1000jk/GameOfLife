package board.Snake;

import board.Board;

public class SnakeViewEvent {
	public enum SVEType {START, RESET};
	
	private SVEType type;
	
	protected SnakeViewEvent(SVEType t) {
		type = t;
	}
	
	public SVEType getType() {
		return type;
	}
}

class SVEStart extends SnakeViewEvent {
	protected SVEStart() {
		super(SVEType.START);
	}
}

class SVEReset extends SnakeViewEvent {
	
	private Board b;
	
	protected SVEReset(Board board) {
		super(SVEType.RESET);
		b = board;
	}
	
	public Board getBoard() {
		return b;
	}
}

