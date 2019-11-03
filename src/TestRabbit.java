

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRabbit {

	GameObject[][] board;

	
	@BeforeEach
	void setUp() {
		board = new GameObject[5][5]; 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new GameObject(new Point(i, j)); 
			}
		}
	}
	
	protected Point optionMaker(int x1, int y1) {
		return new Point (x1,y1);
	}

	@Test
	void testNumberOptions1Mush() {
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
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(0,2));
		options.add(optionMaker(4,2));
		options.add(optionMaker(2,0));
		options.add(optionMaker(2,4));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());

		
	}
	
	@Test
	void testCorrectOptions1Mush() {
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
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(0,2));
		options.add(optionMaker(4,2));
		options.add(optionMaker(2,0));
		options.add(optionMaker(2,4));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertTrue(options.equals(setOptions));

		
	}
	
	@Test
	void testNumberOptionsMultipleMush() {
		Rabbit rabbit = new Rabbit(new Point(1, 1), "R1");
		GameObject mush1 = new GameObject(new Point(1, 2), "M1");
		GameObject mush2 = new GameObject(new Point(1, 3), "M2");
		GameObject mush3 = new GameObject(new Point(2, 1), "M3");
		GameObject mush4 = new GameObject(new Point(3, 1), "M4");

		
		board[1][1] = rabbit;
		board[2][1] = mush1;
		board[3][1] = mush2;
		board[1][2] = mush3;
		board[1][3] = mush4;
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(1,4));
		options.add(optionMaker(4,1));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());

		
	}
	
	@Test
	void testCorrectOptionsMultipleMush() {
		Rabbit rabbit = new Rabbit(new Point(1, 1), "R1");
		GameObject mush1 = new GameObject(new Point(1, 2), "M1");
		GameObject mush2 = new GameObject(new Point(1, 3), "M2");
		GameObject mush3 = new GameObject(new Point(2, 1), "M3");
		GameObject mush4 = new GameObject(new Point(3, 1), "M4");

		
		board[1][1] = rabbit;
		board[2][1] = mush1;
		board[3][1] = mush2;
		board[1][2] = mush3;
		board[1][3] = mush4;
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(1,4));
		options.add(optionMaker(4,1));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertTrue(options.equals(setOptions));

		
	}
	
	@Test
	void testNumberOptionsHoleNoObstacle() {
		Rabbit rabbit = new Rabbit(new Point(2, 1), "R1");

		
		board[1][2] = rabbit;
		
		HashSet <Point> options = new HashSet<Point>();
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());

		
	}
	
	@Test
	void testCorrectOptionsHoleNoObstacle() {
		Rabbit rabbit = new Rabbit(new Point(2, 1), "R1");

		
		board[1][2] = rabbit;
	
		HashSet <Point> options = new HashSet<Point>();
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertTrue(options.equals(setOptions));

		
	}
	
	@Test
	void testNumberOptionsHoleObstacle() {
		Rabbit rabbit = new Rabbit(new Point(2, 1), "R1");
		GameObject mush1 = new GameObject(new Point(2, 2), "M1");
		

		
		board[1][2] = rabbit;
		board[2][2] = mush1;

		
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(2,3));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());

		
	}
	
	@Test
	void testCorrectOptionsHoleObstacle() {
		Rabbit rabbit = new Rabbit(new Point(2, 1), "R1");
		GameObject mush1 = new GameObject(new Point(2, 2), "M1");


		
		board[1][2] = rabbit;
		board[2][2] = mush1;
	
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(2,3));

		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertTrue(options.equals(setOptions));

		
	}

	@Test
	void testNumberOptionsObstacleThenOutOfBounds() {
		Rabbit rabbit = new Rabbit(new Point(2, 3), "R1");
		GameObject mush1 = new GameObject(new Point(2, 4), "M1");
		

		
		board[3][2] = rabbit;
		board[4][2] = mush1;

		
		HashSet <Point> options = new HashSet<Point>();
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(options.size(), returnedOptions.size());

		
	}
	
	@Test
	void testCorrectOptionsThenOutOfBounds() {
		Rabbit rabbit = new Rabbit(new Point(2, 3), "R1");
		GameObject mush1 = new GameObject(new Point(2, 4), "M1");
		

		
		board[3][2] = rabbit;
		board[4][2] = mush1;
	
		HashSet <Point> options = new HashSet<Point>();
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertEquals(options, setOptions);

		
	}
	
	@Test
	void testNumberOptionsJumpOverRabbit() {
		Rabbit rabbit = new Rabbit(new Point(1, 2), "R1");
		Rabbit rabbit2 = new Rabbit(new Point(1, 3), "R2");
		

		
		board[2][1] = rabbit;
		board[3][1] = rabbit2;

		
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(1,4));

		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());

		
	}
	
	@Test
	void testCorrectOptionsJumpOverRabbit() {
		Rabbit rabbit = new Rabbit(new Point(1, 2), "R1");
		Rabbit rabbit2 = new Rabbit(new Point(1, 3), "R2");
		

		
		board[2][1] = rabbit;
		board[3][1] = rabbit2;
	
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(1,4));
		
		ArrayList <Object> returnedOptions = rabbit.determineOptions(board);
		HashSet <Point> setOptions = new HashSet<Point>();
		
		for (Object element : returnedOptions) {
			setOptions.add((Point)element);
		}
		
		assertEquals(options,setOptions);

		
	}
	
	
	
	@Test
	void testDisplayOptions() {
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
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(0,2));
		options.add(optionMaker(4,2));
		options.add(optionMaker(2,0));
		options.add(optionMaker(2,4));
		
		
		String output = "1 (0.0,2.0)\n"+
				"2 (4.0,2.0)\n"+
				"3 (2.0,0.0)\n"+
				"4 (2.0,4.0)\n";	
	
		assertEquals(rabbit.displayOptions(board), output);

	}
		

	@Test
	void testRabbitConstructorIllegalArgument() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new Rabbit(new Point(-1,1), "R1");
			  });	
	}

	@Test
	void testHandleEvent() {
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
		HashSet <Point> options = new HashSet<Point>();
		options.add(optionMaker(0,2));
		options.add(optionMaker(4,2));
		options.add(optionMaker(2,0));
		options.add(optionMaker(2,4));
		
		JumpIn game = new JumpIn(3);
		Point point1 = new Point(2,3);
		Point[] holes = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0),
				new Point(4, 4) };

		JumpInEvent event = new JumpInEvent(game, rabbit, point1, holes);
		rabbit.handleEvent(event);
		assertTrue(rabbit.getCoordinate().equals(point1));
	}

	@Test
	void testEqualsTrue() {
		Rabbit r1 = new Rabbit (new Point (2,1), "R1");
		Rabbit r2 = new Rabbit (new Point (2,1), "R1");
		
		assertTrue(r1.equals(r2));
	}
	
	@Test
	void testEqualsFalsePoint() {
		Rabbit r1 = new Rabbit (new Point (3,1), "R1");
		Rabbit r2 = new Rabbit (new Point (2,1), "R1");
		
		assertFalse(r1.equals(r2));
	}
	
	@Test
	void testEqualsFalseName() {
		Rabbit r1 = new Rabbit (new Point (2,1), "R1");
		Rabbit r2 = new Rabbit (new Point (2,1), "R2");
		
		assertFalse(r1.equals(r2));
	}

}
