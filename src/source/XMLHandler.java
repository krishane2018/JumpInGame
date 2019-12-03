package source;

import java.awt.Point;
import java.util.HashMap;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.*;

/**
 * The XML handler interprets the xml file and creates a level of the type
 * Level.
 * 
 * @author Aashna Narang
 *
 */
public class XMLHandler extends DefaultHandler {
	private StringBuilder data;
	private Level levelModel;

	private String name;
	private Point coordinate1;
	private Point coordinate2;
	private int level;
	private String direction;

	private int wantedLevel;
	private Level wantedLevelModel;

	private HashMap<String, Boolean> states;

	public XMLHandler() {
		this(-2);
	}

	/**
	 * Constructor for the xml handler, sets all the variables and creates a
	 * "dictionary" of desired words to find in the xml
	 * 
	 * @param wantedLevel
	 */
	public XMLHandler(int wantedLevel) {
		states = new HashMap<String, Boolean>();
		String list[] = new String[] { "JumpIn", "isWantedLevel", "name", "x1", "x2", "y1", "y2", "level", "direction",
				"Rabbit", "Fox", "Mushroom" };
		for (String word : list) {
			states.put(word, false);
		}
		data = null;
		level = -1;
		name = "";
		coordinate1 = null;
		coordinate2 = null;
		direction = "";
		this.wantedLevel = wantedLevel;
	}

	/**
	 * Starts to parse through the xml file
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		states.replace(qName, true);
		data = new StringBuilder();
	}

	/**
	 * Continues to parse through the xml file until the end element is found
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		boolean justCreated = false;
		if (states.get("JumpIn") && states.get("isWantedLevel")) {
			wantedLevelModel = levelModel;
			states.replace("isWantedLevel", false);
		}
		if (states.get("level")) {
			level = Integer.parseInt(data.toString());
			if (level == wantedLevel) {
				states.replace("isWantedLevel", true);
			}
			levelModel = new Level(level);
			states.replace("level", false);
		} else if (states.get("name")) {
			name = endElementHelperString("name");
		} else if (states.get("x1")) {
			/**
			 * safety check that it is not null. Coordinate has to be null so we can include
			 * a check when initializing game objects to make sure a coordinate has been
			 * created.
			 */
			if (coordinate1 == null)
				coordinate1 = new Point();
			coordinate1.x = endElementHelperInt("x1", true);
		} else if (states.get("x2")) {
			if (coordinate2 == null)
				coordinate2 = new Point();
			coordinate2.x = endElementHelperInt("x2", false);
		} else if (states.get("y1")) {
			coordinate1.y = endElementHelperInt("y1", true);
		} else if (states.get("y2")) {
			coordinate2.y = endElementHelperInt("y2", false);
		} else if (states.get("direction")) {
			direction = endElementHelperString("direction");
		} else if (states.get("Rabbit")) {
			if (name != "" && !coordinate1.equals(null)) {
				Rabbit r = new Rabbit(coordinate1, name);
				levelModel.addListener(r);
				levelModel.placeGameObject(r);
			}
			states.replace("Rabbit", false);
			justCreated = true;
		} else if (states.get("Fox")) {
			if (name != "" && !coordinate1.equals(null) && !coordinate2.equals(null) && direction != "") {
				Fox f = new Fox(coordinate1, coordinate2, name, direction);
				levelModel.addListener(f);
				levelModel.placeGameObject(f);
			}
			states.replace("Fox", false);
			justCreated = true;
		} else if (states.get("Mushroom")) {
			GameObject g = new GameObject(coordinate1, name);
			levelModel.placeGameObject(g);
			states.replace("Mushroom", false);
			justCreated = true;
		}

		if (justCreated) {
			name = "";
			coordinate1 = new Point();
			coordinate2 = new Point();
			direction = "";
		}
	}

	/**
	 * 
	 * @param elem
	 * @param coordinateNum
	 * @return
	 */
	private int endElementHelperInt(String elem, boolean coordinateNum) {
		if (coordinateNum && coordinate1 == null)
			coordinate1 = new Point();
		else if (!coordinateNum && coordinate2 == null)
			coordinate2 = new Point();
		states.replace(elem, false);
		return Integer.parseInt(data.toString());
	}

	/**
	 * 
	 * @param elem
	 * @return
	 */
	private String endElementHelperString(String elem) {
		states.replace(elem, false);
		return new String(data.toString());
	}

	/**
	 * 
	 */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}

	/**
	 * 
	 */
	@Override
	public void endDocument() {
		if (wantedLevelModel == null) {
			wantedLevelModel = levelModel;
		}
	}

	/**
	 * returns the level after being parsed through
	 * 
	 * @return
	 */
	public Level getWantedLevel() {
		return wantedLevelModel;
	}

	/**
	 * gets the level number of the level from the xml file
	 * 
	 * @return
	 */
	public int getLevel() {
		return this.level;
	}
}
