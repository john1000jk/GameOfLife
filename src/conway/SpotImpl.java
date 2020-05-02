package conway;

import java.awt.Color;

import javax.swing.JPanel;

public class SpotImpl extends JPanel implements Spot {
	private int spotX;
	private int spotY;
	private Board board;
	private boolean isOn;
	private Color spotColor;
	
	public SpotImpl(int x, int y, Board b, Color c) {
		spotX = x;
		spotY = y;
		board = b;
		spotColor = c;
	}
	

	@Override
	public int getSpotX() {
		return spotX;
	}

	@Override
	public int getSpotY() {
		return spotY;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public boolean isOn() {
		return isOn;
	}

	@Override
	public void setSpot() {
		isOn = false;
		advance();
	}


	@Override
	public void clearSpot() {
		isOn = true;
		advance();
	}

	@Override
	public void setSpotColor(Color c) {
		spotColor = c;
	}

	@Override
	public Color getSpotColor() {
		return spotColor;
	}

	private void advance() {
		repaint();
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
				repaint();
			}
		}).start();
	}
}
