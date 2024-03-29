package source;

import java.awt.Point;
import java.util.EventObject;

/**
 * @author Aashna Narang
 *
 *         JumpInEvents are created when a user makes a move
 */
public class JumpInEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private GameObject chosenPiece; 
	private Point initialLocation1;
	private Point initialLocation2;
	private Point finalLocation1;
	private Point finalLocation2;
	private Point[] holes;
	private boolean isEmpty;

	
	/**
	 * Constructor used when the chosen game piece has 1 coordinate / takes up one
	 * space
	 * 
	 * @param source      The creator of the event
	 * @param chosenPiece The GameObject that the user would like to move
	 * @param p           The new coordinate of the GameObject
	 * @param holes       The coordinates of all of the holes on the board
	 */
	public JumpInEvent(Object source, GameObject chosenPiece, Point initialLocation, Point finalLocation, Point[] holes) {
		this(source, chosenPiece, initialLocation, new Point(), finalLocation, new Point(), holes);
	}

	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}


	/**
	 * Empty constructor to create empty object to avoid returning null
	 */
	public JumpInEvent() {
		super(new Object());
		chosenPiece = new GameObject(new Point()); 
		initialLocation1 = new Point();
		initialLocation2 = new Point();
		finalLocation1 = new Point();
		finalLocation2 = new Point();
		holes = new Point[5];
		this.isEmpty = true;
	}
	
	/**
	 * Constructor used when the chosen game piece has 2 coordinates / takes up two
	 * spaces
	 * 
	 * @param source      The creator of the event
	 * @param chosenPiece The GameObject that the user would like to move
	 * @param p1          The new coordinate of the GameObject
	 * @param p2          The second new coordinate of the GameObject
	 * @param holes       The coordinates of all of the holes on the board
	 */
	public JumpInEvent(Object source, GameObject chosenPiece, Point initialLocation1, Point intialLocation2,
			Point finalLocation1, Point finalLocation2, Point holes[]) {
		super(source);
		this.chosenPiece = chosenPiece;
		this.initialLocation1 = initialLocation1;
		this.initialLocation2 = intialLocation2;
		this.finalLocation1 = finalLocation1;
		this.finalLocation2 = finalLocation2;
		this.isEmpty = false;
		this.holes = holes;
	}
	
	/**
	 * Get the coordinates of the rabbit holes
	 * 
	 * @return an array of the coordinates of the rabbit holes
	 */
	public Point[] getHoles() {
		return holes;
	}

	/**
	 * Get the object the user wants to move
	 * 
	 * @return a GameObject of the chosen piece
	 */
	public GameObject getChosenPiece() {
		return chosenPiece;
	}

	/**
	 * Set the object the user wants to move
	 * 
	 * @param chosenPiece the object the user wants to move
	 */
	public void setChosenPiece(GameObject chosenPiece) {
		this.chosenPiece = chosenPiece;
	}

	/**
	 * Get the location of the first coordinate of the initial location of the animal
	 * @return x,y coordinate of initial location
	 */
	public Point getInitialLocation1() {
		return initialLocation1;
	}

	/**
	 * Set the location of the first coordinate of the initial location of the animal
	 * @param initialLocation1 - x,y coordinate of initial location
	 */
	public void setInitialLocation1(Point initialLocation1) {
		this.initialLocation1 = initialLocation1;
	}

	/**
	 * Get the location of the second coordinate of the initial location of the animal if it exists otherwise
	 * it is an empty point object
	 * @return x,y coordinate of initial location
	 */
	public Point getInitialLocation2() {
		return initialLocation2;
	}

	/**
	 * Set the location of the second coordinate of the initial location of the animal
	 * @param initialLocation2 - x,y coordinate of initial location
	 */
	public void setInitialLocation2(Point initialLocation2) {
		this.initialLocation2 = initialLocation2;
	}

	/**
	 * Get the first coordinate of final location 
	 * @return coordinate of final location
	 */
	public Point getFinalLocation1() {
		return finalLocation1;
	}

	/**
	 * set the first coordinate of final location
	 * @param finalLocation1 coordinate of final location
	 */
	public void setFinalLocation1(Point finalLocation1) {
		this.finalLocation1 = finalLocation1;
	}

	/**
	 * get the second coordinate of final location if it exists
	 * @return the coordinate of final location 
	 */
	public Point getFinalLocation2() {
		return finalLocation2;
	}

	/**
	 * set the second coordinate of final location if it exists
	 * @param finalLocation2 - the coordinate of final location
	 */
	public void setFinalLocation2(Point finalLocation2) {
		this.finalLocation2 = finalLocation2;
	}

}
