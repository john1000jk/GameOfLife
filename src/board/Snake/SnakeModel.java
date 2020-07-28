package board.Snake;

import java.util.*;

import board.*;

public class SnakeModel {
	private Board board;
	private List<SnakeModelObserver> observers;
	private List<Spot> snakeSpots;
	private boolean isReady;
	public SnakeModel(int y, int x) {
		board = new BoardImpl(17, 15, 0);
		observers = new ArrayList<SnakeModelObserver>();
		isReady = true;
		snakeSpots = new ArrayList<Spot>();
		snakeSpots.add(board.getSpotAt(10, 10));
		board.getSpotAt(10, 10).toggleSpot();
		board.getUnoccupiedSpots().get((int) (Math.random()*board.getUnoccupiedSpots().size())).morphApple();
	}
	
	public void advance(String direction) {
		Spot spot = board.getSpotAt(10, 10);
		switch(direction) {
			case ("RIGHT"):
				try {
					spot = board.getSpotAt(snakeSpots.get(snakeSpots.size()-1).getSpotX(), snakeSpots.get(snakeSpots.size()-1).getSpotY() + 1);
				} catch (IllegalArgumentException e) {
					notifyObservers(new SMEStop());
					return;
				}
				break;
			case ("LEFT"):
				try {
					spot = (board.getSpotAt(snakeSpots.get(snakeSpots.size()-1).getSpotX(), snakeSpots.get(snakeSpots.size()-1).getSpotY() - 1));
				} catch (IllegalArgumentException e) {
					notifyObservers(new SMEStop());
					return;
				}
				break;
			case ("UP"):
				try {
					spot = (board.getSpotAt(snakeSpots.get(snakeSpots.size()-1).getSpotX() - 1, snakeSpots.get(snakeSpots.size()-1).getSpotY()));
				} catch (IllegalArgumentException e) {
					notifyObservers(new SMEStop());
					return;
				}
				break;
			case ("DOWN"):
				try {
					spot = (board.getSpotAt(snakeSpots.get(snakeSpots.size()-1).getSpotX() + 1, snakeSpots.get(snakeSpots.size()-1).getSpotY()));
				} catch (IllegalArgumentException e) {
					notifyObservers(new SMEStop());
					return;
				}
				break;
		}
		
		snakeSpots.add(spot);
		if (spot.isSet()) {
			spot.toggleSpot();
			notifyObservers(new SMEStop());
			return;
		}
		if (spot.isApple()) {
			spot.morphApple();
			spot.toggleSpot();
			notifyObservers(new SMEUpdate());
			board.getUnoccupiedSpots().get((int) (Math.random()*board.getUnoccupiedSpots().size())).morphApple();
		} else {
			spot.toggleSpot();
			snakeSpots.get(0).toggleSpot();
			snakeSpots.remove(0);
		}
	}
	
	public void reset(Board b) {
		board = b;
		notifyObservers(new SMEReset());
	}
	
	public List<Spot> getSnakeSpots() {
		return snakeSpots;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public void setReady(boolean b) {
		isReady = b;
	}
	public void addObserver(SnakeModelObserver smo) {
		observers.add(smo);
	}
	
	public void removeObserver(SnakeModelObserver smo) {
		observers.remove(smo);
	}
	
	public void notifyObservers(SnakeModelEvent sme) {
		for (SnakeModelObserver smo: observers) {
			smo.handleEvent(sme);
		}
	}
}
