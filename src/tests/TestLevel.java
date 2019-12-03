package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Fox;
import source.GameObject;
import source.JumpIn;
import source.Level;
import source.Rabbit;

class TestLevel {

	private Level level1;
	
	@BeforeEach
	void setUp() throws Exception {
		level1 = new Level(1);
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
	
//	@Test
//	void testGetHoles() {
//		assertEquals(level1.getHoles(), new Point[] { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
//			new Point(4, 4) });
//	}
	@Test
	void testGetGameBoard() {
		JumpIn jumpIn1 = new JumpIn(1);
		JumpIn jumpIn2 = new JumpIn(1);
		GameObject[][] gameBoard = new GameObject[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				gameBoard[j][i] = new GameObject(new Point(i, j));
			}
		}
		jumpIn1.setGameBoard(gameBoard);
		jumpIn2.setGameBoard(level1.getGameBoard());
		assertEquals(jumpIn1.toString(),jumpIn2.toString());
	}
	
//	@Test
//	void testPlaceGameObjectMushroom() {
//		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
//		level1.placeGameObject(Mush1);
//		assertSame(level1.getGameBoard()[0][1], Mush1);
//	}
//	
//	@Test
//	void testPlaceGameObjectRabbit() {
//		Rabbit R1 = new Rabbit(new Point(0, 1), "R1");
//		level1.placeGameObject(R1);
//		assertSame(level1.getGameBoard()[0][1], R1);
//	}
//	
//	@Test
//	void testPlaceGameObjectFox() {
//		Fox F1 = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
//		level1.placeGameObject(F1);
//		assertSame(level1.getGameBoard()[0][1], F1);
//		assertSame(level1.getGameBoard()[0][2], F1);
//	}
//	
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
		assertTrue(level1.getListeners().isEmpty());
	}
	@Test
	void testAddListener() {
		Rabbit R1 = new Rabbit(new Point(0, 1), "R1");
		level1.addListener(R1);
		assertSame(level1.getListeners().get(0), R1);
	}
}
