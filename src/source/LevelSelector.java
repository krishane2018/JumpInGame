package source;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * 
 * @author Nick Anderson and Aashna Narang
 *
 *         Initialize a game board
 */
public class LevelSelector {

	private static final String savedProgress = new File("").getAbsolutePath()+"\\saveLevel.txt";
	private static final String levels = new File("").getAbsolutePath()+"\\levels.xml";


	public static Level getLevel(int l, boolean isSaved) {
		String file;
		file = isSaved?savedProgress:levels;
		System.out.println(file);
		XMLHandler x = new XMLHandler(l);
		SAXParserFactory sax;
		try {
			sax = SAXParserFactory.newInstance();
			SAXParser parser = sax.newSAXParser();
			parser.parse(new File(file), x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x.getWantedLevel();
	}	
}
	