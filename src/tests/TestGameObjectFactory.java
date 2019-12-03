
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
	
	private GameObjectFactory factory;
	private Point p;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new GameObjectFactory();
		p = new Point(1,1);
	}
	
	@Test
	void testGetGameObjectMushroom() {
		assertTrue(factory.getGameObject(p, "Mushroom", "") instanceof GameObject);
	}
	@Test
	void testGetGameObjectRabbit() {
		assertTrue(factory.getGameObject(p, "Rabbit", "") instanceof Rabbit);
	}
	@Test
	void testGetGameObjectFoxVertical() {
		assertTrue(factory.getGameObject(p, "Fox", "Vertical") instanceof Fox);
	}
	@Test
	void testGetGameObjectFoxHorizontal() {
		assertTrue(factory.getGameObject(p, "Fox", "Horizontal") instanceof Fox);
	}

	@Test
	void testConstructor() {
		assertNotNull(factory);
	}

}
