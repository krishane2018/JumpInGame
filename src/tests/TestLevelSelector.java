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

	@Test
	void testGetLevel() {
		assertNotNull(tester);
	}
}
