package source;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import gui.JumpInView;


/**
 * 
 * JumpIn is a single player puzzle game where the user has to strategically
 * move the rabbits and foxes to get all the rabbits in a hole. To play the
 * game the user has to click on the piece they would like to move, then click
 * on a highlighted area to make a move.
 * 
 * 
 * @author Kush Gopeechund and Aashna Narang
 *
 */
// TODO add an easy and hard mode - easy is with highlight, hard without

public class JumpIn {

	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private Parser parser;
	private int level;
	private LevelSelector levelSelector;
	public final static int NUM_ROWS = 5;
	public final static int NUM_COLUMNS = 5;
	private Point[] holes;
	private UndoRedo undoRedo;
	private Queue<Move> solverMoves;
	 

	/**
	 * This is a constructor for the main class JumpIn which initializes
	 * all the variables based off the level.
	 * 
	 * @param level - game level
	 */
	public JumpIn(int level) {
		this.level = level;
		listeners = new ArrayList<JumpInListener>();
		undoRedo = new UndoRedo();
		levelSelector = new LevelSelector(level, this);
		gameBoard = levelSelector.getBoard();
		holes = LevelSelector.getHoles();
		parser = new Parser();
		solverMoves = new LinkedList<Move>();
		solver();
	}

	/**
	 * @return the solverMoves
	 */
	public Queue<Move> getSolverMoves() {
		return solverMoves;
	}

	/**
	 * Determines the moves that will lead the user to a successful win.
	 */
	public void solver() {
		ArrayList<JumpInListener> viewListeners = new ArrayList<JumpInListener>();
		for (int i = 0; i<listeners.size();i++) {
			JumpInListener l = listeners.get(i);
			if(l instanceof JumpInView) {
				viewListeners.add(listeners.remove(i));
				i--;
			}
		}
		solverHelper(new Stack<Move>(), new Stack<ArrayList<Point>>());
		listeners.addAll(viewListeners);
		undoRedo.clearRedo();
	}
	
	/**
	 * Determines if the game board is in a state that it was in previously 
	 * @param previousStates An ArrayList containing the previous states of the board.
	 * @return A boolean indicating whether the game board is in a state that it was in previously. 
	 */
	private boolean isPreviousState(Stack<ArrayList<Point>> previousStates) {
		for (ArrayList<Point> state : previousStates) {
			if (helperIsPreviousState(state)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Helper method for isPreviousState
	 * @param state A previous state of the game board. 
	 * @return A boolean indicating whether the game board is in a previous state. 
	 */
	private boolean helperIsPreviousState(ArrayList<Point> state) {
		for (int i = 0; i<listeners.size();i++) {
			GameObject g = (GameObject)listeners.get(i);
			if (!(g.getCoordinate().equals(state.get(i)))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Determines if the Fox has moved since the last Rabbit move.
	 * @param tryMoves Stack of moves that the solver has tried.
	 * @param animal The Fox which is being checked.
	 * @return A boolean indicating whether the Fox has moved since the last Rabbit move.
	 */
	private boolean isMovedFox (Stack<Move> tryMoves, MovableAnimal animal) {
		for (int i = tryMoves.size()-1; i>-1;i--) {
			Move move = tryMoves.get(i);
			if (move.getChosenAnimal()==animal) {
				return true;
			}
			else if (move.getChosenAnimal() instanceof Rabbit){
				break;
			}
		}
		return false;
	}
	
	/**
	 * Helper method for solver, determines if a solution is available.
	 * @param previousAnimal Animal that was moved in previous move.
	 * @param tryMoves ArrayList of moves that the solver has tried.
	 * @param previousStates ArrayList of states that the game board has already been in.
	 * @return A boolean indicating whether a solution has been found.
	 */
	
	private boolean solverHelper(Stack<Move> tryMoves, Stack<ArrayList<Point>> previousStates) {
		boolean isWin = false;
		MovableAnimal animal;
		
		if (isPreviousState(previousStates)) {
			return false;
		}
		
		
		ArrayList<Point> currentState = new ArrayList<Point>();

		for (JumpInListener l : listeners) {
			Point p = ((GameObject)l).getCoordinate();
			currentState.add(new Point(p));
		}
		previousStates.add(currentState);
		
		
		for (JumpInListener l : listeners) {
			animal = (MovableAnimal)l;
			ArrayList<Object>options = animal.determineOptions(gameBoard);
			if (animal instanceof Fox && isMovedFox(tryMoves, animal)) {
				continue;
			}
			for(int i=0; i<options.size();i++) {
				Object option = options.get(i);
				undoRedo.setState(false);
				Move tryMove = (animal instanceof Rabbit) ? new Move(animal.getPosition(), (Point)option, animal) : new Move(animal.getPosition(), (Point[])option, animal);
				tryMoves.add(tryMove);
				isWin = processCommand(tryMove);
				if (isWin) {
					solverMoves = new LinkedList<Move>(tryMoves);
					undoMove();
					return true;
				}
				else {
					if (solverHelper(tryMoves,previousStates)) {
						undoMove();
						return true;
					}
					else {
						tryMoves.pop();
						undoMove();
					}
				}
			}
		}
		previousStates.pop();
		return false;
	}
	
	/**
	 * Getter for the game board
	 * @return - an array of game objects, defined by the level
	 */

	public GameObject[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * Sets the game board
	 * @param gameBoard
	 */
	public void setGameBoard(GameObject[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	/**
	 * Creates a string representation of a point in the game board.
	 * 
	 * @param y - y coordinate of the game board
	 * @param x - x coordinate of the game board
	 * @return - string of the game board
	 */
	public String objectToString(int x, int y) {
		if (isHole(x, y) && gameBoard[y][x] instanceof Rabbit) {
			return gameBoard[y][x].getName() + "H";
		} else if (isHole(x, y)) {
			return "H";
		} else if (!(gameBoard[y][x].getClass().getSimpleName().equals(""))) {
			return gameBoard[y][x].getName();
		}
		return "  ";
	}

	/**
	 * Getter for current level.
	 * @return - current level object
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Checks if a point in the game board is a hole.
	 * @param x - x coordinate of the board
	 * @param y - y coordinate of the board
	 * @return - boolean of if it is a hole
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
	 * Welcome statement for the text based version
	 */
	private void printWelcome() {
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
				move = parser.confirmOption(options, (Rabbit) chosenAnimal, chosenAnimal.displayOptions(gameBoard));
			}

			else if (chosenAnimal.getClass().getSimpleName().equals("Fox")) {
				move = parser.confirmOption(options, (Fox) chosenAnimal, chosenAnimal.displayOptions(gameBoard));
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
	
	/**
	 * Adds listener
	 * @param j - new jump in listener
	 * @return updated listeners
	 */
	public boolean addListener(JumpInListener j) {
		return listeners.add(j);
	}

	/**
	 * Iterates through the game board to see if any of the rabbits
	 * are not in a hole. If one of them isn't, it returns false. 
	 * @return - boolean of if the game is won
	 */
	public boolean checkWin() {
		for (JumpInListener j : getGameObjectListeners()) {
				GameObject g = (GameObject) j;
				if (g instanceof Rabbit) {
					Rabbit r = (Rabbit) g;
					if (r.getStatus() == false) {
						return false;
					}
				}
		}
		return true;
	}

	/**
	 * Processes through a move object to change the locations of a piece
	 * @param move - user inputed move
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
		if (move.getChosenAnimal() instanceof Rabbit) {
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
		
		undoRedo.addMove(event);
		
		// model must be updated first
		for(JumpInListener j : getGameObjectListeners()) {
			if((GameObject) j == move.getChosenAnimal()) {
				j.handleEvent(event);
			}
		}
		
		boolean win = checkWin();
		
		for(JumpInView v : getViewListeners()) {
			v.handleEvent(event);
			if(win) {
				v.handleWin();
			}
		}
		
		return win;
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
	 * Author for all methods below: Aashna Narang
	 */
	
	
	/**
	 * Method to get listeners of type GameObject. Use this instead of separating
	 * listeners array list because listeners can be added at any time
	 * @return JumpIn listeners of type GameObject
	 */
	public ArrayList<JumpInListener> getGameObjectListeners() {
		ArrayList<JumpInListener> list = new ArrayList<JumpInListener>();
		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if ((l instanceof GameObject)) {
				list.add(l);
			}
		}
		return list;
	}
	
	/**
	 * Method to get listeners of type JumpInView. Use this instead of separating
	 * listeners array list because listeners can be added at any time
	 * @return JumpInViews that are listening to the game
	 */
	public ArrayList<JumpInView> getViewListeners() {
		ArrayList<JumpInView> list = new ArrayList<JumpInView>();
		for (int i = 0; i < listeners.size(); i++) {
			JumpInListener l = listeners.get(i);
			if ((l instanceof JumpInView)) {
				JumpInView v = (JumpInView)l;
				list.add(v);
			}
		}
		return list;
	}
	
	/**
	 * @return the undoRedo object
	 */
	public UndoRedo getUndoRedo() {
		return undoRedo;
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
	 * updates the game board and calls a function to remove the view's highlighting if necessary 
	 * @param initial - the initial location of the animal / location of the animal user would like to move
	 * @param finalLocation - location of where the user would like to move the animal
	 * @return true if animal can be moved there, otherwise false
	 */
	public boolean moveAnimal(Point initial, Point finalLocation) {
		GameObject g = gameBoard[initial.y][initial.x];
		ArrayList<Object> options = getAnimalOptions(initial);
		
		if(g instanceof Rabbit && options.contains(finalLocation)) {
			showOptions(initial, false);
			Move move = new Move(initial, finalLocation, g);
			processCommand(new Move(initial, finalLocation, g));
			if (!(solverMoves.poll().equals(move))) {
				solver();
			}
			return true;
		} else if (g instanceof Fox) {
			boolean selectedInOptions = false;
			Point foxLocation[] = (Point[])options.get(0);
			for(Object o : options) {
				foxLocation = (Point[])o;
				for(Point pt : foxLocation) {
					if (pt.equals(finalLocation)) selectedInOptions = true;
				}
				if (selectedInOptions) break;
			}
			if (selectedInOptions) {
				Fox f = (Fox)g;
				showOptions(initial, false);
				Move move = new Move(f.getPosition(), foxLocation, g);
				processCommand(move);
				if (!(solverMoves.poll().equals(move))) {
					solver();
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Call the view listeners to highlight the options of an animal when the user
	 * has selected to move that animal.
	 * @param initialLocation - the coordinate the user has selected
	 * @param show - whether the options will be highlighted or remove highlight
	 * @return true if there are options to highlight/unhighlight, otherwise false
	 */
	public boolean showOptions(Point initialLocation, boolean show) {
		ArrayList<Object> options = getAnimalOptions(initialLocation);
		String selectedAnimalType = selectedAnimalType(initialLocation);
		for (JumpInView v : getViewListeners()) {
				if(show) return (v.highlightOptions(initialLocation, selectedAnimalType, options));
				else return (v.highlight(selectedAnimalType, false, options));
		}
		return false;
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
	 * @return the JumpIn listeners
	 */
	public ArrayList<JumpInListener> getListeners() {
		return listeners;
	}

	/**
	 * Undo a move by getting the last move that was done. Update the board
	 * and update the view
	 * @return True if a move was undone, otherwise false
	 */
	public boolean undoMove() {
		JumpInEvent e = undoRedo.undoMove();
		if(e.isEmpty()) {
			for (JumpInView v : getViewListeners()) {
					v.displayError(1);
			}
			return false;
		}
		if(e.getChosenPiece() instanceof Rabbit) {
			processCommand(new Move(e.getFinalLocation1(), e.getInitialLocation1(), e.getChosenPiece()));
		} else {
			Point[] initialLocation = {e.getFinalLocation1(), e.getFinalLocation2()};
			Point[] finalLocation = {e.getInitialLocation1(), e.getInitialLocation2()};
			processCommand(new Move (initialLocation, finalLocation, e.getChosenPiece()));
		}
		return true;
	}
	
	/**
	 * Redo a move by getting the last JumpInEvent that was "undone" and update the board accordingly
	 * @return true if a move was redone, otherwise false.
	 */
	public boolean redoMove() {
		JumpInEvent e = undoRedo.redoMove();
		if(e.isEmpty()) {
			for (JumpInView v : getViewListeners()) {
					v.displayError(2);
			}
			return false;
		}
		if(e.getChosenPiece() instanceof Rabbit) {
			processCommand(new Move(e.getInitialLocation1(), e.getFinalLocation1(), e.getChosenPiece()));
		} else {
			Point[] initialLocation = {e.getInitialLocation1(), e.getInitialLocation2()};
			Point[] finalLocation = {e.getFinalLocation1(), e.getFinalLocation2()};
			processCommand(new Move (initialLocation, finalLocation, e.getChosenPiece()));
		}
		return true;
	}
	
	/**
	 * Set the state of the undoRedo class. True if currently undoing/redoing moves. 
	 * False if the user is selecting a new object to move.
	 * @param state
	 */
	public void setUndoState(boolean state) {
		undoRedo.setState(state);
	}
	
	/**
	 * Creates a game, the GUI, and the controller which handles user input
	 * @param args 

	 */
	public static void main(String[] args) {
		Play.play(1);
	}


}
