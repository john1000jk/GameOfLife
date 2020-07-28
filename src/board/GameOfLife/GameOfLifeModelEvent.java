package board.GameOfLife;

abstract public class GameOfLifeModelEvent {
	public enum GMEType {ADVANCED, RESET};
	
	private GMEType type;
	
	protected GameOfLifeModelEvent(GMEType t) {
		type = t;
	}
	
	public GMEType getType() {
		return type;
	}
	
	public static final GameOfLifeModelEvent ADVANCED_EVENT = new GMEAdvanced();

}

class GMEAdvanced extends GameOfLifeModelEvent {
	protected GMEAdvanced() {
		super(GMEType.ADVANCED);
	}
}

class GMEReset extends GameOfLifeModelEvent {
	protected GMEReset() {
		super(GMEType.RESET);
	}
}
