package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.*;

import source.LevelBuilder;
import source.GameObject;
import source.JumpIn;
import source.Rabbit;
import source.Fox;

class TestLevelBuilder {
	
	private LevelBuilder builder;
	
	@BeforeEach
	void setUp() throws Exception {
		builder = new LevelBuilder();
	}

	@Test
	void testAddGameObjectFoxHorizontalTrue(){
		assertTrue(builder.addGameObject(new Point(1, 1), "fox", "Horizontal"));
	}
	@Test
	void testAddGameObjectFoxVerticalTrue(){
		assertTrue(builder.addGameObject(new Point(1, 1), "fox", "Vertical"));
	}
	
	@Test 
	void testAddGameObjectRabbitMushroomTrue(){
		assertTrue(builder.addGameObject(new Point(1, 1), "Rabbit", ""));
	}
	
	@Test
	void testAddGemObjectRabbitMushroomFalse(){
		builder.addGameObject(new Point(1, 1), "Rabbit", "");
		assertFalse(builder.addGameObject(new Point(1, 1),"Rabbit", ""));
	}
	
	@Test
	void testRemoveGameObjectValid(){
		builder.addGameObject(new Point(1, 1), "Mushroom", "");
		assertTrue(builder.removeGameObject(new Point(1, 1)));
	}
	@Test
	void testRemoveGameObjectInvalid(){
		assertFalse(builder.removeGameObject(new Point(1, 1)));
	}
	
}
