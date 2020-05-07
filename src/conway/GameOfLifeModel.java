package conway;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeModel {
	private Board board;
	private int simSpeed = 1;
	private List<Integer> stayAliveCount;
	private List<Integer> reviveCount;
	private List<GameOfLifeModelObserver> observers;
	
	public GameOfLifeModel(int x, int y) {
		board = new BoardImpl(x, y, 20);
		observers = new ArrayList<GameOfLifeModelObserver>();
		stayAliveCount = new ArrayList<Integer>();
		reviveCount = new ArrayList<Integer>();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void reset(Board b) {
		board = b;
		notifyObservers(new GMEReset());
	}
	
	public synchronized void advance() {
		for (int i = 0; i < board.getSpotWidth(); i++) {
			for (int j = 0; j < board.getSpotHeight(); j++) {
				if (stayAliveCount.contains(board.getNumNeighbors(board.getSpotAt(i, j))) && 
						board.getSpotAt(i, j).isSet()) {
					board.getSpotAt(i, j).setShouldSet(true);
				} else if (reviveCount.contains(board.getNumNeighbors(board.getSpotAt(i, j))) &&
						!board.getSpotAt(i, j).isSet()) {
					board.getSpotAt(i, j).setShouldSet(true);
				}
			}
		}
		for (int i = 0; i < board.getSpotWidth(); i++) {
			for (int j = 0; j < board.getSpotHeight(); j++) {
				if (board.getSpotAt(i, j).getShouldSet()) {
					board.getSpotAt(i, j).setSpot();
				} else {
					board.getSpotAt(i, j).clearSpot();
				}
				board.getSpotAt(i, j).setShouldSet(false);
			}
		}
		
		notifyObservers(GameOfLifeModelEvent.ADVANCED_EVENT);
	}
	
	public void addReviveCount(int a) {
		reviveCount.add(a);
	}
	
	public void removeReviveCount(int a) {
		reviveCount.remove(Integer.valueOf(a));
	}
	
	public void addStayAliveCount(int a) {
		stayAliveCount.add(a);
	}
	
	public void removeStayAliveCount(int a) {
		stayAliveCount.remove(Integer.valueOf(a));
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
