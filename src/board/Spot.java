package board;

import java.awt.Color;
import java.awt.event.KeyListener;


public interface Spot {
	
	int getSpotX();
	int getSpotY();
	Board getBoard();
	
	boolean isSet();
	void setSpot();
	void clearSpot();
	default void toggleSpot() {
		if (!isSet()) {
			setSpot();
		} else {
			clearSpot();
		}
	}
	
	void addSpotListener(SpotListener a);
	void removeSpotListener(SpotListener a);
	boolean getShouldSet();
	boolean isApple();
	void morphApple();
	void setShouldSet(boolean b);
	boolean getShouldClear();
	void setShouldClear(boolean b);
	void setSpotColor(Color c);
	Color getSpotColor();
	default String getCoordString() {
		return "(" + getSpotX() + ", " + getSpotY() + ")";
	}
}
