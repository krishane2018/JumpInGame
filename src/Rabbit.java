import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * 
 * @author Aashna Narang
 * 
 */
public class Rabbit extends MovableAnimal {
	// If the rabbit is inside a rabbit hole or not
	private boolean status;

	/**
	 * Constructor to create a rabbit game piece
	 * 
	 * @param p    The coordinates of the rabbit
	 * @param name The name of the rabbit. Two character string starting with R then
	 *             a number (R1,R2, etc)
	 */
	public Rabbit(Point p, String name) {
		super(p, name);
		status = false;
	}

	/**
	 * Set the status of the rabbit
	 * 
	 * @param status whether or not the rabbit is in a rabbit hole or not
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Get the current status of the rabbit
	 * 
	 * @return whether the rabbit is in a hole or not
	 */
	public boolean getStatus() {
		return this.status;
	}

	@Override
	/**
	 * Change the coordinates of the rabbit and check if it is in a rabbit hole when
	 * a JumpInEvent happens
	 * 
	 * @param The JumpInEvent that contains the chosen piece, new coordinates,
	 *            coordinates of the rabbit holes
	 */
	public void handleEvent(JumpInEvent e) {
		Rabbit r = (Rabbit) e.getChosenPiece();
		if (r.equals(this)) {
			super.setCoordinate(e.getFinalLocation1());
			for (Point p : e.getHoles()) {
				if (p.equals(super.getCoordinate())) {
					this.status = true;
					break;
				} else {
					this.status = false;
				}
			}
		}
	}

	/**
	 * Checks if a given object is equivalent to the current object.
	 * 
	 * @param An object that the user would like to compare
	 * @return True if the objects have the same instance variables or are the same
	 *         object
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		if (this == o)
			return true;

		Rabbit r = (Rabbit) o;
		return (super.getCoordinate() == r.getCoordinate() & this.status == r.getStatus()
				& super.getName() == r.getName());
	}

	@Override
	public String displayOptions(ArrayList<Object> options) {
		String output = "";
		int counter = 1;
		if (options.isEmpty()) {
			output = "No options available.";
		} else {
			for (Object element : options) {
				Point point = (Point) element;
				output += counter + " (" + point.getX() + "," + point.getY() + ")\n";
				counter++;
			}
		}
		return output;
	}

	@Override
	public ArrayList<Object> determineOptions(GameObject[][] gameBoard) {

		ArrayList<Object> options = new ArrayList<Object>();
		int x = this.getX1();
		int y = this.getY1();

		helperDetermineOptions(options, x - 1, y, Utility.getDecrement(), Utility.getIncrement(), gameBoard,
				"Horizontal");

		helperDetermineOptions(options, x + 1, y, Utility.getIncrement(), Utility.getDecrement(), gameBoard,
				"Horizontal");

		helperDetermineOptions(options, y - 1, x, Utility.getDecrement(), Utility.getIncrement(), gameBoard,
				"Vertical");

		helperDetermineOptions(options, y + 1, x, Utility.getIncrement(), Utility.getDecrement(), gameBoard,
				"Vertical");

		return options;
	}

	protected boolean helperDetermineOptions(ArrayList<Object> options, int changingCoordinate, int uniformCoordinate,
			Function<Integer, Integer> increment, Function<Integer, Integer> offset, GameObject[][] gameBoard,
			String direction) {

		if (!Utility.checkBounds(changingCoordinate, gameBoard.length)) {
			return false;
		}

		if (moveLogic(direction, gameBoard, changingCoordinate, uniformCoordinate)) {
			boolean canJump = helperDetermineOptions(options, increment.apply(changingCoordinate), uniformCoordinate,
					increment, offset, gameBoard, direction);
			if (canJump == true) {
				addOption(increment.apply(changingCoordinate), uniformCoordinate, options, offset, direction);
			}
			return false;
		}

		else {
			return true;
		}

	}

	@Override
	protected boolean moveLogicHelper(GameObject space) {
		return !(space.getName().equals(""));

	}

	@Override
	protected void addOption(int changingCoordinate, int uniformCoordinate, ArrayList<Object> options,
			Function<Integer, Integer> offset, String direction) {
		if (direction.equals("Vertical")) {
			options.add(new Point(uniformCoordinate, changingCoordinate));
		} else if (direction.equals("Horizontal")) {
			options.add(new Point(changingCoordinate, uniformCoordinate));
		}

	}

}
