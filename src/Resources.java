import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
	public final static ImageIcon HOLE_WITH_MUSHROOM = resizeMushroom(new ImageIcon("resources//mushroomInHole.png"));
	public final static ImageIcon HOLE_WITH_WHITE = resize(new ImageIcon("resources//whiteRabbitInHole.png"));
	public final static ImageIcon HOLE_WITH_BROWN = resize(new ImageIcon("resources//brownRabbitInHole.png"));
	public final static ImageIcon HOLE_WITH_GREY = resize(new ImageIcon("resources//greyRabbitInHole.png"));
	
	public static ImageIcon resize(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(220, 150, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon resizeRabbit(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(110, 130, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon resizeMushroom(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon resizeFoxV(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(155, 155, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon resizeFoxH(ImageIcon icon) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(155, 155, java.awt.Image.SCALE_SMOOTH));
	}
	
	//Add resize for horizontal fox and hole with brown + grey
	
	
}
