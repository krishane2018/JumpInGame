import java.awt.Color;
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
		GridLayout g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);
		for(int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
//				buttons[i][j].setBackground(new Color(70,170,70));
				ImageIcon icon = new ImageIcon("images//greenCircle.png");
				buttons[i][j] = new GameButton(new Point(i,j), icon);
				panel.add(buttons[i][j]);
				
				//colour for circles
//				buttons[i][j].setBackground(new Color(60,150,80));
			}
		}
		
		ImageIcon icon = new ImageIcon("images//brownRabbit.png");
		buttons[0][0].setIcon(icon);

		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(500,500);		
		this.setVisible(true);	
		
		
//		i = new ImageIcon("images//board.png");
//		JLabel label = new JLabel(icon);
//		this.add(label);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.pack();
//		this.setResizable(true);
//		this.setVisible(true);
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
