import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Resources {
	public final static ImageIcon HOLE = resize(new ImageIcon("resources//brownCircle.png"));	
	public final static ImageIcon GREEN_CIRCLE = resize(new ImageIcon("resources//greenCircle.png"));
	public final static ImageIcon WHITE_RABBIT = resize(new ImageIcon("resources//whiteRabbit.png"));
	public final static ImageIcon BROWN_RABBIT = resize(new ImageIcon("resources//brownRabbit.png"));
	public final static ImageIcon GREY_RABBIT = resize(new ImageIcon("resources//greyRabbit.png"));
	public final static ImageIcon FOX_VERTICAL1 = resize(new ImageIcon("resources//foxVertical1.png"));
	public final static ImageIcon FOX_HORIZONTAL1 = resize(new ImageIcon("resources//foxHorizontal1.png"));
	public final static ImageIcon FOX_VERTICAL2 = resize(new ImageIcon("resources//foxVertical2.png"));
	public final static ImageIcon FOX_HORIZONTAL2 = resize(new ImageIcon("resources//foxHorizontal2.png"));
	public final static ImageIcon MUSHROOM = resize(new ImageIcon("resources//mushroom.png"));
	public final static ImageIcon HOLE_WITH_WHITE = resize(new ImageIcon("resources//holeWithWhite.png"));
	public final static ImageIcon HOLE_WITH_BROWN = resize(new ImageIcon("resources//holeWithBrown.png"));
	public final static ImageIcon HOLE_WITH_GREY = resize(new ImageIcon("resources//holeWithGrey.png"));
	
	public static ImageIcon resize(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(220, 150, java.awt.Image.SCALE_SMOOTH));
	}
}
