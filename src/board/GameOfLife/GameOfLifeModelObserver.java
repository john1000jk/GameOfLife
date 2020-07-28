package board.GameOfLife;

public interface GameOfLifeModelObserver {
	
	public void update(GameOfLifeModel model, GameOfLifeModelEvent gme);
}
