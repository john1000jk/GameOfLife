package conway;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardIterator implements Iterator<Spot> {

	private Board board;
	int x;
	int y;
	
	public BoardIterator(Board b) {
		board = b;
		x = 0;
		y = 0;
	}

	@Override
	public boolean hasNext() {
		return (y < board.getSpotHeight());
	}

	@Override
	public Spot next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Spot s = board.getSpotAt(x, y);
		if (x < board.getSpotWidth()-1) {
			x++;
		} else {
			x = 0;
			y++;
		}
		return s;
	}

}