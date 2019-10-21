import java.awt.Point;

public class LevelSelector {
	private GameObject[][] board;
	private final Point[] HOLES = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) };

	public LevelSelector(int level, JumpIn game) {
		if (1 > level | level > 3) {
			throw new IllegalArgumentException("Levels must be between 1-3");
		}
		board = new GameObject[5][5]; // creates a new board (C)
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = new GameObject(new Point(i, j)); // sets each object in the array as a GameObject (C)
			}
		}

		if (level == 1) {
			// based on JumpIn' level 5 (C)
			Rabbit r1 = new Rabbit(new Point(2, 0), "R1");
			Rabbit r2 = new Rabbit(new Point(0, 4), "R2");

			// creates rabbit objects with coordinates (2, 0) and (0, 4) (C)

			GameObject m1 = new GameObject(new Point(0, 2), "M1");
			GameObject m2 = new GameObject(new Point(0, 3), "M2");
			GameObject m3 = new GameObject(new Point(1, 1), "M3");

			// creates mushroom objects with coordinates (0,2), (0,3) and (1,1) (C)

			game.addListener(r1);
			game.addListener(r2);

			// adds all rabbits as Listeners (C)

			board[0][2] = r1;
			board[4][0] = r2;
			board[0][2] = m1;
			board[0][3] = m2;
			board[1][1] = m3;

			// sets the rabbits and mushrooms as objects on board (C)
		} else if (level == 2) {
			// based on JumpIn' level 6
			Rabbit whiteRabbit2 = new Rabbit(new Point(0, 0), "R1");
			Rabbit redRabbit2 = new Rabbit(new Point(3, 2), "R2");

			// creates rabbit objects with coordinates (0,0) and (3,2) (C)

			GameObject mush21 = new GameObject(new Point(1, 0), "M1");
			GameObject mush22 = new GameObject(new Point(2, 1), "M2");
			GameObject mush23 = new GameObject(new Point(1, 3), "M3");

			// creates mushroom objects with coordinates (1,0), (2,1) and (1,3) (C)

			game.addListener(redRabbit2);
			game.addListener(whiteRabbit2);

			// adds the rabbits as listeners (C)

			board[0][0] = whiteRabbit2;
			board[3][2] = redRabbit2;
			board[1][0] = mush21;
			board[2][1] = mush22;
			board[1][3] = mush23;

			// places all rabbits and mushrooms on board (C)
		} else if (level == 3) {

			// based on JumpIn' level 16 (C)
			Rabbit whiteRabbit3 = new Rabbit(new Point(1, 0), "R1");

			// creates a rabbit object with coordinates (1,0) (C)

			Fox fox31 = new Fox(new Point(1, 3), new Point(1, 4), "F1", "Vertical");

			// creates a fox object with Coordinates (1,3) and (1,4)(C)

			GameObject mush31 = new GameObject(new Point(0, 1), "M1");
			GameObject mush32 = new GameObject(new Point(0, 2), "M2");
			GameObject mush33 = new GameObject(new Point(2, 3), "M3");

			// creates mushroom objects with coordinates (0,1), (0,2) and (2,3) (C)

			game.addListener(whiteRabbit3);
			game.addListener(fox31);

			// add the rabbit and the fox as listeners (C)

			board[1][0] = whiteRabbit3;
			board[1][3] = fox31;
			board[1][4] = fox31;
			board[0][1] = mush31;
			board[0][2] = mush32;
			board[2][3] = mush33;

			// places all the pieces on board (C)
		}
	}

	public Point[] getHoles() {
		return this.HOLES;
	}
	
	public GameObject[][] getBoard(){
		return this.board;
	}

//	public String boardtoString(){
//	String board ="";
//	for(int j=0;j<5;j++) {
//		board += "_______________________\n";
//		for(int i=0;i<5;i++) {
//			board += "| ";
//
//			if(gameBoard[i][j] instanceof Rabbit) {
//				board += "R ";
//			}else if(gameBoard[i][j] instanceof Fox) {
//				board += "F ";
//			else if(gameBoard[i][j] instanceof GameObject) {
//				board += "M ";
//			}else {
//				return "  ";
//			}
//		}
//		board += "|\n";
//	}
//	board += "_______________________\n";
//	return board;
//}

}
