package source;

import java.io.File;
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
		if (!(level > 0 && level <= 3)) level = 1;
		model = new JumpIn(level, false);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	
	/**
	 * Move onto the next level by updating the view with the new model, remove listeners from the old 
	 * controller and create a new model + controller.
	 */
	public static void nextLevel(String file) {
		filename = file;
		Play.level = level + 1;
		if (Play.level > LevelBuilder.nextLevelNumber()) {
			view.handleDone();
		} else {
			try {
				changeBoard(true);
			} catch (IOException e) {
				//add something but shouldn't get here bc input = true
			}
		}
	}	
	
	public static void loadGame(String file) throws IOException {
		filename = file;
		if (!(level > 0 && level <= 3)) level = 1;
		controller.removeListener();
		model = importFromXMLFile(filename);
		view.setModel(model);
		view.createNextBoard();
		controller = new JumpInController(view, model);
		view.getMMenu().showGame();
	}
	
	
	private static void changeBoard(boolean nextLevel) throws IOException {
		controller.removeListener();
		if (nextLevel) {
			model = new JumpIn(level, false);
		} else {
			model = importFromXMLFile(filename);
			System.out.println(model.toString());
		}
		view.setModel(model);
		view.createNextBoard();
		controller = new JumpInController(view, model);
		view.getMMenu().showGame();
	}
	
	private static JumpIn importFromXMLFile(String filename) throws IOException  {
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			XMLHandler handler = new XMLHandler();
			parser = sax.newSAXParser();
			parser.parse(new File(filename), handler);
			Level modelLevel = handler.getWantedLevel();
			level = modelLevel.getLevel();
			model = new JumpIn (modelLevel);
			return model;
		} catch(SAXException e) {
			
		} catch(ParserConfigurationException e) {
			
		}
		return model;
	}
}
