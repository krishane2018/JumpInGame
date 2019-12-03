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
	

	@Test 
  void testGetName() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals("M1", Mush1.getName());
	}
	
	@Test 
  void testSetName() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		Mush1.setName("M2");
		assertEquals("M2", Mush1.getName());
	}
	
	@Test 
  void testGetCoordinate() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getCoordinate(), new Point(0, 1));
	}
	
	@Test 
  void testSetCoordinate() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		Mush1.setCoordinate(new Point(0,2));
		assertEquals(Mush1.getCoordinate(), new Point(0,2));
	}
	
	@Test 
  void testGetX1() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getX1(), 0);
	}

	@Test 
  void testGetY1() {
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.getY1(),1);
	}
	

	@Test 
  void testToXML(){
		GameObject Mush1 = new GameObject(new Point(0, 1), "M1");
		assertEquals(Mush1.toXML(),"<name>M1</name>\n<x1>0</x1>\n<y1>1</y1>\n");
	}
	
	@Test
	void testEqualsFox() {
		GameObject M1 = new GameObject(new Point(2, 3), "M1");
		Fox fox = new Fox(new Point(1,1), new Point(1, 2), "F1", "Vertical");
		assertFalse(M1.equals(fox));
	}
		
	@Test
	void testEqualsSame() {
		GameObject M1 = new GameObject(new Point(2, 3), "M1");
		assertTrue(M1.equals(M1));
	}
		
	@Test
	void testEqualsDiffCoord() {
		GameObject M1 = new GameObject(new Point(2, 3), "M1");
		GameObject M2 = new GameObject(new Point(2, 4), "M1");
		assertFalse(M1.equals(M2));
	}
	
		
	@Test
	void testEqualsDiffName() {
		GameObject m1 = new GameObject(new Point(2, 3), "M1");
		GameObject M2 = new Object(new Point(2, 3), "M2");
		assertFalse(M1.equals(M2));
	}

}
