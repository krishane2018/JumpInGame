package source;

/**
*@author Krishan Easparan
**/

import java.awt.Point;

import test.Utility;

public class Move {
	private Point initialLocation;
	private Point finalLocation;
	private Point initialLocation2;
	private Point finalLocation2;
	private GameObject chosenAnimal;
	private boolean noMove;

	public Move(Point initialLocation, Point finalLocation, GameObject chosenAnimal) {
		this(initialLocation, new Point(), finalLocation, new Point(), chosenAnimal);
	}

	public Move(Point initialLocation, Point initialLocation2, Point finalLocation, Point finalLocation2,
			GameObject chosenAnimal) {
		if (Utility.checkValidPoint(initialLocation)&&Utility.checkValidPoint(initialLocation2)
				&&Utility.checkValidPoint(finalLocation)&&Utility.checkValidPoint(finalLocation2)) {
			this.initialLocation = initialLocation;
			this.finalLocation = finalLocation;
			this.initialLocation2 = initialLocation2;
			this.finalLocation2 = finalLocation2;
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
