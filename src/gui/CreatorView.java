package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import source.Fox;
import source.GameObject;
import source.JumpIn;
import source.Level;
import source.LevelBuilder;
import source.LevelBuilderListener;
import source.Rabbit;

public class CreatorView implements LevelBuilderListener {
	private GameButton[][] buttons;
	private SelectorButton mushroom, rabbit, hFox, vFox, remove, save, menu;
	private JPanel panel, creator, sidePanel;
	private GridLayout g;
	private final int ROWS = JumpIn.NUM_ROWS;
	private final int COLS = JumpIn.NUM_COLUMNS;
	private ArrayList<SelectorButton> buttonList;
	private MainMenu mainMenu;

	public CreatorView(MainMenu m) {
		this.mainMenu=m;
		buttons = new GameButton[ROWS][COLS];
		g = new GridLayout(ROWS, COLS, 0, 0);
		panel = new JPanel(new BorderLayout());
		sidePanel = new JPanel();
		creator = new JPanel();
		mushroom = new SelectorButton("", "Mushroom");
		rabbit = new SelectorButton("", "Rabbit");
		hFox = new SelectorButton("", "HFox");
		vFox = new SelectorButton("", "VFox");
		remove = new SelectorButton("REMOVE", "Remove");
		remove.setPreferredSize(new Dimension(145,50));
		save = new SelectorButton("SAVE", "Save");
		save.setPreferredSize(new Dimension(145,50));
		menu = new SelectorButton("GO BACK", "Menu");
		menu.setPreferredSize(new Dimension(145,50));

		buttonList = new ArrayList<SelectorButton>();

		buttonList.add(hFox);
		buttonList.add(mushroom);
		buttonList.add(rabbit);
		buttonList.add(vFox);
		buttonList.add(remove);
		buttonList.add(save);
		buttonList.add(menu);

		setUpCreator();
		setUpSidePanel();

		panel.add(creator, BorderLayout.EAST);
		panel.add(sidePanel, BorderLayout.WEST);
		Board.createEmpty(this);
		LevelBuilder b = new LevelBuilder();
		b.addListener(this);
		CreatorController controller = new CreatorController(this, b);
	}

	private void setUpSidePanel() {
		mushroom.setIcon(Resources.MUSHROOM);
		sidePanel.add(mushroom);
		rabbit.setIcon(Resources.BROWN_RABBIT);
		sidePanel.add(rabbit);
		vFox.setIcon(Resources.FOX_VERTICAL1);
		sidePanel.add(vFox);
		hFox.setIcon(Resources.FOX_HORIZONTAL1);
		sidePanel.add(hFox);
		sidePanel.add(remove);
		sidePanel.add(save);
		sidePanel.add(menu);
		sidePanel.setPreferredSize(new Dimension(160, 800));
	}

	private void setUpCreator() {
		creator.setLayout(g);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				buttons[j][i] = new GameButton(new Point(j, i));
				creator.add(buttons[j][i]);
			}
		}
		creator.setPreferredSize(new Dimension(800, 800));
	}

	public JPanel getPanel() {
		return panel;
	}

	public GameButton[][] getButtons() {
		return buttons;
	}

	public ArrayList<SelectorButton> getButtonList() {
		return buttonList;
	}
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	@Override
	public void handleEvent(GameObject piece, boolean removeState) {
		GameButton b = this.buttons[piece.getX1()][piece.getY1()];
		if (piece instanceof Rabbit && !removeState) {
			setIconHelper(b,Resources.HOLE_WITH_BROWN,Resources.BROWN_RABBIT);
		} else if (piece instanceof Fox && !removeState) {
			Fox f = (Fox)piece;
			if (f.getDirection().equals("Vertical")) {
				setIconHelperFox(b, piece, Resources.FOX_VERTICAL2, Resources.FOX_VERTICAL1);
			} else {
				setIconHelperFox(b, piece, Resources.FOX_HORIZONTAL1, Resources.FOX_HORIZONTAL2);
			}
		} else if (piece.getName().charAt(0) == 'M' && !removeState) {
			setIconHelper(b, Resources.HOLE_WITH_MUSHROOM, Resources.MUSHROOM);
		} else if (Level.isHole(b.getCoordinate().x,b.getCoordinate().y) && removeState) {
			b.setIcon(Resources.HOLE);
		} else if (removeState) {
			b.setIcon(Resources.GREEN_CIRCLE);
			if (piece instanceof Fox) {
				setIconHelperFox(b, piece,Resources.GREEN_CIRCLE,Resources.GREEN_CIRCLE);
			}
		}
	}
	
	private void setIconHelperFox(GameButton b, GameObject piece, ImageIcon first, ImageIcon second) {
		Fox f = (Fox)piece;
		b.setIcon(first);
		this.buttons[f.getX2()][f.getY2()].setIcon(second);
	}
	
	private void setIconHelper(GameButton b, ImageIcon withHole, ImageIcon normal) {
		if (Level.isHole(b.getCoordinate().x, b.getCoordinate().y)) {
			b.setIcon(withHole);
		} else {
			b.setIcon(normal);
		}
	}
	
	public static void displayError(int i) {
		if ( i == 1 ) JOptionPane.showMessageDialog(null, "Could not save level");
		else if ( i == 2 ) JOptionPane.showMessageDialog(null, "Could not load game, please check");
		else return;
	}

	/**
	 * Disable play button on home screen
	 */
	public void enablePlay(boolean enable) {
		mainMenu.enablePlay(enable);
	}
}
