import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * 
 * @author Aashna Narang
 *
 */
class TestUndoRedo {
	private UndoRedo u;
	private JumpInEvent e;
	
	
	@BeforeEach
	void setUp() throws Exception {
		u = new UndoRedo();
		e = new JumpInEvent();
	}

	@AfterEach
	void tearDown() throws Exception {
		u.getUndo().clear();
		u.getRedo().clear();
	}

	@Test
	void testConstructor() {
		assertNotNull(u.getUndo());
		assertNotNull(u.getRedo());
	}
	
	@Test
	void testGetUndo() {
		u.addMove(e);
		assertNotNull(u.getUndo());
		assertNotNull(u.getUndo().get(0));
	}
	
	@Test
	void testGetRedo() {
		u.addMove(e);
		u.undoMove();
		assertNotNull(u.getRedo());
		assertNotNull(u.getRedo().get(0));
	}
	
	@Test
	void testAddMove() {
		u.addMove(e);
		assertNotNull(u.getUndo().get(0));
	}
	
	@Test
	void testUndoMove() {
		u.addMove(e);
		assertEquals(e, u.undoMove());
		assertTrue(u.getUndo().isEmpty());
		assertNotNull(u.getRedo().get(0));
	}
	
	@Test
	void testRedoMove() {
		u.addMove(e);
		u.undoMove();
		assertEquals(e, u.redoMove());
		assertTrue(u.getRedo().isEmpty());
	}
	
	@Test
	void testUndoMoveEmpty() {
		assertTrue(u.undoMove().isEmpty());
	}
	
	@Test
	void testRedoMoveEmpty() {
		assertTrue(u.redoMove().isEmpty());
	}
	
	@Test
	void testProperState() {
		u.addMove(e);
		u.undoMove();
		assertTrue(u.isState());
	}
	
	@Test
	void testSetState() {
		u.setState(true);
		assertTrue(u.isState());
		u.setState(false);
		assertFalse(u.isState());
	}
	
	@Test
	void testInitialState() {
		assertFalse(u.isState());
	}
}