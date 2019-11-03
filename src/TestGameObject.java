import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGameObject {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGameObjectConstructorIllegalArgument() {
		 assertThrows(IllegalArgumentException.class, () -> {
			    new GameObject(new Point(-1,1), "F1");
			  });	
	}

}
