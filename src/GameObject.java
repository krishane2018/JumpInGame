import java.awt.Point;
/**
 * 
 * @author Aashna Narang
 * 
 * 
 *
 */
public class GameObject {
	private Point coordinate;
	private String name;
	
	public GameObject(Point p, String name) {
		this.coordinate = p;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCoordinate() {
		return coordinate;
	}
	
	public int getX1() {
		return (int) this.coordinate.getX();
	}
	
	public int getY1() {
		return (int) this.coordinate.getY();
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

}
