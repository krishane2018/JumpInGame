package source;

import java.awt.Point;
import java.util.HashMap;
import java.util.Stack;

public class GameObjectFactory {
	private HashMap<String, Integer> nameCounters;
	private HashMap<String, Stack<Integer>> removedCounters;
	public static final int NUM_ROWS = JumpIn.NUM_ROWS;
	public static final int NUM_COLUMNS = JumpIn.NUM_COLUMNS;

	/**
	 * Constructs new instance of GameObjectFactory
	 */
	public GameObjectFactory() {
		nameCounters = new HashMap<String, Integer>();
		removedCounters = new HashMap<String, Stack<Integer>>();
		addToHashMaps();
	}

	/**
	 * Initializes HashMaps.
	 */
	private void addToHashMaps() {
		String objects[] = new String[] { "mushroom", "fox", "rabbit" };
		for (String s : objects) {
			nameCounters.put(s, 1);
			removedCounters.put(s, new Stack<Integer>());
		}
	}

	/**
	 * Returns a GameObject based on the parameters given.
	 * @param p Point that the GameObject should occupy.
	 * @param object Name of the class of the object that is suppose to be created.
	 * @param direction Direction of the GameObject.
	 * @return A GameObject based on the parameters given.
	 */
	public GameObject getGameObject(Point p, String object, String direction) {
		object = object.toLowerCase();
		int counter = nextCounter(object);
		if (object.equalsIgnoreCase("Rabbit")) {
			return new Rabbit(p, "R" + counter);
		} else if (object.equalsIgnoreCase("Fox")) {
			try {
				return foxMaker(p, direction, counter);
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else if (object.equalsIgnoreCase("Mushroom")) {
			return new GameObject(p, "M" + counter);
		} else {
			return null;
		}
	}

	/**
	 * Helper method used to make Fox objects.
	 * @param p Point which the Fox should occupy.
	 * @param direction Direction of the fox.
	 * @param counter Integer associated with the name of the Fox.
	 * @return
	 */
	private Fox foxMaker(Point p, String direction, int counter) {
		Point p2 = new Point(p);
		if (direction.equalsIgnoreCase("vertical")) {
			p2.y = foxMakerHelper(p2.y);
		} else if (direction.equalsIgnoreCase("horizontal")) {
			p2.x = foxMakerHelper(p2.x);
		}
		return new Fox(p, p2, "F" + counter, direction);
	}

	/**
	 * Helper method for FoxMaker.
	 * @param coord Second different coordinate of the Fox.
	 * @return An integer of the second different coordinate of the Fox.
	 */
	private int foxMakerHelper(int coord) {
		coord += 1;
		if (coord == NUM_ROWS) {
			coord -= 2;
		}
		return coord;
	}

	/**
	 * Gets the next integer that is to be associated with the name of the GameObject.
	 * @param object Name of the class of the GameObject.
	 * @return Integer that is to be associated with the name of the GameObject.
	 */
	private int nextCounter(String object) {
		Stack<Integer> tempStack = removedCounters.get(object);
		if (tempStack.isEmpty()) {
			int counter = nameCounters.get(object.toLowerCase());
			nameCounters.put(object, counter + 1);
			return counter;
		} else {
			return tempStack.pop();
		}
	}
	/**
	 * Reduces the counter of the given GameObject.
	 * @param object The GameObject whose counter is to be reduced.
	 */
	public void reduceCounter(String object) {
		int initialCounter = nameCounters.get(object);
		nameCounters.put(object, initialCounter - 1);
	}

	/**
	 * Adds the integer that was associated with a removed GameObject to a HashMap to be used later.
	 * @param object Name of the class of the GameObject which was removed.
	 * @param counter The integer that was associated with the removed GameObject.
	 */
	public void addRemovedCounter(String object, int counter) {
		Stack<Integer> tempStack = removedCounters.get(object);
		tempStack.add(counter);
	}
}
