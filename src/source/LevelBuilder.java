package source;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class LevelBuilder {

	public JumpIn gameBeingBuilt;
	private GameObjectFactory factory;
	private static final String filePath = new File("").getAbsolutePath()+"\\levels.xml";
	
	public LevelBuilder() {
		reset();
	}
	
	private void reset() {
		GameObject[][] board = new GameObject[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new GameObject(new Point(i, j));
			}
		}
		gameBeingBuilt = new JumpIn(nextLevelNumber(), board);
		factory = new GameObjectFactory();
	}
	
	private int nextLevelNumber() {
		int numLevels = -1;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
	
			NodeList list = doc.getElementsByTagName("JumpIn");
	
			numLevels = list.getLength();
		} catch (Exception e) {
			e.printStackTrace();
			numLevels = 0;
		}
		return numLevels;
	}
	
	public boolean isValidGame() {
		return gameBeingBuilt.solver();
	}
		
//	public void saveLevel() {
//		if (isValidGame()) {
//				File f = new File(filePath);
//				String xml = gameBeingBuilt.toXml();
//				FileWriter writer;
//				writer = new FileWriter(f, true);
//				writer.write(xml);
//				writer.close();
//				reset();
//		}
//	}
	
	public void removeGameObject (Point p) {
		GameObject[][] board = gameBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		String className = space.getClass().getSimpleName();
		if (className.equalsIgnoreCase("Fox")) {
			Fox tempFox = (Fox)space;
			Point p2 = tempFox.getCoordinate().equals(p) ? tempFox.getCoordinate2() : tempFox.getCoordinate();
			board[p.y][p.x] = new GameObject(p);
			Integer counter = Integer.valueOf(space.getName().charAt(1));
			factory.addRemovedCounter(className.toLowerCase(), counter);
			board[p2.y][p2.x] = new GameObject(p2);
		}
		else {
			board[p.y][p.x] = new GameObject(p);
		}
	}
	
	public boolean addGameObject(Point p, String object, String direction) {
		GameObject g = factory.getGameObject(p, object, direction);
		GameObject[][] board = gameBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		if (g==null) {
			return false;
		}
		else if (object.equalsIgnoreCase("Fox")) {
			Fox f = (Fox) g;
			Point p2 = f.getCoordinate2();
			GameObject space2 = board[p2.y][p2.x];
			if (validSpaceFox(space)&&validSpaceFox(space2)) {
				board[p.y][p.x] = f;
				board[p2.y][p2.x] = f;
				gameBeingBuilt.addListener(f);
				return true;
			} 
			else {
				factory.reduceCounter(object);
			}
		}
		else  {
			if (validSpaceGameObject(space)) {
				board[p.y][p.x] = g;
				try {
					gameBeingBuilt.addListener((MovableAnimal)g);
				}
				catch (Exception e) {}
				return true;
			} else {
				factory.reduceCounter(object);
			}
		}
		return false;
		
	}
	
	private boolean validSpaceFox(GameObject space) {
		List<Point> list = Arrays.asList(LevelSelector.getHoles());
		return space.getName().equals("") && !list.contains(space.getCoordinate());
	}
	
	private  boolean validSpaceGameObject(GameObject space) {
		return space.getName().equals("");
	}

	public static void main(String[] args) {
		String direction;
		String point;
		String[] coords;
		String object;
		
		LevelBuilder bob = new LevelBuilder();
		Scanner input = new Scanner(System.in);
		System.out.println(bob.gameBeingBuilt.toString());
		
		while (true) {
			direction = "";
			System.out.println("Enter point");
			point = input.nextLine();
			coords = point.split(",");
			
			System.out.println("Enter object");
			object = input.nextLine();
			if (object.equalsIgnoreCase("Fox")) {
				System.out.println("Enter direction");
				direction = input.nextLine();
			}
			bob.addGameObject(new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1])), object, direction);
			System.out.println(bob.gameBeingBuilt.toString());
			System.out.println("Enter q for quit");
			if (input.nextLine().equalsIgnoreCase("q")) {
				break;
			}
		}
		
	}
	
}
