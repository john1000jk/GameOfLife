package conway;

import java.awt.color.*;
import java.util.Iterator;

public interface Board {
	int getSpotWidth();
	int getSpotHeight();
	int getNumNeighbors(Spot s);
	Spot getSpotAt(int x, int y);
	void addSpotListener(SpotListener spot_listener);
	void removeSpotListener(SpotListener spot_listener);
	Iterator<Spot> iterator();
}
