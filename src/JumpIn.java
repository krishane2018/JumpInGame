import java.awt.Point;
import java.util.ArrayList;

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
	private final static int NUM_ROWS = 5;
	private final static int NUM_COLUMNS = 5;
	private Point[] HOLES;

	/**
	 * 
	 * @param i
	 */
	public JumpIn(int level) {
		this.level = level;
		listeners = new ArrayList<JumpInListener>();
		LevelSelector l = new LevelSelector(level, this);
		gameBoard = l.getBoard();
		HOLES = LevelSelector.getHoles();
		parser = new Parser();
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
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isHole(int x, int y) {
		for (int i = 0; i < HOLES.length; i++) {
			if (HOLES[i].getX() == x && HOLES[i].getY() == y) {
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
				System.out.println("You completed all of the level!");
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
			GameObject g = (GameObject) listeners.get(i);
			if (g.getClass().getSimpleName().equals("Rabbit")) {
				Rabbit r = (Rabbit) g;
				if (r.getStatus() == false) {
					return false;
				}
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
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), HOLES);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(
					new Point(move.getInitialLocation().x, move.getInitialLocation().y));
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();

		} else {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), move.getFinalLocation2(),
					HOLES);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(
					new Point(move.getInitialLocation().x, move.getInitialLocation().y));
			;
			gameBoard[move.getInitialLocation2().y][move.getInitialLocation2().x] = new GameObject(
					new Point(move.getInitialLocation2().x, move.getInitialLocation2().y));
			;
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();
			gameBoard[move.getFinalLocation2().y][move.getFinalLocation2().x] = move.getChosenAnimal();
		}

		for (int i = 0; i < listeners.size(); i++) {
			if ((GameObject) listeners.get(i) == move.getChosenAnimal()) {
				listeners.get(i).handleEvent(event);
			}
		}

		return checkWin();
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

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JumpIn game = new JumpIn(3);
		game.printWelcome();
		game.play();

	}

}
