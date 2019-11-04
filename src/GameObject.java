import java.awt.Point;

/**
 * 
 * @author Aashna Narang
 *
 *         Class to create pieces of the game board
 */
public class GameObject {
	private Point coordinate;
	private String name;

	/**
	 * Constructor to create an empty game piece
	 * 
	 * @param p The coordinate of the game piece
	 */
	public GameObject(Point p) {
		this(p, "");
	}

	/**
	 * Constructor to create a game piece
	 * 
	 * @param p    The coordinate of the game piece
	 * @param name The name of the piece
	 */
	public GameObject(Point p, String name) {
		this.coordinate = p;
		this.name = name;
	}

	/**
	 * Get the name of the game piece
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the game piece
	 * 
	 * @param name the name of the game piece
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the coordinate the game piece is at on the game board
	 * 
	 * @return A poont object of the game piece
	 */
	public Point getCoordinate() {
		return coordinate;
	}

	/**
	 * Get the x coordinate of the game piece
	 * 
	 * @return integer value of the x-coordinate
	 */
	public int getX1() {
		return (int) this.coordinate.getX();
	}

	/**
	 * Get the y value of the coordinate of the game piece
	 * 
	 * @return integer value of the y coordinate
	 */
	public int getY1() {
		return (int) this.coordinate.getY();
	}

	/**
	 * Set the coordinate for the game piece
	 * 
	 * @param coordinate The x,y value the game piece is on the game board
	 */
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

}
