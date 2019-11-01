import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.*;

public class JumpInView extends JFrame {
	private JButton[][] buttons;
	private JumpIn model;
	
	public JumpInView(JumpIn model){
		this.model = model;
		int rows = model.getNumRows();
		int cols = model.getNumColumns();
		//model.addView(this);
		buttons = new JButton[rows][cols];
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

	
	
}
