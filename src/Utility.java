import java.util.function.Function;

public class Utility {
	private final static int NUM_ROWS = 5;
	private final static int NUM_COLUMNS = NUM_ROWS;

	
	private final static Function<Integer, Boolean> checkBounds = (Integer x) -> (x < NUM_ROWS && x > -1);
	
	private final static Function<Integer, Integer> increment = (Integer x) -> x + 1;
	private final static Function<Integer, Integer> decrement = (Integer x) -> x - 1;
	
	public static Function<Integer, Boolean> getCheckBounds() {
		return checkBounds;
	}
	
	public static Function<Integer, Integer> getIncrement() {
		return increment;
	}
	public static Function<Integer, Integer> getDecrement() {
		return decrement;
	}
	public static int getNumRows() {
		return NUM_ROWS;
	}
	public static int getNumColumns() {
		return NUM_COLUMNS;
	}
}
