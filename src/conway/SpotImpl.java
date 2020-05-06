package conway;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SpotImpl extends JPanel implements Spot, MouseListener {
	private int spotX;
	private int spotY;
	private Board board;
	private boolean isSet;
	private boolean isOn;
	private boolean shouldSet;
	private boolean shouldClear;
	private Color spotColor;
	private List<SpotListener> spotListeners;
	
	public SpotImpl(int x, int y, Board b, Color c) {
		spotX = x;
		spotY = y;
		board = b;
		spotColor = c;
		isSet = true;
		spotListeners = new ArrayList<SpotListener>();
		addMouseListener(this);
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
	
	public void addSpotListener(SpotListener s) {
		spotListeners.add(s);
	}
	
	public void removeSpotListener(SpotListener s) {
		spotListeners.remove(s);
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
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
		for (SpotListener s : spotListeners) {
			s.spotClicked(this);
		}		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
