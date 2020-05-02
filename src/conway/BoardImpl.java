package conway;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BoardImpl extends JPanel implements Board {

	private Spot[][] spots;
	private Spot[][] spotsWrap;
	
	public BoardImpl(int y, int x) {
		if (x < 1 || y < 1 || x > 500 || y > 500) {
			throw new IllegalArgumentException("Illegal spot board geometry");
		}
		spots = new Spot[x][y];
		spotsWrap = new Spot[x+2][y+2];
		setLayout(new GridLayout(x, y));
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				spots[i][j] = new SpotImpl(i, j, this, Color.BLACK);
				spots[i][j].toggleSpot();
				spotsWrap[i+1][j+1] = spots[i][j];
				Dimension preferredSize = new Dimension(500/x, 500/y);
				((SpotImpl) spots[i][j]).setPreferredSize(preferredSize);
				add((SpotImpl) spots[i][j]);
			}
		}
		spots[1][1].toggleSpot();
		spots[1][2].toggleSpot();
		spots[2][1].toggleSpot();
		spots[2][2].toggleSpot();
		for (int i = 1; i < x + 1; i++) {
			spotsWrap[i][0] = spots[i-1][y-1];
			spotsWrap[i][y+1] = spots[i-1][0];
		}
		for (int j = 1; j < y + 1; j++) {
			spotsWrap[0][j] = spots[x-1][j-1];
			spotsWrap[x+1][j] = spots[0][j -1];
		}
		spotsWrap[0][0] = spots[x-1][y-1];
		spotsWrap[x+1][0] = spots[0][y-1];
		spotsWrap[0][y+1] = spots[x-1][0];
		spotsWrap[x+1][y+1] = spots[0][0];
	}
	

	@Override
	public int getSpotWidth() {
		return spots.length;
	}

	@Override
	public int getSpotHeight() {
		return spots[0].length;
	}
	
	public Spot getSpotAt(int x, int y) {
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight()) {
			throw new IllegalArgumentException("Illegal spot coordinates");
		}
		return spots[x][y];
	}
	
	public Iterator<Spot> iterator() {
		return new BoardIterator(this);
	}
	
	public int getNumNeighbors(Spot s) {
		int numNeighbors = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (spotsWrap[s.getSpotX() + 1 + i][s.getSpotY() + 1 + j].isSet()) {
					if (!(i == 0 && j == 0)) {
						numNeighbors += 1;
					}
				}
			}
		}
		return numNeighbors;
	}
}