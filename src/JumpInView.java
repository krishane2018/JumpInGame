import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.net.URL;

import javax.annotation.Resources;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class JumpInView extends JFrame implements JumpInListener {
	private JButton[][] buttons;
	private JumpIn model;
	
	public JumpInView(JumpIn model){
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
		
		
		
		model.addListener(this);
		buttons = new JButton[rows][cols];
		JPanel panel = new JPanel();
		GridLayout g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ImageIcon icon = new ImageIcon("resources\\greenCircle.png");
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance(220, 150, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);
//				buttons[i][j] = new GameButton(new Point(i,j), icon);
				buttons[j][i] = new JButton(icon);
				buttons[j][i].setBackground(new Color(70,170,70));
				panel.add(buttons[j][i]);
			}
		}
		
		// Intialize other buttons
		buttons[0][0].setBorder(new LineBorder(Color.WHITE));
		ImageIcon i = new ImageIcon("resources\\brownCircle.png");
		
		Image img = i.getImage();
		Image newimg = img.getScaledInstance(220, 150, java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(newimg);
		buttons[0][0].setIcon(i);
		this.add(panel);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800,800);
		this.setVisible(true);	
		
		
//		i = new ImageIcon("images//board.png");
//		JLabel label = new JLabel(icon);
//		this.add(label);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.pack();
//		this.setResizable(true);
//		this.setVisible(true);
	}

//	public GameButton[][] getButtons() {
//		return buttons;
//	}

	public void setButtons(GameButton[][] buttons) {
		this.buttons = buttons;
	}

	@Override
	public void handleEvent(JumpInEvent e) {
		//update view (move piece, update coordinates)
		
	}

	
	
}
