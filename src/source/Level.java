package source;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Level is used to create a level type object whenever making a new level. This
 * gives a common structure to all the levels.
 * 
 * @author Krishan Easparan
 *
 */
public class Level {

	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private int level;

	public final static int NUM_ROWS = JumpIn.NUM_ROWS;
	public final static int NUM_COLUMNS = JumpIn.NUM_COLUMNS;
	public static final Point[] HOLES = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) };

	/**
	 * When creating a new level it sets the level to -2
	 */
	public Level() {
		this(-2);
	}

	/**
	 * main constructor to set all the variables and game board.
	 * 
	 * @param l
	 */
	public Level(int l) {
		gameBoard = new GameObject[NUM_ROWS][NUM_COLUMNS];
		for (int i = 0; i < NUM_COLUMNS; i++) {
			for (int j = 0; j < NUM_ROWS; j++) {
				gameBoard[j][i] = new GameObject(new Point(i, j));
			}
		}
		listeners = new ArrayList<JumpInListener>();
		level = l;
	}

	/**
	 * Adds a new listener
	 * 
	 * @param l
	 */
	public void addListener(JumpInListener l) {
		listeners.add(l);
	}

	/**
	 * Setter for the level
	 * 
	 * @param l
	 */
	public void setLevel(int l) {
		level = l;
	}

	/**
	 * Places a gameobject on the board
	 * 
	 * @param o
	 */
	public void placeGameObject(GameObject o) {
		Point p1 = o.getCoordinate();
		gameBoard[p1.y][p1.x] = o;
		if (o.getClass().getSimpleName().equalsIgnoreCase("Fox")) {
			Fox f = (Fox) o;
			Point p2 = f.getCoordinate2();
			gameBoard[p2.y][p2.x] = f;
		}
		System.out.println((new JumpIn(this)).toString());
	}

	/**
	 * Removes a gameobject from the board and removes it as a listener.
	 * 
	 * @param p
	 */
	public void removeGameObject(Point p) {
		GameObject space = gameBoard[p.y][p.x];
		String className = space.getClass().getSimpleName();
		gameBoard[p.y][p.x] = new GameObject(p);

		if (className.equalsIgnoreCase("Fox")) {
			Fox tempFox = (Fox) space;
			Point p2 = tempFox.getCoordinate().equals(p) ? tempFox.getCoordinate2() : tempFox.getCoordinate();
			gameBoard[p2.y][p2.x] = new GameObject(p2);
		}
		listeners.remove(space);
	}

	/**
	 * Getter for the game board
	 * 
	 * @return
	 */
	public GameObject[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * Getter for the listeners
	 * 
	 * @return
	 */
	public ArrayList<JumpInListener> getListeners() {
		return listeners;
	}

	/**
	 * Getter for the level number
	 * 
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Checks if a point in the game board is a hole.
	 * 
	 * @param x - x coordinate of the board
	 * @param y - y coordinate of the board
	 * @return - boolean of if it is a hole
	 */
	public static boolean isHole(int x, int y) {
		for (int i = 0; i < HOLES.length; i++) {
			if (HOLES[i].getX() == x && HOLES[i].getY() == y) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return (new JumpIn(this)).toString();
	}

}
