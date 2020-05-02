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
	
	public BoardImpl(int x, int y) {
		if (x < 1 || y < 1 || x > 500 || y > 500) {
			throw new IllegalArgumentException("Illegal spot board geometry");
		}
		spots = new Spot[x][y];
		spotsWrap = new Spot[x+2][y+2];
		setLayout(new GridLayout(x, y));
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				spots[i][j] = new SpotImpl(i, j, this, Color.BLACK);
				if (Math.random() < .8) {
					spots[i][j].toggleSpot();
				}
				spotsWrap[i+1][j+1] = spots[i][j];
				Dimension preferredSize = new Dimension(500/x, 500/y);
				((SpotImpl) spots[i][j]).setPreferredSize(preferredSize);
				add((SpotImpl) spots[i][j]);
			}
		}
		for (int i = 0; i < x; i++) {
			spotsWrap[i][0] = spots[i][y-1];
			spotsWrap[i][y+1] = spots[i][0];
		}
		for (int j = 0; j < y; j++) {
			spotsWrap[0][j] = spots[x-1][j];
			spotsWrap[x+1][j] = spots[0][j];
		}
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
		return spots[x][y];
	}
	
	public Iterator<Spot> iterator() {
		return new BoardIterator(this);
	}
	
	public int getNumNeighbors(Spot s) {
		int numNeighbors = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (spotsWrap[s.getSpotX() + 1 -i][s.getSpotY() + 1 -j].isOn()) {
					numNeighbors += 1;
				}
			}
		}
		return numNeighbors;
	}
}