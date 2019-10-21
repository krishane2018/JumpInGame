import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * This is the main class for the "Jump in" game application. This is a
 * simple puzzle game to get rabbits into a rabbit hole to hide from the
 * foxes.
 * 
 * Rules: 
 * Rabbits can only jump over obstacles to move, which can either be Fox or a mushroom.
 * The user can move rabbits up, down, left or right. The Fox on the other hand
 * can only be moved forwards or backwards. To win the game, the user has to 
 * strategically move the pieces to get all the rabbits into a rabbit hole.
 * 
 * 
 * @author Kush Gopeechund
 *
 */
public class JumpIn {

	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private Parser parser;
	private int level;
	private final static int NUM_ROWS = 5;
	private final static int NUM_COLUMNS = 5;
	private final Point[] HOLES;

	/**
	 * Selects the level to initialize the array list and the game board.
	 * 
	 * @param i
	 */
	public JumpIn(int i) {
		level = i;
		listeners = new ArrayList<JumpInListener>();
		LevelSelector l = new LevelSelector(level, this);
		gameBoard = l.getBoard();
		HOLES = l.getHoles();
	}

	/**
	 * Finds out the appropriate symbol to show in the visual board.
	 * 
	 * @param y
	 * @param x
	 * @return
	 */

	public String objectToString(int x, int y) {
		if (isHole(x, y)&&gameBoard[y][x].getClass().getSimpleName()=="Rabbit") {
			return gameBoard[y][x].getName() + "H";
		}else if(isHole(x, y)) {
			return "H";
		} else if (!(gameBoard[y][x].equals(""))) {
			return gameBoard[y][x].getName();
		}
		return "  ";
	}

	/**
	 * Checks if a location on the board is a hole.
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
	 * Prints a wwelcome statement.
	 */
	public void printWelcome() {
		System.out.println("Welcome to JumpIn!");
		System.out.println("Please type \"play\" to start the game.\n");
	}

	/**
	 * Main play routine. Runs through loop till finished then asks whether to continue 
	 * to the next level or exit.
	 */
	public void play() {

		boolean finished = false;
		while (!finished) {
			GameObject chosenAnimal = parser.getAnimal(listeners);
			Move move = new Move();
			if (chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
				ArrayList<Point> options = determineOptions((Rabbit) chosenAnimal);
				move = parser.confirmOption(options, (Rabbit) chosenAnimal, displayOptions(options));
			}

			else if (chosenAnimal.getClass().getSimpleName().equals("Fox")) {
				ArrayList<Point[]> options = determineOptions((Fox) chosenAnimal);
				move = parser.confirmOption(options, (Fox) chosenAnimal, displayOptions(options));
			}

			finished = processCommand(move);
			System.out.println(gameBoard.toString());
		}
		System.out.println(
				"Congrats! \nPease type \"continue\" if you would like to go to the next level.\nType \"exit\" if you would like to stop.");
		String status = parser.playAgain();
		if (status == "continue") {
			JumpIn game = new JumpIn(level + 1);
			game.play();
		} else if (status == "exit") {
			return;
		}
	}

	/**
	 * Adds a listener
	 * 
	 * @param j
	 * @return
	 */
	public boolean addListener(JumpInListener j) {
		return listeners.add(j);
	}

	private <E> String displayOptions(ArrayList<E> options) {
		String output = "";
		if (options.get(0).getClass().getSimpleName().equals("Point")) {
			for (E element : options) {
				Point point = (Point) element;
				output += "(" + point.getX() + "," + point.getY() + ")\n";
			}

		} else if (options.get(0).getClass().getSimpleName().equals("Point[]")) {
			for (E element : options) {
				Point[] points = (Point[]) (element);
				output += "{";
				for (Point point : points) {
					output += "(" + point.getX() + "," + point.getY() + ") ";
				}
				output = output.trim();
				output += "}\n";

			}

		}
		return output;
	}

	private ArrayList<Point> determineOptions(Rabbit chosenRabbit) {
		ArrayList<Point> rabbitOptionsArrayList = new ArrayList<Point>();
		rabbitOptionsHelper(chosenRabbit, "Left", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Right", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Up", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Down", rabbitOptionsArrayList);
		return rabbitOptionsArrayList;
	}

	private ArrayList<Point[]> determineOptions(Fox chosenFox) {
		ArrayList<Point[]> foxOptionsArrayList = new ArrayList<Point[]>();
		if (chosenFox.getDirection().equals("Vertical")) {
			foxOptionsHelper(chosenFox, "Up", foxOptionsArrayList);
			foxOptionsHelper(chosenFox, "Down", foxOptionsArrayList);
		} else if (chosenFox.getDirection().equals("Horizontal")) {
			foxOptionsHelper(chosenFox, "Left", foxOptionsArrayList);
			foxOptionsHelper(chosenFox, "Right", foxOptionsArrayList);
		}
		return foxOptionsArrayList;
	}

	private void rabbitOptionsHelper(Rabbit chosenRabbit, String movingDirection, ArrayList<Point> rabbitOptions) {

		int changingCoordinate;
		int uniformCoordinate;
		int upperBound;
		int startingPosition;
		boolean isJump = false;
		Function<Integer, Boolean> checkBounds;
		Function<Integer, Integer> increment;
		Function<Integer, Boolean> isObstacle;

		if (movingDirection.equals("Up") || movingDirection.equals("Down")) {
			changingCoordinate = (int) chosenRabbit.getCoordinate().getY();
			uniformCoordinate = (int) chosenRabbit.getCoordinate().getX();
			upperBound = NUM_ROWS;
			isObstacle = (Integer y) -> gameBoard[y][uniformCoordinate] != null
					&& !(gameBoard[y][uniformCoordinate].getClass().getSimpleName().equals("RabbitHole"));
		} else {
			changingCoordinate = (int) chosenRabbit.getCoordinate().getX();
			uniformCoordinate = (int) chosenRabbit.getCoordinate().getY();
			upperBound = NUM_COLUMNS;
			isObstacle = (Integer x) -> gameBoard[uniformCoordinate][x] != null
					&& !(gameBoard[uniformCoordinate][x].getClass().getSimpleName().equals("RabbitHole"));
		}
		if (movingDirection.equals("Left") || movingDirection.equals("Up")) {
			checkBounds = (Integer x) -> x > -1;
			increment = (Integer x) -> x - 1;
			startingPosition = changingCoordinate - 1;
		} else {
			checkBounds = (Integer x) -> x < upperBound;
			increment = (Integer x) -> x + 1;
			startingPosition = changingCoordinate + 1;
		}
		for (int i = startingPosition; checkBounds.apply(i); i = increment.apply(i)) {
			if (isObstacle.apply(i)) {
				isJump = true;
			} else if (isJump == true) {
				rabbitOptions.add(new Point(uniformCoordinate, i));
				break;
			} else if (!isObstacle.apply(i)) {
				break;
			}
		}

	}

	private void foxOptionsHelper(Fox chosenFox, String movingDirection, ArrayList<Point[]> foxOptions) {

		int changingCoordinate = 0;
		int uniformCoordinate;
		int upperBound;
		int startingPosition;
		Function<Integer, Boolean> checkBounds;
		Function<Integer, Integer> increment;
		Function<Integer, Boolean> isEmpty;

		if (movingDirection.equals("Up") || movingDirection.equals("Down")) {
			if (movingDirection.equals("Up")) {
				changingCoordinate = (int) chosenFox.getCoordinate().getY();

			} else if (movingDirection.equals("Down")) {
				changingCoordinate = (int) chosenFox.getCoordinate2().getY();
			}
			uniformCoordinate = (int) chosenFox.getCoordinate().getX();
			upperBound = NUM_ROWS;
			isEmpty = (Integer y) -> gameBoard[y][uniformCoordinate] == null;
		} else {
			if (movingDirection.equals("Left")) {
				changingCoordinate = (int) chosenFox.getCoordinate().getX();
			} else if (movingDirection.equals("Right")) {
				changingCoordinate = (int) chosenFox.getCoordinate2().getX();
			}

			uniformCoordinate = (int) chosenFox.getCoordinate().getY();
			upperBound = NUM_COLUMNS;
			isEmpty = (Integer x) -> gameBoard[uniformCoordinate][x] == null;
		}
		if (movingDirection.equals("Left") || movingDirection.equals("Up")) {
			checkBounds = (Integer x) -> x > -1;
			increment = (Integer x) -> x - 1;
			startingPosition = changingCoordinate - 1;
		} else {
			checkBounds = (Integer x) -> x < upperBound;
			increment = (Integer x) -> x + 1;
			startingPosition = changingCoordinate + 1;
		}
		for (int i = startingPosition; checkBounds.apply(i); i = increment.apply(i)) {
			if (isEmpty.apply(i)) {
				Point[] tempArray = { new Point(i, (int) chosenFox.getCoordinate().getY()),
						new Point(i + 1, (int) chosenFox.getCoordinate().getY()) };
				foxOptions.add(tempArray);
			} else {
				break;
			}
		}

	}

	/**
	 * Checks if all the rabbits are in a hole.
	 * 
	 * @return
	 */
	private boolean checkWin() {
		for (int i = 0; i < listeners.size(); i++) {
			GameObject g = (GameObject) listeners.get(i);
			if (g.getClass().getSimpleName() == "Rabbit") {
				Rabbit r = (Rabbit) g;
				if (r.getStatus() == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Notifies listener when a move is made.
	 * 
	 * @param move
	 * @return
	 */
	private boolean processCommand(Move move) {
		JumpInEvent event;
		if (move.getChosenAnimal().getClass().getSimpleName() == "Rabbit") {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), HOLES);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = null;
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();
		} else {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), move.getFinalLocation2(),
					HOLES);
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = null;
			gameBoard[move.getInitialLocation2().y][move.getInitialLocation2().x] = null;
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
	 * Converts the game board to a string so it can be displayed
	 */
	public String toString() {
		String board = "";
		for (int i = 0; i < NUM_ROWS; i++) {
			board += "---------------------\n";
			for (int j = 0; j < NUM_COLUMNS; j++) {
				board += "| ";
				objectToString(j, i);
			}
			board += "|\n";
		}
		board += "---------------------\n";
		return board;
	}

	public static void main(String[] args) {
		JumpIn game = new JumpIn(1);
		game.printWelcome();
		game.play();

	}

}
