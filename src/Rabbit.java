import java.awt.Point;
/**
 * 
 * @author Aashna Narang
 * 
 * 
 *
 */
public class Rabbit extends GameObject implements JumpInListener{
	private boolean status; 
	
	public Rabbit(Point p, String name) {
		super(p,name);
		status = false;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return this.status;
	}

	@Override
	public void handleEvent(JumpInEvent e) {
		Rabbit r = (Rabbit)e.getChosenPiece();
		if(r.equals(this)) {
			// should super's x,y variables be protected? so just do super.x = __
			super.setCoordinate(e.getCoordinate1());
			for(Point p : e.getHoles()) {		
				if(p.equals(super.getCoordinate())) {
					this.status = true;
				}
			}	
		}
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		if(this == o) return true;
		
		Rabbit r = (Rabbit) o;
		return (super.getCoordinate() == r.getCoordinate() & this.status == r.getStatus() & super.getName() == r.getName());
	}
	
	
}
