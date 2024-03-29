package source;


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

	public MovableAnimal getAnimal(ArrayList<JumpInListener> animalOptions) {
		System.out.println("Enter one of the following 2 character strings to choose which animal" + " to move:\n ");
		for (JumpInListener animal : animalOptions) {

			System.out.print(((GameObject) animal).getName() + " ");
		}
		System.out.println("");

		String command = input.nextLine();

		for (JumpInListener animal : animalOptions) {
			MovableAnimal tempAnimal = (MovableAnimal) animal;
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

	public Move confirmOption(ArrayList<Object> options, Rabbit chosenRabbit, String displayOptions) {
		HashMap<Integer, Point> userOptions = new HashMap<Integer, Point>();
		for (int i = 1; i <= options.size(); i++) {
			userOptions.put(i, (Point) options.get(i - 1));
		}
		if (options.isEmpty()) {
			System.out.println(displayOptions);
			return new Move();
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			return new Move(chosenRabbit.getCoordinate(), new Point(userOptions.get(selectedOption)), chosenRabbit);
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

	public Move confirmOption(ArrayList<Object> options, Fox chosenFox, String displayOptions) {
		HashMap<Integer, Point[]> userOptions = new HashMap<Integer, Point[]>();
		for (int i = 1; i <= options.size(); i++) {

			userOptions.put(i, (Point[]) options.get(i - 1));
		}
		if (options.isEmpty()) {
			return new Move();
		}
		System.out.println("Please enter one of the following options (using the digits):");
		System.out.println(displayOptions);
		int selectedOption = Integer.valueOf(input.nextLine());
		if (userOptions.keySet().contains(selectedOption)) {
			Point[] chosenDestination = userOptions.get(selectedOption);
			return new Move(chosenFox.getPosition(),	chosenDestination, chosenFox);
		} else {
			System.out.println("Invalid input.");
			return confirmOption(options, chosenFox, displayOptions);
		}
	}

	/**
	 * 
	 * @author Aashna Narang
	 *
	 *         Ask the user if they would like to play the next level or quit the
	 *         game
	 * @return A string containing the command the user selected
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
