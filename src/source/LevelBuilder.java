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

	public Level levelBeingBuilt;
	private GameObjectFactory factory;
	private static final String filePath = new File("").getAbsolutePath()+"\\levels.xml";
	
	public LevelBuilder() {
		reset();
	}
	
	private void reset() {
		levelBeingBuilt = new Level(nextLevelNumber());
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
	
	private boolean isValidGame(JumpIn game) {
		return game.solver();
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
	
	public boolean removeGameObject (Point p) {
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		String className = space.getClass().getSimpleName();
		if(space.getName().equals("")) {
			return false;
		} 
		else {
			levelBeingBuilt.removeGameObject(p);
			Integer counter = Character.getNumericValue(space.getName().charAt(1));
			factory.addRemovedCounter(className.toLowerCase(), counter);
			return true;
		}	
	}
	
	public boolean addGameObject(Point p, String object, String direction) {
		GameObject g = factory.getGameObject(p, object, direction);
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		if (g==null) {
			factory.reduceCounter(object);
			System.out.println("invalid fox out of bounds");
			return false;
		}
		else if (object.equalsIgnoreCase("Fox")) {
			Fox f = (Fox) g;
			Point p2 = f.getCoordinate2();
			GameObject space2 = board[p2.y][p2.x];
			if (validSpaceFox(space)&&validSpaceFox(space2)) {
				levelBeingBuilt.placeGameObject(f);
				levelBeingBuilt.addListener(f);
				return true;
			} 
			else {
				factory.reduceCounter(object);
				System.out.println("invalid fox");
			}
		}
		else  {
			if (validSpaceGameObject(space)) {
				levelBeingBuilt.placeGameObject(g);
			try {
					levelBeingBuilt.addListener((MovableAnimal)g);
				}
				catch (Exception e) {}
				return true;
			} else {
				factory.reduceCounter(object);
				System.out.println("invalid mushroom/rabbit");
			}
		}
		return false;
		
	}
	
	private boolean validSpaceFox(GameObject space) {
		List<Point> list = Arrays.asList(Level.getHoles());
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
		System.out.println(new JumpIn(bob.levelBeingBuilt).toString());
		
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
			System.out.println(new JumpIn(bob.levelBeingBuilt).toString());
			System.out.println("Enter q for quit");
			System.out.println("Enter r for remove");
			String kush = input.nextLine();
			if (kush.equalsIgnoreCase("q")) {
				break;
			}else if (kush.equalsIgnoreCase("r")) {
				System.out.println("Enter point");
				point = input.nextLine();
				coords = point.split(",");
				bob.removeGameObject(new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1])));
				System.out.println(new JumpIn(bob.levelBeingBuilt).toString());
			}
		}
		
	}
	
}
