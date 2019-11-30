package source;

import java.awt.Point;
import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;

public class XMLHandler extends DefaultHandler {
	private StringBuilder data;
	private static final Point[] HOLES = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) };
	private Level levelModel;
	
	private boolean rabbitState;
	private boolean x1;
	private boolean y1;
	private boolean x2;
	private boolean y2;
	private boolean jumpInState;
	private boolean nameState;
	private boolean foxState;
	private boolean mushState;
	private boolean directionState;
	private boolean levelState;
	
	private String name;
	private Point coordinate1;
	private Point coordinate2;
	private int level;
	private String direction;
	
	private int wantedLevel;
	private boolean isWantedLevel;
	private Level wantedLevelModel;
	
	public XMLHandler() {
		this(-2);
	}
	
	public XMLHandler(int wantedLevel) {
		data = null;
		level = -1;
		rabbitState = false;
		nameState = false;
		foxState = false;
		directionState = false;
		levelState = false;
		x1 = false;
		x2 = false;
		y1 = false;
		y2 = false;	
		name = "";
		coordinate1 = new Point();
		coordinate2 = new Point();
		level = -1;
		direction = "";
		this.wantedLevel=wantedLevel;
		isWantedLevel = false;
		jumpInState=true;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("JumpIn")) {
			jumpInState = true;
		}
		if(qName.equals("name")) {
			nameState = true;
		} else if (qName.equals("x1")) {
			x1 = true;
		} else if (qName.equals("x2")) {
			x2 = true;
		} else if(qName.equals("y1")) {
			y1 = true;
		} else if(qName.equals("y2")) {
			y2 = true;
		} else if(qName.equals("level")) {
			levelState = true;
		} else if(qName.equals("direction")) {
			directionState = true;
		} else if(qName.equals("Rabbit")) {
			rabbitState = true;
		} else if(qName.equals("Fox")) {
			foxState = true;
		} else if (qName.equals("Mushroom")) {
			mushState = true;
		}
		data = new StringBuilder();
	}
	
	@Override
	// to reduce smellyness maybe make a hashmap of states and their corresponding object
	public void endElement(String uri, String localName, String qName) throws SAXException {
		boolean justCreated = false;
		if (jumpInState && isWantedLevel) {
			wantedLevelModel = levelModel;
			isWantedLevel = false;
		}
		if (levelState) {
			level = Integer.parseInt(data.toString());
			if (level == wantedLevel) {
				isWantedLevel = true;
			}
			levelModel = new Level(level);
			levelState = false;
		} else if(nameState) {
			name = new String(data.toString());
			nameState = false;
		} else if(x1) {
			coordinate1.x = Integer.parseInt(data.toString());
			x1 = false;
		} else if(x2) {
			coordinate2.x = Integer.parseInt(data.toString());
			x2 = false;
		} else if(y1) {
			coordinate1.y = Integer.parseInt(data.toString());
			y1 = false;
		} else if(y2) {
			coordinate2.y = Integer.parseInt(data.toString());
			y2 = false;
		} else if (directionState) {
			direction = new String(data.toString());
			directionState = false;
		} else if (rabbitState) {
			if (name != "" && !coordinate1.equals(new Point())) {
				Rabbit r = new Rabbit(coordinate1, name);
				levelModel.addListener(r);
				levelModel.placeGameObject(r);
			}
			rabbitState = false;
			justCreated = true;
		} else if (foxState) {
			if (name != "" && !coordinate1.equals(new Point()) && !coordinate2.equals(new Point())
					&& direction != "") {
				Fox f = new Fox(coordinate1, coordinate2, name, direction);
				levelModel.addListener(f);
				levelModel.placeGameObject(f);
			}
			foxState = false;
			justCreated = true;
		} else if (mushState) {
			GameObject g = new GameObject(coordinate1, name);
			levelModel.placeGameObject(g);
			mushState = false;
			justCreated = true;
		}
		
		if(justCreated) {
			name = "";
			coordinate1 = new Point();
			coordinate2 = new Point();
			direction = "";
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch,start,length));
	} 
	
	@Override
	public void endDocument() {
		if (wantedLevelModel == null) {
			wantedLevelModel = levelModel;
		}
	}
	
	public Level getWantedLevel() {
		return wantedLevelModel;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String toString() {
		String board = "";
		for (int i = 0; i < 5; i++) {
			board += "--------------------------\n";
			for (int j = 0; j < 5; j++) {
				board += String.format("|%4s", objectToString(j, i));
			}
			board += "|\n";
		}
		board += "--------------------------\n";
		return board;
	}
	
	public String objectToString(int x, int y) {
		GameObject[][] board = levelModel.getGameBoard();
		if (isHole(x, y) && board[y][x] instanceof Rabbit) {
			return board[y][x].getName() + "H";
		} else if (isHole(x, y)) {
			return "H";
		} else if (!(board[y][x].getClass().getSimpleName().equals(""))) {
			return board[y][x].getName();
		}
		return "  ";
	}

	/**
	 * Checks if a point in the game board is a hole.
	 * @param x - x coordinate of the board
	 * @param y - y coordinate of the board
	 * @return - boolean of if it is a hole
	 */
	public boolean isHole(int x, int y) {
		for (int i = 0; i < HOLES.length; i++) {
			if (HOLES[i].getX() == x && HOLES[i].getY() == y) {
				return true;
			}
		}
		return false;
	}

	
}
