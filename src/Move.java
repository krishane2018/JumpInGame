

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

	public Move() {
		noMove = true;
	}

	public Point getInitialLocation2() {
		return initialLocation2;
	}

	public void setInitialLocation2(Point initialLocation2) {
		this.initialLocation2 = initialLocation2;
	}

	public Point getFinalLocation2() {
		return finalLocation2;
	}

	public void setFinalLocation2(Point finalLocation2) {
		this.finalLocation2 = finalLocation2;
	}

	public Point getInitialLocation() {
		return initialLocation;
	}

	public void setInitialLocation(Point initialLocation) {
		this.initialLocation = initialLocation;
	}

	public Point getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(Point finalLocation) {
		this.finalLocation = finalLocation;
	}

	public GameObject getChosenAnimal() {
		return chosenAnimal;
	}

	public void setChosenAnimal(GameObject chosenAnimal) {
		this.chosenAnimal = chosenAnimal;
	}

	public boolean isNoMove() {
		return noMove;
	}

	
	public String toString() {
		if (initialLocation2==null&&finalLocation2==null) {
			return "Animal: "+chosenAnimal.getName()+", Initial Location: "+initialLocation+", Final Location: "
					+finalLocation;
		}
		else {
			return "Animal: "+chosenAnimal.getName()+", Initial Location: "+initialLocation
					+", Initial Location2: "+initialLocation2+", Final Location: "
					+finalLocation+", Final Location2: "+finalLocation2;
		}
	}
	
	
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
}
