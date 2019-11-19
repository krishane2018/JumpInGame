package gui;
import java.awt.Color;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * Class of JButtons but with coordinates and different coloured backgrounds
 * @author Aashna Narang
 *
 */
public class GameButton extends JButton {
	private static final long serialVersionUID = 1L;
	private Point coordinate;
	private Color background;

	/**
	 * Initialize a default button using the default icon at a given coordinate
	 * @param coordinate - Location/ x,y coordinate of the button on the board
	 */
	public GameButton(Point coordinate) {
		this(Resources.GREEN_CIRCLE, coordinate);
	}
	
	/**
	 * Initialize a button with an icon at a given coordinate on the board
	 * @param icon - Given image to put on button
	 * @param coordinate - Location/ x,y coordinate of the button on the board
	 */
	public GameButton(ImageIcon icon, Point coordinate) {
		super(icon);
		this.coordinate = coordinate;
		background = new Color(70,170,70);
		this.setBackground(background);
		this.setBorderPainted(false);
	}

	/**
	 * Get the x,y coordinate of the button
	 * @return Point object with coordinates
	 */
	public Point getCoordinate() {
		return coordinate;
	}

	/**
	 * Set coordinate of the button
	 * @param coordinate Point object with coordinates
	 */
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Highlight the button by changing the background to a lighter colour
	 */
	public void highlightButton() {
		this.setBackground(new Color(70,200,70));
	}
	
	/**
	 * Remove the highlight by setting the background to the original colour
	 */
	public void removeHighlight() {
		this.setBackground(background);
	}
}