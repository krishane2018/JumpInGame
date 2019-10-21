import java.util.*;

public class LevelSelector {

	public String boardtoString(){
		String board ="";
		for(int j=0;j<5;j++) {
			board += "_______________________\n";
			for(int i=0;i<5;i++) {
				board += "| ";

				if(gameBoard[i][j] instanceof Rabbit) {
					board += "R ";
				}else if(gameBoard[i][j] instanceof Fox) {
					board += "F ";
				else if(gameBoard[i][j] instanceof GameObject) {
					board += "M ";
				}else {
					return "  ";
				}
			}
			board += "|\n";
		}
		board += "_______________________\n";
		return board;
	}
	public static void main(String[] args)
	{
		System.out.println("Please select one of the following levels:");
		System.out.println("Type \"1\" for Level 1, \"2\" for Level 2, or \"3\" for level 3");
		Scanner levelChoice = new Scanner(System.in);
		String Choice = levelChoice.nextLine();
		while(!Choice.equals("1") & !Choice.equals("2") & !Choice.equals("3")) {
			System.out.println("I'm afraid that's not an option, please select a level:");
			Choice = levelChoice.nextLine();
		}
		System.out.println("Nice choice!");
		
		GameObject[][] board1 = new GameObject[5][5]; // creates a new board  (C)
		 for(int i=0;i<5;i++)
		 {
		 	for(int j=0; j<5;j++)
		 	{
		 		board1[i][j] = new GameObject(); //sets each object in the array as a GameObject (C)
		 	}
		 }
		 GameObject[][] board2 = new GameObject[5][5]; // creates a new board  (C)
		 for(int i=0;i<5;i++)
		 {
		 	for(int j=0; j<5;j++)
		 	{
		 		board2[i][j] = new GameObject(); //sets each object in the array as a GameObject (C)
		 	}
		 }
		 GameObject[][] board3 = new GameObject[5][5]; // creates a new board  (C)
		 for(int i=0;i<5;i++)
		 {
		 	for(int j=0; j<5;j++)
		 	{
		 		board3[i][j] = new GameObject(); //sets each object in the array as a GameObject (C)
		 	}
		 }
		 
		//based on JumpIn' level 5 (C)
		Rabbit redRabbit1 = new Rabbit((2, 0), "Red Rabbit");
		Rabbit whiteRabbit1 = new Rabbit((0, 4), "White Rabbit");
		
		//creates rabbit objects with coordinates (2, 0) and (0, 4) (C)
		
		GameObject mush11 = new GameObject((0,2), "Mushroom 1");
		Mushroom mush12 = new Mushroom((0,3), "Mushroom 2");
		Mushroom mush13 = new Mushroom((1,1), "Mushroom 3");
		
		//creates mushroom objects with coordinates (0,2), (0,3) and (1,1) (C)
		
		JumpIn.addListener(redRabbit1);
		JumpIn.addListener(whiteRabbit1);
		
		//adds all rabbits as Listeners (C)
		
		board1[0][2] = redRabbit1;
		board1[4][0] = whiteRabbit1;
		board1[0][2] = mush11;
		board1[0][3] = mush12;
		board1[1][1] = mush13;
		
		//sets the rabbits and mushrooms as objects on board 1 (C) 
		 
		 
		 
		 
		//based on JumpIn' level 6
		Rabbit whiteRabbit2 = new Rabbit((0,0), "White Rabbit");
		Rabbit redRabbit2 = new Rabbit((3,2), "Red Rabbit");
			
		//creates rabbit objects with coordinates (0,0) and (3,2) (C)
			
		GameObject mush21 = new GameObject((1,0), "Mushroom1");
		GameObject mush22 = new GameObject((2,1), "Mushroom2");
		GameObject mush23 = new GameObject((1,3), "Mushroom3");
			
		//creates mushroom objects with coordinates (1,0), (2,1) and (1,3) (C)
			
		JumpIn.addListener(redRabbit2);
		JumpIn.addListener(whiteRabbit2);
			
		//adds the rabbits as listeners (C)
			
		board2[0][0] = whiteRabbit2;
		board2[3][2] = redRabbit2;
		board2[1][0] = mush21;
		board2[2][1] = mush22;
		board2[1][3] = mush23;
			
		//places all rabbits and mushrooms on board 2 (C)
		 
		 
		 
		//based on JumpIn' level 16 (C)
		Rabbit whiteRabbit3 = new Rabbit((1,0), "White Rabbit");
			
		//creates a rabbit object with coordinates (1,0) (C)
			
		Fox fox31 = new Fox((1,3), (1,4), "Fox 1", "Up");
			
		//creates a fox object with Coordinates (1,3) and (1,4)(C)
			
		GameObject mush31 = new GameObject((0,1), "Mushroom 1");
		GameObject mush32 = new GameObject((0,2), "Mushroom 2");
		GameObject mush33 = new GameObject((2,3), "Mushroom 3");
			
		//creates mushroom objects with coordinates (0,1), (0,2) and (2,3) (C)
			
		JumpIn.addListener(whiteRabbit3);
		JumpIn.addListener(fox31);
			
		//add the rabbit and the fox as listeners (C)
			
		board3[1][0] = whiteRabbit3;
		board3[1][3] = fox31;
		board3[1][4] = fox31;
		board3[0][1] = mush31;
		board3[0][2] = mush32;
		board3[2][3] = mush33;
			
		//places all the pieces on board 3(C)
		 
		 
		 
		if(Choice.equals("1"))
		{
			//JumpIn game = new JumpIn(1);
		}
		else if(Choice.equals("2"))
		{
			//JumpIn game = new JumpIn(2);	
		}
		else
		{
			//JumpIn game = new JumpIn(3);
		}
		
		
		//initializes a JumpInModel with the selected level as the board (C)
		
		//System.out.println(game.boardtoString());

	}
	
}
