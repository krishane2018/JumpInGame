package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;
import source.GameObjectFactory;

class TestGameObjectFactory {

	private GameObjectFactory factory;
	private Point p;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new GameObjectFactory();
		p = new Point(1,2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor() {
		assertNotNull(factory);
	}
	
	@Test
	void testGetGameObjectMushroom() {
		GameObject o = new GameObject(p,"M1");
		assertEquals(factory.getGameObject(p, "Mushroom", ""), o);
	}
	
	@Test
	void testGetGameObjectRabbit() {
		GameObject o = new GameObject(p,"R1");
		assertEquals(factory.getGameObject(p, "Rabbit", "").getName(), o.getName());
	}
	
	@Test
	void testGetGameObjectFoxH() {
		GameObject o = new GameObject(p, "F1");
		assertEquals(factory.getGameObject(p, "Fox", "Horizontal").getName(), o.getName());
	}
	
	@Test
	void testGetGameObjectFoxV() {
		GameObject o = new GameObject(p,"F1");
		assertEquals(factory.getGameObject(p, "Fox", "Vertical").getName(), o.getName());
	}

}
