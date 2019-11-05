import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class JumpInController implements MouseListener {
	private JumpInView view;
	private JumpIn model;
	private boolean inMovingState;
	private boolean inSelectingState;
	private Point initialLocation;
	private Point finalLocation;
	
	public JumpInController(JumpInView view, JumpIn model) {
		this.view = view; 
		this.model = model;
		this.inMovingState = false;
		this.inSelectingState = false;
		GameButton[][] b = view.getButtons();
		for(int i = 0; i < JumpIn.NUM_ROWS; i ++) {
			for(int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				b[i][j].addMouseListener(this);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		GameButton b = (GameButton)e.getSource();
		inSelectingState = (inMovingState ? false : true);
		if(inSelectingState) { 
			initialLocation = b.getCoordinate();
			if(view.highlightOptions(initialLocation)) {
				inSelectingState = false;
				inMovingState = true;
			}
		} else if (inMovingState) {
			finalLocation = b.getCoordinate();
			boolean moved = model.moveAnimal(initialLocation, finalLocation);
			if(moved) {
				inMovingState = false;
				inSelectingState = true;
				view.highlight(model.selectedAnimalType(finalLocation), false);
			} 
			else if (model.selectedAnimalType(finalLocation).equals("GameObject")) {
				view.displayInvalidOption();
			}
			else {
				inMovingState = false;
				inSelectingState = true;
				view.highlight(model.selectedAnimalType(initialLocation), false);
			}
		}
	}
	
	
	
	/**
	 * @return the inMovingState
	 */
	public boolean isInMovingState() {
		return inMovingState;
	}

	/**
	 * @param inMovingState the inMovingState to set
	 */
	public void setInMovingState(boolean inMovingState) {
		this.inMovingState = inMovingState;
	}

	/**
	 * @return the inSelectingState
	 */
	public boolean isInSelectingState() {
		return inSelectingState;
	}

	/**
	 * @param inSelectingState the inSelectingState to set
	 */
	public void setInSelectingState(boolean inSelectingState) {
		this.inSelectingState = inSelectingState;
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("Mouse");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
