import java.awt.Point;

public class Fox extends GameObject implements JumpInListener{
	private Point coordinate2; 
	private String direction;
	
	public Fox(Point p1, Point p2, String name, String direction) {
		super(p1, name);
		this.coordinate2 = p2; 
		this.direction = direction;
	}

	public Point getCoordinate2() {
		return coordinate2;
	}

	public void setCoordinate2(Point coordinate2) {
		this.coordinate2 = coordinate2;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@Override
	public void handleEvent(JumpInEvent e) {
		Fox f = (Fox)e.getChosenPiece();
		if(f.equals(this)) {
			// should super's x,y variables be protected? so just do super.x = __
			super.setCoordinate(e.getCoordinate1());
			setCoordinate2(e.getCoordinate2());
		}
	}
	
}
