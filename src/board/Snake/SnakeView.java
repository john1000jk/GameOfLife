package board.Snake;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.util.*;
import board.Board;
import board.BoardImpl;
import board.Spot;
import board.SpotListener;

public class SnakeView extends JPanel implements ActionListener, SnakeModelObserver {
	private List<SnakeViewObserver> observers;
	private Board board;
	private SnakeModel model;
	private JPanel boardPanel = new JPanel();
	private JLabel score = new JLabel("0");
	private JButton reset = new JButton("RESET");
	private String direction;
	private String direction2;
	private KeyListener keynote = new CoolKeyListener();
	private boolean isOn = false;
	
	public SnakeView(SnakeModel m) {
		model = m;
		board = m.getBoard();
		model.addObserver(this);
		setFocusable(true);
		direction = "";
		direction2 = "";
		observers = new ArrayList<SnakeViewObserver>();
		addKeyListener(keynote);
		boardPanel.setLayout(new BorderLayout());
		boardPanel.add((BoardImpl) board, BorderLayout.CENTER);
		reset.addActionListener(this);
		setLayout(new BorderLayout());
		add(score, BorderLayout.SOUTH);
		add(boardPanel, BorderLayout.CENTER);
	}
	
	public void addObserver(SnakeViewObserver svo) {
		observers.add(svo);
	}
	
	public void removeObserver(SnakeViewObserver svo) {
		observers.remove(svo);
	}
	
	public void notifyObservers(SnakeViewEvent sve) {
		for (SnakeViewObserver svo: observers) {
			svo.handleEvent(sve);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			for (SnakeViewObserver svo: observers) {
				svo.handleEvent(new SVEReset(board));
			}
		} else {
			if (!isOn()) {
				isOn = true;
				for (SnakeViewObserver svo: observers) {
					svo.handleEvent(new SVEStart());
				}
			}
		}
	}

	@Override
	public void handleEvent(SnakeModelEvent sme) {
		switch(sme.getType()) {
		case STOP:
			isOn = false;
			direction2 = "";
			while (model.getSnakeSpots().size() != 0) {
				model.getSnakeSpots().get(0).toggleSpot();
				model.getSnakeSpots().remove(0);
			}
			score.setText("0");
			model.getSnakeSpots().add(board.getSpotAt(10, 10));
			model.getSnakeSpots().get(0).toggleSpot();
			break;
		case UPDATE:
			score.setText(Integer.parseInt(score.getText()) + 1 + "");
			break;
		}
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection2(String s) {
		direction2 = s;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	
	private class CoolKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	
		@Override
		public void keyPressed(KeyEvent e) {
			if (!isOn() && model.isReady()) {
				for (SnakeViewObserver svo: observers) {
					svo.handleEvent(new SVEStart());
				}
				isOn = true;
			}
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_UP:
				if (direction2 != "DOWN") {
					direction = "UP";
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction2 != "LEFT") {
					direction = "RIGHT";
				}
				break;
			case KeyEvent.VK_LEFT:
				if (direction2 != "RIGHT") {
					direction = "LEFT";
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction2 != "UP") {
					direction = "DOWN";
				}
				break;
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
