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
}
