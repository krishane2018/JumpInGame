import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Main menu button acts a a default button for the main menu.
 * 
 * @author Kush Gopeechund
 *
 */
public class MainMenuButton extends JButton{
	private static final long serialVersionUID = 1L;

	public MainMenuButton(String title) {
		super(title);
		this.setBackground(new Color(70,170,70));
		this.setForeground(Color.white);
		this.setFocusPainted(false);
		this.setFont(new Font("Arial", Font.BOLD,20));
	}
}
