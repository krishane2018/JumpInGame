import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class JumpInView extends JFrame implements JumpInListener, WinListener {
	private static final long serialVersionUID = 1L;
	
	private GameButton[][] buttons;
	private JumpIn model;
	private ArrayList<Object> options;
	
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
		this.options = new ArrayList<Object>();
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
		
		win.setLayout(null);
		next.setBounds(350, 250, 100, 50);
		exit.setBounds(350, 350, 100, 50);
		win.add(next);
		win.add(exit);
		win.setBackground(new Color(152,233,233));
		
		buttons = new GameButton[rows][cols];
		
		content.setLayout(layout);					
		content.add(panel, "Game");					
		content.add(menu, "Menu");
		content.add(win, "Win");
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
	public void handleEvent(WinEvent e) {
		//update view (move piece, update coordinates)
		layout.show(content, "Win");
		Board.create(model.getLevel()+1, this, model);
	}
	
	@Override
	public void handleEvent(JumpInEvent e) {
		Point initialLocation = e.getChosenPiece().getCoordinate();
		if(e.getChosenPiece().getClass().getSimpleName().contentEquals("Rabbit")) {
			buttons[e.getCoordinate1().x][e.getCoordinate1().y].setIcon(buttons[initialLocation.x][initialLocation.y].getIcon());
			if (model.isHole(initialLocation.y, initialLocation.x)) {
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.HOLE);
			} else {
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.GREEN_CIRCLE);
			}	
		} else if (e.getChosenPiece().getClass().getSimpleName().contentEquals("Fox")) {
			
		}
	}

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
			}else if(src.equals(exit)) {
				SwingUtilities.getWindowAncestor((JPanel)src.getParent()).dispose();
			}
		}
	}
	
	public boolean highlightOption(Point p) {
		buttons[p.x][p.y].highlightButton();
		return true;
	}
	
	public boolean unhighlight(Point p) {
		buttons[p.x][p.y].removeHighlight();
		return true;
	}
	
	public boolean highlightOptions(Point initialLocation) {
		options = model.getAnimalOptions(initialLocation);
		String selectedAnimalType = model.selectedAnimalType(initialLocation);
		if(options.isEmpty() && (selectedAnimalType.equals("Rabbit") || selectedAnimalType.equals("Fox"))){
			JOptionPane.showMessageDialog(null, "Selected box has no available moves");
			return false;
		} else if (options.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select an animal to move");
			return false;
		}
		highlight(selectedAnimalType, true);
		return true;
	}
	
	public void highlight(String selectedAnimalType, boolean highlight) {
		if (selectedAnimalType.equals("Rabbit")){
			for (Object o : options) {
				Point pt = (Point)o;
				if(highlight) highlightOption(pt);
				else unhighlight(pt);
			}
		} else if (selectedAnimalType.equals("Fox")) {
			for(Object o : options) {
				Point point[] = (Point[])o;
				for (Point pt : point) {
					if(highlight) highlightOption(pt);
					else unhighlight(pt);
				}
			}
		}
	}
}
