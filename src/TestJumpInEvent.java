import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJumpInEvent {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testJumpInEventConstructorIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> {
			new JumpInEvent(new JumpIn(3), new Rabbit(new Point(0, 0), "R1"), new Point(0, -1), new Point[] {
					new Point(0, 0), new Point(2, 2), new Point(0, 4), new Point(4, 0), new Point(4, 4) });
		});
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
