package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.Queue;
import org.junit.jupiter.api.Test;

import source.Fox;
import source.GameObject;
import source.JumpIn;
import source.JumpInListener;
import source.LevelSelector;
import source.Move;
import source.Rabbit;
import source.Level;

/**
 * 
 * @author Aashna Narang and Kush
 *
 */
class TestJumpIn {
	private JumpIn jumpIn;
	private JumpIn jumpIn3;
	private Point inRabbit, fRabbit, inFox, fFox;
	
	@BeforeEach
	void setUp() throws Exception {
		jumpIn = new JumpIn(1);
		jumpIn3 = new JumpIn(3);
		inRabbit = new Point(0,4);
		fRabbit = new Point(0,1);
		inFox = new Point(1,4);
		fFox = new Point(1,2);
		jumpIn.setUndoState(false);
		jumpIn3.setUndoState(false);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsHole() {
		assertEquals(true, jumpIn.isHole(0, 0)&&jumpIn.isHole(0, 4)
				&&jumpIn.isHole(2, 2)&&jumpIn.isHole(4, 0)&&jumpIn.isHole(4, 4));
	}
	
	@Test
	void testToStringLevel1() {
		String level1 = "--------------------------\n"
				+ "|   H|    |  R1|    |   H|\n"
				+ "--------------------------\n"
				+ "|    |  M3|    |    |    |\n"
				+ "--------------------------\n"
				+ "|  M1|    |   H|    |    |\n"
				+ "--------------------------\n"
				+ "|  M2|    |    |    |    |\n"
				+ "--------------------------\n"
				+ "| R2H|    |    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn.toString(),level1);
	}
	
	@Test
	void testToStringLevel2() {
		jumpIn = new JumpIn(2);
		String level2 = "--------------------------\n"
				+ "| R1H|  M1|    |    |   H|\n"
				+ "--------------------------\n"
				+ "|    |    |  M2|    |    |\n"
				+ "--------------------------\n"
				+ "|    |  M3|   H|  R2|    |\n"
				+ "--------------------------\n"
				+ "|    |    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|   H|    |    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn.toString(),level2);
	}
	
	@Test
	void testToStringLevel3() {
		String level3 = "--------------------------\n"
				+ "|   H|  R1|    |    |   H|\n"
				+ "--------------------------\n"
				+ "|  M1|    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|  M2|    |   H|    |    |\n"
				+ "--------------------------\n"
				+ "|    |  F1|  M3|    |    |\n"
				+ "--------------------------\n"
				+ "|   H|  F1|    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn3.toString(),level3);
	}
	
	@Test
	void testSetGetGameBoard1() {
		LevelSelector level = new LevelSelector(1,jumpIn);
		jumpIn.setGameBoard(level.getBoard());
		assertEquals(level.getBoard(),jumpIn.getGameBoard());
	}
	
	@Test
	void testSetGetGameBoard2() {
		JumpIn jumpIn = new JumpIn(2);
		LevelSelector level = new LevelSelector(1,jumpIn);
		jumpIn.setGameBoard(level.getBoard());
		assertEquals(level.getBoard(),jumpIn.getGameBoard());
	}
	
	@Test
	void testSetGetGameBoard3() {
		LevelSelector level = new LevelSelector(1,jumpIn3);
		jumpIn3.setGameBoard(level.getBoard());
		assertEquals(level.getBoard(),jumpIn3.getGameBoard());
	}
	
	@Test
	void testSolverLevel1() {
		Queue<Move> moves = jumpIn.getSolverMoves();
		while (!(moves.isEmpty())) {
			Move move = moves.peek();
			jumpIn.moveAnimal(move.getInitialLocation(), move.getFinalLocation());			
		}
		assertTrue(jumpIn.checkWin());
	}
	
	@Test
	void testSolverLevel2() {
		JumpIn jumpIn2 = new JumpIn(2);
		Queue<Move> moves = jumpIn2.getSolverMoves();
		while (!(moves.isEmpty())) {
			Move move = moves.peek();
			jumpIn2.moveAnimal(move.getInitialLocation(), move.getFinalLocation());			
		}
		assertTrue(jumpIn2.checkWin());
	}
	
	@Test
	void testSolverLevel3() {
		Queue<Move> moves = jumpIn3.getSolverMoves();
		while (!(moves.isEmpty())) {
			Move move = moves.peek();
			jumpIn3.moveAnimal(move.getInitialLocation(), move.getFinalLocation());			
		}
		assertTrue(jumpIn3.checkWin());
	}
	
	/**
	 * Author: Aashna Narang
	 */
	
	@Test
	void testCheckWin() {
		for(JumpInListener j : jumpIn.getGameObjectListeners()) {
			if (j instanceof Rabbit) {
				Rabbit r = (Rabbit)j;
				r.setStatus(true);
			}
		}
		assertTrue(jumpIn.checkWin());
	}
	
	@Test
	void testGetLevel() {
		assertEquals(1, jumpIn.getLevel());
	}
	
	@Test 
	void testObjectToString() {
		assertEquals("R2H", jumpIn.objectToString(0, 4));
		assertEquals("H", jumpIn.objectToString(0, 0));
		assertEquals("M2", jumpIn.objectToString(0, 3));
	}
	
	@Test
	void testAddListener() {
		Rabbit r = new Rabbit(new Point(0,0), "R3");
		Rabbit b;
		jumpIn.addListener(r);
		for(JumpInListener j : jumpIn.getGameObjectListeners()) {
			if(j instanceof Rabbit) {
				b = (Rabbit)j;
				if (b.equals(r)) assertEquals(r, b);
			}
		}
	}
	
	@Test
	void testSelectedAnimalTypeRabbitAndGameObject() {
		assertEquals("GameObject" , jumpIn.selectedAnimalType(new Point(0,0)));
		assertEquals("Rabbit" , jumpIn.selectedAnimalType(new Point(2,0)));
		assertEquals("Rabbit" , jumpIn.selectedAnimalType(inRabbit));
	}
	
	@Test
	void testSelectedAnimalTypeFox() {
		assertEquals("Fox" , jumpIn3.selectedAnimalType(inFox));
	}
	
	@Test
	void testAnimalOptionsNone() {
		ArrayList<Object> options = jumpIn3.getAnimalOptions(new Point(1,0));
		assertEquals(0, options.size());
	}
	
	@Test 
	void testAnimalOptionsRabbit() {
		ArrayList<Object> options = jumpIn.getAnimalOptions(inRabbit);
		assertEquals(1, options.size());
	}
	
	@Test
	void testAnimalOptionsFox() {
		ArrayList<Object> options = jumpIn3.getAnimalOptions(inFox);
		assertEquals(2, options.size());
	}
	
	@Test 
	void testMoveAnimalValidRabbit() {
		assertTrue(jumpIn.moveAnimal(inRabbit, fRabbit));
		GameObject[][] g = jumpIn.getGameBoard();
		assertTrue(g[1][0] instanceof Rabbit);
	}
	
	@Test
	void testMoveAnimalInvalid() {
		assertFalse(jumpIn.moveAnimal(inRabbit, new Point(0,2)));
	}
	
	@Test
	void testMoveNotAnAnimal() {
		assertFalse(jumpIn.moveAnimal(new Point(0,3), new Point(0,2)));
	}
	
	@Test
	void testMoveValidFox() {
		GameObject[][] g = jumpIn3.getGameBoard();
		assertTrue(jumpIn3.moveAnimal(inFox, fFox));
		assertTrue(g[2][1] instanceof Fox);
	}
	
	@Test
	void testMoveInvalidFox() {
		// attempt to move vertical fox horizontally
		assertFalse(jumpIn3.moveAnimal(inFox, new Point(3,2)));
		GameObject[][] g = jumpIn3.getGameBoard();
		assertTrue(g[4][1] instanceof Fox);
	}
	
	@Test 
	void testUndoMoveRabbit() {
		jumpIn.moveAnimal(inRabbit, fRabbit);
		assertTrue(jumpIn.undoMove());
		GameObject[][] g = jumpIn.getGameBoard();
		assertTrue(g[4][0] instanceof Rabbit);
	}
	
	@Test
	void testRedoMoveRabbit() {
		jumpIn.moveAnimal(inRabbit, fRabbit);
		jumpIn.undoMove();
		jumpIn.redoMove();
		GameObject[][] g = jumpIn.getGameBoard();
		assertTrue(g[1][0] instanceof Rabbit);
	}
	
	@Test
	void testNoMoveToUndo() {
		assertFalse(jumpIn.undoMove());
	}
	
	@Test
	void testNoMoveToRedo() {
		assertFalse(jumpIn.redoMove());
	}
	
	@Test
	void testUndoTwoMoves() {
		jumpIn.moveAnimal(inRabbit, fRabbit);
		jumpIn.moveAnimal(fRabbit, new Point(2,1));
		assertTrue(jumpIn.undoMove());
		assertTrue(jumpIn.undoMove());
		GameObject[][] g = jumpIn.getGameBoard();
		assertTrue(g[4][0] instanceof Rabbit);
	}
	
	@Test
	void testRedoTwoMoves() {
		jumpIn.moveAnimal(inRabbit, fRabbit);
		jumpIn.moveAnimal(fRabbit, new Point(2,1));
		jumpIn.undoMove();
		jumpIn.undoMove();
		jumpIn.redoMove();
		jumpIn.redoMove();
		GameObject[][] g = jumpIn.getGameBoard();
		assertTrue(g[1][2] instanceof Rabbit);
	}
	
	@Test
	void testUndoMoveFox() {
		jumpIn3.moveAnimal(inFox, fFox);
		assertTrue(jumpIn3.undoMove());
		GameObject[][] g = jumpIn3.getGameBoard();
		assertTrue(g[4][1] instanceof Fox);
	}
	
	@Test
	void testRedoMoveFox() {
		jumpIn3.moveAnimal(inFox, fFox);
		jumpIn3.undoMove();
		jumpIn3.redoMove();
		GameObject[][] g = jumpIn3.getGameBoard();
		assertTrue(g[2][1] instanceof Fox);
	}
	
	@Test
	void testGetNumRows() {
		assertEquals(jumpIn.getNumRows(), 5);
	}
	
	@Test
	void testGetNumColumns() {
		assertEquals(jumpIn.getNumColumns(), 5);
	}
	
	@Test
	void testGetInitialPositionsMushroom() {
		ArrayList<Point> posLevel1Mushrooms = new ArrayList<Point>();
		posLevel1Mushrooms.add(new Point(0, 2));
		posLevel1Mushrooms.add(new Point(0, 3));
		posLevel1Mushrooms.add(new Point(1, 1));
		assertEquals(posLevel1Mushrooms, jumpIn.getInitialMushroomPositions());
	}
	@Test
	void testGetInitialPositionsRabbit() {
		ArrayList<Point> posLevel1Rabbits = new ArrayList<Point>();
		posLevel1Rabbits.add(new Point(0, 5));
		posLevel1Rabbits.add(new Point(2, 0));
		assertEquals(posLevel1Rabbits, jumpIn.getInitialRabbitPositions());
	}
	@Test
	void testGetInitialFoxPositions() {
		HashMap<ArrayList<Point> ,String> foxMap = new HashMap<ArrayList<Point>,String>();
		ArrayList<Point> posLevel3Foxes = new ArrayList<Point>();
		posLevel3Foxes.add(new Point(1, 3));
		posLevel3Foxes.add(new Point(1, 4));
		foxMap.put(pos, "Vertical");
		assertEquals(foxMap, jumpIn3.getInitialFoxPositions());
	}

}
