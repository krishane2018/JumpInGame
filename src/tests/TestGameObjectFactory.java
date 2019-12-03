
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.Queue;
import org.junit.jupiter.api.Test;

import source.Fox;
import source.GameObject;
import source.JumpIn;
import source.Rabbit;
import source.Level;
import source.GameObjectFactory;

class TestGameObjectFactory {
	@BeforeEach
	void setUp() throws Exception {
		GameObjectFactory factory = new GameObjectFactory();
	}
	@Test
	void testFoxMakerHelper3() {
		assertEquals(factory.foxMakerHelper(3), 4);
	}
	@Test
	void testFoxMakerHelper5() {
		assertEquals(factory.foxMakerHelper(5), 4);
	}
	@Test
	void testFoxMakerVertical() {
		Fox fox = new Fox (new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		assertEqual(factory.foxMaker(new Point(0, 1), "Vertical", 1), fox);
	}
	@Test
	void testFoxMakerHorizontal() {
		Fox fox = new Fox (new Point(0, 1), new Point(1, 1), "F1", "Horizontal");
		assertEqual(factory.foxMaker(new Point(0, 1), "Horizontal", 1), fox);
	}
	@Test
	void testEmptyNextCounter() {
		assertTrue(factory.getGameObject("mushroom") == 1);
	}
	
	@Test
	void testReduceNextCounter() {
		factory.reduceCounter("mushroom");
		assertTrue(factory.getGameObject(mushroom) == 0);
	}
	@Test
	void testGetGameObjectMushroom() {
		assertTrue(factory.getGameObject(new Point(1, 1), "Mushroom", "") instanceof GameObject);
	}
	@Test
	void testGetGameObjectRabbit() {
		assertTrue(factory.getGameObject(new Point(1, 1), "Rabbit", "") instanceof Rabbit);
	}
	@Test
	void testGetGameObjectFoxVertical() {
		assertTrue(factory.getGameObject(new Point(1, 1), "Fox", "Vertical") instanceof Fox);
	}
	@Test
	void testGetGameObjectFoxHorizontal() {
		assertTrue(factory.getGameObject(new Point(1, 1), "Fox", "Horizontal") instanceof Fox);
	}
	@Test
	void testGetGameObjectInvalid() {
		assertNull(factory.getGameObject(new Point(1, 1), "Tree", ""));
	}

}

