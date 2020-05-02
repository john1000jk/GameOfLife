package conway;

import java.awt.Color;


public interface Spot {
	
	int getSpotX();
	int getSpotY();
	Board getBoard();
	
	boolean isOn();
	void setSpot();
	void clearSpot();
	default void toggleSpot() {
		if (isOn()) {
			setSpot();
		} else {
			clearSpot();
		}
	}
	
	void setSpotColor(Color c);
	Color getSpotColor();
	default String getCoordString() {
		return "(" + getSpotX() + ", " + getSpotY() + ")";
	}
}
