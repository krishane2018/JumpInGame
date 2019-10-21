import java.awt.Point;
import java.util.EventObject;
/**
 * 
 * @author Aashna Narang
 * 
 * 
 *
 */
public class JumpInEvent extends EventObject {
	private GameObject chosenPiece; 
	private Point coordinate1;
	private Point coordinate2;
	private Point[] holes;
	
	
	public JumpInEvent(Object source, GameObject chosenPiece, Point p, Point[] holes) {
		super(source);
		this.chosenPiece = chosenPiece;
		this.coordinate1 = p;
		this.coordinate2 = new Point();
		this.holes = holes;
	}
	
	public JumpInEvent(Object source, GameObject chosenPiece, Point p1, Point p2, Point holes[]) {
		super(source);
		this.chosenPiece = chosenPiece;
		this.coordinate1 = p1;
		this.coordinate2 = p2;
		this.holes = holes;
	}

	public Point[] getHoles() {
		return holes;
	}


	public GameObject getChosenPiece() {
		return chosenPiece;
	}

	public void setChosenPiece(GameObject chosenPiece) {
		this.chosenPiece = chosenPiece;
	}

	public Point getCoordinate1() {
		return coordinate1;
	}

	public void setCoordinate1(Point coordinate1) {
		this.coordinate1 = coordinate1;
	}

	public Point getCoordinate2() {
		return coordinate2;
	}

	public void setCoordinate2(Point coordinate2) {
		this.coordinate2 = coordinate2;
	}

}
