import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

public class Board {
	
	/**
	 * Create the GUI for the board based on the initial positions of each object for the current level 
	 * @param view - The object that contains all of the GUI configuration
	 * @param model - The object that takes care setting up the game and has the positions of all elements
	 */
	public static void create(JumpInView view, JumpIn model) {
		// Initializing holes 
		GameButton[][] g = view.getButtons();
		for(Point p : LevelSelector.getHoles()) {
			g[p.y][p.x].setIcon(Resources.HOLE);
		}
		view.setButtons(g);
		
		// Initialize Rabbit Images
		ImageIcon rabbits[] = {Resources.BROWN_RABBIT, Resources.GREY_RABBIT, Resources.WHITE_RABBIT};
		ImageIcon rabbitsInHole[] = {Resources.HOLE_WITH_BROWN, Resources.HOLE_WITH_GREY, Resources.HOLE_WITH_WHITE};
		g = iterateThroughImages(rabbits, rabbitsInHole, model.getInitialRabbitPositions(), g);
		
		//Initialize Mushroom Images
		ImageIcon mushroom[] = {Resources.MUSHROOM};
		ImageIcon[] mushroomInHole = {Resources.HOLE_WITH_MUSHROOM};
		g = iterateThroughImages(mushroom, mushroomInHole, model.getInitialMushroomPositions(), g);
		
		// Initialize Fox Images
		g = iterateThroughImagesFox(model.getInitialFoxPositions(), g);
	}
	
	/**
	 * Private helper function used to go through each mushroom and rabbit image and initialize board piece with 
	 * each icon in the given list. If a the list has more than one image, it will iterate through each image 
	 * making sure the type of image is evenly distributed. For example, if there are 3 rabbits - this will 
	 * go through each image and make sure each rabbit is different.
	 * @param gameObjects - The icon images used for regular rabbits and mushrooms
	 * @param gameObjects2 - The icon images used for when rabbits are in holes or mushrooms are in holes
	 * @param positions - The positions of where each type of object goes 
	 * @param g - The Game Buttons used in the GUI which will be updated with the right images
	 * @return the updated game buttons with the right images on them
	 */
	private static GameButton[][] iterateThroughImages(ImageIcon[] gameObjects, ImageIcon[] gameObjects2,
			ArrayList<Point> positions, GameButton[][] g) {
		int i = 0;
		List<Point> list = Arrays.asList(LevelSelector.getHoles());
		for (Point p : positions) {
			if (list.contains(p)) {
				setIconHelper(gameObjects2[i], g, p);
			}
			else {
				setIconHelper(gameObjects[i], g, p);
			}
			i++; 
			i = i%(gameObjects.length);
		}
		return g;
	}
	
	/**
	 * Private helper function to iterate through the positions of the foxes in the level and initializing it to a 
	 * certain image depending on whether the fox at that certain point is vertical or horizontal.
	 * @param hash - hashmap of coordinates of the fox and whether or not they are vertical or horizontal
	 * @param g - The Game Buttons used in the GUI which will be updated with the right images
	 * @return the updated game buttons with the right images on them
	 */
	private static GameButton[][] iterateThroughImagesFox(HashMap<ArrayList<Point>, String> hash, GameButton[][] g) {
		for (ArrayList<Point> p : hash.keySet()) {
			Point p0 = p.get(0);
			Point p1 = p.get(1);
			if (hash.get(p) == "Vertical") {
				setIconHelper(Resources.FOX_VERTICAL2, g, p0);
				setIconHelper(Resources.FOX_VERTICAL1, g, p1);
			} else {
				setIconHelper(Resources.FOX_HORIZONTAL1, g, p0);
				setIconHelper(Resources.FOX_HORIZONTAL2, g, p1);
			}
		}
		return g;
	}
	
	/**
	 * private helper function to reduce repetition of setting the icon of a button at a specific location
	 * @param icon The image the user would like to set the button to
	 * @param g - the game buttons used in the GUI
	 * @param p - the coordinate of which button the user would like to update with the given image
	 */
	private static void setIconHelper(ImageIcon icon, GameButton[][] g, Point p) {
		g[p.x][p.y].setIcon(icon);
	}
}
