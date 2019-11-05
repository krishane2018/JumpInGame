import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Kush Gopeechund and Aashna Narang
 * 
 * 
 *
 */
// TODO add an easy and hard mode - easy is with highlight, hard without

public class JumpIn {

	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private WinListener winListener;
	private Parser parser;
	private int level;
	private LevelSelector levelSelector;
	public final static int NUM_ROWS = 5;
	public final static int NUM_COLUMNS = 5;
	private Point[] holes;

	/**
	 * 
	 * @param level
	 */
	public JumpIn(int level) {
		this.level = level;
		listeners = new ArrayList<JumpInListener>();
		levelSelector = new LevelSelector(level, this);
		gameBoard = levelSelector.getBoard();
		holes = LevelSelector.getHoles();
		parser = new Parser();
	}

	/**
	 * 
	 * @return
	 */
	public GameObject[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * 
	 * @param gameBoard
	 */
	public void setGameBoard(GameObject[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	/**
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public String objectToString(int x, int y) {
		if (isHole(x, y) && gameBoard[y][x].getClass().getSimpleName().equals("Rabbit")) {
			return gameBoard[y][x].getName() + "H";
		} else if (isHole(x, y)) {
			return "H";
		} else if (!(gameBoard[y][x].getClass().getSimpleName().equals(""))) {
			return gameBoard[y][x].getName();
		}
		return "  ";
	}

	/**
	 * 
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHole(int x, int y) {
		for (int i = 0; i < holes.length; i++) {
			if (holes[i].getX() == x && holes[i].getY() == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	public void printWelcome() {
		System.out.println("Welcome to JumpIn!");
		System.out.println("Please type \"play\" to start the game.\n");
	}

	/**
	 * Use this function if you'd like to play the text based game
	 */
	public void play() {

		boolean finished = false;
		System.out.println(toString());
		while (!finished) {
			MovableAnimal chosenAnimal = parser.getAnimal(listeners);
			Move move = new Move();
			// Presents users with move options after the user has chosen the animal they
			// would like to move.
			ArrayList<Object> options = chosenAnimal.determineOptions(gameBoard);

			if (chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
				move = parser.confirmOption(options, (Rabbit) chosenAnimal, chosenAnimal.displayOptions(options));
			}

			else if (chosenAnimal.getClass().getSimpleName().equals("Fox")) {
				move = parser.confirmOption(options, (Fox) chosenAnimal, chosenAnimal.displayOptions(options));
			}
			// Moves the animal to the user's selected position on the game board.
			finished = processCommand(move);
			System.out.println(toString());
		}
		String status = parser.playAgain();
		if (status == "continue") {
			if (level >= 3) {
				System.out.println("You completed all of the levels!");
			} else {
				JumpIn game = new JumpIn(level + 1);
				game.play();
			}

		} else if (status == "exit") {
			return;
		}
	}
	
	public boolean addListener(JumpInListener j) {
		return listeners.add(j);
	}

	/**
	 * 
	 * @return
	 */
	private boolean checkWin() {
		for (int i = 0; i < listeners.size(); i++) {
			if(listeners.get(i) instanceof GameObject) {
				GameObject g = (GameObject) listeners.get(i);
				if (g.getClass().getSimpleName().equals("Rabbit")) {
					Rabbit r = (Rabbit) g;
					if (r.getStatus() == false) {
						return false;
					}
				}
			} else {
				
			}
		}
		return true;
	}

	/**
	 * 
	 * @param move
	 * @return
	 */
	private boolean processCommand(Move move) {
		JumpInEvent event;
		int initialX = move.getInitialLocation().x;
		int initialY = move.getInitialLocation().y;
		int finalX = move.getFinalLocation().x;
		int finalY = move.getFinalLocation().y;
		
		if (move.isNoMove()) {
			return false;
		}
		if (move.getChosenAnimal().getClass().getSimpleName() == "Rabbit") {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getChosenAnimal().getCoordinate(), move.getFinalLocation(), holes);
			gameBoard[initialY][initialX] = new GameObject(new Point(initialX, initialY));
			gameBoard[finalY][finalX] = move.getChosenAnimal();

		} else {
			int initial2X = move.getInitialLocation2().x;
			int initial2Y = move.getInitialLocation2().y;
			int final2X = move.getFinalLocation2().x;
			int final2Y = move.getFinalLocation2().y;
			Fox tempFox  = (Fox)move.getChosenAnimal();
			event = new JumpInEvent(this, move.getChosenAnimal(), tempFox.getCoordinate(), tempFox.getCoordinate2(),
					move.getFinalLocation(), move.getFinalLocation2(), holes);
			gameBoard[initialY][initialX] = new GameObject(new Point(initialX, initialY));
			gameBoard[initial2Y][initial2X] = new GameObject(new Point(initial2X, initial2X));
			gameBoard[finalY][finalX] = move.getChosenAnimal();
			gameBoard[final2Y][final2X] = move.getChosenAnimal();
		}

		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if ((l instanceof GameObject) && (GameObject) l == move.getChosenAnimal()) {
				l.handleEvent(event);
			}
		}
		
		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if(l instanceof JumpInView) {
				l.handleEvent(event);
			} 
		}

		return checkWin();
	}

	/**
	 * Get the type of Animal on the board at a given point
	 * @param p Coordinate on the board the user would like to check
	 * @return a string with the name of the class - Rabbit object returns "Rabbit" for example
	 */
	public String selectedAnimalType(Point p) {
		GameObject g = gameBoard[p.y][p.x];
		return g.getClass().getSimpleName();
	}
	
	/**
	 * Get the list of legal options to move an object at a given point
	 * @param p Coordinate where user would like to check where the legal options are
	 * @return the list of either point arrays (fox since they take up 2 spaces) or list of points (for rabbits)
	 */
	public ArrayList<Object> getAnimalOptions(Point p) {
		GameObject g = gameBoard[p.y][p.x];
		if(g instanceof MovableAnimal) {
			MovableAnimal m = (MovableAnimal)g;
			return m.determineOptions(gameBoard);
		} else {
			return new ArrayList<Object>();
		}
	}
	
	/**
	 * This function is called from the controller whenever the user gives input. This function 
	 * updates the game board if necessary 
	 * @param initial - the initial location of the animal / location of the animal user would like to move
	 * @param finalLocation - location of where the user would like to move the animal
	 * @return true if animal can be moved there, otherwise false
	 */
	public boolean moveAnimal(Point initial, Point finalLocation) {
		GameObject g = gameBoard[initial.y][initial.x];
		ArrayList<Object> options = getAnimalOptions(initial);
		
		if(selectedAnimalType(initial).equals("Rabbit") && options.contains(finalLocation)) {
			processCommand(new Move(initial, finalLocation, g));
			return true;
		} else if (selectedAnimalType(initial).equals("Fox")) {
			boolean selectedInOptions = false;
			Point foxLocation[] = (Point[])options.get(0);
			for(Object o : options) {
				foxLocation = (Point[])o;
				for(Point pt : foxLocation) {
					if (pt.equals(finalLocation)) selectedInOptions = true;
				}
				if (selectedInOptions) break;
			}
			System.out.println(selectedInOptions);
			if (selectedInOptions) {
				Fox f = (Fox)g;
				processCommand(new Move(f.getCoordinate(), f.getCoordinate2(), foxLocation[0], foxLocation[1], g));
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 */
	public String toString() {
		String board = "";
		for (int i = 0; i < NUM_ROWS; i++) {
			board += "--------------------------\n";
			for (int j = 0; j < NUM_COLUMNS; j++) {
				board += String.format("|%4s", objectToString(j, i));
			}
			board += "|\n";
		}
		board += "--------------------------\n";
		return board;
	}
	
	public int getNumRows() {
		return JumpIn.NUM_ROWS;
	}
	
	public int getNumColumns() {
		return JumpIn.NUM_COLUMNS;
	}
	
	/**
	 * Get the initial positions of the mushroom for the current level
	 * @return an array list of all the coordinates of the mushrooms
	 */
	public ArrayList<Point> getInitialMushroomPositions() {
		return levelSelector.getMushroomPositions();
	}
	
	/**
	 * Get the initial positions of the rabbits for the current level
	 * @return an array list of all the coordinates of the mushrooms
	 */
	public ArrayList<Point> getInitialRabbitPositions() {
		return levelSelector.getRabbitInitialPositions();
	}
	
	/**
	 * Get the initial positions of the foxes for the current level
	 * @return hash map of all array list of the coordinate of the fox (key) and 
	 * their orientation (vertical or horizontal) (value)
	 */
	public HashMap<ArrayList<Point>, String> getInitialFoxPositions() {
		return levelSelector.getFoxInitialPositions();
	}
	
	/**
	 * Checks if object at given location is a rabbit
	 * @param p coordinate user would like to check
	 * @return true if it is a rabbit
	 */
	public boolean isRabbit(Point p) {
		return gameBoard[p.y][p.x].getClass().getSimpleName().contentEquals("Rabbit");
	}

	/**
	 * Creates a game, the GUI, and the controller which handles user input
	 * @param args 
	 */
	public static void main(String[] args) {
		JumpIn game = new JumpIn(3);
		JumpInView view = new JumpInView(game);
		MainMenu menu = new MainMenu(view);
		JumpInController controller = new JumpInController(menu.getView(), game);

	}


}
