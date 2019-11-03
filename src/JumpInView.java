import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
		play = new JButton("PLAY!");			//
		play.setBackground(new Color(70,170,70));
		play.setForeground(Color.white);
		play.setFocusPainted(false);
		play.setPreferredSize(new Dimension(100,50));
		play.setFont(new Font("Arial", Font.BOLD,20));
		
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;

		model.addListener(this);
		
		actionListener al = new actionListener();	//
		play.addActionListener(al);					//
		try {
			img=ImageIO.read(new File("D:/school/year3/softwareproject/png/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel logo = new JLabel(new ImageIcon(img));
		menu.add(logo);
		menu.add(play);								//
		menu.setBackground(Color.white);
		
		
		buttons = new GameButton[rows][cols];
		content.setLayout(layout);					//
		content.add(panel, "Game");					//
		content.add(menu, "Menu");					//
		this.setContentPane(content);				//
		layout.show(content , "Menu");				//
		
		g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);

		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buttons[j][i] = new GameButton(new Point(j,i));
				panel.add(buttons[j][i]);
			}
		}
		this.setTitle("JumpIn");					
		Board.create(model.getLevel(), this, model);
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
