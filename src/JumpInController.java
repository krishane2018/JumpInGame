import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This class takes in user input and deals with it accordingly 
 * @author Aashna Narang
 *
 */

public class JumpInController implements MouseListener {
	private JumpInView view;
	private JumpIn model;
	/*
	 * State is saved through inMovingState and inSelectingState
	 * inSelectingState - the user is selecting which animal they would like to move
	 * inMovingState - the user is selecting where the animal should move
	 */
	private boolean inMovingState;
	private boolean inSelectingState;
	private Point initialLocation;
	private Point finalLocation;
	
	/**
	 * Initialize all instance variables and add the controller as a listener to events created by
	 * the mouse movements on each of the buttons in the gameBoard
	 * @param view - The object that handles all updates needed to be made on the GUI
	 * @param model - The object that handles all the updates of the actual game 
	 */
	public JumpInController(JumpInView view, JumpIn model) {
		this.view = view; 
		this.model = model;
		this.inMovingState = false;
		this.inSelectingState = false;
		// listen to the events created by each button
		GameButton[][] b = view.getButtons();
		for(int i = 0; i < JumpIn.NUM_ROWS; i ++) {
			for(int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				b[i][j].addMouseListener(this);
			}
		}
	}

	@Override
	/**
	 * Function that handles events created by mouse movements in any of the buttons on the gameBoard.
	 * If it's in the selecting state, tell the view to highlight all of the options the user can legally 
	 * move that piece. If it's in the moving state, tell the model to keep track of this movement and tell 
	 * the view to update the GUI
	 * @param e - an event generated by a mouse clicking on a specific button
	 */
	public void mouseClicked(MouseEvent e) {
		GameButton b = (GameButton)e.getSource();
		// If a button was clicked, user is in selecting state as long as not in moving state
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
			} else {
				boolean startNewMove = view.didNotMove(initialLocation,finalLocation, model.selectedAnimalType(initialLocation), model.selectedAnimalType(finalLocation) );
				inMovingState = startNewMove ? false : true;
			}
		}
	}
	
	
	
	/**
	 * Check if in moving state
	 * @return inMovingState - true or false
	 */
	public boolean isInMovingState() {
		return inMovingState;
	}

	/**
	 * @param inMovingState - set to either in this state or not
	 */
	public void setInMovingState(boolean inMovingState) {
		this.inMovingState = inMovingState;
	}

	/**
	 * @return whether in Selecting State or not
	 */
	public boolean isInSelectingState() {
		return inSelectingState;
	}

	/**
	 * @param inSelectingState set whether in this state or not
	 */
	public void setInSelectingState(boolean inSelectingState) {
		this.inSelectingState = inSelectingState;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	// TODO have a lighter image appear to show what the animal would look 
	// 		like if this box was selected
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
