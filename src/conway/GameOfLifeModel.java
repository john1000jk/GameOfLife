package conway;

public class GameOfLifeModel {
	
	private Board board;
//	private int reviveCount;
//	private int stayAliveCount;
	
	public GameOfLifeModel(Board b) {
		board = b;
	}
	
	public Board getBoard() {
		return board;
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
	}
}
