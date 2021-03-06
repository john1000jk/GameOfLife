package board.Snake;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake {
	public static void main(String[] args) {
		/* Create top level window. */

		JFrame main_frame = new JFrame();
		main_frame.setTitle("John Kirollos Snake Game Simulator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create panel for content. Uses BorderLayout. */
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		SnakeModel model = new SnakeModel(20, 20);
		SnakeView view = new SnakeView(model);
		SnakeController controller = new SnakeController(model, view);
		
		
		top_panel.add(view, BorderLayout.CENTER);
		/* Pack main frame and make visible. */

		main_frame.pack();
		main_frame.setVisible(true);
	}		
}
