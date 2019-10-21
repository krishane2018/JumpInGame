/**
*@author Krishan Easparan
**/

import java.awt.Point;

public class Move {
	private Point initialLocation;
	private Point finalLocation;
	private GameObject chosenAnimal;
	
	public Move (Point initialLocation, Point finalLocation, GameObject chosenAnimal) {
		this.initialLocation=initialLocation;
		this.finalLocation=finalLocation;
		this.chosenAnimal=chosenAnimal;
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
}
