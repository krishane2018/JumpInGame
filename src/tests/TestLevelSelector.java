package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.JumpIn;
import source.Level;
import source.LevelSelector;



class TestLevelSelector {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetLevel() throws Exception {
		assertTrue(LevelSelector.getLevel(1, false) instanceof Level);
	}
}
