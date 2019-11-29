package source;

import java.awt.Point;
import java.util.ArrayList;

public class Level {
	
	private GameObject[][] gameBoard;
	private ArrayList<JumpInListener> listeners;
	private int level;
	
	public final static int NUM_ROWS = 5;
	public final static int NUM_COLUMNS = 5;
	
	public Level () {
		gameBoard = new GameObject[NUM_ROWS][NUM_COLUMNS];
		for (int i = 0; i < NUM_COLUMNS; i++) {
			for (int j = 0; j < NUM_ROWS; j++) {
				gameBoard[j][i] = new GameObject(new Point(i, j));
			}
		}
		listeners = new ArrayList<JumpInListener>();
		level = -1;
	}
	
	public void addListener(JumpInListener l) {
		listeners.add(l);
	}
	
	public void setLevel(int l) {
		level = l;
	}
	
	public void placeGameObject (GameObject o) {
		Point p1 = o.getCoordinate();
		gameBoard[p1.y][p1.x] = o;
		if (o.getClass().getSimpleName().equalsIgnoreCase("Fox")) {
			Fox f = (Fox)o;
			Point p2 = f.getCoordinate2();
			gameBoard[p2.y][p2.x] = f;
		}
	}
	
	public GameObject[][] getGameBoard() {
		return gameBoard;
	}

	public ArrayList<JumpInListener> getListeners() {
		return listeners;
	}

	public int getLevel() {
		return level;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
