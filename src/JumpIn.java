import java.util.ArrayList;

/**
 * 
 * @author Kush Gopeechund
 * 
 * 
 *
 */
public class JumpIn {
	
	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private Parser parser;
	private int level;
	private final int NUM_ROW=5;
	private final int NUM_COL=5;
	
	/**
	 * 
	 * @param i
	 */
	public JumpIn(int i) {
		level = i;
		listeners = new ArrayList<JumpInListener>();
		gameBoard = new LevelSelector(level);
	}
	
	/**
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public String objectToString(int y, int x) {
		if(gameBoard[y][x].getClass().getName()=="Rabbit") {
			return "R ";
		}else if(gameBoard[y][x].getClass().getName()=="Fox") {
			return "F ";
		}else if(gameBoard[y][x].getClass().getName()=="Hole") {
			return "O ";
		}
		else if(gameBoard[y][x].getClass().getName()=="Mushroom") {
			return "M ";
		}else {
			return "  ";
		}
	}
	
	/**
	 * 
	 */
	public void printWelcome() {
		System.out.println("Welcome to JumpIn!");
		System.out.println("Please type \"play\" to start the game.\n");
	}
	
	/**
	 * 
	 */
	public void play() {
		
		boolean finished = false;
        while (! finished) {
            Move move = parser.getAnimal(listeners);
            finished = processCommand(move);
            System.out.println(gameBoard.toString());
        }
        System.out.println("Congrats! \nPease type \"continue\" if you would like to go to the next level.\nType \"exit\" if you would like to stop.");
        String status = parser.getNext();
        if(status == "continue") {
        	JumpIn game = new JumpIn(level+1);
        	game.play();
        }
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkWin() {
		boolean win=false;
		for(int i = 0;i<listeners.size();i++) {
			if(listeners.get(i).getName()=="Rabbit") {
				win = listeners.get(i).getStatus;
			}
		}
		return win;
	}
	
	/**
	 * 
	 * @param move
	 * @return
	 */
	private boolean processCommand(Move move) {
		JumpInEvent event = new JumpInEvent(this,move.getGameObject,move.end.x,move.end.y);
		gameBoard[move.initial.y][move.initial.x]=null;
		gameBoard[move.end.y][move.end.x]=move.getGameObject;
		for(int i = 0;i<listeners.size();i++) {
			if(listeners.get(i).equals(move.getGameObject)) {
				listeners.get(i).handleEvent(event);
			}
		}
		
		return checkWin();
	}
	
	/**
	 * 
	 */
	public String toString(){
		String board ="";
		for(int i=0;i<NUM_ROW;i++) {
			board += "---------------------\n";
			for(int j=0;j<NUM_COL;j++) {
				board += "| ";
				objectToString(j,i);
			}
			board += "|\n";
		}
		board += "---------------------\n";
		return board;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JumpIn game = new JumpIn(1);
		game.printWelcome();
		game.play();
		
	}

}
