import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	
	private Scanner input;
	
	
	public Parser() {
		input = new Scanner(System.in);
	}
	
	public GameObject getAnimal(ArrayList<JumpInListener> animalOptions) {
		System.out.println("Enter one of the following 2 character strings to choose which animal"
				+ "to move:\n "
				+ "Bunnies: B1, B2, B3 or Foxes: F1, F2");
		
		String command = input.nextLine();
		
		for (JumpInListener animal: animalOptions) {
			GameObject tempAnimal = (GameObject)animal;
			if (tempAnimal.getName().equals(command)){
				return tempAnimal;
			}
		}
		
		return (getAnimal(animalOptions));
		
		
	}

	public Move confirmOption(String options, GameObject chosenAnimal) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter one of the following options:");
		System.out.println(options);
		String selectedOption = input.nextLine();
		if (options.contains(selectedOption)) {
			String[] point = options.split(",");
			input.close();
			return new Move(new Point(Integer.valueOf(point[0]),Integer.valueOf(point[1]))
					, chosenAnimal.getCoordinate(), chosenAnimal);
		}
		else {
			input.close();
			return confirmOption(options, chosenAnimal);
		}
	}
	
}
