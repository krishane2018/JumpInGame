/**
*@author Krishan Easparan
**/
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {
	
	private Scanner input;
	
	
	public Parser() {
		input = new Scanner(System.in);
	}
	
	public GameObject getAnimal(ArrayList<JumpInListener> animalOptions) {
		System.out.println("Enter one of the following 2 character strings to choose which animal"
				+ "to move:\n ");
		for (JumpInListener animal: animalOptions) {
			System.out.print(((GameObject)animal).getName()+" ");
		}
		
		String command = input.nextLine();
		
		for (JumpInListener animal: animalOptions) {
			GameObject tempAnimal = (GameObject)animal;
			if (tempAnimal.getName().equalsIgnoreCase(command)){
				return tempAnimal;
			}
		}
		
		System.out.println("Invalid input.");
		return (getAnimal(animalOptions));
		
		
	}

	public Move confirmOption(ArrayList<Point> options, Rabbit chosenRabbit, String displayOptions) {
		HashMap<Integer, Point> userOptions = new HashMap<Integer, Point>();
		for (int i=1;i<=options.size();i++) {
			userOptions.put(i, userOptions.get(i-1));
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			input.close();
			return new Move(new Point(userOptions.get(selectedOption))
					, chosenRabbit.getCoordinate(), chosenRabbit);
		}
		else {
			System.out.println("Invalid Input");
			return confirmOption(options, chosenRabbit, displayOptions);
		}
	}
	
	
	public Move confirmOption(ArrayList<Point[]> options, Fox chosenFox, String displayOptions) {
		HashMap<Integer, Point[]> userOptions = new HashMap<Integer, Point[]>();
		for (int i=1;i<=options.size();i++) {
			userOptions.put(i, userOptions.get(i-1));
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			input.close();
			Point[] chosenDestination = userOptions.get(selectedOption);
			return new Move(chosenFox.getCoordinate(), chosenFox.getCoordinate(), chosenDestination[0], chosenDestination[1], chosenFox);
		}
		else {
			System.out.println("Invalid input.");
			return confirmOption(options, chosenFox, displayOptions);
		}
	}
	
}
