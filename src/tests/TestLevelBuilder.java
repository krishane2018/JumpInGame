package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import gui.CreatorView;
import gui.JumpInView;
import gui.MainMenu;
import source.JumpIn;
import source.Level;
import source.LevelBuilder;
import source.Rabbit;

class TestLevelBuilder {

	@Test
	public void testAddListenerSize() {
		LevelBuilder l = new LevelBuilder();
		JumpIn j = new JumpIn(1);
		JumpInView jv = new JumpInView(j);
		MainMenu m = new MainMenu(jv);
		CreatorView c = new CreatorView(m);
		l.addListener(c);
		assertEquals(1, l.getListeners().size());
	}
	
	@Test
	public void testAddListenerElement() {
		LevelBuilder l = new LevelBuilder();
		JumpIn j = new JumpIn(1);
		JumpInView jv = new JumpInView(j);
		MainMenu m = new MainMenu(jv);
		CreatorView c = new CreatorView(m);
		l.addListener(c);
		assertEquals(c, l.getListeners().get(0));
	}

}
