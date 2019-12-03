package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Play;

class TestPlay {

	@Test
	void test() {
		assertTrue(Play.fileIsEmpty(""));
	}

}
