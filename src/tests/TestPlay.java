  
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;
import source.JumpIn;
import source.GameObjectFactory;
import source.Play;

class TestPlay {
	@BeforeEach 
	void setUp() Throws Exception {
		Play play1 =new Play(1);
	}
	
	@Test
	void testFileIsEmptyTrue() {
		String filePath = new File("").getAbsolutePath() + "\\empty.xml";
		assertTrue(play1.fileIsEmpty(filePath));
	}
	@Test
	void testFileIsEmptyNoFile() {
		String filePath = new File("").getAbsolutePath() + "\\empty2.xml";
		assertTrue(play1.fileIsEmpty(filePath));
	}
	@Test
	void testFileIsEmptyFalse() {
		String filePath = new File("").getAbsolutePath() + "\\levels.xml";
		assertFalse(play1.fileIsEmpty(filePath));
	}
	@Test
	void testImportFromXML() {
		String filePath = new File("").getAbsolutePath() + "\\levels.xml";
		JumpIn jump = new JumpIn(1, false);	
		assertEquals(jump, play1.importFromXML(filePath));
	}
	@Test
	void testNextLevel() {
		String filePath = new File("").getAbsolutePath() + "\\levels.xml";
		JumpIn jump = new JumpIn(2, false);	
		play1.nextLevel(filePath);
		assertEquals(jump, play1.importFromXML(filePath));
	}
}
