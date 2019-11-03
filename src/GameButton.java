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
	private ImageIcon icon;
	private Point coordinate;
	// Either the action is picking an animal (0/false) or moving an animal (1/true)
	private Boolean action;
	// add way to check which type of button if needed
	private final String[] PICTURE_OPTIONS = {"whiteRabbit", "brownRabbit", "greyRabbit", "hole", "foxVertical", "foxHorizontal", "mushroom", "holeWhiteRabbit", "holeBrownRabbit", "holeGreyRabbit"};

	
	public GameButton(Point coordinate, ImageIcon icon) {
		super(icon);
		this.setBackground(new Color(70,170,70));
		this.coordinate = coordinate;
		this.setBorder(new RoundedBorder(15));
//		this.setIcon(icon);
//		this.setIcon(icon); 
//		g.setColor(new Color(60,150,80));
//		g.fillOval(this.getHorizontalAlignment(), this.getVerticalAlignment(), 20, 20);
//		this.paint(g);
	}
	
	public GameButton(Point coordinate, String picture) {
		this(coordinate, new ImageIcon(picture));
	}

	public GameButton(Point coordinate) {
		this.setBackground(new Color(70,170,70));
		this.coordinate = coordinate;
		this.setBorder(new RoundedBorder(15));
	}
	

	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * The user pick between any of the options listed in PICTURE_OPTIONS and use that to 
	 * form a string to get the correct image. It then retrieves that image and makes that 
	 * the icon of the button
	 * @param pictureName the name of the picture the user would like to use as the icon
	 */
	public void setIcon(String pictureName) {
		if(Arrays.asList(PICTURE_OPTIONS).contains(pictureName)) {
			throw new IllegalArgumentException("Please select a valid picture name");
		}
		this.icon = new ImageIcon(this.getClass().getResource(pictureName + ".png"));
		this.setIcon(icon);
		
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	public void setAction(Boolean action) {
		this.action = action;
	}
	
	// find new name, getAction might be confusing
//	public Boolean getAction(Boolean action) {
//		return this.action;
//	}

	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}