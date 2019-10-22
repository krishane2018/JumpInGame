
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

	/**
	 * Determines the animal the user selected.
	 * 
	 * @author Krishan Easparan
	 * @param animalOptions An ArrayList containing all the animals the user can
	 *                      chose.
	 * @return A GameObject representing the animal the user has chosen.
	 */

	public GameObject getAnimal(ArrayList<JumpInListener> animalOptions) {
		System.out.println("Enter one of the following 2 character strings to choose which animal" + "to move:\n ");
		for (JumpInListener animal : animalOptions) {
			System.out.print(((GameObject) animal).getName() + " ");
		}
		System.out.println("");

		String command =  "R1"; //input.nextLine();

		for (JumpInListener animal : animalOptions) {
			GameObject tempAnimal = (GameObject) animal;
			if (tempAnimal.getName().equalsIgnoreCase(command)) {
				return tempAnimal;
			}
		}

		System.out.println("Invalid input.");
		return (getAnimal(animalOptions));

	}

	/**
	 * Returns a Move object containing the information of the user's selected move.
	 * 
	 * @author Krishan Easparan
	 * @param options        An ArrayList containing the move options the user's
	 *                       rabbit can take.
	 * @param chosenRabbit   The rabbit the user has chosen to move.
	 * @param displayOptions A string representation of the move options.
	 * @return A Move object containing the information of the user's selected move.
	 */

	public Move confirmOption(ArrayList<Point> options, Rabbit chosenRabbit, String displayOptions) {
		HashMap<Integer, Point> userOptions = new HashMap<Integer, Point>();
		for (int i = 1; i <= options.size(); i++) {
			userOptions.put(i, options.get(i - 1));
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = 1;//Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			return new Move(new Point(userOptions.get(selectedOption)), chosenRabbit.getCoordinate(), chosenRabbit);
		} else {
			System.out.println("Invalid Input");
			return confirmOption(options, chosenRabbit, displayOptions);
		}
	}

	/**
	 * Returns a Move object containing the information of the user's selected move.
	 * 
	 * @author Krishan Easparan
	 * @param options        An ArrayList containing the move options the user's fox
	 *                       can take.
	 * @param chosenFox      The fox the user has chosen to move.
	 * @param displayOptions A string representation of the move options.
	 * @return A Move object containing the information of the user's selected move.
	 */

	public Move confirmOption(ArrayList<Point[]> options, Fox chosenFox, String displayOptions) {
		HashMap<Integer, Point[]> userOptions = new HashMap<Integer, Point[]>();
		for (int i = 1; i <= options.size(); i++) {
			userOptions.put(i, userOptions.get(i - 1));
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			Point[] chosenDestination = userOptions.get(selectedOption);
			return new Move(chosenFox.getCoordinate(), chosenFox.getCoordinate(), chosenDestination[0],
					chosenDestination[1], chosenFox);
		} else {
			System.out.println("Invalid input.");
			return confirmOption(options, chosenFox, displayOptions);
		}
	}

	/**
	 * 
	 * @author Aashna Narang
	 * 
	 * 
	 *
	 */
	public String playAgain() {
		System.out.println(
				"Congrats! \nPease type \"continue\" if you would like to go to the next level.\nType \"exit\" if you would like to stop.");
		String command = input.nextLine();
		if (command.equalsIgnoreCase("continue")) {
			return "continue";
		} else if (command.equalsIgnoreCase("exit")) {
			input.close();
			return "exit";
		} else {
			System.out.println("Command does not exist.");
			return playAgain();
		}
	}

}
