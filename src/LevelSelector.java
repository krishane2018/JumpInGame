import java.awt.Point;

/**
 * 
 * @author Nick Anderson and Aashna Narang
 *
 * Initialize a game board
 */
public class LevelSelector {
	private GameObject[][] board;
	// coordinates of the game board
	private static final Point[] HOLES = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) };

	/**
	 * Initialize a game board
	 * @param level which level the user would like to play
	 * @param game the JumpIn game object
	 */
	public LevelSelector(int level, JumpIn game) {
		if (1 > level | level > 3) {
			throw new IllegalArgumentException("Levels must be between 1-3");
		}
		board = new GameObject[5][5]; 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new GameObject(new Point(i, j)); 
			}
		}

		if (level == 1) {
			// based on JumpIn' level 5 (C)
			Rabbit r1 = new Rabbit(new Point(2, 0), "R1");
			Rabbit r2 = new Rabbit(new Point(0, 4), "R2");

			GameObject m1 = new GameObject(new Point(0, 2), "M1");
			GameObject m2 = new GameObject(new Point(0, 3), "M2");
			GameObject m3 = new GameObject(new Point(1, 1), "M3");

			// adds all rabbits as Listeners (C)
			game.addListener(r1);
			game.addListener(r2);

			board[2][0] = r1;
			board[4][0] = r2;
			board[2][0] = m1;
			board[3][0] = m2;
			board[1][1] = m3;

		} else if (level == 2) {
			// based on JumpIn' level 6
			Rabbit whiteRabbit2 = new Rabbit(new Point(0, 0), "R1");
			Rabbit redRabbit2 = new Rabbit(new Point(3, 2), "R2");

			GameObject mush21 = new GameObject(new Point(1, 0), "M1");
			GameObject mush22 = new GameObject(new Point(2, 1), "M2");
			GameObject mush23 = new GameObject(new Point(1, 3), "M3");

			game.addListener(redRabbit2);
			game.addListener(whiteRabbit2);

			board[0][0] = whiteRabbit2;
			board[2][3] = redRabbit2;
			board[0][1] = mush21;
			board[1][2] = mush22;
			board[3][1] = mush23;
		} else if (level == 3) {

			// based on JumpIn' level 16 (C)
			Rabbit whiteRabbit3 = new Rabbit(new Point(1, 0), "R1");

			Fox fox31 = new Fox(new Point(1, 3), new Point(1, 4), "F1", "Vertical");

			GameObject mush31 = new GameObject(new Point(0, 1), "M1");
			GameObject mush32 = new GameObject(new Point(0, 2), "M2");
			GameObject mush33 = new GameObject(new Point(2, 3), "M3");
			// add the rabbit and the fox as listeners (C)
			game.addListener(whiteRabbit3);
			game.addListener(fox31);

			board[0][1] = whiteRabbit3;
			board[3][1] = fox31;
			board[4][1] = fox31;
			board[1][0] = mush31;
			board[2][0] = mush32;
			board[3][2] = mush33;
		}
	}

	/**
	 * Get the coordinates of the rabbit holes
	 * @return an array of Point objects 
	 */
	public static Point[] getHoles() {
		return HOLES;
	}
	
	/**
	 * Return the game board 
	 * @return a 2d array of the GameObjects
	 */
	public GameObject[][] getBoard(){
		return this.board;
	}

	


}
