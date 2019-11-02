import java.awt.Color;
import java.awt.Point;
import javax.swing.border.LineBorder;

public class Board {
	public static void create(int level, JumpInView view) {
		// Initializing holes 
		GameButton[][] g = view.getButtons();
		for(Point p : LevelSelector.getHoles()) {
			g[p.y][p.x].setIcon(Resources.HOLE);
			g[p.y][p.x].setBorder(new LineBorder(Color.WHITE, 3, true));
//			g[p.y][p.x].setBorder(new RoundedBorder(15));
		}
		view.setButtons(g);
		
		// Initialize animals and mushrooms
	}
}
