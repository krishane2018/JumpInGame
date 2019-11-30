package source;

import java.awt.Point;
import java.util.HashMap;
import java.util.Stack;

public class GameObjectFactory {
	private HashMap<String, Integer> nameCounters;
	private HashMap<String, Stack<Integer>> removedCounters;
	
	public GameObjectFactory() {
		nameCounters = new HashMap<String, Integer>();
		nameCounters.put("mushroom", 1);
		nameCounters.put("rabbit", 1);
		nameCounters.put("fox", 1);
		removedCounters = new HashMap<String, Stack<Integer>>();
		removedCounters.put("mushroom", new Stack<Integer>());
		removedCounters.put("rabbit", new Stack<Integer>());
		removedCounters.put("fox", new Stack<Integer>());
	}
	
	public GameObject getGameObject(Point p, String object, String direction) {
		object = object.toLowerCase();
		int counter = nextCounter(object);
		if (object.equalsIgnoreCase("Rabbit")) {
			return new Rabbit(p, "R"+counter);
		}
		else if (object.equalsIgnoreCase("Fox")) {
			try {
				return foxMaker(p, direction, counter);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		else if (object.equalsIgnoreCase("Mushroom")){
			return new GameObject(p, "M"+counter);
		}
		else {
			return null;
		}
	}
	
	private Fox foxMaker (Point p, String direction, int counter) {
		Point p2 = new Point(p);
		if (direction.equalsIgnoreCase("vertical")) {
			p2.y+=1;
		}
		else if (direction.equalsIgnoreCase("horizontal")) {
			p2.x+=1;
		}
		return new Fox (p, p2, "F"+counter, direction);
	}
	
	private int nextCounter(String object) {
		Stack<Integer> tempStack = removedCounters.get(object);
		if (tempStack.isEmpty()) {
			int counter = nameCounters.get(object.toLowerCase());
			nameCounters.put(object, counter+1);
			return counter;
		}
		else {
			return tempStack.pop();
		}
	}
	
	public void reduceCounter(String object) {
		System.out.println(object);
		int initialCounter = nameCounters.get(object);
		nameCounters.put(object, initialCounter-1);
	}
	
	public void addRemovedCounter (String object, int counter) {
		Stack<Integer> tempStack = removedCounters.get(object);
		tempStack.add(counter);
	}
}
