
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 
 * @author Aashna Narang
 * 
 */
public class Fox extends MovableAnimal {
	private Point coordinate2;
	private String direction;

	/**
	 * Fox constructor
	 * 
	 * @param p1        Fox takes up two spaces, coordinate of first
	 * @param p2        Fox takes up two spaces, coordinate of the second
	 * @param name      Name of object. Two character string starting with F and a
	 *                  number (F1,F2, etc)
	 * @param direction If the fox can move vertically or horizontally
	 */
	public Fox(Point p1, Point p2, String name, String direction) {
		
		super(p1, name);
		if (Utility.checkValidPoint(p2)) {
			this.coordinate2 = p2; 
		}
		else {
			throw new IllegalArgumentException("Points must be between (0,0) and (4,4)");
		}
		this.direction = direction;
		Point[] points = correctPointOrdering(new Point[] {p1, p2});
		p1.setLocation(points[0]);
		p2.setLocation(points[1]);

	}

	public Point[] getCoordinates() {
		return new Point[] {this.getCoordinate(), this.getCoordinate2()};
	}
	
	/**
	 * Get the second coordinate of the fox
	 * 
	 * @return a Point with the x and y coordinates
	 */
	public Point getCoordinate2() {
		return coordinate2;
	}

	/**
	 * Set the second coordinate of the fox
	 * 
	 * @param coordinate2 a Point with the x and y coordinates
	 */
	public void setCoordinate2(Point coordinate2) {
		this.coordinate2 = coordinate2;
	}

	/**
	 * Get the x value of the second coordinate
	 * 
	 * @return an integer value of the x coordinate
	 */
	public int getX2() {
		return (int) this.coordinate2.getX();
	}

	/**
	 * Get the y value of the second coordinate
	 * 
	 * @return an integer value of the y coordinate
	 */
	public int getY2() {
		return (int) this.coordinate2.getY();
	}

	/**
	 * Get the direction the fox can move in
	 * 
	 * @return A string, either "vertical" or "horizontal"
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Set the direction of the fox
	 * 
	 * @param direction Either "vertical" or "horizontal"
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	private Point[] correctPointOrdering(Point[] points) {
		
		Point p1 = points[0];
		Point p2 = points[1];

		if (direction.equals("Vertical")) {
			double y1 = p1.getY();
			double y2 = p2.getY();
			if (y2 < y1) {
				points[0] = p2;
				points[1] = p1;
			}
		} else if (direction.equals("Horizontal")) {
			double x1 = p1.getX();
			double x2 = p2.getX();
			if (x2 < x1) {
				points[0] = p2;
				points[1] = p1;
			}
		}
		return points;
	}

	@Override
	/**
	 * Handle an event created from the JumpIn model
	 * 
	 * @param A JumpInEvent that may alter the fox object
	 */
	public void handleEvent(JumpInEvent e) {
		Fox f = (Fox) e.getChosenPiece();
		if (f.equals(this)) {
			Point[] points = correctPointOrdering(new Point[] {e.getFinalLocation1(), e.getFinalLocation2()});
			System.out.println(Arrays.toString(points));
			super.setCoordinate(points[0]);
			setCoordinate2(points[1]);
		}
	}

	@Override
	public ArrayList<Object> determineOptions(GameObject[][] gameBoard) {
		int startingPosition1, startingPosition2, uniformCoordinate;
		startingPosition1 = startingPosition2 = uniformCoordinate = -1;

		options.clear();
		
		if (direction.equals("Horizontal")) {

			startingPosition1 = this.getX1() - 1;
			startingPosition2 = this.getX2() + 1;
			uniformCoordinate = this.getY1();

		}

		else if (direction.equals("Vertical")) {

			startingPosition1 = this.getY1() - 1;
			startingPosition2 = this.getY2() + 1;
			uniformCoordinate = this.getX1();

		}

		helperDetermineOptions(options, startingPosition1, uniformCoordinate, Utility.getDecrement(),
				Utility.getIncrement(), gameBoard, this.direction);

		helperDetermineOptions(options, startingPosition2, uniformCoordinate, Utility.getIncrement(),
				Utility.getDecrement(), gameBoard, this.direction);

		return options;
	}

	protected boolean helperDetermineOptions(ArrayList<Object> options, int changingCoordinate, int uniformCoordinate,
			Function<Integer, Integer> increment, Function<Integer, Integer> offset, GameObject[][] gameBoard,
			String direction) {

		if (!Utility.checkBounds(changingCoordinate, gameBoard.length)) {
			return false;
		}

		if (moveLogic(direction, gameBoard, changingCoordinate, uniformCoordinate)) {
			addOption(changingCoordinate, uniformCoordinate, options, offset, direction);
			return helperDetermineOptions(options, increment.apply(changingCoordinate), uniformCoordinate, increment,
					offset, gameBoard, direction);
		}

		else {
			return false;
		}

	}

	protected boolean moveLogicHelper(GameObject space) {
		List<Point> list = Arrays.asList(LevelSelector.getHoles());
		return space.getName().equals("") && !list.contains(space.getCoordinate());
	}

	protected void addOption(int changingCoordinate, int uniformCoordinate, ArrayList<Object> options,
			Function<Integer, Integer> offset, String direction) {

		Point[] tempArray = new Point[0];

		if (direction.equals("Vertical")) {
			tempArray = new Point[] { new Point(uniformCoordinate, changingCoordinate),
					new Point(uniformCoordinate, offset.apply(changingCoordinate)) };
		} else if (direction.equals("Horizontal")) {
			tempArray = new Point[] { new Point(changingCoordinate, uniformCoordinate),
					new Point(offset.apply(changingCoordinate), uniformCoordinate) };
		}

		options.add(correctPointOrdering(tempArray));
	}

	@Override
	public String displayOptions(GameObject[][] gameBoard) {
		if (options.isEmpty()) {
			this.determineOptions(gameBoard);
		}
		String output = "";
		int counter = 1;

		if (options.isEmpty()) {
			output = "No options available.";
		} else {
			for (Object element : options) {
				Point[] points = (Point[]) (element);
				output += counter + " {";
				for (Point point : points) {
					output += "(" + point.getX() + "," + point.getY() + ") ";
				}
				counter++;
				output = output.trim();
				output += "}\n";

			}
		}
		return output;
	}

}
