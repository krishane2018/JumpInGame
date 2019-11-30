package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;



class TestGameObject {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGameObjectConstructorIllegalArgumentLowerX() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(-1,1), "F1");
			  });	
	}
	
	@Test
	void testGameObjectConstructorIllegalArgumentLowerY() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(1,-1), "F1");
			  });	
	}

	@Test
	void testGameObjectConstructorIllegalArgumentUpperX() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(5,1), "F1");
			  });	
	}
	
	@Test
	void testGameObjectConstructorIllegalArgumentUpperY() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(1,5), "F1");
			  });	
	}
	
	@Test
	void test1ArgsGameObjectConstructorIllegalArgumentLowerX() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(-1,1));
			  });	
	}
	
	@Test
	void test1ArgsGameObjectConstructorIllegalArgumentLowerY() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(1,-1));
			  });	
	}
	
	@Test
	void test1ArgsGameObjectConstructorIllegalArgumentUpperX() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(5,1));
			  });	
	}
	
	@Test
	void test1ArgsGameObjectConstructorIllegalArgumentUpperY() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(1,5));
			  });	
	}
	@Test testToXML(){
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertTrue(Mush1.toXML().equals("<name>M1</name>\n<x1>0</x1>\n<y1>1</y1>"));
	}
}
