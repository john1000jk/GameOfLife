package conway;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLifeView extends JPanel implements ActionListener, GameOfLifeModelObserver {
	private List<GameOfLifeViewObserver> observers;
	private Board board;
	private GameOfLifeModel model;
	private JButton start = new JButton("Start");
	private JButton reset = new JButton("Reset");
	private JLabel widthL = new JLabel("Width: ");
	private JTextField width = new JTextField(100 + "");
	private JLabel heightL = new JLabel("Height: ");
	private JTextField height = new JTextField(100 + "");
	private JLabel simSpeedL = new JLabel("Sim Speed: ");
	private JSlider simSpeed = new JSlider(1, 100);
	private ActionListener enterListener = new CoolActionListener();

	
	public GameOfLifeView(GameOfLifeModel m) {
		model = m;
		board = m.getBoard();
		model.addObserver(this);
		observers = new ArrayList<GameOfLifeViewObserver>();
		
		setLayout(new BorderLayout());
		add((BoardImpl) board, BorderLayout.CENTER);
		
		JPanel topRow = new JPanel();
		JPanel sliderPanel = new JPanel();
		JPanel sliderNamePanel = new JPanel();
		JPanel superSliderPanel = new JPanel();
		JPanel totalDisplayPanel = new JPanel();
		
		
		topRow.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 40;
		topRow.add(start, c);
		topRow.add(widthL, c);
		topRow.add(width, c);
		topRow.add(heightL, c);
		topRow.add(height, c);
		topRow.add(reset, c);
		
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
		totalDisplayPanel.add(topRow, c);
		c.gridx = 0;
		c.gridy = 1;
		totalDisplayPanel.add(superSliderPanel, c);
		
		width.addActionListener(enterListener);
		height.addActionListener(enterListener);
		start.addActionListener(this);
		reset.addActionListener(this);
		
		simSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (simSpeed.getValue() == 0 && start.getText().equals("Stop")) {
					actionPerformed(new ActionEvent(start, ActionEvent.ACTION_PERFORMED, "apple"));
				}
				model.setSimSpeed(simSpeed.getValue());
				simSpeedL.setText("Sim Speed (steps/sec): " + simSpeed.getValue());
			}
		});
		
		add(totalDisplayPanel, BorderLayout.SOUTH);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().equals("Start")) {
			button.setText("Stop");
		} else if (button.getText().equals("Stop")) {
			button.setText("Start");
		} else if (button.getText().equals("Reset")) {
			enterListener.actionPerformed(e);
		}
		if (button.getText().equals("Start") || button.getText().equals("Stop")) {
			for (GameOfLifeViewObserver v: observers) {
				v.handleEvent(new GVEStep());
			}
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
			 	} else if(start.getText().equals("Start")) {
				}
		    	int a;
		    	int b;
		    	double c;
		    	try {
		    		a = Integer.parseInt(width.getText());
		    	} catch (Exception f) {
		    		a = 100;
		    	}
		    	try {
		    		b = Integer.parseInt(height.getText());
		    	} catch (IllegalArgumentException f) {
		    		b = 100;
		    	}
		    	for (GameOfLifeViewObserver v: observers) {
		    		v.handleEvent(new GVEReset(a, b));
		    	}
		    }
	}

}
