package board;

import java.awt.color.*;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

public interface Board {
	int getSpotWidth();
	int getSpotHeight();
	int getNumNeighbors(Spot s);
	Spot getSpotAt(int x, int y);
	void addSpotListener(SpotListener spot_listener);
	List<Spot> getUnoccupiedSpots();
	void removeSpotListener(SpotListener spot_listener);
	Iterator<Spot> iterator();
}
