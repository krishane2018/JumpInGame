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
	private final int NUM_ROWS=5;
	private final int NUM_COLUMNS=5;
	
	/**
	 * 
	 * @param i
	 */
	public JumpIn(int i) {
		level = i;
		listeners = new ArrayList<JumpInListener>();
		gameBoard = new LevelSelector(level);
	}
	
	/**
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public String objectToString(int y, int x) {
		if(gameBoard[y][x].getClass().getName()=="Rabbit") {
			return "R ";
		}else if(gameBoard[y][x].getClass().getName()=="Fox") {
			return "F ";
		}else if(gameBoard[y][x].getClass().getName()=="Hole") {
			return "O ";
		}
		else if(gameBoard[y][x].getClass().getName()=="Mushroom") {
			return "M ";
		}else {
			return "  ";
		}
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
        while (! finished) {
        	GameObject chosenAnimal = parser.getAnimal(listeners);
        	String options;
        	if (chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
        		options = displayOptions(determineOptions((Rabbit)chosenAnimal));
        	}
        	
        	else if (chosenAnimal.getClass().getSimpleName().equals("Fox")) {
        		options = displayOptions(determineOptions((Fox)chosenAnimal));
        	}
    		Move move = parser.confirmOption(options, chosenAnimal);
            finished = processCommand(move);
            System.out.println(gameBoard.toString());
        }
        System.out.println("Congrats! \nPease type \"continue\" if you would like to go to the next level.\nType \"exit\" if you would like to stop.");
        String status = parser.getNext();
        if(status == "continue") {
        	JumpIn game = new JumpIn(level+1);
        	game.play();
        }
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
					&& !(gameBoard[y][uniformCoordinate].getClass().getName().equals("RabbitHole"));
		} else {
			changingCoordinate = (int) chosenRabbit.getCoordinate().getX();
			uniformCoordinate = (int) chosenRabbit.getCoordinate().getY();
			upperBound = NUM_COLUMNS;
			isObstacle = (Integer x) -> gameBoard[uniformCoordinate][x] != null
					&& !(gameBoard[uniformCoordinate][x].getClass().getName().equals("RabbitHole"));
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
	 * 
	 * @return
	 */
	private boolean checkWin() {
		boolean win=false;
		for(int i = 0;i<listeners.size();i++) {
			if(listeners.get(i).getName()=="Rabbit") {
				win = listeners.get(i).getStatus;
			}
		}
		return win;
	}
	
	/**
	 * 
	 * @param move
	 * @return
	 */
	private boolean processCommand(Move move) {
		JumpInEvent event = new JumpInEvent(this,move.getGameObject,move.end.x,move.end.y);
		gameBoard[move.initial.y][move.initial.x]=null;
		gameBoard[move.end.y][move.end.x]=move.getGameObject;
		for(int i = 0;i<listeners.size();i++) {
			if(listeners.get(i).equals(move.getGameObject)) {
				listeners.get(i).handleEvent(event);
			}
		}
		
		return checkWin();
	}
	
	/**
	 * 
	 */
	public String toString(){
		String board ="";
		for(int i=0;i<NUM_ROW;i++) {
			board += "---------------------\n";
			for(int j=0;j<NUM_COL;j++) {
				board += "| ";
				objectToString(j,i);
			}
			board += "|\n";
		}
		board += "---------------------\n";
		return board;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JumpIn game = new JumpIn(1);
		game.printWelcome();
		game.play();
		
	}

}
