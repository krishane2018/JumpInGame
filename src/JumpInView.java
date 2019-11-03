import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JumpInView extends JFrame implements JumpInListener {
	private static final long serialVersionUID = 1L;
	
	private GameButton[][] buttons;
	private JumpIn model;
	private BufferedImage img;
	private ImageIcon icon;
	
	JPanel panel = new JPanel();
	
	CardLayout layout = new CardLayout(); 	//new
	JPanel menu = new JPanel();				//
	JPanel content = new JPanel();			//
	
	JButton play;							//
	GridLayout g;
	
	public JumpInView(JumpIn model){
		play = new JButton("Play");			//
		
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
		model.addListener(this);
		
		actionListener al = new actionListener();	//
		play.addActionListener(al);					//
		try {
			img=ImageIO.read(new File("resources//logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logo = new JLabel(new ImageIcon(img));
		menu.add(play);								//
		menu.setBackground(Color.white);
		menu.add(logo);
		
		buttons = new GameButton[rows][cols];
		content.setLayout(layout);					//
		content.add(panel, "Game");					//
		content.add(menu, "Menu");					//
		this.setContentPane(content);				//
		layout.show(content , "Menu");				//
		
		g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);
		for(int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
//				buttons[i][j].setBackground(new Color(70,170,70));
				//ImageIcon icon = new ImageIcon("images//greenCircle.png");
				buttons[i][j] = new GameButton(new Point(i,j));
				buttons[i][j].setIcon(icon);
				panel.add(buttons[i][j]);
				
				//colour for circles
//				buttons[i][j].setBackground(new Color(60,150,80));
			}
		}
		
		ImageIcon icon = new ImageIcon("images//brownRabbit.png");
		buttons[0][0].setIcon(icon);

		this.setTitle("JumpIn");					//
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

	// new override
	public class actionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			
			if(src.equals(play)) {
				layout.show(content, "Game");
			}
		}
	}
	
}
