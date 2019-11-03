import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//THIS IS THE LISTENER 

public class JumpInController implements MouseListener {
	private JumpInView view;
	private JumpIn model;
	
	public JumpInController(JumpInView view, JumpIn model) {
		this.view = view; 
		this.model = model;
		GameButton[][] g = view.getButtons();
		for (int i = 0; i < g.length; i ++)  {
			for (int j = 0; j < g.length; j ++) {
				g[i][j].addMouseListener(this);
			}
		}
		view.setButtons(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.print("clicked");
		
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
		System.out.println("Mouse");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
