import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.util.Arrays;

/**
 * 
 * @author Aashna Narang
 *
 */
public class GameButton extends JButton {
	private Point coordinate;
	// Either the action is 0 = nothing, 1 = selected, 2 = move animal to here
	private int act;
	// add way to check which type of button if needed
//	private final String[] PICTURE_OPTIONS = {"whiteRabbit", "brownRabbit", "greyRabbit", "hole", "greenCircle", "foxVertical", "foxHorizontal", "mushroom", "holeWhiteRabbit", "holeBrownRabbit", "holeGreyRabbit"};

	public GameButton(Point coordinate) {
		this(Resources.GREEN_CIRCLE, coordinate);
	}
	
	public GameButton(Icon icon, Point coordinate) {
		super(icon);
		this.coordinate = coordinate;
		act = 0;
		this.setBorder(new RoundedBorder(15));
		this.setBackground(new Color(70,170,70));
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	public void setAction(int act) {
		this.act = act;
	}
	
	public int getAct(int act) {
		return this.act;
	}
}