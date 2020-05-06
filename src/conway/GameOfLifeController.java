package conway;

public class GameOfLifeController implements GameOfLifeViewObserver {

	private GameOfLifeModel model;
	private GameOfLifeView view;
	public GameOfLifeController(GameOfLifeModel m, GameOfLifeView v) {
		model = m;
		view = v;
		view.addObserver(this);
	}
	
	@Override
	public void handleEvent(GameOfLifeViewEvent gve) {
		switch (gve.getType()) {
		case STEP:
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (!view.isOn()) {
							return;
						}
						int delay = 1000/view.getSimSpeed();
						model.advance();
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
						}
					}
				}
			}).start();
			break;
		case RESET:
			GVEReset reset = (GVEReset) gve;
			model.reset(reset.getBoard());
			break;
		}
	}

}
