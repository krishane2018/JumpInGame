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
	
	@Test testGetName() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals("M1", Mush1.getName());
	}
	
	@Test testSetName() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		Mush1.setName("M2");
		assertEquals("M2", Mush1.getName());
	}
	
	@Test testGetCoordinate() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getCoordinate(), new Point(0, 1));
	}
	
	@Test testSetCoordinate() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		Mush1.setCoordinate(new Point(0,2));
		assertEquals(Mush1.getCoordinate(), new Point(0,2));
	}
	
	@Test testGetX1() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getX1(), 0);
	}
	
	@Test testGetY1() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getY1(),1);
	}
	
	@Test testToXML(){
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.toXML(),"<name>M1</name>\n<x1>0</x1>\n<y1>1</y1>\n");
	}
}
