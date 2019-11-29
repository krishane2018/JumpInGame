package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class SelectorButton extends JButton{

	private String tag;
	
	public SelectorButton(String text, String tag) {
		super(text);
		this.tag=tag;
		
		setBackground(new Color(41, 163, 41));
		setPreferredSize(new Dimension(145, 145));
	}
	
	public String getTag() {
		return tag;
	}
}
