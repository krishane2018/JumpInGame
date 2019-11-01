import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
	 * @param p1 Fox takes up two spaces, coordinate of first 
	 * @param p2 Fox takes up two spaces, coordinate of the second	
	 * @param name Name of object. Two character string starting with F and a number (F1,F2, etc)
	 * @param direction If the fox can move vertically or horizontally 
	 */
	public Fox(Point p1, Point p2, String name, String direction) {
		super(p1, name);
		this.coordinate2 = p2; 
		this.direction = direction;
		correctPointOrdering();
		
	}

	/**
	 * Get the second coordinate of the fox
	 * @return a Point with the x and y coordinates
	 */
	public Point getCoordinate2() {
		return coordinate2;
	}

	/**
	 * Set the second coordinate of the fox
	 * @param coordinate2 a Point with the x and y coordinates
	 */
	public void setCoordinate2(Point coordinate2) {
		this.coordinate2 = coordinate2;
	}

	/**
	 * Get the x value of the second coordinate
	 * @return an integer value of the x coordinate
	 */
	public int getX2() {
		return (int) this.coordinate2.getX();
	}
	
	/**
	 * Get the y value of the second coordinate
	 * @return an integer value of the y coordinate
	 */
	public int getY2() {
		return (int) this.coordinate2.getY();
	}
	
	/**
	 * Get the direction the fox can move in
	 * @return A string, either "vertical" or "horizontal"
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Set the direction of the fox
	 * @param direction Either "vertical" or "horizontal"
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	private void correctPointOrdering() {
		Point p1 = this.getCoordinate();
		Point p2 = this.getCoordinate2();
		
		if (direction.equals("Vertical")) {
			double y1 = p1.getY();
			double y2 = p2.getY();
			if (y2<y1) {
				p1.setLocation(p1.getX(), y2);
				p2.setLocation(p2.getX(), y1);
			}
		}
		else if(direction.equals("Horizontal")) {
			double x1 = p1.getX();
			double x2 = p2.getX();
			if (x2<x1) {
				p1.setLocation(x2, p1.getY());
				p2.setLocation(x1, p2.getY());
			} 
		}
	}
	
	@Override
	/**
	 * Handle an event created from the JumpIn model
	 * @param A JumpInEvent that may alter the fox object
	 */
	public void handleEvent(JumpInEvent e) {
		Fox f = (Fox)e.getChosenPiece();
		if(f.equals(this)) {
			super.setCoordinate(e.getCoordinate1());
			setCoordinate2(e.getCoordinate2());
			correctPointOrdering();
		}
	}

	@Override
	public ArrayList determineOptions(GameObject[][] gameBoard) {
		ArrayList<Point[]> options = new ArrayList<Point[]>();
		int startingPosition1=-1;
		int startingPosition2=-1;
		int uniformCoordinate=-1;
		
		
		
		if (direction.equals("Horizontal")) {
			
			startingPosition1 = this.getX1() - 1 ;
			startingPosition2 = this.getX2() + 1 ;
			uniformCoordinate = this.getY1();
			
			
		}
		
		else if (direction.equals("Vertical")) {
			
			startingPosition1 = this.getY1() - 1;
			startingPosition2 = this.getY2() + 1;
			uniformCoordinate = this.getX1();
			
		}
		
		helperdetermineOptions(options, startingPosition1,
				uniformCoordinate,  Utility.getCheckBounds(),
				Utility.getDecrement(), Utility.getIncrement(),
				gameBoard, this.direction);
		
		helperdetermineOptions(options, startingPosition2,
				uniformCoordinate,  Utility.getCheckBounds(),
				Utility.getIncrement(), Utility.getDecrement(),
				gameBoard, this.direction);
		
		return options;
	}

	
	@Override
	protected int forLoopBody(ArrayList options, int changingCoordinate,
			int uniformCoordinate, Function<Integer, Integer> offset, 
			GameObject[][] gameBoard, String direction) {
		
		if (moveLogic(direction, gameBoard, changingCoordinate, uniformCoordinate)) {
			addOption(changingCoordinate, uniformCoordinate, options, offset, direction);
		}
		else {
			return Utility.getNumColumns()+1;
		}
		
		return changingCoordinate;
	}
	
	protected boolean moveLogicHelper(GameObject space) {
		return space.getName().equals("");
	}
	
	
	protected void addOption(int changingCoordinate, int uniformCoordinate, ArrayList options, 
			Function<Integer, Integer> offset, String direction) {
		
		if (direction.equals("Vertical")) {
			Point[] tempArray = {new Point (uniformCoordinate, changingCoordinate),
					new Point (uniformCoordinate, offset.apply(changingCoordinate))};
			options.add(tempArray);
		}
		else if (direction.equals("Horizontal")) {
			Point[] tempArray = { new Point (changingCoordinate, uniformCoordinate), 
					new Point (offset.apply(changingCoordinate), uniformCoordinate)};
			options.add(tempArray);
		}
	}

	
	

}
