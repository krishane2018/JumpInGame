import java.awt.Point;
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
	private final int NUM_ROWS=5;
	private final int NUM_COLUMNS=5;
	
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
        	GameObject chosenAnimal = parser.getAnimal(listeners);
    		String options = displayOptions(chosenAnimal);
    		Move move = parser.confirmOption(options, chosenAnimal);
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
	
	
	private String displayOptions (GameObject chosenAnimal){
		
		String output = "";
		
		if (chosenAnimal.getClass().getName().equals("Rabbit")) {
			ArrayList<Point> rabbitOptions = new ArrayList<Point>();
			boolean isJump = false;
			for (int x = (int) (chosenAnimal.getCoordinate().getX()-1); x>-1; x--) {
				if (gameBoard[(int) chosenAnimal.getCoordinate().getY()][x]!=null && 
						!(gameBoard[(int) chosenAnimal.getCoordinate().getY()][x].getClass().getName().equals("RabbitHole"))) {
					isJump = true;
				}
				else if (isJump == true){
					rabbitOptions.add(new Point(x,(int) chosenAnimal.getCoordinate().getY()));
					break;
				}
			}
			
			isJump = false;
			for (int x = (int) (chosenAnimal.getCoordinate().getX()+1); x<NUM_COLUMNS; x++) {
				if (gameBoard[(int) chosenAnimal.getCoordinate().getY()][x]!=null && 
						!(gameBoard[(int) chosenAnimal.getCoordinate().getY()][x].getClass().getName().equals("RabbitHole"))) {
					isJump = true;
				}
				else if (isJump == true){
					rabbitOptions.add(new Point(x,(int) chosenAnimal.getCoordinate().getY()));
					break;
				}
			}
			
			isJump = false;
			for (int y = (int) (chosenAnimal.getCoordinate().getY()-1); y>-1; y--) {
				if (gameBoard[y][(int) chosenAnimal.getCoordinate().getX()]!=null && 
						!(gameBoard[y][(int) chosenAnimal.getCoordinate().getX()].getClass().getName().equals("RabbitHole"))) {
					isJump = true;
				}
				else if (isJump == true){
					rabbitOptions.add(new Point((int) chosenAnimal.getCoordinate().getX(),y));
					break;
				}
			}
			
			isJump = false;
			for (int y = (int) (chosenAnimal.getCoordinate().getY()+1); y<NUM_ROWS; y++) {
				if (gameBoard[y][(int) chosenAnimal.getCoordinate().getX()]!=null && 
						!(gameBoard[y][(int) chosenAnimal.getCoordinate().getX()].getClass().getName().equals("RabbitHole"))) {
					isJump = true;
				}
				else if (isJump == true){
					rabbitOptions.add(new Point((int) chosenAnimal.getCoordinate().getX(),y));
					break;
				}
			}
			
			
			for (Point point : rabbitOptions) {
				output+="("+point.getX()+","+point.getY()+")\n";
			}
			
		}
		else {
				ArrayList<Point[]> foxOptions = new ArrayList<Point[]>();
				Fox chosenFox = (Fox)(chosenAnimal);
				if (chosenFox.getDirection().equals("Horizontal")) {
					for (int x = (int) (chosenFox.getCoordinate().getX()-1); x>-1; x--) {
						if (gameBoard[(int) chosenFox.getCoordinate().getY()][x]==null) {
							Point[] tempArray = {new Point(x,(int) chosenFox.getCoordinate().getY()),new Point(x+1,(int) chosenFox.getCoordinate().getY())};
							foxOptions.add(tempArray);
						}
						else {
							break;
						}
					}
					for (int x = (int) (chosenFox.getCoordinate2().getX()+1); x<NUM_COLUMNS; x++) {
						if (gameBoard[(int) chosenFox.getCoordinate().getY()][x]==null) {
							Point[] tempArray = {new Point(x,(int) chosenFox.getCoordinate().getY()),new Point(x+1,(int) chosenFox.getCoordinate().getY())};
							foxOptions.add(tempArray);
						}
						else {
							break;
						}
					}
				}
				else {
					for (int y = (int) (chosenFox.getCoordinate().getY()-1); y>-1; y--) {
						if (gameBoard[y][(int) chosenFox.getCoordinate().getX()]==null) {
							Point[] tempArray = {new Point((int) chosenFox.getCoordinate().getX(),y),new Point((int) chosenFox.getCoordinate().getX(),y+1)};
							foxOptions.add(tempArray);
						}
						else {
							break;
						}
					}
					for (int y = (int) (chosenFox.getCoordinate2().getY()+1); y<NUM_ROWS; y++) {
						if (gameBoard[y][(int) chosenFox.getCoordinate().getX()]==null) {
							Point[] tempArray = {new Point((int) chosenFox.getCoordinate().getX(),y),new Point((int) chosenFox.getCoordinate().getX(),y+1)};
							foxOptions.add(tempArray);
						}
						else {
							break;
						}
					}
				}
				
				for (Point[] points : foxOptions) {
					output+="{";
					for (Point point: points) {
						output+="("+point.getX()+","+point.getY()+") ";
					}
					output=output.trim();
					output+="}\n";
				}
		}
		
		return output;
		
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
