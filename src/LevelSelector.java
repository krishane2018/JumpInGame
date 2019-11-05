
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Nick Anderson and Aashna Narang
 *
 *         Initialize a game board
 */
public class LevelSelector {
	private GameObject[][] board;
	// coordinates of the game board
	private static final Point[] HOLES = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) };
	private ArrayList<Point> rabbitInitialPositions;
	private ArrayList<Point> mushroomPositions;
	private HashMap<ArrayList<Point>, String> foxInitialPositions;

	/**
<<<<<<< HEAD
	 * Initialize a game board
	 * 
=======
	 * Initialize a game board depending on the level
>>>>>>> refs/heads/AashnaViewController
	 * @param level which level the user would like to play
	 * @param game  the JumpIn game object
	 */
	public LevelSelector(int level, JumpIn game) {
		rabbitInitialPositions = new ArrayList<Point>();
		mushroomPositions = new ArrayList<Point>();
		foxInitialPositions = new HashMap<ArrayList<Point>, String>();
		if (1 > level | level > 3) {
			throw new IllegalArgumentException("Levels must be between 1-3");
		}
		board = new GameObject[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new GameObject(new Point(i, j));
			}
		}

		if (level == 1) {
			// based on JumpIn' level 5 (C)
			
			// Rabbit positions
			Point p1 = new Point(2,0);
			Point p2 = new Point(0,4);
			
			rabbitInitialPositions.add(p1);
			rabbitInitialPositions.add(p2);
			
			// Mushroom positions
			Point p3 = new Point(0,2);
			Point p4 = new Point(0,3);
			Point p5 = new Point(1,1);
			
			mushroomPositions.add(p3);
			mushroomPositions.add(p4);
			mushroomPositions.add(p5);

			gameObjectInit('R', rabbitInitialPositions, game);
			gameObjectInit('M', mushroomPositions, game);


		} else if (level == 2) {
			// based on JumpIn' level 6
			
			// Rabbit positions
			Point p1 = new Point(0,0);
			Point p2 = new Point(3,2);
			
			rabbitInitialPositions.add(p1);
			rabbitInitialPositions.add(p2);
			
			// Mushroom positions
			Point p3 = new Point(1,0);
			Point p4 = new Point(2,1);
			Point p5 = new Point(1,2);
			
			mushroomPositions.add(p3);
			mushroomPositions.add(p4);
			mushroomPositions.add(p5);
			
			gameObjectInit('R', rabbitInitialPositions, game);
			gameObjectInit('M', mushroomPositions, game);


		} else if (level == 3) {

			// based on JumpIn' level 16 
			
			// Rabbit positions
			Point p1 = new Point(1,0);
			
			rabbitInitialPositions.add(p1);
			
			// Mushroom positions
			Point p3 = new Point(0,1);
			Point p4 = new Point(0,2);
			Point p5 = new Point(2,3);
			
			mushroomPositions.add(p3);
			mushroomPositions.add(p4);
			mushroomPositions.add(p5);
			
			gameObjectInit('R', rabbitInitialPositions, game);
			gameObjectInit('M', mushroomPositions, game);
		
			// Fox Positions
			Point p6 = new Point(1,3);
			Point p7 = new Point(1,4);
			
			ArrayList<Point> arr = new ArrayList<Point>();
			arr.add(p6);
			arr.add(p7);
			foxInitialPositions.put(arr, "Vertical");
			
			foxObjectInit(game);
		}
	}

	/**
	 * Initialize all of the given positions to a game object with a certain name to it
	 * @param type - type of animal user would like to initialize. R = rabbit, M = Mushroom
	 * @param arr - Array of coordinates of where to initialize objects to 
	 * @param game - the jump in game the objects may listen to for events and updates
	 */
	private void gameObjectInit(char type, ArrayList<Point> arr, JumpIn game) {
		for(int i = 0; i < arr.size(); i++) {
			Point p = arr.get(i);
			if (type == 'R') {
				Rabbit g = new Rabbit(p, "R" + (i + 1) );
				game.addListener(g);
				board[p.y][p.x] = g;
			} else if (type == 'M') {
				GameObject g = new GameObject(p, "M" + (i + 1));
				board[p.y][p.x] = g;
			} else {
				throw new IllegalArgumentException("Can only initialize rabbits or mushrooms");
			}
		}
	}
	
	/**
	 * Initialize all fox objects at the initial positions 
	 * @param game - the game that the fox objects listen to for events and updates
	 */
	private void foxObjectInit(JumpIn game) {
		int i = 1;
		for (ArrayList<Point> p : foxInitialPositions.keySet()) {
			Point p0 = p.get(0);
			Point p1 = p.get(1);
			Fox f = new Fox(p0, p1, "F" + i, foxInitialPositions.get(p));
			game.addListener(f);
			board[p0.y][p0.x] = f;
			board[p1.y][p1.x] = f;
		}
	}
	
	/**
	 * Get the coordinates of the rabbit holes
	 * 
	 * @return an array of Point objects
	 */
	public static Point[] getHoles() {
		return HOLES;
	}

	/**
	 * Return the game board
	 * 
	 * @return a 2d array of the GameObjects
	 */
	public GameObject[][] getBoard() {
		return this.board;
	}

	/**
	 * @return the rabbitInitialPositions
	 */
	public ArrayList<Point> getRabbitInitialPositions() {
		return rabbitInitialPositions;
	}

	/**
	 * @param rabbitInitialPositions the rabbitInitialPositions to set
	 */
	public void setRabbitInitialPositions(ArrayList<Point> rabbitInitialPositions) {
		this.rabbitInitialPositions = rabbitInitialPositions;
	}

	/**
	 * @return the mushroomPositions
	 */
	public ArrayList<Point> getMushroomPositions() {
		return mushroomPositions;
	}

	/**
	 * @param mushroomPositions the mushroomPositions to set
	 */
	public void setMushroomPositions(ArrayList<Point> mushroomPositions) {
		this.mushroomPositions = mushroomPositions;
	}

	/**
	 * @return the foxInitialPositions
	 */
	public HashMap<ArrayList<Point>, String> getFoxInitialPositions() {
		return foxInitialPositions;
	}

	/**
	 * @param foxInitialPositions the foxInitialPositions to set
	 */
	public void setFoxInitialPositions(HashMap<ArrayList<Point>, String> foxInitialPositions) {
		this.foxInitialPositions = foxInitialPositions;
	}

}
	