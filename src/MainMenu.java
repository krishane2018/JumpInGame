import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * The main menu class is a JFrame that holds all the panels in a card layout. This allows
 * the user to view the main menu, game, and a win panel.
 * 
 * @author Kush Gopeechund
 *
 */
public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private CardLayout layout;
	private JPanel menu, content, win;
	private MainMenuButton play, easy, hard, next, exit1, exit2;
	private JumpInView view;
	
	/**
	 * Creates all the panels and adds it to the card layout.
	 * 
	 * @param view - sets game panel
	 */
	public MainMenu(JumpInView view) {
		this.view = view;
		
		JPanel panel = new JPanel();
		layout = new CardLayout();
		menu = new JPanel();
		content = new JPanel();
		win = new JPanel();

		actionListener al = new actionListener();

		play = new MainMenuButton("PLAY!");
		play.addActionListener(al);
		exit1 = new MainMenuButton("EXIT");
		exit1.addActionListener(al);
		exit2 = new MainMenuButton("EXIT");
		exit2.addActionListener(al);

		JLabel logo = new JLabel(Resources.LOGO);
		menu.setLayout(null);
		play.setBounds(350, 250, 100, 50);
		exit1.setBounds(350, 350, 100, 50);
		logo.setBounds(160, 0, 500, 300);
		menu.add(logo);
		menu.add(play);
		menu.add(exit1);
		menu.setBackground(new Color(152, 233, 233));
		
		win.setLayout(null);
		exit2.setBounds(350, 350, 100, 50);
		win.add(exit2);
		win.setBackground(new Color(152, 233, 233));

		content.setLayout(layout);
		content.add(view.getPanel(), "Game");
		content.add(menu, "Menu");
		content.add(win, "Win");
		this.setContentPane(content);
		layout.show(content, "Menu");

		panel.setLayout(layout);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800,800);
		this.setVisible(true);
	}

	/**
	 * @param e
	 */
	public void handleWin() {
		layout.show(content, "Win");
	}

	/**
	 * getter for view
	 * @return
	 */
	public JumpInView getView() {
		return view;
	}

	/**
	 * A new action listener class was created to change what
	 * happens when the buttons are clicked.
	 * 
	 * @author Kush Gopeechund
	 *
	 */
	public class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			if (src.equals(play)) {
				layout.show(content, "Game");
			} else if (src.equals(next)) {
				layout.show(content, "Game");
			} else if (src.equals(exit1)||src.equals(exit2)) {
				SwingUtilities.getWindowAncestor((JPanel) src.getParent()).dispose();
			}
		}
	}
}
