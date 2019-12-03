package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * 
 * Sets up the side panel buttons, adding a tag feature so no text is needed.
 * 
 * @author Kush Gopeechund
 *
 */
public class SelectorButton extends JButton {

	private String tag;

	/**
	 * Sets up the name and tag
	 * @param text
	 * @param tag
	 */
	public SelectorButton(String text, String tag) {
		super(text);
		this.tag = tag;

		setBackground(new Color(41, 163, 41));
		setPreferredSize(new Dimension(145, 145));
	}

	/**
	 * getter for the tag to make the buttons unique
	 * @return
	 */
	public String getTag() {
		return tag;
	}
}
