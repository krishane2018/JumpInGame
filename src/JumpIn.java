import java.awt.Point;
import java.util.ArrayList;
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
		HOLES = l.getHoles();
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
			GameObject chosenAnimal = parser.getAnimal(listeners);
			Move move = new Move();
			// Presents users with move options after the user has chosen the animal they
			// would like to move.
			if (chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
				ArrayList<Point> options = determineOptions((Rabbit) chosenAnimal);
				move = parser.confirmOption(options, (Rabbit) chosenAnimal, displayOptions(options));
			}

			else if (chosenAnimal.getClass().getSimpleName().equals("Fox")) {
				ArrayList<Point[]> options = determineOptions((Fox) chosenAnimal);
				move = parser.confirmOption(options, (Fox) chosenAnimal, displayOptions(options));
			}
			//Moves the animal to the user's selected position on the game board. 
			finished = processCommand(move);
			System.out.println(toString());
		}
		String status = parser.playAgain();
		if (status == "continue") {
			JumpIn game = new JumpIn(level + 1);
			game.play();
		} else if (status == "exit") {
			return;
		}
	}

	public boolean addListener(JumpInListener j) {
		return listeners.add(j);
	}

	/**
	 * Used to display options in console for user.
	 * 
	 * @author Krishan Easparan
	 * @param options An ArrayList containing the options the user can pick to move the animal.
	 * @return A string representation of the options.
	 */

	private <E> String displayOptions(ArrayList<E> options) {
		String output = "";
		int counter = 1;
		
		if (options.isEmpty()) {
			output = "No options available.";
		}
		else if (options.get(0).getClass().getSimpleName().equals("Point")) {
			for (E element : options) {
				Point point = (Point) element;
				output += counter +" (" + point.getX() + "," + point.getY() + ")\n";
				counter++;
			}

		} else if (options.get(0).getClass().getSimpleName().equals("Point[]")) {
			for (E element : options) {
				Point[] points = (Point[]) (element);
				output += counter+" {";
				for (Point point : points) {
					output += "(" + point.getX() + "," + point.getY() + ") ";
				}
				counter++;
				output = output.trim();
				output += "}\n";

			}

		}
		return output;
	}

	/**
	 * Used to determine move options for the rabbit chosen by the user. 
	 * 
	 * @author Krishan Easparan
	 * @param chosenRabbit The rabbit that the user chose to move.
	 * @return An ArrayList containing the options the user can pick to move the rabbit.
	 */
	private ArrayList<Point> determineOptions(Rabbit chosenRabbit) {
		ArrayList<Point> rabbitOptionsArrayList = new ArrayList<Point>();
		rabbitOptionsHelper(chosenRabbit, "Left", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Right", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Up", rabbitOptionsArrayList);
		rabbitOptionsHelper(chosenRabbit, "Down", rabbitOptionsArrayList);
		return rabbitOptionsArrayList;
	}

	/**
	 * Used to determine move options for the fox chosen by the user.
	 * 
	 * @author Krishan Easparan
	 * @param chosenFox The fox that the user chose to move.
	 * @return An ArrayList containing the options the user can pick to move the fox.
	 */
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

	/**
	 * Used as helper method for determineOptions().
	 * 
	 * @author Krishan Easparan
	 * @param chosenRabbit The rabbit that the user chose to move.
	 * @param movingDirection The direction that the rabbit is trying to move in.
	 * @param rabbitOptions An ArrayList containing the options the user can pick to move the rabbit.
	 */
	private void rabbitOptionsHelper(Rabbit chosenRabbit, String movingDirection, ArrayList<Point> rabbitOptions) {

		int changingCoordinate;					//initialization of variables
		int uniformCoordinate;
		int upperBound;
		int startingPosition;
		boolean isJump = false;
		Function<Integer, Boolean> checkBounds;
		Function<Integer, Integer> increment;
		Function<Integer, Boolean> isObstacle;
		Function<Integer, Point> newPoint;

		/*The following if statements are used to set up expressions and variables so that they can
		be used to make the for loop as general as possible (to avoid repeating code).*/
		
		if (movingDirection.equals("Up") || movingDirection.equals("Down")) { 
			changingCoordinate = (int) chosenRabbit.getCoordinate().getY();
			uniformCoordinate = (int) chosenRabbit.getCoordinate().getX();
			upperBound = NUM_ROWS;
			newPoint = (Integer x) -> new Point(uniformCoordinate, x); 
			//The following lambda determines if a board space is not empty.
			isObstacle = (Integer y) -> !(gameBoard[y][uniformCoordinate].getName().equals(""));
		} else {
			changingCoordinate = (int) chosenRabbit.getCoordinate().getX();
			uniformCoordinate = (int) chosenRabbit.getCoordinate().getY();
			upperBound = NUM_COLUMNS;
			newPoint = (Integer x) -> new Point(x, uniformCoordinate);
			//The following lambda determines if a board space is not empty.
			isObstacle = (Integer x) -> !(gameBoard[uniformCoordinate][x].getName().equals(""));
		}
		if (movingDirection.equals("Left") || movingDirection.equals("Up")) {
			checkBounds = (Integer x) -> x > -1; // lambda for boolean expression of for loop
			increment = (Integer x) -> x - 1; //lambda for update expression of for loop
			startingPosition = changingCoordinate - 1;
			
			
		} else {
			checkBounds = (Integer x) -> x < upperBound; // lambda for boolean expression of for loop
			increment = (Integer x) -> x + 1; //lambda for update expression of for loop
			startingPosition = changingCoordinate + 1;
		}
		
		//General for loop
		for (int i = startingPosition; checkBounds.apply(i); i = increment.apply(i)) {
			if (isObstacle.apply(i)) {
				isJump = true;
			} else if (isJump == true) {
				rabbitOptions.add(newPoint.apply(i));
				break;
			} else if (!isObstacle.apply(i)) {
				break;
			}
		}

	}

	/**
	 * 
	 * 
	 * @author Krishan Easparan
	 * @param chosenFox The fox chosen by the user to move.
	 * @param movingDirection The direction that the fox is trying to move in.
	 * @param foxOptions An ArrayList containing the options the user can pick to move the fox.
	 */
	private void foxOptionsHelper(Fox chosenFox, String movingDirection, ArrayList<Point[]> foxOptions) {

		int changingCoordinate = 0;						//initialization of variables
		int uniformCoordinate;
		int upperBound;
		int startingPosition;
		Function<Integer, Boolean> checkBounds;
		Function<Integer, Integer> increment;
		Function<Integer, Boolean> isEmpty;

		/*The following if statements are used to set up expressions and variables so that they can
		be used to make the for loop as general as possible (to avoid repeating code).*/
		
		if (movingDirection.equals("Up") || movingDirection.equals("Down")) {
			if (movingDirection.equals("Up")) {
				changingCoordinate = (int) chosenFox.getCoordinate().getY();

			} else if (movingDirection.equals("Down")) {
				changingCoordinate = (int) chosenFox.getCoordinate2().getY();
			}
			uniformCoordinate = (int) chosenFox.getCoordinate().getX();
			upperBound = NUM_ROWS;
			//The following lambda determines if a board space is empty.
			isEmpty = (Integer y) -> gameBoard[y][uniformCoordinate].getName().equals("");
		} else {
			if (movingDirection.equals("Left")) {
				changingCoordinate = (int) chosenFox.getCoordinate().getX();
			} else if (movingDirection.equals("Right")) {
				changingCoordinate = (int) chosenFox.getCoordinate2().getX();
			}

			uniformCoordinate = (int) chosenFox.getCoordinate().getY();
			upperBound = NUM_COLUMNS;
			//The following lambda determines if a board space is not empty.
			isEmpty = (Integer x) -> gameBoard[uniformCoordinate][x].getName().equals("");
		}
		if (movingDirection.equals("Left") || movingDirection.equals("Up")) {
			checkBounds = (Integer x) -> x > -1;	// lambda for boolean expression of for loop
			increment = (Integer x) -> x - 1;// lambda for boolean expression of for loop
			startingPosition = changingCoordinate - 1;
		} else {
			checkBounds = (Integer x) -> x < upperBound;// lambda for boolean expression of for loop
			increment = (Integer x) -> x + 1;// lambda for boolean expression of for loop
			startingPosition = changingCoordinate + 1;
		}
		//General for loop
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
	 * 
	 * @return
	 */
	private boolean checkWin() {
		for (int i = 0; i < listeners.size(); i++) {
			GameObject g = (GameObject) listeners.get(i);
			if (g.getClass().getSimpleName().equals("Rabbit")) {
				Rabbit r = (Rabbit) g;
				if (r.getStatus() == false) {
					System.out.println(r.getName());
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
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(new Point(move.getInitialLocation().x, move.getInitialLocation().y));
		} else {
			event = new JumpInEvent(this, move.getChosenAnimal(), move.getFinalLocation(), move.getFinalLocation2(),
					HOLES);
			gameBoard[move.getFinalLocation().y][move.getFinalLocation().x] = move.getChosenAnimal();
			gameBoard[move.getFinalLocation2().y][move.getFinalLocation2().x] = move.getChosenAnimal();
			gameBoard[move.getInitialLocation().y][move.getInitialLocation().x] = new GameObject(new Point(move.getInitialLocation().x, move.getInitialLocation().y));;
			gameBoard[move.getInitialLocation2().y][move.getInitialLocation2().x] = new GameObject(new Point(move.getInitialLocation2().x, move.getInitialLocation2().y));;
			
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
