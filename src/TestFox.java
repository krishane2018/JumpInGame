
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

	@Test
	void testDisplayOptions() {
		Fox fox = new Fox(new Point(1, 1), new Point(1, 2), "F1", "Vertical");
		board[1][1] = fox;
		board[2][1] = fox;
		String output = "1 {(1.0,0.0) (1.0,1.0)}\n" + "2 {(1.0,3.0) (1.0,2.0)}\n" + "3 {(1.0,4.0) (1.0,3.0)}\n";
		assertEquals(fox.displayOptions(board), output);
	}

	@Test
	void testFoxConstructorIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Fox(new Point(0, 1), new Point(0, -1), "F1", "north");
		});
	}

	@Test
	void testHandleEvent() {
		Fox fox = new Fox(new Point(1, 3), new Point(2, 3), "F1", "Horizontal");
		board[3][1] = fox;
		board[3][2] = fox;
		JumpIn game = new JumpIn(3);
		Point point1 = new Point(2, 3);
		Point point2 = new Point(3, 3);
		Point[] holes = { new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0), new Point(4, 4) };
		JumpInEvent event = new JumpInEvent(game, fox, point1, point2, holes);
		fox.handleEvent(event);
		assertTrue(fox.getCoordinate().equals(point1) && fox.getCoordinate2().equals(point2));

	}

}
