import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJumpInEvent {

	Point[] holes = new Point[] {new Point(0,0),new Point(0,4),new Point(4,0),new Point(4,4),new Point(2,2)};
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConsrtructorNoParam() {
		JumpInEvent e = new JumpInEvent();
		assertNotNull(e);
	}

	@Test
	void testConsrtructor5Param() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0), holes);
		assertNotNull(e);
	}

	@Test
	void testConsrtructor7Param() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), new Point(2, 0), holes);
		assertNotNull(e);
	}

	@Test
	void testGetHoleNo() {
		JumpInEvent e = new JumpInEvent();
		assertNotNull(e.getHoles());
	}
	
	@Test
	void testGetHole5() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0), holes);
		assertEquals(holes,e.getHoles());
	}
	
	@Test
	void testGetHole7() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), new Point(2, 0), holes);
		assertEquals(holes, e.getHoles());
	}

	@Test
	void testGetChosenPiece() {
		GameObject g =  new GameObject(new Point(0,0));
		JumpInEvent e = new JumpInEvent(new Object(),g, new Point(0, 0), new Point(1, 0), holes);
		assertEquals(g,e.getChosenPiece());
	}

	@Test
	void testGetInitialLocation1() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(),new GameObject(new Point(0,0)),i, new Point(1, 0), holes);
		assertEquals(i,e.getInitialLocation1());
	}

	@Test
	void testSetInitialLocation1() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0), holes);
		e.setInitialLocation1(i);
		assertEquals(i,e.getInitialLocation1());
	}

	@Test
	void testGetFinalLocation1() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(),new GameObject(new Point(0,0)), new Point(1, 0),i, holes);
		assertEquals(i,e.getFinalLocation1());
	}

	@Test
	void testSetFinalLocation1() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0), holes);
		e.setFinalLocation1(i);
		assertEquals(i,e.getFinalLocation1());
	}

	@Test
	void testGetInitialLocation2() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), i,
				new Point(1, 0), new Point(2, 0), holes);
		assertEquals(i, e.getInitialLocation2());
	}

	@Test
	void testSetInitialLocation2() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), new Point(2, 0), holes);
		e.setInitialLocation2(i);
		assertEquals(i, e.getInitialLocation2());
	}

	@Test
	void testGetFinalLocation2() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), i, holes);
		assertEquals(i, e.getFinalLocation2());
	}

	@Test
	void testSetFinalLocation2() {
		Point i = new Point(1,1);
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), new Point(2, 0), holes);
		e.setFinalLocation2(i);
		assertEquals(i, e.getFinalLocation2());
	}

	@Test
	void testIsEmptyTrue() {
		JumpInEvent e = new JumpInEvent();
		assertTrue(e.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse1() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0),
				new Point(1, 0), new Point(2, 0), holes);
		assertFalse(e.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse2() {
		JumpInEvent e = new JumpInEvent(new Object(), new GameObject(new Point(0,0)), new Point(0, 0), new Point(1, 0), holes);
		assertFalse(e.isEmpty());
	}
}
