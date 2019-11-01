import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton implements ActionListener{
	ImageIcon icon;
	Point coordinate;
	//add way to check which image (boolean or byte)
	
	public GameButton(Point coordinate) {
//		icon = new ImageIcon(this.getClass().getResource("fox.png"));
//		icon = new ImageIcon(this.getClass().getResource("rabbit.png"));
//		icon = new ImageIcon(this.getClass().getResource("mushroom.png"));
		this.coordinate = coordinate;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if rabbit, fox, mushroom then setIcon()
		
	}
	
}
