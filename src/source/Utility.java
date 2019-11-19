package source;

import java.awt.Point;
import java.util.function.Function;

public class Utility {

	private final static Function<Integer, Integer> increment = (Integer x) -> x + 1;
	private final static Function<Integer, Integer> decrement = (Integer x) -> x - 1;

	public static Function<Integer, Integer> getIncrement() {
		return increment;
	}

	public static Function<Integer, Integer> getDecrement() {
		return decrement;
	}

	public static boolean checkBounds(int changingCoordinate, int upperBound) {
		return (changingCoordinate < upperBound && changingCoordinate > -1);
	}
	
	public static boolean checkValidPoint(Point p) {
		int lowerBound = 0;
		int upperBound = 4;
		if (p.x<lowerBound || p.x>upperBound || p.y<lowerBound || p.y>upperBound) {
			return false;
		}
		return true;
	}
}
