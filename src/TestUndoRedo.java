
import static org.junit.jupiter.api.Assertions.*;


import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Nicholas Anderson
 *
 */

class TestUndoRedo {

	GameObject[][] board;
	UndoRedo undoredo;
	@BeforeEach
	void setUp() {
		board = new GameObject[5][5];
		for(int i = 0; 1<5; i++)
		{
			for(int j = 0; j<5; j++)
			{
				board[j][i] = new GameObject(new Point(i, j))
			}
		}
		undoredo = new UndoRedo();
		//creates and initializes an UndoRedo object
		
		Rabbit rabbit = new Rabbit(new Point(2, 2), "R1");
		GameObject mush12 = new GameObject(new Point(1, 2), "M1");
		GameObject mush32 = new GameObject(new Point(3, 2), "M2");
		GameObject mush21 = new GameObject(new Point(2, 1), "M3");
		GameObject mush23 = new GameObject(new Point(2, 3), "M4");

		board[2][2] = rabbit;
		board[2][1] = mush12;
		board[2][3] = mush32;
		board[1][2] = mush21;
		board[3][2] = mush23;
		HashSet<Point> options = new HashSet<Point>();
		options.add(optionMaker(0, 2));
		options.add(optionMaker(4, 2));
		options.add(optionMaker(2, 0));
		options.add(optionMaker(2, 4));

		JumpIn game = new JumpIn(3);
		Point point1 = new Point(2, 2);
		Point point2 = new Point(0, 2);
		Point[] holes = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0), new Point(4, 4) };

		JumpInEvent event = new JumpInEvent(game, rabbit, point1, point2, holes);
		//creates an event to be done undone and redone
	}
	//sets up a board for the JumpIn game and also an UndoRedo
	
	
	@Test
	void testInitialState() {
		assertFalse(undoredo.isState());
	}
	
	@Test
	void testAddMoveStateTrue(){
		undoredo.setState(true);
		//sets the state to true so that an addMove will not work
		undoredo.addMove(event);
		//adds the move 
		
		assertTrue(undoredo.undoMove().isEmpty());
		//tests that nothing is done in addMove 
		//if the state is set to true
	}
	@Test
	void testAddMoveRedoCleared(){
		undoredo.addMove(event);
		//adds a move with the given event 
		undoredo.undoMove();
		//undo the move to make sure the redo stack is not empty
		undoredo.setState(false);
		//reset state to false so a move can be added
		undoredo.addMove(event);
		//redo the move manually to clear the redo stack
		
		assertTrue(undoredo.redoMove().isEmpty());
		//tests that the redo stack is cleared once a move is added
	}
	@Test
	void testAddMoveUndoAdded() {
		undoredo.addMove(event);
		//adds the event as a move
		
		assertFalse(undoredo.undoMove().isEmpty());
		//tests that the undo stack has the move added to it once a 
		//new move is performed
	}
	
	
	
	
	@Test
	void testUndoMoveEmptyUndo() {
		assertTrue(undoredo.undoMove().isEmpty());
		//tests to see if an UndoRedo with nothing in the undo stack
		//returns an empty JumpInEvent
	}
	@Test
	void testUndoMoveUndoLength() {
		undoredo.addMove(event);
		//adds the event as a move
		undoredo.undoMove();
		//undoes the move to clear the undo stack
		
		assertTrue(undoredo.undoMove().isEmpty());
		// tests that the undo stack is popped after an undo is performed
	}
	@Test
	void testUndoMoveRedoLength() {
		undoredo.addMove(event);
		//adds the event as a move
		undoredo.undoMove();
		//undoes the move
		
		assertFalse(undoredo.redoMove().isEmpty());
		//tests that the redo stack after an undo is performed is
		//not empty
	}
	@Test
	void testUndoMoveStateTrue() {
		undoredo.addMove(event);
		//adds the event as a move
		undoredo.undoMove();
		//undoes the move
		
		assertTrue(undoredo.isState());
		//tests that the state is set to true after a move is undone
	}
	
	
	
	
	@Test
	void testSetGetStateTrue() {
		undoredo.setState(true);
		//sets the state to true
		
		assertTrue(undoredo.isState());
		//tests that setState does set the state to true successfully
	}
	@Test
	void testSetGetStateFalse(){
		undoredo.setstate(true);
		//sets state to true so it can be changed back to false
		undoredo.setState(false);
		//sets the state to false
		
		assertFalse(undoredo.isState());
		//tests that setState sets the state to false successfully
	}
	
	
	
	
	@Test
	void testRedoMoveRedoIsEmpty() {
		assertTrue(undoredo.redoMove().isEmpty());
		//tests to see if an redoMove with an empty redo stack returns
		//an empty JumpInEvent
	}
	@Test
	void testRedoMoveRedoLength() {
		undoredo.addMove(event);
		//adds the event as a move
		undoredo.undoMove();
		//undoes the move
		undoredo.redoMove();
		//redoes the move
		
		assertTrue(undoredo.redoMove().isEmpty());
		//tests the size of the redo stack after a redo is performed
	}
	@Test
	void testRedoMoveUndoLength() {
		undoredo.addMove(event);
		//adds the event as a move
		undoredo.undoMove();
		//undoes the move
		undoredo.redoMove();
		//redoes the move
		
		assertFalse(undoredo.undoMove().isEmpty());
		//tests the size of the undo stack after an undo is performed
	}

}
