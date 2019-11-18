import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class handles all of the GUI manipulation and updates the GUI whenever an event occurs
 * @author Aashna Narang
 *
 */
public class JumpInView extends JFrame implements JumpInListener {
	private GameButton[][] buttons;
	private JumpIn model;
	private JPanel panel;
	private GridLayout g;
	private MainMenu mainMenu;

	/**
	 * JumpInView (JFrame) constructor that creates a grid of Game Buttons and adds them all to 
	 * a panel using a grid layout. The constructor also changes the default JFrame settings.
	 * The JumpInView listens to changes made in the model.
	 * @param Model - The JumpInGame that keeps track of the moves being made and if there is a win. The view listens 
	 * to the model and changes the GUI respectively. 
	 */

	public JumpInView(JumpIn model){
		this.model = model;
		int rows = JumpIn.NUM_ROWS;
		int cols = JumpIn.NUM_COLUMNS;
		
		model.addListener(this);
		buttons = new GameButton[rows][cols];
		g = new GridLayout(rows, cols, 0, 0);
		this.panel = new JPanel();
		panel.setLayout(g);
		
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buttons[j][i] = new GameButton(new Point(j,i));
				panel.add(buttons[j][i]);
			}
		}
		Board.create(this, this.model);
		this.mainMenu = new MainMenu(this);
	}

	/**
	 * Get all of the game buttons in the frame
	 * @return 2d array of game buttons
	 */
	public GameButton[][] getButtons() {
		return buttons;
	}

	/**
	 * Set the game buttons in the frame to a new set of game buttons
	 * @param buttons - New 2d array of game buttons to put in the frame 
	 */
	public void setButtons(GameButton[][] buttons) {
		this.buttons = buttons;
	}

	
	public JPanel getPanel() {
		return panel;
	}

	public void createNextBoard() {
		Board.reset(this);
		Board.create(this, model);
		model.addListener(this);
	}

	@Override
	/**
	 * This function changes the view any time a change has occurred in the game depending on 
	 * whether a fox or rabbit was chosen
	 * @param e - A JumpInEvent object created by the model. It includes all the necessary information needed to move 
	 * a piece on the board (initial location(s), final location(s), chosen piece)
	 */
	public void handleEvent(JumpInEvent e) {
		Point initialLocation = e.getInitialLocation1();
		Point finalLocation1 = e.getFinalLocation1();
		if(e.getChosenPiece().getClass().getSimpleName().contentEquals("Rabbit")) {
			handleRabbit(initialLocation, finalLocation1);
		} else if (e.getChosenPiece().getClass().getSimpleName().contentEquals("Fox")) {
			handleFox(initialLocation, e.getInitialLocation2(), finalLocation1, e.getFinalLocation2());
		}
	}
	
	public void handleWin() {
		mainMenu.handleWin();
	}
	
	public void handleDone() {
		mainMenu.handleDone();
	}
	
	/**
	 * Private helper function that moves the correct rabbit piece for the GUI
	 * @param initialLocation - initial rabbit location on grid given as a Point object
	 * @param finalLocation - final rabbit location on grid, given as a Point object
	 */
	private void handleRabbit(Point initialLocation, Point finalLocation) {
		setButtonIcon(finalLocation, (ImageIcon)getButtonIcon(initialLocation));
		if (model.isHole(initialLocation.x, initialLocation.y)) {
			setFlippedRabbit(finalLocation, initialLocation);
			setButtonIcon(initialLocation, Resources.HOLE);
		} else {
			if (model.isHole(finalLocation.x, finalLocation.y)) {
				setFlippedRabbit(finalLocation, initialLocation);
			}
			setButtonIcon(initialLocation, Resources.GREEN_CIRCLE);
		}	
	}
	
	/**
	 * Private helper function that moves the correct fox piece for the GUI
	 * NOTE: Cannot get rid of duplicate code because updates need to happen in this order
	 * @param initialLocation - one coordinate for the initial fox location
	 * @param initialLocation2 - second coordinate for the initial fox location
	 * @param finalLocation - one coordinate for the final fox location
	 * @param finalLocation2 - second coordinate for the final fox location
	 */
	private void handleFox(Point initialLocation, Point initialLocation2, Point finalLocation, Point finalLocation2) {
		Icon fox1 = getButtonIcon(initialLocation);
		Icon fox2 = getButtonIcon(initialLocation2);
		setButtonIcon(initialLocation, Resources.GREEN_CIRCLE);
		setButtonIcon(initialLocation2, Resources.GREEN_CIRCLE);
		setButtonIcon(finalLocation, (ImageIcon)fox1);
		setButtonIcon(finalLocation2, (ImageIcon)fox2);
	}
	
	/**
	 * Helper function for handleRabbit for when a rabbit leaves a hole or enters hole. It uses another helper 
	 * function to retrieve the corresponding icon. For ex. If the white rabbit is leaving a hole, it will set the
	 * icon of the final location to just the white rabbit. If a brown rabbit is entering a hole, it will set the final
	 * location to have an icon with the brown rabbit in the hole.
	 * @param finalLocation - location the rabbit is going to 
	 * @param initialLocation - the location the rabbit is coming from 
	 */
	private void setFlippedRabbit(Point finalLocation, Point initialLocation) {
		setButtonIcon(finalLocation, (ImageIcon)(flipRabbit(getButtonIcon(initialLocation))));
	}
	
	/**
	 * Helper function to set the icon of a button given a point and an ImageIcon
	 * @param p - Coordinate on the board where the icon needs to be updated
	 * @param setTo - the icon that the button will be set to
	 */
	private void setButtonIcon(Point p, ImageIcon setTo) {
		buttons[p.x][p.y].setIcon(setTo);
	}
	
	/**
	 * Helper function to retrieve the icon of a button at a given coordinate
	 * @param p - the coordinate of the button
	 * @return the icon of the button at the given coordinate
	 */
	private Icon getButtonIcon(Point p) {
		return buttons[p.x][p.y].getIcon();
	}
	
	/**
	 * Helper function that gets the icon that is mapped to the given icon. For example, 
	 * a brown rabbit will return a brown rabbit in a hole and vice versa. This is used 
	 * to change the icons when a rabbit is leaving or entering a hole.
	 * @param icon
	 * @return
	 */
	private Icon flipRabbit(Icon icon) {
		if(Resources.FLIPPED_RABBIT.containsKey(icon)) return Resources.FLIPPED_RABBIT.get(icon);
		else return Resources.GREEN_CIRCLE;
	}
	
	/**
	 * Highlight a button at a given coordinate
	 * @param p - the coordinate of the button
	 * @return true if it worked and is highlighted
	 */
	public boolean highlightOption(Point p) {
		buttons[p.x][p.y].highlightButton();
		return true;
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
	 * Remove the highlight at a given point
	 * @param p - the coordinate of the button the user would like to remove the highlight
	 * @return true if it worked and is highlighted
	 */
	public boolean unhighlight(Point p) {
		buttons[p.x][p.y].removeHighlight();
		return true;
	}
	
	/**
	 * Given a coordinate on the game board, highlight the different squares the user can legally move to.
	 * If there are no legal moves or the selected button does not have a movable animal on it, output 
	 * a warning to the user. Return if options were highlighted
	 * @param initialLocation - the coordinate of the button the user selected
	 * @return true if options were highlighted, false otherwise
	 */
	public boolean highlightOptions(Point initialLocation, String selectedAnimalType, ArrayList<Object> options) {
		if(options.isEmpty() && (selectedAnimalType.equals("Rabbit") || selectedAnimalType.equals("Fox"))){
			JOptionPane.showMessageDialog(null, "Selected box has no available moves");
			return false;
		} else if (options.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select an animal to move");
			return false;
		}
		highlight(selectedAnimalType, true, options);
		return true;
	}
	
	/**
	 * Go through all of the coordinates for possible moves of either a rabbit or fox and highlight them
	 * or remove the  highlight depending on what is asked from the user. 
	 * @param selectedAnimalType - the type of movable animal that was selected, either a rabbit or fox
	 * @param highlight - true if the user would like to highlight the options or false if remove highlight
	 * @param options - the animal's options from the selecting state
	 * @param location - initial location of the animal from the selecting state
	 */
	public boolean highlight(String selectedAnimalType, boolean highlight, ArrayList<Object> options) {
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
		return true;
	}
	
	/**
	 * Function that allows user to pick which error message to output to reduce 
	 * the number of one-line functions needed for outputting message dialogs
	 * @param i the number corresponding to the error message
	 * 			0 - invalid option
	 * 			1 - no more undo
	 * 			2 - no more redo
	 */
	public void displayError(int i) {
		if (i == 0 ) JOptionPane.showMessageDialog(null, "Invalid Option. "
				+ "Please pick one of the highlighted options.");
		else if ( i == 1 ) JOptionPane.showMessageDialog(null, "No more moves to undo.");
		else if ( i == 2 ) JOptionPane.showMessageDialog(null, "No more moves to redo.");
		else return;
	}
	
	/**
	 * 
	 * @return JMenuItem corresponding to the undo button
	 */
	public JMenuItem getUndo() {
		return mainMenu.getUndo();
	}
	
	/**
	 * 
	 * @return JMenuItem corresponding to the redo button
	 */
	public JMenuItem getRedo() {
		return mainMenu.getRedo();
	}
	
	public MainMenu getMMenu() {
		return mainMenu;
	}
	/**
	 * 
	 * @return JMenuItem corresponding to the solve button
	 */
	public JMenuItem getHint() {
		return mainMenu.getHint();
	}
	
	public void showHint() {
		Move hint = model.getSolverMoves().peek();
		String name = hint.getNameType();
		String selectedAnimalType = hint.getChosenAnimal().getClass().getSimpleName();
		String s = "Hint: Move " + name + " " + selectedAnimalType + " to the square with the coordinates of " + hint.pointToString();
		JOptionPane.showMessageDialog(null, s);
	}
}
