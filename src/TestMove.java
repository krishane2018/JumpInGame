import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMove {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testMove3ArgConstructorIllegalArgument() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new Move(new Point(0,0), new Point(0, -1), new Rabbit(new Point(0, 0), "R1"));
			  });	
	}
	
	void testMove5ArgConstructorIllegalArgument() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new Move(new Point(0,0), new Point(0, 1), new Point(0,-1), new Point(0, 0), 
			    		new Fox(new Point(0,0), new Point(0, 1), "F1", "Vertical"));
			  });	
	}

}
