package gui;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 
 * The main menu class is a JFrame that holds all the panels in a card layout.
 * This allows the user to view the main menu, game, and a win panel.
 * 
 * @author Kush Gopeechund
 *
 */
public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenu options;
	private JMenuBar menuBar;
	private CardLayout layout;
	private JPanel menu, content, win, done;
	private JumpInView view;
	private ArrayList<JButton> buttons;
	private JMenuItem undo, redo, hint, save;
	private ArrayList<JMenuItem> menuItems;

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
		done = new JPanel();
		buttons = new ArrayList<JButton>();
		menuItems = new ArrayList<JMenuItem>();
		options = new JMenu("options");
		undo = new JMenuItem("undo");
		redo = new JMenuItem("redo");
		hint = new JMenuItem("hint");
		save = new JMenuItem("save");

		setUpTitleScreen();
		setUpWinScreen();
		setUpDoneScreen();
		setUpContent();
		setUpMenuBar();

		panel.setLayout(layout);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 800);
		this.setVisible(true);
	}

	/**
	 * Sets up the menu bar to include the option undo, redo and hint.
	 */
	private void setUpMenuBar() {
		menuItems.add(undo);
		menuItems.add(redo);
		menuItems.add(hint);
		menuItems.add(save);

		for (JMenuItem j : menuItems) {
			options.add(j);
		}

		menuBar = new JMenuBar();
		menuBar.add(options);
		menuBar.setVisible(false);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Sets up the card layout to include the menu, win screen, and game.
	 */
	private void setUpContent() {
		content.setLayout(layout);
		content.add(this.view.getPanel(), "Game");
		content.add(menu, "Menu");
		content.add(win, "Win");
		content.add(done, "Done");
		this.setContentPane(content);
		layout.show(content, "Menu");
	}

	/**
	 * Sets up the title screen.
	 */
	private void setUpTitleScreen() {
		JLabel logo = new JLabel(Resources.LOGO);
		JButton play = MainMenuButton("PLAY!");
		buttons.add(play);
		JButton exit1 = MainMenuButton("EXIT");
		buttons.add(exit1);
		JButton cont = MainMenuButton("CONTINUE");
		buttons.add(cont);
		menu.setLayout(null);
		play.setBounds(300, 250, 200, 50);
		exit1.setBounds(300, 350, 200, 50);
		cont.setBounds(300, 450, 200, 50);
		logo.setBounds(160, 0, 500, 300);
		for(JButton button : buttons) {
			menu.add(button);
		}
		menu.add(logo);
		menu.setBackground(new Color(152, 233, 233));
	}

	/**
	 * Sets up the win screen.
	 */
	private void setUpWinScreen() {
		JLabel trophy = new JLabel(Resources.WIN);
		JButton exit2 = MainMenuButton("EXIT");
		JButton next = MainMenuButton("NEXT");
		buttons.add(next);
		buttons.add(exit2);
		win.setLayout(null);
		trophy.setBounds(155, 0, 500, 300);
		win.add(trophy);
		next.setBounds(350, 300, 100, 50);
		exit2.setBounds(350, 400, 100, 50);
		win.add(next);
		win.add(exit2);
		win.setBackground(new Color(152, 233, 233));
	}

	/**
	 * Sets up the last screen.
	 */
	private void setUpDoneScreen() {
		JButton exit3 = MainMenuButton("EXIT");
		JLabel logo2 = new JLabel(Resources.LOGO);
		buttons.add(exit3);
		done.setLayout(null);
		exit3.setBounds(350, 350, 100, 50);
		logo2.setBounds(160, 0, 500, 300);
		done.add(logo2);
		done.add(exit3);
		done.setBackground(new Color(152, 233, 233));
	}

	/**
	 * Changes screen to win screen once game is won.
	 */
	public void handleWin() {
		layout.show(content, "Win");
		menuBar.setVisible(false);
	}

	/**
	 * Getter for view
	 * 
	 * @return
	 */
	public JumpInView getView() {
		return view;
	}

	/**
	 * Get the menu items.
	 * 
	 * @return all of the JMenItems in the menu bar
	 */
	public ArrayList<JMenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * Getter for buttons.
	 * 
	 * @return
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/**
	 * Sets the general form of the buttons.
	 * 
	 * @param title
	 * @return
	 */
	public JButton MainMenuButton(String title) {
		JButton b = new JButton(title);
		b.setBackground(new Color(70, 170, 70));
		b.setForeground(Color.white);
		b.setFocusPainted(false);
		b.setFont(new Font("Arial", Font.BOLD, 20));
		return b;
	}

	/**
	 * Displays game screen
	 */
	public void showGame() {
		layout.show(content, "Game");
		menuBar.setVisible(true);
	}

	/**
	 * Displays end screen once all levels are finished.
	 */
	public void handleDone() {
		layout.show(content, "Done");
		menuBar.setVisible(false);
	}

}

