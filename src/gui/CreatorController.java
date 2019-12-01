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
	private String objectName;
	private String direction;
	private boolean removeState;
	private GameButton[][] board;

	public CreatorController(CreatorView view, LevelBuilder builder) {
		this.view = view;
		board = view.getButtons();
		for (int i = 0; i < JumpIn.NUM_ROWS; i++) {
			for (int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				board[i][j].addMouseListener(this);
			}
		}
		for (SelectorButton btn : this.view.getButtonList()) {
			btn.addActionListener(this);
		}
		this.builder = builder;
		objectName = "";
		direction = "";
		removeState = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		GameButton b = (GameButton) e.getSource();
		if (!removeState) {
			System.out.println("in mouse clicked " + b.getCoordinate());
			builder.addGameObject(b.getCoordinate(), objectName, direction);
		} else {
			System.out.println("in remove state controler");
			builder.removeGameObject(b.getCoordinate());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		String objectName = e.getActionCommand();
		String objectName = ((SelectorButton) (e.getSource())).getTag();
		if (objectName.equals("Mushroom") || objectName.equals("Rabbit")) {
			System.out.println("In actione performed " + objectName);
			changeState(objectName, false);
		} else if (objectName.equals("HFox")) {
			changeState("Fox", false, "Horizontal");
		} else if (objectName.equals("VFox")) {
			changeState("Fox", false, "Vertical");
		} else if (objectName.equals("Save")) {
			if (builder.saveLevel()) {
				System.out.println("Is valid game");
				// add something else
			} else {
				JOptionPane.showMessageDialog(view.getPanel(), "Not a winnable game", "Is valid",
						JOptionPane.PLAIN_MESSAGE);
			}
		} else if (objectName.equals("Remove")) {
			removeState = true;
		}
	}
	
	private void changeState(String objectName, boolean removeState, String direction) {
		this.removeState = removeState;
		this.objectName = objectName;
		this.direction = direction;
	}
	
	private void changeState(String objectName, boolean removeState) {
		changeState(objectName, removeState, "");
	}

}
