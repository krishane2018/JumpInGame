package source;

import java.io.File;
import java.io.FileNotFoundException;
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
	
	public static void loadGame(String file, boolean fromLevel, int level) throws IOException {
		filename = file;
		controller.removeListener();
		if(fromLevel) {
			model = importFromXMLWithLevel(filename, true, 1);
		} else {
			model = importFromXMLFile(filename);
		}
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
		}
		view.setModel(model);
		view.createNextBoard();
		controller = new JumpInController(view, model);
		view.getMMenu().showGame();
	}
	
	private static JumpIn importFromXMLFile(String filename) throws IOException  {
		return importFromXMLWithLevel(filename, false, -2);
	}
	
	private static JumpIn importFromXMLWithLevel(String filename, boolean fromLevel, int level) throws IOException {
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
			parser.parse(new File(filename), handler);
			Level modelLevel = handler.getWantedLevel();
			level = modelLevel.getLevel();
			model = new JumpIn (modelLevel);
			return model;
		} catch(SAXException e) {
			view.showMainMenu();
		} catch(ParserConfigurationException e) {
			System.out.println("error2");
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
		} catch (FileNotFoundException e) {
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
}
