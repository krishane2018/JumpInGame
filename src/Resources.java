import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * This class is used to avoid initializing the same Image Icons over and over again. 
 * This class also includes a dictionary with key value pairs that include a specific rabbit 
 * and the corresponding image of that rabbit in a hole
 * @author Aashna Narang
 *
 */
public class Resources {
	public final static ImageIcon HOLE = resize(new ImageIcon("resources//brownCircle.png"));	
	public final static ImageIcon GREEN_CIRCLE = resize(new ImageIcon("resources//greenCircle.png"));
	public final static ImageIcon WHITE_RABBIT = resizeRabbit(new ImageIcon("resources//whiteRabbit.png"));
	public final static ImageIcon BROWN_RABBIT = resizeRabbit(new ImageIcon("resources//brownRabbit.png"));
	public final static ImageIcon GREY_RABBIT = resizeRabbit(new ImageIcon("resources//greyRabbit.png"));
	public final static ImageIcon FOX_VERTICAL1 = resizeFoxV(new ImageIcon("resources//foxVertical1.png"));
	public final static ImageIcon FOX_HORIZONTAL1 = resizeFoxH(new ImageIcon("resources//foxHorizontal1.png"));
	public final static ImageIcon FOX_VERTICAL2 = resizeFoxV(new ImageIcon("resources//foxVertical2.png"));
	public final static ImageIcon FOX_HORIZONTAL2 = resizeFoxH(new ImageIcon("resources//foxHorizontal2.png"));
	public final static ImageIcon MUSHROOM = resizeMushroom(new ImageIcon("resources//mushroom.png"));
	public final static ImageIcon LOGO = new ImageIcon("resources//logo.png");
	public final static ImageIcon WIN = resizeTrophy(new ImageIcon("resources//win.png"));
	public final static ImageIcon HOLE_WITH_MUSHROOM = resizeMushroom(new ImageIcon("resources//mushroomInHole.png"));
	public final static ImageIcon HOLE_WITH_WHITE = resize(new ImageIcon("resources//whiteRabbitInHole.png"));
	public final static ImageIcon HOLE_WITH_BROWN = resize(new ImageIcon("resources//brownRabbitInHole.png"));
	public final static ImageIcon HOLE_WITH_GREY = resize(new ImageIcon("resources//greyRabbitInHole.png"));
	public final static HashMap<ImageIcon, ImageIcon> FLIPPED_RABBIT = createHashmap();
	
	/**
	 * Resize an image that includes a brown circle, a green circle or rabbit in a hole
	 * @param icon The icon the user would like to resize
	 * @return the resized image icon
	 */
	public static ImageIcon resize(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(220, 150, java.awt.Image.SCALE_SMOOTH));
	}
	
	private static ImageIcon resizeTrophy(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
	}

	/**
	 * Resize an rabbit image to fit in the button properly
	 * @param icon The icon the user would like to resize
	 * @return the resized image icon
	 */
	public static ImageIcon resizeRabbit(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(110, 130, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
	 * Resize a mushroom image to fit in the button properly
	 * @param icon The icon the user would like to resize
	 * @return the resized image icon
	 */
	public static ImageIcon resizeMushroom(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
	 * Resize a vertical fox image to fit in the button properly
	 * @param icon The icon the user would like to resize
	 * @return the resized image icon
	 */
	public static ImageIcon resizeFoxV(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(155, 155, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
	 * Resize a horizontal fox image to fit in the button properly
	 * @param icon The icon the user would like to resize
	 * @return the resized image icon
	 */
	public static ImageIcon resizeFoxH(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(164, 164, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
	 * Create a hash map will all of the images of rabbits in a hole mapped to the original rabbit image 
	 * and vice versa
	 * @return The hashmap containing all of the key value pairs with rabbits and rabbits in a hole
	 */
	private static HashMap<ImageIcon, ImageIcon> createHashmap() {
		HashMap<ImageIcon, ImageIcon> d = new HashMap<ImageIcon, ImageIcon>();
		d.put(Resources.BROWN_RABBIT, Resources.HOLE_WITH_BROWN);
		d.put(Resources.WHITE_RABBIT, Resources.HOLE_WITH_WHITE);
		d.put(Resources.GREY_RABBIT, Resources.HOLE_WITH_GREY);
		d.put(Resources.HOLE_WITH_BROWN, Resources.BROWN_RABBIT);
		d.put(Resources.HOLE_WITH_WHITE, Resources.WHITE_RABBIT);
		d.put(Resources.HOLE_WITH_GREY, Resources.GREY_RABBIT);
		return d;
	}
	
}
