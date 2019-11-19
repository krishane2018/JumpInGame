import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.*;


class TestFox {

	GameObject[][] board;

	@BeforeEach
	protected void setUp() {
		board = new GameObject[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new GameObject(new Point(i, j));
			}
		}

	}

	protected HashSet<Point> optionMaker(int x1, int y1, int x2, int y2) {
		return new HashSet<Point>(Arrays.asList(new Point[] { new Point(x1, y1), new Point(x2, y2) }));
	}

	@Test
	void testNumberOptionsVertical() {

		GameObject mush31 = new GameObject(new Point(0, 1), "M1");
		GameObject mush32 = new GameObject(new Point(0, 2), "M2");

		board[1][0] = mush31;
		board[2][0] = mush32;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(1, 0, 1, 1));
		options.add(optionMaker(1, 2, 1, 3));
		options.add(optionMaker(1, 3, 1, 4));
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsVertical() {

		GameObject mush31 = new GameObject(new Point(0, 1), "M1");
		GameObject mush32 = new GameObject(new Point(0, 2), "M2");

		board[1][0] = mush31;
		board[2][0] = mush32;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(1, 0, 1, 1));
		options.add(optionMaker(1, 2, 1, 3));
		options.add(optionMaker(1, 3, 1, 4));
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsHorizontal() {

		GameObject mush31 = new GameObject(new Point(0, 1), "M1");
		GameObject mush32 = new GameObject(new Point(0, 2), "M2");

		board[1][0] = mush31;
		board[2][0] = mush32;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(0, 3, 1, 3));
		options.add(optionMaker(2, 3, 3, 3));
		options.add(optionMaker(3, 3, 4, 3));
		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsHorizontal() {

		GameObject mush31 = new GameObject(new Point(0, 1), "M1");
		GameObject mush32 = new GameObject(new Point(0, 2), "M2");

		board[1][0] = mush31;
		board[2][0] = mush32;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(0, 3, 1, 3));
		options.add(optionMaker(2, 3, 3, 3));
		options.add(optionMaker(3, 3, 4, 3));
		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsVerticalMushroomBlocking() {

		GameObject mush1 = new GameObject(new Point(1, 0), "M1");
		GameObject mush2 = new GameObject(new Point(1, 3), "M2");

		board[0][1] = mush1;
		board[3][1] = mush2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsVerticalMushroomBlocking() {
		GameObject mush1 = new GameObject(new Point(1, 0), "M1");
		GameObject mush2 = new GameObject(new Point(1, 3), "M2");

		board[0][1] = mush1;
		board[3][1] = mush2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsHorizontalMushroomBlocking() {

		GameObject mush1 = new GameObject(new Point(0, 3), "M1");
		GameObject mush2 = new GameObject(new Point(3, 3), "M2");

		board[3][0] = mush1;
		board[3][3] = mush2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsHorizontalMushroomBlocking() {
		GameObject mush1 = new GameObject(new Point(0, 3), "M1");
		GameObject mush2 = new GameObject(new Point(3, 3), "M2");

		board[3][0] = mush1;
		board[3][3] = mush2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();

		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsVerticalRabbitBlocking() {

		Rabbit rabbit1 = new Rabbit(new Point(1, 0), "R1");
		Rabbit rabbit2 = new Rabbit(new Point(1, 3), "R2");

		board[0][1] = rabbit1;
		board[3][1] = rabbit2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsVerticalRabbitBlocking() {
		Rabbit rabbit1 = new Rabbit(new Point(1, 0), "R1");
		Rabbit rabbit2 = new Rabbit(new Point(1, 3), "R2");

		board[0][1] = rabbit1;
		board[3][1] = rabbit2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsHorizontalRabbitBlocking() {

		GameObject rabbit1 = new Rabbit(new Point(0, 3), "R1");
		GameObject rabbit2 = new Rabbit(new Point(3, 3), "R2");

		board[3][0] = rabbit1;
		board[3][3] = rabbit2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsHorizontalRabbitBlocking() {
		GameObject rabbit1 = new Rabbit(new Point(0, 3), "R1");
		GameObject rabbit2 = new Rabbit(new Point(3, 3), "R2");

		board[3][0] = rabbit1;
		board[3][3] = rabbit2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();

		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsVerticalHoleBlocking() {

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(0, 2, 0, 3));

		Fox fox = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		board[1][0] = fox;
		board[2][0] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsVerticalHoleBlocking() {

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(0, 2, 0, 3));

		Fox fox = new Fox(new Point(0, 1), new Point(0, 2), "F1", "Vertical");
		board[1][0] = fox;
		board[2][0] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}

	@Test
	void testNumberOptionsHorizontalHoleBlocking() {

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(2, 0, 3, 0));

		Fox fox = new Fox(new Point(1, 0), new Point(2, 0), "F1", "Horizontal");
		board[0][1] = fox;
		board[0][2] = fox;
		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		assertEquals(returnedOptions.size(), options.size());
	}

	@Test
	void testCorrectOptionsHorizontalHoleBlocking() {
		GameObject rabbit1 = new Rabbit(new Point(0, 3), "R1");
		GameObject rabbit2 = new Rabbit(new Point(3, 3), "R2");

		board[3][0] = rabbit1;
		board[3][3] = rabbit2;

		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
		options.add(optionMaker(2, 0, 3, 0));

		Fox fox = new Fox(new Point(1, 0), new Point(2, 0), "F1", "Horizontal");
		board[0][1] = fox;
		board[0][2] = fox;

		ArrayList<Object> returnedOptions = fox.determineOptions(board);
		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
		for (Object element : returnedOptions) {
			Point[] tempArray = (Point[]) element;
			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
		}
		assertTrue(options.equals(setOptions));
	}
	
	//FIX
//	@Test
//	void testNumberOptionsVerticalHorizontalFoxBlocking() {
//		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
//
//		board[3][1] = fox;
//		board[3][2] = fox;
//
//		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
//		Fox fox2 = new Fox(new Point(1, 1), new Point(1, 2), "F2", "Vertical");
//		board[1][1] = fox2;
//		board[2][1] = fox2;
//		ArrayList<Object> returnedOptions = fox2.determineOptions(board);
//		assertEquals(returnedOptions.size(), options.size());
//	}
	
	//FIX
//	@Test
//	void testCorrectOptionsVerticalHorizontalFoxBlocking() {
//		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
//
//		board[3][1] = fox;
//		board[3][2] = fox;
//
//		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
//		Fox fox2 = new Fox(new Point(1, 1), new Point(1, 2), "F2", "Vertical");
//		board[1][1] = fox2;
//		board[2][1] = fox2;
//		ArrayList<Object> returnedOptions = fox2.determineOptions(board);
//		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
//		for (Object element : returnedOptions) {
//			Point[] tempArray = (Point[]) element;
//			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
//		}
//		assertTrue(options.equals(setOptions));
//	}

//	@Test
//	void testNumberOptionsHorizontalVerticalFoxBlocking() {
//		Fox fox = new Fox(new Point(4, 3), new Point(4,4), "F1", "Vertical");
//
//		board[3][4] = fox;
//		board[4][4] = fox;
//
//		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
//		Fox fox2 = new Fox(new Point(1, 3), new Point(2, 3), "F2", "Horizontal");
//		board[3][1] = fox2;
//		board[3][2] = fox2;
//		ArrayList<Object> returnedOptions = fox2.determineOptions(board);
//		assertEquals(returnedOptions.size(), options.size());
//	}

//	@Test
//	void testCorrectOptionsHorizontalVerticalFoxBlocking() {
//		Fox fox = new Fox(new Point(4, 3), new Point(4,4), "F1", "Vertical");
//
//		board[3][4] = fox;
//		board[4][4] = fox;
//
//		HashSet<HashSet<Point>> options = new HashSet<HashSet<Point>>();
//
//		Fox fox2 = new Fox(new Point(1, 3), new Point(2, 3), "F2", "Horizontal");
//		board[3][1] = fox2;
//		board[3][2] = fox2;
//		ArrayList<Object> returnedOptions = fox2.determineOptions(board);
//		HashSet<HashSet<Point>> setOptions = new HashSet<HashSet<Point>>();
//		for (Object element : returnedOptions) {
//			Point[] tempArray = (Point[]) element;
//			setOptions.add(new HashSet<Point>(Arrays.asList(tempArray)));
//		}
//		assertTrue(options.equals(setOptions));
//	}

	//FIX
//	@Test
//	void testDisplayOptions() {
//		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
//		board[1][1] = fox;
//		board[2][1] = fox;
//		String output = "1 {(1.0,0.0) (1.0,1.0)}\n" + "2 {(1.0,3.0) (1.0,2.0)}\n" + "3 {(1.0,4.0) (1.0,3.0)}\n";
//		assertEquals(fox.displayOptions(board), output);
//	}

	@Test
	void testFoxConstructorIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Fox(new Point(0, 1), new Point(0, -1), "F1", "north");
		});
	}
	
	@Test
	void testGetCoordinates2() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		assertEquals(new Point(1,2), fox.getCoordinate2());
	}
	
	@Test
	void testSetCoordinate2() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		fox.setCoordinate2(new Point(1,3));
		assertEquals(new Point(1,3),fox.getCoordinate2());
	}

	@Test
	void testGetX2() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		assertEquals(1, fox.getX2());
	}
	
	@Test
	void testGetY2() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		assertEquals(2,fox.getY2());
	}
	
	@Test
	void testGetDirection() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		assertEquals("Vertical",fox.getDirection());
	}
	
	@Test
	void testSetDirection() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		fox.setDirection("Horizontal");
		assertEquals("Horizontal",fox.getDirection());
	}
}