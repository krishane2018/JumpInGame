import java.awt.Point;
/**
 * 
 * @author Aashna Narang
 * 
 */
public class Rabbit extends GameObject implements JumpInListener{
	// If the rabbit is inside a rabbit hole or not
	private boolean status; 
	
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
	
	
}
