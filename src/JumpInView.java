import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JumpInView extends JFrame implements JumpInListener {
	private static final long serialVersionUID = 1L;
	
	private GameButton[][] buttons;
	private JumpIn model;
	
	JPanel panel = new JPanel();
	
	CardLayout layout = new CardLayout(); 	
	JPanel menu = new JPanel();	
	JPanel difficulty = new JPanel();
	JPanel content = new JPanel();	
	JPanel win = new JPanel();
	MainMenuButton play,easy,hard,next,exit;
	GridLayout g;
	
	public JumpInView(JumpIn model){
		actionListener al = new actionListener();
		
		play = new MainMenuButton("PLAY!");			
		play.addActionListener(al);	
		easy = new MainMenuButton("EASY");			
		easy.addActionListener(al);
		hard = new MainMenuButton("HARD");			
		hard.addActionListener(al);
		next = new MainMenuButton("NEXT");			
		next.addActionListener(al);
		exit = new MainMenuButton("EXIT");			
		exit.addActionListener(al);
		
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;

		model.addListener(this);
				
		JLabel logo1 = new JLabel(Resources.LOGO);
		menu.setLayout(null);
		play.setBounds(350, 250, 100, 50);
		exit.setBounds(350, 350, 100, 50);
		logo1.setBounds(160, 0, 500, 300);
		menu.add(logo1);
		menu.add(play);
		menu.add(exit);
		menu.setBackground(new Color(152,233,233));
		
		JLabel logo2 = new JLabel(Resources.LOGO);
		difficulty.setLayout(null);
		easy.setBounds(350, 250, 100, 50);
		hard.setBounds(350, 350, 100, 50);
		logo2.setBounds(160, 0, 500, 300);
		difficulty.add(logo2);
		difficulty.add(easy);
		difficulty.add(hard);
		difficulty.setBackground(new Color(152,233,233));
		
		buttons = new GameButton[rows][cols];
		
		content.setLayout(layout);					
		content.add(panel, "Game");					
		content.add(menu, "Menu");
		content.add(difficulty,"Difficulty");
		this.setContentPane(content);				
		layout.show(content , "Menu");				
		
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
				layout.show(content, "Difficulty");
			}else if(src.equals(easy)) {
				layout.show(content, "Game");
			}else if(src.equals(hard)) {
				layout.show(content, "Game");
			}else if(src.equals(next)) {
				layout.show(content, "Game");
			}
		}
	}
	
}
