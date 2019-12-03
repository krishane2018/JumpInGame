package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.*;

import org.junit.jupiter.api.*;

import source.Fox;
import source.GameObject;
import source.Rabbit;
import source.XMLHandler;

class TestXMLHandler {
  
	private XMLHandler handler1;
	
  @BeforeEach
  void setUp() throws Exception {
    handler1 = new XMLHandler(1); 
  }
  @Test
  void testIsHoleTrue() {
    assertTrue(handler1.isHole(2, 2));
  }
  @Test
  void testIsholeFalse() {
    assertFalse(handler1.isHole(1, 2));
  }
  @Test
  void testGetLevel() {
    assertEquals(handler1.getLevel(), -1);
  }
}
