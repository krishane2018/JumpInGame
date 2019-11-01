import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.*;

public class JumpInView extends JFrame implements JumpInListener {
	private GameButton[][] buttons;
	private JumpIn model;
	
	public JumpInView(JumpIn model){
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
		model.addListener(this);
		buttons = new GameButton[rows][cols];
		JPanel panel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout g = new GridLayout(rows, cols);
		panel.setLayout(g);
		for(int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
				buttons[i][j] = new GameButton(new Point(i,j));
				panel.add(buttons[i][j]);
			}
		}
		
		this.add(panel);
		//change if pics look ugly
		this.setResizable(true);
		this.setSize(500,500);
		this.setVisible(true);
		
	}

	public GameButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(GameButton[][] buttons) {
		this.buttons = buttons;
	}

	@Override
	public void handleEvent(JumpInEvent e) {
		//update view (move piece, update coordinates)
		
	}

	
	
}
