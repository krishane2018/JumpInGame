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
	
	/**
	 * Create a model, view, controller for the level
	 * @param level - Level of the game 
	 */
	public static void play(int level) {
		Play.level = level;
		if (!(level > 0 && level <= 3)) level = 1;
		model = new JumpIn(level);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	
	/**
	 * Move onto the next level by updating the view with the new model, remove listeners from the old 
	 * controller and create a new model + controller.
	 */
	public static void nextLevel() {
		Play.level = level + 1;
		if (Play.level > 3) {
			view.handleDone();
		} else {
			controller.removeListener();
			model = new JumpIn(level);
			view.setModel(model);
			view.createNextBoard();
			controller = new JumpInController(view, model);
			view.getMMenu().showGame();
		}
	}	
	
	public static void loadGame(String filename) throws IOException {
		System.out.println(filename);
		GameObject[][] board = importFromXMLFile(filename);
		if (!(level > 0 && level <= 3)) level = 1;
		model = new JumpIn(level);
		model.setGameBoard(board);
		view = new JumpInView(model);
		controller = new JumpInController(view, model);
	}
	
	private static GameObject[][] importFromXMLFile(String filename) throws IOException  {
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser parser;
		GameObject[][] board = new GameObject[5][5];
		try {
			XMLHandler handler = new XMLHandler();
			parser = sax.newSAXParser();
			parser.parse(new File(filename), handler);
			level = handler.getLevel();
			board = handler.getGameBoard();
			System.out.println(handler.toString());
		} catch(SAXException e) {
			
		} catch(ParserConfigurationException e) {
			
		}
		return board;
	}
}
