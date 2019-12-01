package source;

import java.awt.Point;
import java.util.function.Function;

public class Utility {

	private final static Function<Integer, Integer> increment = (Integer x) -> x + 1;
	private final static Function<Integer, Integer> decrement = (Integer x) -> x - 1;

	/**
	 * Returns an increment lambda function.
	 * @return An increment lambda function.
	 */
	
	public static Function<Integer, Integer> getIncrement() {
		return increment;
	}

	/**
	 * Returns an decrement lambda function.
	 * @return An decrement lambda function.
	 */
	
	public static Function<Integer, Integer> getDecrement() {
		return decrement;
	}

	/**
	 * Checks whether a coordinate is within the declared boundaries.
	 * @param changingCoordinate Coordinate that is being checked that it is valid.
	 * @param upperBound The upper bound value that the coordinate cannot exceed.
	 * @return A boolean indicating whether the coordinate is valid.
	 */
	
	public static boolean checkBounds(int changingCoordinate, int upperBound) {
		return (changingCoordinate < upperBound && changingCoordinate > -1);
	}
	
	/**
	 * Checks if a point is within the boundaries of the game.
	 * @param p The Point being checked.
	 * @return A boolean indicating whether the point is valid or not.
	 */
	
	public static boolean checkValidPoint(Point p) {
		int lowerBound = 0;
		int upperBound = 4;
		if (p.x<lowerBound || p.x>upperBound || p.y<lowerBound || p.y>upperBound) {
			return false;
		}
		return true;
	}
}
