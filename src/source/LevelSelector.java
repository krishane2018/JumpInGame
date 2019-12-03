package source;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 
 * @author Nick Anderson and Aashna Narang
 *
 *         Initialize a game board
 */
public class LevelSelector {

	private static final String savedProgress = new File("").getAbsolutePath()+"\\saveLevel.txt";
	private static final String levels = new File("").getAbsolutePath()+"\\levels.xml";


	public static Level getLevel(int l, boolean isSaved) throws Exception {
		String file;
		file = isSaved ? savedProgress : levels;
		XMLHandler x = new XMLHandler(l);
		SAXParserFactory sax;
		sax = SAXParserFactory.newInstance();
		SAXParser parser = sax.newSAXParser();
		parser.parse(new File(file), x);
		return x.getWantedLevel();
	}	
}
	