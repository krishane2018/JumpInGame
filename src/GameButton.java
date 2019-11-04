import java.awt.Color;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * 
 * @author Aashna Narang
 *
 */
public class GameButton extends JButton {
	private Point coordinate;
	private Color background;

	public GameButton(Point coordinate) {
		this(Resources.GREEN_CIRCLE, coordinate);
	}
	
	public GameButton(ImageIcon icon, Point coordinate) {
		super(icon);
		this.coordinate = coordinate;
		background = new Color(70,170,70);
		this.setBackground(background);
		this.setBorderPainted(false);
//		this.setBorder(new RoundedBorder(15));
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void highlightButton() {
		this.setBackground(new Color(70,200,70));
	}
	
	public void removeHighlight() {
		this.setBackground(background);
	}
}