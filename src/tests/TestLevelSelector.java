package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.LevelSelector;

class TestLevelSelector {

	private LevelSelector tester;

	@BeforeEach
	void setUp() throws Exception {
		tester = new LevelSelector();
	}
	/*
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
	}*/
	@Test
	void testGetLevel() {
		LevelSelector selector;
		assertTrue(selector.getLevel(1, false) instanceof Level);

	void testGetLevelNull() {
		assertNotNull(tester);

	}
}
