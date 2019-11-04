import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import javax.swing.*;
import javax.swing.border.LineBorder;

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
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buttons[j][i] = new GameButton(new Point(j,i));
				panel.add(buttons[j][i]);
			}
		}
		Board.create(model.getLevel(), this, model);
		this.add(panel);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800,800);
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
	
	public void highlightOption(Point p) {
		buttons[p.x][p.y].highlightButton();
	}
}
