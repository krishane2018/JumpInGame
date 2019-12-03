
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.*;

import source.Fox;
import source.GameObject;
import source.Rabbit;
import source.Level;

class TestLevel {

	@BeforeEach
	void setUp() throws Exception {
		Level level1 = new Level(1);
	}
	
	@Test
	void testGetLevel() {
		assertTrue(level1.getLevel() == 1);
	}
	@Test
	void testSetLevel() {
		level1.setLevel(3);
		assertTrue(level1.getLevel() == 3);
	}
	@Test
	void testGetHoles() {
		assertEquals(level1.getHoles(), new Point[] { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
			new Point(4, 4) });
	}
	@Test
	void testGetGameBoard() {
		newGameBoard = new GameObject[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				gameBoard[j][i] = new GameObject(new Point(i, j));
			}
		}
		assertEquals(level1.getGameBoard(), newGameBoard);
	}
	
	@Test
	void testPlaceGameObjectMushroom() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		level1.placeGameObject(Mush1);
		assertSame(level1.getGameBoard()[0][1], Mush1);
	}
	@Test
	void testPlaceGameObjectRabbit() {
		Rabbit R1 = new Rabbit(new Point(0, 1), "R1");
		level1.placeGameObject(R1);
		assertSame(level1.getGameBoard()[0][1], R1);
	}
	@Test
	void testPlaceGameObjectFox() {
		Fox F1 = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		level1.placeGameObject(F1);
		assertSame(level1.getGameBoard()[0][1], F1);
		assertSame(level1.getGameBoard()[0][2], F1);
	}
	
	@Test
	void testRemoveGameObjectMushroom() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		level1.placeGameObject(Mush1);
		level1.removeGameObject(new Point(0, 1));
		assertNotSame(level1.getGameBoard()[0][1], Mush1);
	}
	@Test
	void testRemoveGameObjectRabbit() {
		Rabbit R1 = new Rabbit(new Point(0, 1), "R1");
		level1.placeGameObject(R1);
		level1.removeGameObject(new Point(0, 1));
		assertNotSame(level1.getGameBoard()[0][1], R1);
	}
	@Test
	void testRemoveGameObjectFox1() {
		Fox F1 = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		level1.placeGameObject(F1);
		level1.removeGameObject(new Point(0, 1));
		assertNotSame(level1.getGameBoard()[0][1], F1);
		assertNotSame(level1.getGameBoard()[0][2], F1);
	}
	@Test
	void testRemoveGameObjectFox2() {
		Fox F1 = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		level1.placeGameObject(F1);
		level1.removeGameObject(new Point(0, 2));
		assertNotSame(level1.getGameBoard()[0][1], F1);
		assertNotSame(level1.getGameBoard()[0][2], F1);
	}
	@Test
	void testGetListenersEmpty() {
		assertNull(level1.getListeners()[0]);
	}
	@Test
	void testAddListener() {
		Rabbit R1 = new Rabbit(new Point(0, 1), "R1");
		level1.addListener(R1);
		assertSame(level1.getListeners()[0], R1);
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
  
  @Test
	void testConstructor() {
		assertNotNull(level);
	}

	@Test
	void testConstructor1Args() {
		assertNotNull(level1);
	}
}


