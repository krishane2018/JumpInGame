

/**
*@author Krishan Easparan
**/

import java.awt.Point;

public class Move {
	private Point initialLocation;
	private Point finalLocation;
	private Point initialLocation2;
	private Point finalLocation2;
	private GameObject chosenAnimal;
	private boolean noMove;

	/**
	 * Constructor to make Move object for GameObject whose location consists of 1 point.
	 * @param initialLocation Initial Location of GameObject.
	 * @param finalLocation Initial Location of GameObject.
	 * @param chosenAnimal GameObject that is being moved.
	 */
	public Move(Point initialLocation, Point finalLocation, GameObject chosenAnimal) {
		if (Utility.checkValidPoint(initialLocation)&&Utility.checkValidPoint(finalLocation)) {
			this.initialLocation = initialLocation;
			this.finalLocation = finalLocation;
			this.initialLocation2=new Point(); 
			this.finalLocation2 = new Point();
		}
		else {
			throw new IllegalArgumentException("Points must be between (0,0) and (4,4)");
		}
		this.chosenAnimal = chosenAnimal;
		noMove = false;
	}

	/**
	 * Constructor to make Move object for GameObject whose location consists of 2 points.
	 * @param initialLocation Initial Location of GameObject.
	 * @param finalLocation Final Location of GameObject.
	 * @param chosenAnimal GameObject that is being moved.
	 */
	public Move(Point[] initialLocation, Point finalLocation[],	GameObject chosenAnimal) {
		
		if (Utility.checkValidPoint(initialLocation[0])&&Utility.checkValidPoint(initialLocation[1])
				&&Utility.checkValidPoint(finalLocation[0])&&Utility.checkValidPoint(finalLocation[1])) {
			this.initialLocation = initialLocation[0];
			this.finalLocation = finalLocation[0];
			this.initialLocation2 = initialLocation[1];
			this.finalLocation2 = finalLocation[1];
		}
		else {
			throw new IllegalArgumentException("Points must be between (0,0) and (4,4)");
		}
		this.chosenAnimal = chosenAnimal;
		noMove = false;
	}

	/**
	 * Default constructor for Move.
	 */
	public Move() {
		noMove = true;
	}

	/**
	 * Gets the second point of the initial location.
	 * @return The second Point of the initial location. 
	 */
	public Point getInitialLocation2() {
		return initialLocation2;
	}

	/**
	 * Sets the second point of the initial location.
	 * @param initialLocation2 The point which the second point of the initial location will be set to. 
	 */
	
	public void setInitialLocation2(Point initialLocation2) {
		this.initialLocation2 = initialLocation2;
	}
	
	/**
	 * Gets the second point of the final location.
	 * @return The second Point of the final location. 
	 */
	
	public Point getFinalLocation2() {
		return finalLocation2;
	}

	/**
	 * Sets the second point of the final location.
	 * @param finalLocation2 The point which the second point of the final location will be set to. 
	 */
	
	public void setFinalLocation2(Point finalLocation2) {
		this.finalLocation2 = finalLocation2;
	}

	/**
	 * Gets the first point of the initial location.
	 * @return The first Point of the initial location. 
	 */
	
	public Point getInitialLocation() {
		return initialLocation;
	}

	/**
	 * Sets the first point of the initial location.
	 * @param initialLocation The point which the first point of the initial location will be set to. 
	 */
	
	public void setInitialLocation(Point initialLocation) {
		this.initialLocation = initialLocation;
	}

	/**
	 * Gets the first point of the final location.
	 * @return The first Point of the final location. 
	 */
	
	public Point getFinalLocation() {
		return finalLocation;
	}

	/**
	 * Sets the first point of the final location.
	 * @param finalLocation The point which the first point of the final location will be set to. 
	 */
	
	public void setFinalLocation(Point finalLocation) {
		this.finalLocation = finalLocation;
	}

	/**
	 * Gets the Game Object that is moving.
	 * @return The Game Object that is moving.
	 */
	
	public GameObject getChosenAnimal() {
		return chosenAnimal;
	}

	/**
	 * Sets the Game Object that is moving.
	 * @param chosenAnimal The Game Object that is moving.
	 */
	
	public void setChosenAnimal(GameObject chosenAnimal) {
		this.chosenAnimal = chosenAnimal;
	}

	/**
	 * Returns whether there is a move or not.
	 * @return A boolean indicating whether there is a move or not.
	 */
	
	public boolean isNoMove() {
		return noMove;
	}

	/**
	 * Returns a string representation of the Move Object.
	 * @return A string representation of the Move Object.
	 */
	public String toString() {
		if (initialLocation2==null&&finalLocation2==null) {
			return "Animal: "+chosenAnimal.getName() + pointToString();
		}
		else {
			return "Animal: "+chosenAnimal.getName()+ pointToString();
		}
	}
	/**
	 * Returns a string representation of the initial and final location of the Move Object.
	 * @return A string representation of the initial and final location of the Move Object.
	 */
	public String pointToString() {
		if (chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
			return  " Initial Location: (" + initialLocation.x + ","+ initialLocation.y +")"+   ", to Final Location: "
					+ "(" + finalLocation.x + ","+ finalLocation.y +")";
		}
		else {
			return " Initial Location: "+ "(" + initialLocation.x + ","+ initialLocation.y +")"
					+",   Initial Location2: " + "(" + initialLocation2.x + ","+ initialLocation2.y +")" + 
					",   to Final Location: " + "(" + finalLocation.x + ","+ finalLocation.y +")" + 
					",   Final Location2: " + "(" + finalLocation2.x + ","+ finalLocation2.y +")";
		}
	}
	
	/**
	 * Determines whether an object is equal to a Move object.
	 * @param Object being compared to Move object.
	 * @return A boolean indicating whether an object is equal to a Move object.
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		if (this == o)
			return true;

		Move m = (Move) o;
		return (this.initialLocation.equals(m.getInitialLocation())&&
				this.initialLocation2.equals(m.getInitialLocation2())&&
				this.finalLocation.equals(m.getFinalLocation())&&
				this.finalLocation2.equals(m.getFinalLocation2())&&
				this.chosenAnimal.equals(m.getChosenAnimal())&&
				this.noMove==m.noMove);
	}
	
	/**
	 * Gets the type of the GameObject.
	 * @return A String representing the type of the GameObject.
	 */
	public String getNameType() {
		if(chosenAnimal.getClass().getSimpleName().equals("Rabbit")) {
			Rabbit r = (Rabbit) chosenAnimal;
			return r.getRabbitColour();
		} else {
			Fox f = (Fox) chosenAnimal;
			return f.getDirection();
		}
	}
}
