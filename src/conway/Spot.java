package conway;

import java.awt.Color;


public interface Spot {
	
	int getSpotX();
	int getSpotY();
	Board getBoard();
	
	boolean isSet();
	boolean isOn();
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
	void setShouldSet(boolean b);
	boolean getShouldClear();
	void setShouldClear(boolean b);
	void setSpotColor(Color c);
	Color getSpotColor();
	default String getCoordString() {
		return "(" + getSpotX() + ", " + getSpotY() + ")";
	}
}
