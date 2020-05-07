package conway;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLifeView extends JPanel implements ActionListener, SpotListener, GameOfLifeModelObserver {
	private List<GameOfLifeViewObserver> observers;
	private Board board;
	private GameOfLifeModel model;
	private JButton start = new JButton("Start");
	private JButton reset = new JButton("Reset");
	private JPanel boardPanel = new JPanel();
	private JLabel iterationsL = new JLabel("Iterations: ");
	private JLabel iterations = new JLabel("0");
	private JLabel widthL = new JLabel("Width: ");
	private JTextField width = new JTextField(20 + "");
	private JLabel heightL = new JLabel("Height: ");
	private JTextField height = new JTextField(20 + "");
	private JLabel densityL = new JLabel("Density: ");
	private JTextField density = new JTextField(20 + "");
	private JLabel simSpeedL = new JLabel("Sim Speed: ");
	private JSlider simSpeed = new JSlider(1, 100);
	private ActionListener enterListener = new CoolActionListener();
	private JSlider resetS = new JSlider(1, 2);

	
	public GameOfLifeView(GameOfLifeModel m) {
		model = m;
		board = m.getBoard();
		model.addObserver(this);
		observers = new ArrayList<GameOfLifeViewObserver>();
		
		setLayout(new BorderLayout());		
		JPanel stayAliveRow = new JPanel();
		JPanel reviveRow = new JPanel();
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel sliderPanel = new JPanel();
		JPanel sliderNamePanel = new JPanel();
		JPanel superSliderPanel = new JPanel();
		JPanel totalDisplayPanel = new JPanel();
		
		for (int i = 0; i < 9; i++) {
			stayAliveRow.add(new JCheckBox(i + ""));
			reviveRow.add(new JCheckBox(i + ""));
		}
		
		for (Component c: stayAliveRow.getComponents()) {
			JCheckBox check = (JCheckBox) c;
			check.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (check.isSelected()) {
						model.addStayAliveCount(Integer.parseInt(check.getText()));
					} else {
						model.removeStayAliveCount(Integer.parseInt(check.getText()));
					}
				}
			});
		}
		
		for (Component c: reviveRow.getComponents()) {
			JCheckBox check = (JCheckBox) c;
			check.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (check.isSelected()) {
						model.addReviveCount(Integer.parseInt(check.getText()));
					} else {
						model.removeReviveCount(Integer.parseInt(check.getText()));
					}
				}
			});
		}
		
		JLabel survive = new JLabel("Survive Rules:");
		JLabel revive = new JLabel("Revive Rules: ");
		
		stayAliveRow.setLayout(new GridBagLayout());
		reviveRow.setLayout(new GridBagLayout());
		
		row1.setLayout(new BorderLayout());
		row1.add(survive, BorderLayout.WEST);
		row1.add(stayAliveRow, BorderLayout.CENTER);
		
		row2.setLayout(new BorderLayout());
		row2.add(revive, BorderLayout.WEST);
		row2.add(reviveRow, BorderLayout.CENTER);
		
		row3.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 40;
		c.ipadx = 5;
		row3.add(iterationsL, c);
		row3.add(iterations, c);
		row3.add(start, c);
		row3.add(new JLabel(), c);
		row3.add(widthL, c);
		row3.add(width, c);
		row3.add(heightL, c);
		row3.add(height, c);
		row3.add(densityL, c);
		row3.add(density, c);
		row3.add(reset, c);
		
		boardPanel.setLayout(new GridLayout());
		boardPanel.add((BoardImpl) board, BorderLayout.CENTER);
		add(boardPanel, BorderLayout.CENTER);

		
		sliderNamePanel.setLayout(new GridBagLayout());
		sliderNamePanel.add(simSpeedL);
		
		sliderPanel.setLayout(new GridBagLayout());
		sliderPanel.add(simSpeed);
		
		superSliderPanel.setLayout(new BorderLayout());
		superSliderPanel.add(sliderNamePanel, BorderLayout.EAST);
		superSliderPanel.add(sliderPanel, BorderLayout.CENTER);
		
		totalDisplayPanel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		totalDisplayPanel.add(row1, c);
		c.gridx = 0;
		c.gridy = 1;
		totalDisplayPanel.add(row2, c);
		c.gridx = 0;
		c.gridy = 2;
		totalDisplayPanel.add(row3, c);
		c.gridx = 0;
		c.gridy = 3;
		totalDisplayPanel.add(superSliderPanel, c);
		
		width.addActionListener(enterListener);
		height.addActionListener(enterListener);
		density.addActionListener(enterListener);
		start.addActionListener(this);
		reset.addActionListener(enterListener);
		board.addSpotListener(this);
		
		simSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				model.setSimSpeed(simSpeed.getValue());
				simSpeedL.setText("Sim Speed (steps/sec): " + simSpeed.getValue());
			}
		});
		
		
		resetS.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		
		add(totalDisplayPanel, BorderLayout.SOUTH);
		
		for (Component f: stayAliveRow.getComponents()) {
			JCheckBox check = (JCheckBox) f;
			if (check.getText().equals("2") || check.getText().equals("3")) {
				check.doClick();
			}
		}
		
		for (Component f: reviveRow.getComponents()) {
			JCheckBox check = (JCheckBox) f;
			if (check.getText().equals("3")) {
				check.doClick();
			}
		}

	}
	
	public boolean isOn() {
		return !(start.getText() == "Start");
	}
	
	public int getSimSpeed() {
		return simSpeed.getValue();
	}
	
	public void addObserver(GameOfLifeViewObserver gvo) {
		observers.add(gvo);
	}
	
	public void removeObserver(GameOfLifeViewObserver gvo) {
		observers.remove(gvo);
	}
	
	public void notifyObservers(GameOfLifeViewEvent gve) {
		for (GameOfLifeViewObserver o: observers) {
			o.handleEvent(gve);
		}
	}
	@Override
	public void update(GameOfLifeModel m, GameOfLifeModelEvent gme) {
		switch (gme.getType()) {
		case RESET:
			iterations.setText("-1");
		case ADVANCED:
			iterations.setText(Integer.parseInt(iterations.getText()) + 1 + "");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("Start")) {
			button.setText("Stop");
		} else if (button.getText().equals("Stop")) {
			button.setText("Start");
		}
		for (GameOfLifeViewObserver v: observers) {
			v.handleEvent(new GVEStep());
		}
	}
	
	GameOfLifeView that = this;
	class CoolActionListener implements ActionListener {
		 @Override
		    public void actionPerformed(ActionEvent e) {
			 	if (start.getText().contentEquals("Stop")) {
			 		e.setSource(start);
			 		that.actionPerformed(e);
			 		return;
			 	} 
		    	int a;
		    	int b;
		    	double c;
		    	try {
		    		a = Integer.parseInt(width.getText());
		    	} catch (Exception f) {
		    		a = 20;
		    	}
		    	try {
		    		b = Integer.parseInt(height.getText());
		    	} catch (IllegalArgumentException f) {
		    		b = 20;
		    	}
		    	try {
		    		c = Double.parseDouble(density.getText());
		    	} catch (IllegalArgumentException f) {
		    		c = 20;
		    	}
		    	boardPanel.removeAll();
		    	board = new BoardImpl(a, b, c);
		    	board.addSpotListener(that);
				boardPanel.add((BoardImpl) board, BorderLayout.CENTER);
		    	for (GameOfLifeViewObserver v: observers) {
		    		v.handleEvent(new GVEReset(board));
		    	}
		    }
	}
	@Override
	public void spotClicked(Spot s) {
		s.toggleSpot();
	}

}
