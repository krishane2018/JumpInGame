import java.awt.Point;
import java.util.ArrayList;
import java.util.function.Function;
/**
 * 
 * @author Aashna Narang
 * 
 */
public class Rabbit extends MovableAnimal{
	// If the rabbit is inside a rabbit hole or not
	private boolean status; 
	private boolean isJump; // Can the rabbit make a jump move
	
	/**
	 * Constructor to create a rabbit game piece
	 * @param p The coordinates of the rabbit
	 * @param name The name of the rabbit. Two character string starting with R then a number (R1,R2, etc)
	 */
	public Rabbit(Point p, String name) {
		super(p,name);
		status = false;
	}
	
	/**
	 * Set the status of the rabbit
	 * @param status whether or not the rabbit is in a rabbit hole or not
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Get the current status of the rabbit
	 * @return whether the rabbit is in a hole or not
	 */
	public boolean getStatus() {
		return this.status;
	}

	@Override
	/**
	 * Change the coordinates of the rabbit and check if it is in a rabbit hole when a JumpInEvent happens
	 * @param The JumpInEvent that contains the chosen piece, new coordinates, coordinates of the rabbit holes
	 */
	public void handleEvent(JumpInEvent e) {
		Rabbit r = (Rabbit)e.getChosenPiece();
		if(r.equals(this)) {
			super.setCoordinate(e.getCoordinate1());
			for(Point p : e.getHoles()) {		
				if(p.equals(super.getCoordinate())) {
					this.status = true;
					break;
				}
				else {
					this.status = false;
				}
			}	
		}
	}
	
	/**
	 * Checks if a given object is equivalent to the current object.
	 * @param An object that the user would like to compare
	 * @return True if the objects have the same instance variables or are the same object
	 */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		if(this == o) return true;
		
		Rabbit r = (Rabbit) o;
		return (super.getCoordinate() == r.getCoordinate() & this.status == r.getStatus() & super.getName() == r.getName());
	}

	@Override
	public ArrayList determineOptions(GameObject[][] gameBoard) {
		
		ArrayList<Point> options = new ArrayList<Point>();
		int x = this.getX1();
		int y = this.getY1();
		
		helperdetermineOptions(options, x-1,
				y,  Utility.getCheckBounds(),
				Utility.getDecrement(), Utility.getIncrement(),
				gameBoard, "Horizontal");
		
		this.isJump = false;
		
		helperdetermineOptions(options, x+1,
				y,  Utility.getCheckBounds(),
				Utility.getIncrement(), Utility.getDecrement(),
				gameBoard, "Horizontal");
		
		this.isJump = false;

		
		helperdetermineOptions(options, y-1,
				x,  Utility.getCheckBounds(),
				Utility.getDecrement(), Utility.getIncrement(),
				gameBoard, "Vertical");
		
		this.isJump = false;

		
		helperdetermineOptions(options, y+1,
				x,  Utility.getCheckBounds(),
				Utility.getIncrement(), Utility.getDecrement(),
				gameBoard, "Vertical");
		
		return options;
	}

	@Override
	protected int forLoopBody(ArrayList options, int changingCoordinate, int uniformCoordinate,
			Function<Integer, Integer> offset, GameObject[][] gameBoard, String direction) {
		
		if (moveLogic(direction, gameBoard, changingCoordinate, uniformCoordinate)) {
			isJump = true;
		}
		
		else {
			if (isJump==true) {
				addOption(changingCoordinate, uniformCoordinate, options, offset, direction);
			}
			return Utility.getNumRows()+1;
		}
		
		return changingCoordinate;
		
	}

	@Override
	protected boolean moveLogicHelper(GameObject space) {
		return !(space.getName().equals(""));
		
	}

	@Override
	protected void addOption(int changingCoordinate, int uniformCoordinate, ArrayList options,
			Function<Integer, Integer> offset, String direction) {
		if (direction.equals("Vertical")) {
			options.add(new Point (uniformCoordinate, changingCoordinate));
		}
		else if (direction.equals("Horizontal")) {
			options.add(new Point (changingCoordinate, uniformCoordinate));
		}
		
	}
	
	
}
