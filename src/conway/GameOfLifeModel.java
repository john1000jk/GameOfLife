package conway;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeModel {
	private int width;
	private int height;
	private Board board;
	private int simSpeed = 1;
//	private int reviveCount;
//	private int stayAliveCount;
	private List<GameOfLifeModelObserver> observers;
	
	public GameOfLifeModel(int x, int y) {
		width = x;
		height = y;
		board = new BoardImpl(x, y);
		observers = new ArrayList<GameOfLifeModelObserver>();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void reset(int x, int y) {
		width = x;
		height = y;
		notifyObservers(new GMEReset());
	}
	
	public synchronized void advance() {
		for (int i = 0; i < board.getSpotWidth(); i++) {
			for (int j = 0; j < board.getSpotHeight(); i++) {
				if (board.getNumNeighbors(board.getSpotAt(i, j)) > 3 || 
						board.getNumNeighbors(board.getSpotAt(i, j)) < 2) {
					board.getSpotAt(i, j).clearSpot();
				} else if (board.getNumNeighbors(board.getSpotAt(i, j)) == 2 && 
						board.getSpotAt(i, j).isOn()) {
					board.getSpotAt(i, j).setSpot();
				} else if (board.getNumNeighbors(board.getSpotAt(i, j)) == 3) {
					board.getSpotAt(i, j).setSpot();
				}
			}
		}
		notifyObservers(GameOfLifeModelEvent.ADVANCED_EVENT);
	}
	
	public int getSimSpeed() {
		return simSpeed;
	}
	
	public void setSimSpeed(int s) {
		simSpeed = s;
	}
	
	public void addObserver(GameOfLifeModelObserver gmo) {
		observers.add(gmo);
	}
	
	public void removeObserver(GameOfLifeModelObserver gmo) {
		observers.remove(gmo);
	}
	
	public void notifyObservers(GameOfLifeModelEvent gme) {
		for (GameOfLifeModelObserver g : observers) {
			g.update(this, gme);
		}
	}
}
