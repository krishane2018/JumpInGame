package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.GameObject;
import source.JumpInEvent;

class TestJumpInEvent {

	private Point[] holes;
	private GameObject gameObject;
	private Point p1;
	private Point p2;
	private Point p3;
	private Point i;
	private Object o;
	private JumpInEvent e;

	@BeforeEach
	void setUpClass() {
		holes = new Point[] { new Point(0, 0), new Point(0, 4), new Point(4, 0), new Point(4, 4), new Point(2, 2) };
		gameObject = new GameObject(new Point(0, 0));
		p1 = new Point(0, 0);
		p2 = new Point(1, 0);
		p3 = new Point(2, 0);
		i = new Point(1, 1);
		o = new Object();

	}

	@Test
	void testConsrtructorNoParam() {
		e = new JumpInEvent();
		assertNotNull(e);
	}

	@Test
	void testConsrtructor5Param() {
		e = new JumpInEvent(o, gameObject, p1, p2, holes);
		assertNotNull(e);
	}

	@Test
	void testConsrtructor7Param() {
		e = new JumpInEvent(o, gameObject, p1, p2, p1, p3, holes);
		assertNotNull(e);
	}

	@Test
	void testGetHoleNo() {
		e = new JumpInEvent();
		assertNotNull(e.getHoles());
	}

	@Test
	void testGetHole5() {
		e = new JumpInEvent(o, gameObject, p1, p2, holes);
		assertEquals(holes, e.getHoles());
	}

	@Test
	void testGetHole7() {
		e = new JumpInEvent(o, gameObject, p1, p2, p1, p3, holes);
		assertEquals(holes, e.getHoles());
	}

	@Test
	void testGetChosenPiece() {
		GameObject g = new GameObject(new Point(0, 0));
		e = new JumpInEvent(o, g, p1, p2, holes);
		assertEquals(g, e.getChosenPiece());
	}

	@Test
	void testGetInitialLocation1() {
		e = new JumpInEvent(o, gameObject, i, p2, holes);
		assertEquals(i, e.getInitialLocation1());
	}

	@Test
	void testSetInitialLocation1() {
		e = new JumpInEvent(o, gameObject, p1, p2, holes);
		e.setInitialLocation1(i);
		assertEquals(i, e.getInitialLocation1());
	}

	@Test
	void testGetFinalLocation1() {
		e = new JumpInEvent(o, gameObject, p2, i, holes);
		assertEquals(i, e.getFinalLocation1());
	}

	@Test
	void testSetFinalLocation1() {
		e = new JumpInEvent(o, gameObject, p1, p2, holes);
		e.setFinalLocation1(i);
		assertEquals(i, e.getFinalLocation1());
	}

	@Test
	void testGetInitialLocation2() {
		e = new JumpInEvent(o, gameObject, p1, i, p2, p3, holes);
		assertEquals(i, e.getInitialLocation2());
	}

	@Test
	void testSetInitialLocation2() {
		e = new JumpInEvent(o, gameObject, p1, p2, p2, p3, holes);
		e.setInitialLocation2(i);
		assertEquals(i, e.getInitialLocation2());
	}

	@Test
	void testGetFinalLocation2() {
		e = new JumpInEvent(o, gameObject, p1, p2, p2, i, holes);
		assertEquals(i, e.getFinalLocation2());
	}

	@Test
	void testSetFinalLocation2() {
		e = new JumpInEvent(o, gameObject, p1, p2, p2, p3, holes);
		e.setFinalLocation2(i);
		assertEquals(i, e.getFinalLocation2());
	}

	@Test
	void testIsEmptyTrue() {
		e = new JumpInEvent();
		assertTrue(e.isEmpty());
	}

	@Test
	void testIsEmptyFalse1() {
		e = new JumpInEvent(o, gameObject, p1, p2, p2, p3, holes);
		assertFalse(e.isEmpty());
	}

	@Test
	void testIsEmptyFalse2() {
		e = new JumpInEvent(o, gameObject, p1, p2, holes);
		assertFalse(e.isEmpty());
	}
}
