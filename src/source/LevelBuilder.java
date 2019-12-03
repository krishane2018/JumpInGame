package source;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import gui.CreatorView;

public class LevelBuilder {

	
	private Level levelBeingBuilt;
	private GameObjectFactory factory;
	private static final String filePath = new File("").getAbsolutePath() + "\\levels.xml";
	private Point foxCoordinates;
	private ArrayList<LevelBuilderListener> listeners;

	/**
	 * Constructor to construct LevelBuilder objects.
	 */
	public LevelBuilder() {
		levelBeingBuilt = new Level(nextLevelNumber() + 1);
		factory = new GameObjectFactory();
		listeners = new ArrayList<LevelBuilderListener>();
	}

	/**
	 * Adds listener to LevelBuilder.
	 * @param l A listener which listens to the events of this object.
	 */
	
	public void addListener(LevelBuilderListener l) {
		listeners.add(l);
	}

	/**
	 * Returns the number of levels there are currently.
	 * @return An integer representing the number of levels there are currently.
	 */
	public static int nextLevelNumber() {
		int numLevels = -1;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);

			NodeList list = doc.getElementsByTagName("JumpIn");

			numLevels = list.getLength();
		} catch (Exception e) {
			numLevels = 0;
		}
		return numLevels;
	}

	/**
	 * Determines if a game can be solved and therefore is a valid game.
	 * @param game Game which is being that it is solvable.
	 * @return A boolean representing whether the game is valid or not.
	 */
	private boolean isValidGame(JumpIn game) {
		return game.solver();
	}

	/**
	 * Saves the level that is being built onto an XML file if it is valid.
	 * @return A boolean representing whether there the level was saved to the XML file. 
	 */
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
				} while (b != 10);
				f.setLength(length + 1);
				f.close();
			} catch (Exception e) {
				// Leave empty - works as intended
			}
			File file = new File(filePath);
			String xml = j.toXML() + "\n</Levels>";
			FileWriter writer;
			try {
				if (Play.fileIsEmpty(filePath)) {
					xml = "<Levels>\n" + xml;
				}
				writer = new FileWriter(file, true);
				writer.write(xml);
				writer.close();
			} catch (IOException e) {
				CreatorView.displayError(1);
			}
			levelBeingBuilt.setLevel(levelBeingBuilt.getLevel() + 1);
			return true;
		}
		return false;
	}

	/**
	 * Returns the second coordinate of a Fox object.
	 * @return A Point representing the second coordinate of the Fox object.
	 */
	public Point getFoxCoordinate2() {
		return foxCoordinates;
	}

	/**
	 * Removes a GameObject from the level at the point specified.
	 * @param p The Point at which the user wants to remove a Game Object from.
	 * @return A boolean representing whether a GameObject was removed.
	 */
	public boolean removeGameObject(Point p) {
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		String className = space.getClass().getSimpleName();
		className = space.getName() != "" && space.getName().charAt(0) == 'M' ? "mushroom" : className;
		if (space.getName().equals("")) {
			return false;
		} else {
			levelBeingBuilt.removeGameObject(p);
			Integer counter = Character.getNumericValue(space.getName().charAt(1));
			factory.addRemovedCounter(className.toLowerCase(), counter);
			updateListeners(space, true);
			return true;
		}
	}

	/**
	 * Adds a specified GameObject at the specified point.
	 * @param p Point where the GameObject will be added.
	 * @param object Name of the class of the object that will be added.
	 * @param direction Direction the object is facing.
	 * @return A boolean representing whether a GameObject has been placed or not.
	 */
	
	public boolean addGameObject(Point p, String object, String direction) {
		object = object.toLowerCase();
		GameObject g = factory.getGameObject(p, object, direction);
		GameObject[][] board = levelBeingBuilt.getGameBoard();
		GameObject space = board[p.y][p.x];
		if (g == null) {
			factory.reduceCounter(object);
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
			}
		} else {
			if (validSpaceGameObject(space)) {
				levelBeingBuilt.placeGameObject(g);
				if (g instanceof Fox || g instanceof Rabbit) {
					levelBeingBuilt.addListener((MovableAnimal) g);
				}
				updateListeners(g, false);
				return true;
			} else {
				factory.reduceCounter(object);
			}
		}
		return false;

	}

	/**
	 * Updates the listeners after an event has happened.
	 * @param g The GameObject which was affected by the event.
	 * @param removeState Whether or not a GameObject has been removed from the board.
	 */
	private void updateListeners(GameObject g, boolean removeState) {
		for (LevelBuilderListener l : listeners) {
			l.handleEvent(g, removeState);
		}
	}
	/**
	 * Indicates whether or not a Fox can be placed into the specified space.  
	 * @param space The GameObject currently located at the space the Fox is trying to be placed into.
	 * @return A boolean representing whether or not a Fox can be placed at the specified space.
	 */
	private boolean validSpaceFox(GameObject space) {
		List<Point> list = Arrays.asList(Level.HOLES);
		return space.getName().equals("") && !list.contains(space.getCoordinate());
	}

	/**
	 * Indicates whether or not a Mushroom/Rabbit can be placed into the specified space.  
	 * @param space The GameObject currently located at the space the Mushroom/Rabbit is trying to be placed into.
	 * @return A boolean representing whether or not a Mushroom/Rabbit can be placed at the specified space.
	 */
	private boolean validSpaceGameObject(GameObject space) {
		return space.getName().equals("");
	}

}
