import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class MovableAnimal extends GameObject {

	public MovableAnimal(Point p, String name) {
		super(p, name);
		// TODO Auto-generated constructor stub
	}

	public abstract void handleEvent(JumpInEvent e);

	public abstract ArrayList determineOptions(GameObject[][] gameBoard);
	
	private void helperdetermineOptions(ArrayList<Point[]> options, int startingPosition,
			int uniformCoordinate,  Function<Integer, Boolean> checkBounds,
			Function<Integer, Integer> increment, Function<Integer, Integer> offset,
			GameObject[][] gameBoard, String direction) {
		
		for (int i = startingPosition; checkBounds.apply(i); i=increment.apply(i)) {
			if (isSpaceAvailable(direction, gameBoard, i, uniformCoordinate)) {
				addOption(i, uniformCoordinate, options, offset, direction);
			}
			else {
				break;
			}
		}
		
	}
	
	private boolean isSpaceAvailable(String direction, GameObject[][] gameBoard, 
			int changingCoordinate, int uniformCoordinate) {
		
		if (direction.equals("Vertical")) {
			return moveLogic(gameBoard[changingCoordinate][uniformCoordinate]);
		}
		else if (direction.equals("Horizontal")) {
			return moveLogic(gameBoard[uniformCoordinate][changingCoordinate]);
		}
		return false;
	}
	
	protected abstract boolean moveLogic(GameObject space);
	
	protected abstract void addOption(int changingCoordinate, int uniformCoordinate, ArrayList options, 
			Function<Integer, Integer> offset, String direction);
	
}
