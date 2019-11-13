

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

}
