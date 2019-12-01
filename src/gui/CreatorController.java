package gui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import source.JumpIn;
import source.LevelBuilder;

public class CreatorController extends MouseAdapter implements ActionListener, MouseListener {

	private CreatorView view;
	private LevelBuilder builder;
	private String objectName = "";
	private String direction = "";
	private Point[] holes = new Point[] { new Point(0, 0), new Point(0, 4), new Point(2, 2), new Point(4, 0),
			new Point(4, 4) };
	private boolean removeState = false;
	private GameButton[][] board;

	public CreatorController(CreatorView view) {
		this.view = view;
		board = view.getButtons();
		for (int i = 0; i < JumpIn.NUM_ROWS; i++) {
			for (int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				board[i][j].addMouseListener(this);
			}
		}
		for (SelectorButton btn : this.view.getButtonList()) {
			btn.addActionListener(this);
			System.out.println(btn.getTag());
		}
		builder = new LevelBuilder();
	}

	/**
	 * Checks if a point in the game board is a hole.
	 * 
	 * @param x - x coordinate of the board
	 * @param y - y coordinate of the board
	 * @return - boolean of if it is a hole
	 */
	public boolean isHole(Point hole) {
		for (Point p : holes) {
			if (p.equals(hole)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		GameButton b = (GameButton) e.getSource();
		if (!removeState) {
			if (builder.addGameObject(b.getCoordinate(), objectName, direction)) {
				if (objectName.equals("Mushroom")) {
					if (isHole(b.getCoordinate())) {
						b.setIcon(Resources.HOLE_WITH_MUSHROOM);
					} else {
						b.setIcon(Resources.MUSHROOM);
					}
				} else if (objectName.equals("Rabbit")) {
					if (isHole(b.getCoordinate())) {
						b.setIcon(Resources.HOLE_WITH_BROWN);
					} else {
						b.setIcon(Resources.BROWN_RABBIT);
					}
				} else if (objectName.equals("Fox")) {
					if (direction.equals("Vertical")) {
						b.setIcon(Resources.FOX_VERTICAL2);
						this.board[builder.getFoxCoordinate2().x][builder.getFoxCoordinate2().y]
								.setIcon(Resources.FOX_VERTICAL1);
					} else if (direction.equals("Horizontal")) {
						b.setIcon(Resources.FOX_HORIZONTAL1);
						this.board[builder.getFoxCoordinate2().x][builder.getFoxCoordinate2().y]
								.setIcon(Resources.FOX_HORIZONTAL2);
					}
				}
			}
		} else {
			if (isHole(b.getCoordinate())) {
				b.setIcon(Resources.HOLE);
			} else {
				b.setIcon(Resources.GREEN_CIRCLE);
				System.out.println(b.getCoordinate());
				if (builder.getObjectName(b.getCoordinate()).equals("Fox")) {
					this.board[builder.getFoxCoordinate2().x][builder.getFoxCoordinate2().y]
							.setIcon(Resources.GREEN_CIRCLE);
					System.out.println(builder.getFoxCoordinate2());
				}
			}
			builder.removeGameObject(b.getCoordinate());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((SelectorButton) (e.getSource())).getTag().equals("Mushroom")) {
			System.out.println("Mushroom");
			removeState = false;
			objectName = "Mushroom";
		} else if (((SelectorButton) (e.getSource())).getTag().equals("Rabbit")) {
			System.out.println("Rabbit");
			removeState = false;
			objectName = "Rabbit";
		} else if (((SelectorButton) (e.getSource())).getTag().equals("HFox")) {
			System.out.println("HFox");
			removeState = false;
			objectName = "Fox";
			direction = "Horizontal";
		} else if (((SelectorButton) (e.getSource())).getTag().equals("VFox")) {
			System.out.println("VFox");
			removeState = false;
			objectName = "Fox";
			direction = "Vertical";
		} else if (((SelectorButton) (e.getSource())).getTag().equals("Save")) {
			if (builder.saveLevel()) {
				System.out.println("Is valid game");
			} else {
				JOptionPane.showMessageDialog(view.getPanel(), "Not a winnable game", "Is valid",
						JOptionPane.PLAIN_MESSAGE);
			}
		} else if (((SelectorButton) (e.getSource())).getTag().equals("Remove")) {
			removeState = true;
		} else if (((SelectorButton) (e.getSource())).getTag().equals("Menu")) {
			view.getMainMenu().showMenu();
		}
	}

}
