
  
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;
import source.JumpIn;
import source.GameObjectFactory;
import source.Play;

class TestPlay {
	private Play play1;
	
	@BeforeEach 
	void setUp() throws Exception {
		play1 =new Play();
	}
	
	@Test
	void testFileIsEmptyNoFile() {
		String filePath = new File("").getAbsolutePath() + "\\empty2.xml";
		assertTrue(Play.fileIsEmpty(filePath));
	}
	@Test
	void testFileIsEmptyFalse() {
		String filePath = new File("").getAbsolutePath() + "\\levels.xml";
		assertFalse(Play.fileIsEmpty(filePath));
	}
}

