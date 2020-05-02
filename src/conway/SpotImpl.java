package conway;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SpotImpl extends JPanel implements Spot {
	private int spotX;
	private int spotY;
	private Board board;
	private boolean isSet;
	private boolean isOn;
	private boolean shouldSet;
	private boolean shouldClear;
	private Color spotColor;
	
	public SpotImpl(int x, int y, Board b, Color c) {
		spotX = x;
		spotY = y;
		board = b;
		spotColor = c;
		isSet = true;
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
	
	public boolean isSet() {
		return isSet;
	}
	
	public boolean getShouldSet() {
		return shouldSet;
	}
	
	public void setShouldSet(boolean b) {
		shouldSet = b;
	}
	
	public boolean getShouldClear() {
		return shouldClear;
	}
	
	public void setShouldClear(boolean b) {
		shouldClear = b;
	}

	@Override
	public void setSpot() {
		isOn = false;
		isSet = true;
		advance();
	}


	@Override
	public void clearSpot() {
		isOn = true;
		isSet = false;
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
	
	@Override
	public void paintComponent(Graphics g) {
		// Super class paintComponent will take care of 
		// painting the background.
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		if (!isOn()) {
			g2d.setColor(getSpotColor());
			g2d.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		} else {
			g2d.fillRect(0, 0, 0, 0);
		}
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
