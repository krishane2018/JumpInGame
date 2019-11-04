import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class MovableAnimal extends GameObject implements JumpInListener {

	protected ArrayList<Object> options;

	public MovableAnimal(Point p, String name) {
		super(p, name);
		options = new ArrayList<Object>();
		// TODO Auto-generated constructor stub
	}

	public abstract ArrayList<Object> determineOptions(GameObject[][] gameBoard);

	protected abstract boolean helperDetermineOptions(ArrayList<Object> options, int startingPosition,
			int uniformCoordinate, Function<Integer, Integer> increment, Function<Integer, Integer> offset,
			GameObject[][] gameBoard, String direction);

	protected boolean moveLogic(String direction, GameObject[][] gameBoard, int changingCoordinate,
			int uniformCoordinate) {

		if (direction.equals("Vertical")) {
			return moveLogicHelper(gameBoard[changingCoordinate][uniformCoordinate]);
		} else if (direction.equals("Horizontal")) {
			return moveLogicHelper(gameBoard[uniformCoordinate][changingCoordinate]);
		}
		return false;
	}

	protected abstract boolean moveLogicHelper(GameObject space);

	protected abstract void addOption(int changingCoordinate, int uniformCoordinate, ArrayList<Object> options,
			Function<Integer, Integer> offset, String direction);

	public abstract String displayOptions(GameObject[][] options);

}
