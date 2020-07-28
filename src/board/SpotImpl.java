package board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SpotImpl extends JPanel implements Spot, MouseListener {
	private int spotX;
	private int spotY;
	private Board board;
	private boolean isSet;
	private boolean shouldSet;
	private boolean shouldClear;
	private boolean isApple;
	private Color spotColor;
	private List<SpotListener> spotListeners;
	
	public SpotImpl(int x, int y, Board b, Color c) {
		setFocusable(true);
		spotX = x;
		spotY = y;
		board = b;
		spotColor = c;
		isSet = true;
		isApple = false;
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
		isSet = true;
		advance();
	}


	@Override
	public void clearSpot() {
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
		if (isSet() && !(isApple)) {
			g2d.setColor(getSpotColor());
			g2d.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		} else if (isApple) {
			g.drawOval(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		} else {
			g2d.fillRect(0, 0, 0, 0);
		}
	}

	private void advance() {
		repaint();
	}
	
	public boolean isApple() {
		return isApple;
	}
		
	public void morphApple() {
		isApple = !isApple;
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
