import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class JumpInView extends JFrame implements JumpInListener {
	private GameButton[][] buttons;
	private JumpIn model;
	private ArrayList<Object> options;
	
	public JumpInView(JumpIn model){
		this.model = model;
		this.options = new ArrayList<Object>();
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
	
		model.addListener(this);
		buttons = new GameButton[rows][cols];
		JPanel panel = new JPanel();
		GridLayout g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buttons[j][i] = new GameButton(new Point(j,i));
				panel.add(buttons[j][i]);
			}
		}
		Board.create(model.getLevel(), this, model);
		this.add(panel);	
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
	public void handleEvent(JumpInEvent e) {
		Point initialLocation = e.getInitialLocation1();
		if(e.getChosenPiece().getClass().getSimpleName().contentEquals("Rabbit")) {
			buttons[e.getFinalLocation1().x][e.getFinalLocation1().y].setIcon((ImageIcon)buttons[initialLocation.x][initialLocation.y].getIcon());
			if (model.isHole(initialLocation.x, initialLocation.y)) {
				buttons[e.getFinalLocation1().x][e.getFinalLocation1().y].setIcon(flipRabbit((ImageIcon)buttons[initialLocation.x][initialLocation.y].getIcon()));
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.HOLE);
			} else if (model.isHole(e.getFinalLocation1().x, e.getFinalLocation1().y)) {
				buttons[e.getFinalLocation1().x][e.getFinalLocation1().y].setIcon(flipRabbit((ImageIcon)buttons[initialLocation.x][initialLocation.y].getIcon()));
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.GREEN_CIRCLE);
			} else {
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.GREEN_CIRCLE);
			}	
		} else if (e.getChosenPiece().getClass().getSimpleName().contentEquals("Fox")) {
			Point initialLocation2 = e.getInitialLocation2();
			Icon fox1 = buttons[initialLocation.x][initialLocation.y].getIcon();
			Icon fox2 = buttons[initialLocation2.x][initialLocation2.y].getIcon();
			buttons[initialLocation.x][initialLocation.y].setIcon(Resources.GREEN_CIRCLE);
			buttons[initialLocation2.x][initialLocation2.y].setIcon(Resources.GREEN_CIRCLE);
			buttons[e.getFinalLocation1().x][e.getFinalLocation1().y].setIcon(fox1);
			buttons[e.getFinalLocation2().x][e.getFinalLocation2().y].setIcon(fox2);
			
		}
	}
	
	private Icon flipRabbit(Icon icon) {
		if (icon.equals(Resources.BROWN_RABBIT)) return Resources.HOLE_WITH_BROWN; 
		else if (icon.equals(Resources.WHITE_RABBIT)) return Resources.HOLE_WITH_WHITE;
		else if (icon.equals(Resources.GREY_RABBIT)) return Resources.HOLE_WITH_GREY;
		else if (icon.equals(Resources.HOLE_WITH_BROWN)) return Resources.BROWN_RABBIT;
		else if (icon.equals(Resources.HOLE_WITH_WHITE)) return Resources.WHITE_RABBIT;
		else if (icon.equals(Resources.HOLE_WITH_GREY)) return Resources.GREY_RABBIT;
		else return Resources.GREEN_CIRCLE;
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
