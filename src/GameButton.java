import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * 
 * @author Aashna Narang
 *
 */
public class GameButton extends JButton implements ActionListener, MouseListener {
	private ImageIcon icon;
	private Point coordinate;
	// Either the action is picking an animal (0/false) or moving an animal (1/true)
	private Boolean action;
	// add way to check which type of button if needed
	private final String[] PICTURE_OPTIONS = {"whiteRabbit", "brownRabbit", "greyRabbit", "hole", "foxVertical", "foxHorizontal", "mushroom", "holeWhiteRabbit", "holeBrownRabbit", "holeGreyRabbit"};

	public GameButton(Point coordinate) {
		icon = new ImageIcon();
		this.coordinate = coordinate;
		this.addActionListener(this);
		this.addMouseListener(this);
		this.setIcon(icon);
	}
	
	public GameButton(Point coordinate, String picture) {
		this(coordinate);
		this.setIcon(picture);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		action ? JumpIn.getOptions(coordinate) : JumpIn.moveAnimal(coordinate);
//		System.out.println(e.getActionCommand());
//		System.out.println(e.getSource());		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("Mouse hover");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
