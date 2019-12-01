package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import source.JumpIn;

public class CreatorView {
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
		menu = new SelectorButton("MENU", "Menu");
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
		CreatorController controller = new CreatorController(this);
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

}
