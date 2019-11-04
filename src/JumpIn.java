import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/**
 * 
 * @author Kush Gopeechund
 * 
 * 
 *
 */
public class JumpIn {

	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private Parser parser;
	private int level;
	private LevelSelector levelSelector;
	public final static int NUM_ROWS = 5;
	public final static int NUM_COLUMNS = 5;
	private Point[] holes;

	/**
	 * 
	 * @param i
	 */
	public JumpIn(int level) {
		this.level = level;
		listeners = new ArrayList<JumpInListener>();
		levelSelector = new LevelSelector(level, this);
		gameBoard = levelSelector.getBoard();
		holes = LevelSelector.getHoles();
		parser = new Parser();
	}

	public GameObject[][] getGameBoard() {
		return gameBoard;
	}

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
	 * 
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
		if (move.isNoMove()) {
			return false;
		}
		if (move.getChosenAnimal().getClass().getSimpleName() == "Rabbit") {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), holes);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(
					new Point(move.getInitialLocation().x, move.getInitialLocation().y));
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();

		} else {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), move.getFinalLocation2(),
					holes);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(
					new Point(move.getInitialLocation().x, move.getInitialLocation().y));
			gameBoard[move.getInitialLocation2().y][move.getInitialLocation2().x] = new GameObject(
					new Point(move.getInitialLocation2().x, move.getInitialLocation2().y));
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();
			gameBoard[move.getFinalLocation2().y][move.getFinalLocation2().x] = move.getChosenAnimal();
		}

		// Need to update the view first 
		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if(l instanceof JumpInView) {
				l.handleEvent(event);
			} 
		}
		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if ((l instanceof GameObject) && (GameObject) l == move.getChosenAnimal()) {
				l.handleEvent(event);
			}
		}

		return checkWin();
	}

	public String selectedAnimalType(Point p) {
		GameObject g = gameBoard[p.y][p.x];
		return g.getClass().getSimpleName();
	}
	
	public ArrayList<Object> getAnimalOptions(Point p) {
		GameObject g = gameBoard[p.y][p.x];
		if(g instanceof MovableAnimal) {
			MovableAnimal m = (MovableAnimal)g;
			return m.determineOptions(gameBoard);
		} else {
			return new ArrayList<Object>();
		}
	}
	
	public boolean moveAnimal(Point initial, Point finalLocation) {
		GameObject g = gameBoard[initial.y][initial.x];
		ArrayList<Object> options = getAnimalOptions(initial);
		
		if(selectedAnimalType(initial).contentEquals("Rabbit") && options.contains(finalLocation)) {
			processCommand(new Move(initial, finalLocation, g));
			System.out.print("moved rabbit");
			return true;
		} else if (selectedAnimalType(initial).contentEquals("Fox")) {
			boolean selectedInOptions = false;
			Point foxLocation[] = (Point[])options.get(0);
			for(Object o : options) {
				foxLocation = (Point[])o;
				for(Point pt : foxLocation) {
					if (pt.equals(finalLocation)) selectedInOptions = true;
				}
			}
			System.out.println(selectedInOptions);
			if (selectedInOptions) {
				System.out.print(g instanceof GameObject);
				System.out.println("moved fox");
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
	
	public ArrayList<Point> getInitialMushroomPositions() {
		return levelSelector.getMushroomPositions();
	}
	
	public ArrayList<Point> getInitialRabbitPositions() {
		return levelSelector.getRabbitInitialPositions();
	}
	
	public HashMap<ArrayList<Point>, String> getInitialFoxPositions() {
		return levelSelector.getFoxInitialPositions();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JumpIn game = new JumpIn(1);
//		game.printWelcome();
//		game.play();
		JumpInView view = new JumpInView(game);
		JumpInController controller = new JumpInController(view, game);

	}

}
