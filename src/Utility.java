import java.util.function.Function;

public class Utility {
	private final static Function<Integer, Boolean> checkUpperBounds = (Integer x) -> x < 5;
	private final static Function<Integer, Boolean> checkLowerBounds = (Integer x) -> x > -1;
	
	private final static Function<Integer, Integer> increment = (Integer x) -> x + 1;
	private final static Function<Integer, Integer> decrement = (Integer x) -> x - 1;
	
	public static Function<Integer, Boolean> getCheckUpperBounds() {
		return checkUpperBounds;
	}
	public static Function<Integer, Boolean> getCheckLowerBounds() {
		return checkLowerBounds;
	}
	public static Function<Integer, Integer> getIncrement() {
		return increment;
	}
	public static Function<Integer, Integer> getDecrement() {
		return decrement;
	}
}
