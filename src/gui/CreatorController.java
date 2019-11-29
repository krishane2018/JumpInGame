package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import source.JumpIn;
import source.LevelBuilder;

public class CreatorController extends MouseAdapter implements ActionListener, MouseListener {

	private CreatorView view;
	private LevelBuilder builder;
	private String objectName = "";
	private String direction = "";

	public CreatorController(CreatorView view) {
		this.view = view;
		GameButton[][] b = view.getButtons();
		for (int i = 0; i < JumpIn.NUM_ROWS; i++) {
			for (int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				b[i][j].addMouseListener(this);
			}
		}
		for(SelectorButton btn: this.view.getButtonList()) {
			btn.addActionListener(this);
			System.out.println(btn.getTag());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GameButton b = (GameButton) e.getSource();
		System.out.println(b.getCoordinate().toString());
		System.out.println(objectName);
		System.out.println(direction);
		builder.addGameObject(b.getCoordinate(), objectName, direction);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((SelectorButton)(e.getSource())).getTag().equals("Mushroom")) {
			System.out.println("Mushroom");
			objectName = "Mushroom";
		} else if (((SelectorButton)(e.getSource())).getTag().equals("Rabbit")) {
			System.out.println("Rabbit");
			objectName = "Rabbit";
		} else if (((SelectorButton)(e.getSource())).getTag().equals("HFox")) {
			System.out.println("HFox");
			objectName = "Fox";
			direction = "Horizontal";
		} else if (((SelectorButton)(e.getSource())).getTag().equals("VFox")) {
			System.out.println("VFox");
			objectName = "Fox";
			direction = "Vertical";
		} else if (((SelectorButton)(e.getSource())).getTag().equals("Play")) {
			System.out.println("Play");
		}
	}

}
