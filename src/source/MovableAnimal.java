package source;

import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class MovableAnimal extends GameObject implements JumpInListener {

	protected ArrayList<Object> options;

	/**
	 * Constructor MovableAnimal
	 * @param p Point representing the Location of the MovableAnimal.
	 * @param name String representing the name of the animal.
	 */
	public MovableAnimal(Point p, String name) {
		super(p, name);
		options = new ArrayList<Object>();
	}
	/**
	 * Returns the position of the MovableAnimal.
	 */
	public abstract <E> E getPosition();
	
	
	/**
	 * Returns the options of where the MovableAnimal can move.
	 * @param gameBoard 2D-array representing the game board.
	 * @return An ArrayList containing the options of where the MovableAnimal can move.  
	 */
	public abstract ArrayList<Object> determineOptions(GameObject[][] gameBoard);

	/**
	 * Helper method for determineOptions.
	 * @param options An ArrayList containing the options of where the MovableAnimal can move.
	 * @param startingPosition The coordinate that will be moving in a direction to determine options.
	 * @param uniformCoordinate The coordinate that will stay the same during the determination of options.
	 * @param increment The lambda function determining whether the we are determining moves up/left or down/right.
	 * @param offset The lambda function that is applied to the second point of a MovableAnimal whose location consists of 2 points.
	 * @param gameBoard 2D-array representing the game board.
	 * @param direction The direction the animal is moving in. 
	 * @return A boolean indicating whether a valid option has been found.
	 */
	
	protected abstract boolean helperDetermineOptions(ArrayList<Object> options, int startingPosition,
			int uniformCoordinate, Function<Integer, Integer> increment, Function<Integer, Integer> offset,
			GameObject[][] gameBoard, String direction);

	/**
	 * The move logic used to determine if a move is valid.
	 * @param direction The direction the MovableAnimal is moving in.
	 * @param gameBoard 2D-array representing the game board.
	 * @param changingCoordinate The coordinate that is moving in a direction to determine options.
	 * @param uniformCoordinate The coordinate that will stay the same during the determination of options.
	 * @return A boolean indicating whether a move is valid.
	 */
	
	protected boolean moveLogic(String direction, GameObject[][] gameBoard, int changingCoordinate,
			int uniformCoordinate) {

		if (direction.equals("Vertical")) {
			return moveLogicHelper(gameBoard[changingCoordinate][uniformCoordinate]);
		} else if (direction.equals("Horizontal")) {
			return moveLogicHelper(gameBoard[uniformCoordinate][changingCoordinate]);
		}
		return false;
	}

	/**
	 * Helper method for moveLogic.
	 * @param space The space where the MovableAnimal is trying to move in.
	 * @return A boolean indicating whether a move is valid.
	 */
	protected abstract boolean moveLogicHelper(GameObject space);

	/**
	 * Adds option to the options of the MovableAnimal.
	 * @param changingCoordinate The coordinate that is moving in a direction to determine options.
	 * @param uniformCoordinate The coordinate that will stay the same during the determination of options.
	 * @param options An ArrayList containing the options of where the MovableAnimal can move.
	 * @param offset The lambda function that is applied to the second point of a MovableAnimal whose location consists of 2 points.
	 * @param direction The direction the MovableAnimal is moving in.
	 */
	
	protected abstract void addOption(int changingCoordinate, int uniformCoordinate, ArrayList<Object> options,
			Function<Integer, Integer> offset, String direction);

	
	/**
	 * Returns a string representation of the options where the MovableAnimal can move.
	 * @param options An ArrayList containing the options of where the MovableAnimal can move.
	 * @return A string representation of the options where the MovableAnimal can move.
	 */
	public abstract String displayOptions(GameObject[][] options);

}
