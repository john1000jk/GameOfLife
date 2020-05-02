package conway;

public interface GameOfLifeModelObserver {
	
	public void update(GameOfLifeModel model, GameOfLifeModelEvent gme);
}
