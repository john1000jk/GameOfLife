package board.Snake;

public class SnakeController implements SnakeViewObserver {
	private SnakeModel model;
	private SnakeView view;
	public SnakeController(SnakeModel m, SnakeView v) {
		model = m;
		view = v;
		view.addObserver(this);
	}
	
	public void handleEvent(SnakeViewEvent sve) {
		switch (sve.getType()) {
		case START:
			new Thread(new Runnable() {
				public void run() {
					model.setReady(false);
					while (true) {
						if (!view.isOn()) {
							model.setReady(true);
							view.setDirection2("");
							return;
						}
						int delay = 105;
						model.advance(view.getDirection());
						view.setDirection2(view.getDirection());
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
						}
					}
				}
			}).start();
			break;
		case RESET:
			SVEReset reset = (SVEReset) sve;
			model.reset(reset.getBoard());
			break;
		}
	}
}
