import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainMenu extends JFrame implements WinListener {
	private static final long serialVersionUID = 1L;

	CardLayout layout;
	JPanel menu, content, win, game;
	MainMenuButton play, easy, hard, next, exit;
	JumpIn jumpIn= new JumpIn(3);
	JumpInView view= new JumpInView(jumpIn);;

	public MainMenu() {

		JPanel panel = new JPanel();
		layout = new CardLayout();
		menu = new JPanel();
		content = new JPanel();
		win = new JPanel();

		actionListener al = new actionListener();

		play = new MainMenuButton("PLAY!");
		play.addActionListener(al);
		exit = new MainMenuButton("EXIT");
		exit.addActionListener(al);

		JLabel logo = new JLabel(Resources.LOGO);
		menu.setLayout(null);
		play.setBounds(350, 250, 100, 50);
		exit.setBounds(350, 350, 100, 50);
		logo.setBounds(160, 0, 500, 300);
		menu.add(logo);
		menu.add(play);
		menu.add(exit);
		menu.setBackground(new Color(152, 233, 233));

		win.setLayout(null);
		exit.setBounds(350, 350, 100, 50);
		win.add(exit);
		win.setBackground(new Color(152, 233, 233));

		content.setLayout(layout);
		content.add(view.getGame(), "Game");
		content.add(menu, "Menu");
		content.add(win, "Win");
		this.setContentPane(content);
		layout.show(content, "Menu");

		panel.setLayout(layout);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 800);
		this.setVisible(true);
	}

	public void handleEvent(WinEvent e) {
		layout.show(content, "Win");
	}

	public JumpInView getView() {
		return view;
	}

	public class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();

			if (src.equals(play)) {
				layout.show(content, "Game");
			} else if (src.equals(next)) {
				layout.show(content, "Game");
			} else if (src.equals(exit)) {
				SwingUtilities.getWindowAncestor((JPanel) src.getParent()).dispose();
			}
		}
	}
}
