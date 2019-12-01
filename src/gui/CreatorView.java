package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

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
	private MainMenu m;

	public CreatorView(MainMenu m) {
		this.m=m;
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
		return m;
	}

	@Override
	public void handleEvent(GameObject piece, boolean removeState) {
		GameButton b = this.buttons[piece.getX1()][piece.getY1()];
		if (piece instanceof Rabbit && !removeState) {
			if (Level.isHole(b.getCoordinate().x, b.getCoordinate().y)) {
				b.setIcon(Resources.HOLE_WITH_BROWN);
			} else {
				b.setIcon(Resources.BROWN_RABBIT);
			}
		} else if (piece instanceof Fox && !removeState) {
			Fox f = (Fox)piece;
			if (f.getDirection().equals("Vertical")) {
				b.setIcon(Resources.FOX_VERTICAL2);
				this.buttons[f.getX2()][f.getY2()].setIcon(Resources.FOX_VERTICAL1);
			} else {
				b.setIcon(Resources.FOX_HORIZONTAL1);
				this.buttons[f.getX2()][f.getY2()].setIcon(Resources.FOX_HORIZONTAL2);
			}
		} else if (piece.getName().charAt(0) == 'M' && !removeState) {
			if (Level.isHole(b.getCoordinate().x, b.getCoordinate().y)) {
				b.setIcon(Resources.HOLE_WITH_MUSHROOM);
			} else {
				b.setIcon(Resources.MUSHROOM);
			}
		} else if (Level.isHole(b.getCoordinate().x,b.getCoordinate().y) && removeState) {
			b.setIcon(Resources.HOLE);
		} else if (removeState) {
			b.setIcon(Resources.GREEN_CIRCLE);
			if (piece instanceof Fox) {
				Fox f = (Fox) piece;
				this.buttons[f.getX2()][f.getY2()].setIcon(Resources.GREEN_CIRCLE);
			}
		}
		
	}

}
