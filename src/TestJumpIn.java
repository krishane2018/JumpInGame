import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJumpIn {

	@Test
	void testLevel1() {
		JumpIn jumpIn = new JumpIn(1);
		String level1 = "--------------------------\n"
				+ "|   H|    |  R1|    |   H|\n"
				+ "--------------------------\n"
				+ "|    |  M3|    |    |    |\n"
				+ "--------------------------\n"
				+ "|  M1|    |   H|    |    |\n"
				+ "--------------------------\n"
				+ "|  M2|    |    |    |    |\n"
				+ "--------------------------\n"
				+ "| R2H|    |    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn.toString(),level1);
	}
	
	@Test
	void testLevel2() {
		JumpIn jumpIn = new JumpIn(2);
		String level2 = "--------------------------\n"
				+ "| R1H|  M1|    |    |   H|\n"
				+ "--------------------------\n"
				+ "|    |    |  M2|    |    |\n"
				+ "--------------------------\n"
				+ "|    |  M3|   H|  R2|    |\n"
				+ "--------------------------\n"
				+ "|    |    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|   H|    |    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn.toString(),level2);
	}
	
	@Test
	void testLevel3() {
		JumpIn jumpIn = new JumpIn(3);
		String level3 = "--------------------------\n"
				+ "|   H|  R1|    |    |   H|\n"
				+ "--------------------------\n"
				+ "|  M1|    |    |    |    |\n"
				+ "--------------------------\n"
				+ "|  M2|    |   H|    |    |\n"
				+ "--------------------------\n"
				+ "|    |  F1|  M3|    |    |\n"
				+ "--------------------------\n"
				+ "|   H|  F1|    |    |   H|\n"
				+ "--------------------------\n";
		
		assertEquals(jumpIn.toString(),level3);
	}

}
