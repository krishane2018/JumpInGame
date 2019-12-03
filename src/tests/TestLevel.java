package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;
import source.Level;

class TestLevel {

	private Level level;
	private Level level1;
	
	@BeforeEach
	void setUp() throws Exception {
		level = new Level();
		level1 = new Level(1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor() {
		assertNotNull(level);
	}

	@Test
	void testConstructor1Args() {
		assertNotNull(level1);
	}
	
	@Test
	void testSetLevel() {
		level.setLevel(1);
		assertEquals(1, level.getLevel());
	}
	
	@Test
	void testGetLevel() {
		assertEquals(1, level1.getLevel());
	}
	
	@Test
	void testPlaceGameObject() {
		GameObject g = new GameObject(new Point(2,2),"Rabbit");
		level.placeGameObject(g);
		assertEquals(level.getGameBoard()[2][2].getName(), "Rabbit");
	}
	
	@Test
	void testRemoveGameObject() {
		GameObject g = new GameObject(new Point(2,2),"Rabbit");
		level.placeGameObject(g);
		level.removeGameObject(new Point(2,2));
		assertNotEquals(level.getGameBoard()[2][2].getName(), "Rabbit");
	}
	
	@Test
	void testGetGameObject() {
		assertTrue(level1.getGameBoard() instanceof GameObject[][]);
	}
	
	@Test
	void testIsHole() {
		assertEquals(true, Level.isHole(0, 0)&&Level.isHole(0, 4)
				&&Level.isHole(2, 2)&&Level.isHole(4, 0)&&Level.isHole(4, 4));
	}
	
	@Test
	void testToString() {
		String board = "--------------------------\n"
				+ "|   H|    |    |    |   H|\n"
				+ "--------------------------\n"
				+ "|    |    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|    |    |   H|    |    |\n"
				+ "--------------------------\n"
				+ "|    |    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|   H|    |    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(level1.toString(),board);
	}
}
