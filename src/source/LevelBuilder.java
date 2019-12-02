package source;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
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
	private static final String filePath = new File("").getAbsolutePath() + "\\levels.xml";
	private Point foxCoordinates;
	private ArrayList<LevelBuilderListener> listeners;

	public LevelBuilder() {
		reset();
	}

	private void reset() {
		levelBeingBuilt = new Level(nextLevelNumber()+1);
		factory = new GameObjectFactory();
		listeners = new ArrayList<LevelBuilderListener>();
	}
	
	public void addListener(LevelBuilderListener l) {
		listeners.add(l);
	}

	public static int nextLevelNumber() {
		int numLevels = -1;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);

			NodeList list = doc.getElementsByTagName("JumpIn");

			numLevels = list.getLength();
		} catch (Exception e) {
//			e.printStackTrace();
			numLevels = 0;
		}
		return numLevels;
	}

	private boolean isValidGame(JumpIn game) {
		return game.solver();
	}

	public boolean saveLevel() {
		JumpIn j = new JumpIn(levelBeingBuilt);
		if (isValidGame(j)) {
			try {
				RandomAccessFile f = new RandomAccessFile(filePath, "rw");
				long length = f.length() - 1;
				byte b;
				do {
				  length -= 1;
				  f.seek(length);
				  b = f.readByte();
				} while(b != 10);
				f.setLength(length+1);
				f.close();
			} catch (Exception e) {
			}
				File file = new File(filePath);
				String xml =  j.toXML() + "\n</Levels>";
				FileWriter writer;
				try {
					if(Play.fileIsEmpty(filePath)) {
						xml = "<Levels>\n" + xml;
					}
					writer = new FileWriter(file, true);
					writer.write(xml);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				reset();
				return true;
		}
		return false;
	}
	
	public Point getFoxCoordinate2() {
		return foxCoordinates;
	}
	
	public boolean removeGameObject (Point p) {
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		String className = space.getClass().getSimpleName();
		className = space.getName()!= "" && space.getName().charAt(0) == 'M' ? "mushroom" : className;
		if(space.getName().equals("")) {
			return false;
		} 
		else {
			levelBeingBuilt.removeGameObject(p);
			Integer counter = Character.getNumericValue(space.getName().charAt(1));
			factory.addRemovedCounter(className.toLowerCase(), counter);
			updateListeners(space, true);
			return true;
		}	
	}

	public boolean addGameObject(Point p, String object, String direction) {
		object = object.toLowerCase();
		GameObject g = factory.getGameObject(p, object, direction);
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		if (g == null) {
			factory.reduceCounter(object);
			System.out.println("invalid fox out of bounds");
			return false;
		} else if (object.equalsIgnoreCase("Fox")) {
			Fox f = (Fox) g;
			Point p2 = f.getCoordinate2();
			foxCoordinates = p2;
			GameObject space2 = board[p2.y][p2.x];
			if (validSpaceFox(space) && validSpaceFox(space2)) {
				levelBeingBuilt.placeGameObject(f);
				levelBeingBuilt.addListener(f);
				updateListeners(g, false);
				return true;
			} else {
				factory.reduceCounter(object);
				System.out.println("invalid fox");
			}
		} else  {
            if (validSpaceGameObject(space)) {
                levelBeingBuilt.placeGameObject(g);
            try {
                    levelBeingBuilt.addListener((MovableAnimal)g);
                }
                catch (Exception e) {}
            updateListeners(g, false);
                return true;
            } else {
                factory.reduceCounter(object);
                System.out.println("invalid mushroom/rabbit");
            }
		}
		return false;

	}
	
	private void updateListeners(GameObject g, boolean removeState) {
        for(LevelBuilderListener l : listeners) {
			l.handleEvent(g, removeState);
		}
	}

	private boolean validSpaceFox(GameObject space) {
		List<Point> list = Arrays.asList(Level.HOLES);
		return space.getName().equals("") && !list.contains(space.getCoordinate());
	}

	private boolean validSpaceGameObject(GameObject space) {
		return space.getName().equals("");
	}

//	public static void main(String[] args) {
//		String direction;
//		String point;
//		String[] coords;
//		String object;
//		
//		LevelBuilder bob = new LevelBuilder();
//		Scanner input = new Scanner(System.in);
//		System.out.println(bob.gameBeingBuilt.toString());
//		
//		while (true) {
//			direction = "";
//			System.out.println("Enter point");
//			point = input.nextLine();
//			coords = point.split(",");
//			System.out.println("Enter object");
//			object = input.nextLine();
//			if (object.equalsIgnoreCase("Fox")) {
//				System.out.println("Enter direction");
//				direction = input.nextLine();
//			}
//			bob.addGameObject(new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1])), object, direction);
//			System.out.println(bob.gameBeingBuilt.toString());
//			System.out.println("Enter q for quit");
//			System.out.println("Enter r for remove");
//			String kush = input.nextLine();
//			if (kush.equalsIgnoreCase("q")) {
//				break;
//			}else if (kush.equalsIgnoreCase("r")) {
//				System.out.println("Enter point");
//				point = input.nextLine();
//				coords = point.split(",");
//				bob.removeGameObject(new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1])));
//				System.out.println(bob.gameBeingBuilt.toString());
//			}
//		}
//		
//	}

}
