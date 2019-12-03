package gui;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import source.JumpIn;
import source.Play;

/**
 * This class takes in user input and deals with it accordingly 
 * @author Aashna Narang
 *
 */

public class JumpInController extends MouseAdapter implements MouseListener, ActionListener {
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
	private String filename;
	
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
		this.initialLocation = new Point();
		this.finalLocation = new Point();
		
		// listen to the events created by each button
		GameButton[][] b = view.getButtons();
		for(int i = 0; i < JumpIn.NUM_ROWS; i ++) {
			for(int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				b[i][j].addMouseListener(this);
			}
		}
		for (JButton button : view.getMMenu().getButtons()) {
				button.addActionListener(this);
		}
		for(JMenuItem j : view.getMenuItems()) {
			j.addActionListener(this);
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
		model.setUndoState(false);
		inSelectingState = (inMovingState ? false : true);
		if (inSelectingState) { 
			initialLocation = b.getCoordinate();
			if(model.showOptions(initialLocation, true)) {
				inSelectingState = false;
				inMovingState = true;
			}
		} else if (inMovingState) {
			finalLocation = b.getCoordinate();
			boolean moved = model.moveAnimal(initialLocation, finalLocation);
			if(moved) {
				inMovingState = false;
				inSelectingState = true;
			} 
			else if (model.selectedAnimalType(finalLocation).equals("GameObject")) {
				view.displayError(0);
			}
			else {
				inMovingState = false;
				inSelectingState = true;
				model.showOptions(initialLocation, false );	
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
	/**
	 * This method checks which JMenuItem was clicked and calls the appropriate function
	 * @param e - action event created when menu item was clicked. Stores all needed information of the action
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("undo")) {
			model.setUndoState(true);
			model.undoMove();
		} else if (e.getActionCommand().equals("redo")) {
			model.redoMove();
		} else if (e.getActionCommand().equals("hint")) {
			view.showHint();
		} else if (e.getActionCommand().equals("menu")) {
			if(!Play.fileIsEmpty("levels.xml")) {
				view.enablePlay(true);
				try {
					Play.updateBoard("levels.xml", false, true, 1);
				} catch (IOException e1) {
					view.displayError(2);
				}
			}
			view.showMainMenu();
		}else if (e.getActionCommand().equals("PLAY!")) {
			view.getMMenu().showGame();
		} else if (e.getActionCommand().equals("NEXT")) {
			Play.nextLevel("levels.xml");
		} else if (e.getActionCommand().equals("EXIT")) {
			JButton src = (JButton) e.getSource();
			SwingUtilities.getWindowAncestor((JPanel) src.getParent()).dispose();
		} else if (e.getActionCommand().equals("CREATOR")) {
			view.creatorView();
		} else if (e.getActionCommand().equals("save")) {
			try {
				this.filename = model.exportToXMLFile("saveLevel.txt");
			} catch (IOException e1) {
				view.displayError(3);
			} catch (Exception e2) {
				view.displayError(3);
			}
		} else if (e.getActionCommand().equals("CONTINUE")) {
			try {
				Play.updateBoard("saveLevel.txt", false, false, -2);
			} catch (IOException e1) {
				view.displayError(5);
				Play.play(1);
			}
		}
	}
	
	/**
	 * Remove this object as a listener, used when going to a new level and a new controller must 
	 * be created.
	 */
	public void removeListener() {
		GameButton[][] b = view.getButtons();
		for(int i = 0; i < JumpIn.NUM_ROWS; i ++) {
			for(int j = 0; j < JumpIn.NUM_COLUMNS; j++) {
				b[i][j].removeMouseListener(this);
			}
		}
		for (JButton button : view.getMMenu().getButtons()) {
				button.removeActionListener(this);
		}
		for(JMenuItem j : view.getMenuItems()) {
			j.removeActionListener(this);
		}
	}

	/**
	 * @return the view
	 */
	public JumpInView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(JumpInView view) {
		this.view = view;
	}

	/**
	 * @return the model
	 */
	public JumpIn getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(JumpIn model) {
		this.model = model;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	

}
