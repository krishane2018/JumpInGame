package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.*;

import source.LevelBuilder;
import soucre.GameObject;
import source.Rabbit;
import source.Fox;

class TestLevelBuilder {
	@BeforeEach
	void setUp() throws Exception {
		LevelBuilder builder = new LevelBuilder();
	}
	@Test
	void testValidSpaceGameObjectTrue() {
		GameObject emptySpace = new GameObject(new Point(1, 1));
		assertTrue(builder.validSpaceGameObject(emptySpace));
	}
	@Test
	void testValidSpaceGameObjectFalse() {
		GameObject Mush1 = new GameObject(new Point(1, 1), "M1");
		assertFalse(builder.validSpaceGameObject(Mush1));
	}
	@Test
	void testValidSpaceFoxTrue() {
		GameObject emptySpace = new GameObject(new Point(1, 1));
		assertTrue(builder.validSpaceFox(emptySpace));
	}
	@Test 
	void testValidSpaceFoxFalseHole() {
		GameObject holeSpace = new GameObject(new Point(0, 0));
		assertFalse(builder.validSpaceFox(holeSpace));
	}
	@Test
	void testValidSpaceFoxFalseName() {
		GameObject Mush1 = new GameObject(new Point(1, 1), "M1");
		assertFalse(builder.validSpaceFox(Mush1));
	}
	@Test
	void testAddGameObjectNull(){
		assertFalse(builder.addGameObject(new Point(1, 1), "turtle", ""));
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
	void testAddGameObjectFoxHorizontalFalse(){
		builder.addGameObject(new Point(1, 1), "fox", "Horizontal");
		assertTrue(builder.addGameObject(new Point(1, 1), "fox", "Horizontal"));
	}
	@Test
	void testAddGameObjectFoxVerticalFalse(){
		builder.addGameObject(new Point(1, 1), "fox", "Vertical");
		assertTrue(builder.addGameObject(new Point(1, 1), "fox", "Vertical"));
	}
	
	@Test 
	void testAddGameObjectRabbitMushroomTrue(){
		assertTrue(builder.addGameObject(new Point(1, 1), "Rabbit", ""));
	}
	
	@Test
	void testAddGemObjectRabbitMushroomFalse(){
		builder.addGameObject(new Point(1, 1), "Rabbit", ""));
		assertFalse(builder.addGameObject(new Point(1, 1),"Rabbit", ""));
	}
	
	@Test
	void testRemoveGameObjectValid(){
		builder.addGameObject(new Point(1, 1), "Mushroom", "");
		assertTrue(builder.removeGameObject(new Point(1, 1)));
	}
	@Test
	void testRemoveGameObjectInvalid(){
		assertFalse(builder.removeGameObject(new point(1, 1)));
	}
	
	@Test
	void testIsValidGameValid1() {
		JumpIn jumpIn = new JumpIn(1);
		assertTrue(builder.isValidGame(jumpIn));
	}
	@Test
	void testIsValidGameValid3() {
		JumpIn jumpIn = new JumpIn(3);
		assertTrue(builder.isValidGame(jumpIn));
	}
	@Test
	void testSaveLevel(){
		assertTrue(builder.saveLevel());
	}
}
