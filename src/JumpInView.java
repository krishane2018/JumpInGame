import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class JumpInView extends JFrame implements JumpInListener{
	private static final long serialVersionUID = 1L;
	
	private GameButton[][] buttons;
	private JumpIn model;
	private ArrayList<Object> options;
	
	JPanel panel = new JPanel();
	GridLayout g;
	
	public JumpInView(JumpIn model){
		this.model = model;
		this.options = new ArrayList<Object>();
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
		model.addListener(this);
		buttons = new GameButton[rows][cols];
		g = new GridLayout(rows, cols, 0, 0);
		panel.setLayout(g);

		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buttons[j][i] = new GameButton(new Point(j,i));
				panel.add(buttons[j][i]);
			}
		}
		this.setTitle("JumpIn");					
		Board.create(model.getLevel(), this, model);
	}

	public GameButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(GameButton[][] buttons) {
		this.buttons = buttons;
	}
	
	public JPanel getGame() {
		return panel;
	}
	
	@Override
	public void handleEvent(JumpInEvent e) {
		Point initialLocation = e.getChosenPiece().getCoordinate();
		if(e.getChosenPiece().getClass().getSimpleName().contentEquals("Rabbit")) {
			buttons[e.getCoordinate1().x][e.getCoordinate1().y].setIcon(buttons[initialLocation.x][initialLocation.y].getIcon());
			if (model.isHole(initialLocation.y, initialLocation.x)) {
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.HOLE);
			} else {
				buttons[initialLocation.x][initialLocation.y].setIcon(Resources.GREEN_CIRCLE);
			}	
		} else if (e.getChosenPiece().getClass().getSimpleName().contentEquals("Fox")) {
			
		}
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
