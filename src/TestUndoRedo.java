import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUndoRedo {

	UndoRedo undoRedo;
	
	@BeforeEach
	void setUp() throws Exception {
		undoRedo = new UndoRedo();
	}

	@Test
	void testConstructor() {
		assertNotNull(undoRedo);
	}

	@Test
	void testAddMove() {
		undoRedo.addMove(new JumpInEvent());
		assertEquals(undoRedo.getUndo().size(),1);
	}
	
	@Test
	void testIsState() {
		assertFalse(undoRedo.isState());
	}
	
	@Test
	void testSetState() {
		undoRedo.setState(true);
		assertTrue(undoRedo.isState());
	}
	
	@Test
	void testUndoMove1() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		undoRedo.undoMove();
		assertEquals(undoRedo.getUndo().size(),0);
	}
	
	@Test
	void testUndoMove2() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		undoRedo.undoMove();
		assertEquals(undoRedo.getRedo().size(),1);
	}
	
	@Test
	void testRedoMove1() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		undoRedo.undoMove();
		undoRedo.redoMove();
		assertEquals(undoRedo.getUndo().size(),1);
	}
	
	@Test
	void testRedoMove2() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		undoRedo.undoMove();
		undoRedo.redoMove();
		assertEquals(undoRedo.getRedo().size(),0);
	}
	
	@Test
	void testGetUndo() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		assertEquals(e, undoRedo.getUndo().peek());
	}
	
	@Test
	void testGetRedo() {
		JumpInEvent e = new JumpInEvent();
		undoRedo.addMove(e);
		undoRedo.undoMove();
		assertEquals(e, undoRedo.getRedo().peek());
	}
}