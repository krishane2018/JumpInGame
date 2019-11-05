package source;
import java.awt.Point;
import java.util.EventObject;

import test.Utility;

/**
 * @author Aashna Narang
 *
 *         JumpInEvents are created when a user makes a move
 */
public class JumpInEvent extends EventObject {
	private GameObject chosenPiece;
	private Point coordinate1;
	private Point coordinate2;
	private Point[] holes;

	/**
	 * Constructor used when the chosen game piece has 1 coordinate / takes up one
	 * space
	 * 
	 * @param source      The creator of the event
	 * @param chosenPiece The GameObject that the user would like to move
	 * @param p           The new coordinate of the GameObject
	 * @param holes       The coordinates of all of the holes on the board
	 */
	public JumpInEvent(Object source, GameObject chosenPiece, Point p, Point[] holes) {
		this(source, chosenPiece, p, new Point(), holes);
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
	public JumpInEvent(Object source, GameObject chosenPiece, Point p1, Point p2, Point holes[]) {
		super(source);
		this.chosenPiece = chosenPiece;
		if (Utility.checkValidPoint(p1)&&Utility.checkValidPoint(p2)) {
			this.coordinate1 = p1;
			this.coordinate2 = p2;
		}
		else {
			throw new IllegalArgumentException("Points must be between (0,0) and (4,4)");
		}
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
	 * Get the first coordinate of the game piece
	 * 
	 * @return A Point object of the coordinate
	 */
	public Point getCoordinate1() {
		return coordinate1;
	}

	/**
	 * Set the first coordinate of the game piece
	 * 
	 * @param coordinate1 A Point object of the coordinate
	 */
	public void setCoordinate1(Point coordinate1) {
		this.coordinate1 = coordinate1;
	}

	/**
	 * Get the second coordinate
	 * 
	 * @return A point object of the second coordinate
	 */
	public Point getCoordinate2() {
		return coordinate2;
	}

	/**
	 * Set the second coordinate
	 * 
	 * @param coordinate2 A point object of the second x,y coordinate
	 */
	public void setCoordinate2(Point coordinate2) {
		this.coordinate2 = coordinate2;
	}

}
