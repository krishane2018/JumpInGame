import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Board {
	public static void create(int level, JumpInView view, JumpIn model) {
		// Initializing holes 
		GameButton[][] g = view.getButtons();
		for(Point p : LevelSelector.getHoles()) {
			g[p.y][p.x].setIcon(Resources.HOLE);
			g[p.y][p.x].setBorder(new LineBorder(Color.WHITE, 3, true));
//			g[p.y][p.x].setBorder(new RoundedBorder(15));
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
	
	//INCLUDE LOGIC FOR IF RABBIT IN A HOLE
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
	
	// CHECK HORIZONTAL
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
	
	private static void setIconHelper(ImageIcon icon, GameButton[][] g, Point p) {
		g[p.x][p.y].setIcon(icon);
	}
}
