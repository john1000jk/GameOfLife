package conway;

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
	private int width;
	private int height;
	
	protected GVEReset(int w, int h) {
		super(GVEType.RESET);
		width = w;
		height = h;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}