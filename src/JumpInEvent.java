import java.awt.Point;
import java.util.EventObject;
/**
 * @author Aashna Narang
 *
 * JumpInEvents are created when a user makes a move
 */
public class JumpInEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private GameObject chosenPiece; 
	private Point initialLocation1;
	private Point initialLocation2;
	private Point finalLocation1;
	private Point finalLocation2;
	private Point[] holes;
	
	/**
	 * Constructor used when the chosen game piece has 1 coordinate / takes up one space 
	 * @param source 		The creator of the event
	 * @param chosenPiece 	The GameObject that the user would like to move
	 * @param p 			The new coordinate of the GameObject
	 * @param holes 		The coordinates of all of the holes on the board
	 */
	public JumpInEvent(Object source, GameObject chosenPiece, Point initialLocation, Point finalLocation, Point[] holes) {
		super(source);
		this.chosenPiece = chosenPiece;
		this.initialLocation1 = initialLocation;
		this.finalLocation1 = finalLocation;
		this.initialLocation2 = new Point();
		this.finalLocation2 = new Point();
		this.holes = holes;
	}
	
	/**
	 * Constructor used when the chosen game piece has 2 coordinates / takes up two spaces 
	 * @param source 		The creator of the event
	 * @param chosenPiece 	The GameObject that the user would like to move
	 * @param p1 			The new coordinate of the GameObject
	 * @param p2			The second new coordinate of the GameObject
	 * @param holes 		The coordinates of all of the holes on the board
	 */
	public JumpInEvent(Object source, GameObject chosenPiece, Point initialLocation1, Point intialLocation2,
			Point finalLocation1, Point finalLocation2, Point holes[]) {
		super(source);
		this.chosenPiece = chosenPiece;
		this.initialLocation1 = initialLocation1;
		this.initialLocation2 = intialLocation2;
		this.finalLocation1 = finalLocation1;
		this.finalLocation2 = finalLocation2;
		this.holes = holes;
	}
	
	/**
	 * Get the coordinates of the rabbit holes 
	 * @return an array of the coordinates of the rabbit holes
	 */
	public Point[] getHoles() {
		return holes;
	}

	/**
	 * Get the object the user wants to move
	 * @return a GameObject of the chosen piece
	 */
	public GameObject getChosenPiece() {
		return chosenPiece;
	}

	/**
	 * Set the object the user wants to move
	 * @param chosenPiece the object the user wants to move
	 */
	public void setChosenPiece(GameObject chosenPiece) {
		this.chosenPiece = chosenPiece;
	}

	public Point getInitialLocation1() {
		return initialLocation1;
	}

	public void setInitialLocation1(Point initialLocation1) {
		this.initialLocation1 = initialLocation1;
	}

	public Point getInitialLocation2() {
		return initialLocation2;
	}

	public void setInitialLocation2(Point initialLocation2) {
		this.initialLocation2 = initialLocation2;
	}

	public Point getFinalLocation1() {
		return finalLocation1;
	}

	public void setFinalLocation1(Point finalLocation1) {
		this.finalLocation1 = finalLocation1;
	}

	public Point getFinalLocation2() {
		return finalLocation2;
	}

	public void setFinalLocation2(Point finalLocation2) {
		this.finalLocation2 = finalLocation2;
	}

	

}
