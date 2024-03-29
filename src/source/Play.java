package source;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gui.JumpInController;
import gui.JumpInView;

/**
 * 
 * @author Aashna Narang
 *
 */

public class Play {
	
	private static JumpIn model;
	private static JumpInView view;
	private static JumpInController controller;
	public static int level;
	private static String filename;
	
	/**
	 * Create a model, view, controller for the level
	 * @param level - Level of the game 
	 */
	public static void play(int level) {
		Play.level = level;
		model = new JumpIn(level, false);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	
	/**
	 * Move onto the next level by updating the view with the new model, remove listeners from the old 
	 * controller and create a new model + controller.
	 */
	public static void nextLevel(String file) {
		Play.level = level + 1;
		if (Play.level > LevelBuilder.nextLevelNumber()) {
			view.handleDone();
		} else {
			try {
				updateBoard(file, true, false, Play.level);
			} catch (IOException e) {
				view.displayError(4);
			}
		}
	}	
	
	public static void updateBoard(String file, boolean nextLevel, boolean fromLevel, int level) throws IOException {
		filename = file;
		controller.removeListener();
		if (nextLevel) {
			model = new JumpIn(level, false);
		} else if(fromLevel) {
			model = importFromXML(filename, true, 1);
		} else {
			importFromXML(filename, false, -2);
		}
		view.setModel(model);
		view.createNextBoard();
		controller = new JumpInController(view, model);
		view.getMMenu().showGame();
	}
	
	private static JumpIn importFromXML(String filename, boolean fromLevel, int level) throws IOException {
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			XMLHandler handler;
			if(fromLevel) {
				handler = new XMLHandler(level);
			} else {
				handler = new XMLHandler();
			}	
			parser = sax.newSAXParser();
			try {
				parser.parse(new File(filename), handler);
			} catch (IOException e) {
				view.displayError(4);
			}
			Level modelLevel = handler.getWantedLevel();
			Play.level = modelLevel.getLevel();
			model = new JumpIn (modelLevel);
			return model;
		} catch(SAXException e) {
			view.showMainMenu();
		} catch(ParserConfigurationException e) {
			view.displayError(4);
		}
		return model;
	}
	
	public static boolean fileIsEmpty(String filename) {
		File file = new File(filename);
		FileReader reader;
		try {
			reader = new FileReader(file);
			boolean ans = reader.read() == -1;
			reader.close();
			return ans;
		} catch (Exception e) {
			// Treat file not found / any error as it is empty (bc it's unreacheable)
			return true;
		}
	}
}
