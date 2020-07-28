package board.Snake;

public class SnakeModelEvent {
	public enum SMEType {UPDATE, RESET, STOP};
	
	private SMEType type;
	
	protected SnakeModelEvent(SMEType t) {
		type = t;
	}
	
	public SMEType getType() {
		return type;
	}
	
	public static final SnakeModelEvent UPDATE_EVENT = new SMEUpdate();
	
}

class SMEUpdate extends SnakeModelEvent {
	protected SMEUpdate() {
		super(SMEType.UPDATE);
	}
}
 
class SMEReset extends SnakeModelEvent {
	protected SMEReset() {
		super(SMEType.RESET);
	}
}

class SMEStop extends SnakeModelEvent {
	protected SMEStop() {
		super(SMEType.STOP);
	}
}