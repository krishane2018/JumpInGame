package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Move;
import source.Rabbit;



class TestMove {

	@BeforeEach
	void setUp() throws Exception {
	}

	//Rabbit
	@Test
	void testMove3ArgConstructorIllegalArgumentLowerFinalY() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, 0), new Point(0, -1), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentLowerInitialY() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, -1), new Point(0, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentLowerFinalX() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, 0), new Point(-1, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentLowerInitialX() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(-1, 0), new Point(0, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentUpperFinalY() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, 0), new Point(0, 5), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentUpperFinalX() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, 0), new Point(5, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentUpperInitialY() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(0, 5), new Point(0, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}

	@Test
	void testMove3ArgConstructorIllegalArgumentUpperInitialX() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Move(new Point(5, 0), new Point(0, 0), new Rabbit(new Point(0, 0), "R1"));
		});
	}
	
	//Fox
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerInitialX1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(-1, 0), new Point(0, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerInitialX2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(-1, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerInitialY1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, -1), new Point(0, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerInitialY2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, -1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerFinalX1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(-1, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerFinalX2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, 0), new Point(-1, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerFinalY1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, -1), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentLowerFinalY2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, 0), new Point(0, -1),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperInitialX1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(5, 0), new Point(0, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperInitialX2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(5, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperInitialY1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 5), new Point(0, 1), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperInitialY2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 5), new Point(0, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperFinalX1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(5, 0), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperFinalX2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, 0), new Point(5, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperFinalY1() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, 5), new Point(0, 0),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
//	
//	@Test
//	void testMove5ArgConstructorIllegalArgumentUpperFinalY2() {
//		assertThrows(IllegalArgumentException.class, () -> {
//			new Move(new Point(0, 0), new Point(0, 1), new Point(0, 0), new Point(0, 5),
//					new Fox(new Point(0, 0), new Point(0, 1), "F1", "Vertical"));
//		});
//	}
}
