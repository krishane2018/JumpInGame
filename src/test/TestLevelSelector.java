package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.JumpIn;
import source.LevelSelector;

class TestLevelSelector {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLevelSelectorConstructorIllegalArgumentSubZero() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LevelSelector(-1, new JumpIn(-1));
		});
	}

	@Test
	void testLevelSelectorConstructorIllegalArgumentAboveThree() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LevelSelector(4, new JumpIn(4));
		});
	}
}
