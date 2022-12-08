package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Color class.
 */
public class TestColor {

  private Color softRedInt;
  private Color softRedHex;
  private Color whiteInt;
  private Color whiteHex;
  private Color blackInt;
  private Color blackHex;
  private Color aquamarineInt;
  private Color aquamarineHex;

  @Before
  public void init() {
    softRedInt = new Color(201, 20, 60);
    softRedHex = new Color("C9143C");
    whiteInt = new Color(255,255,255);
    whiteHex = new Color("FFFFFF");
    blackInt = new Color(0,0,0);
    blackHex = new Color("000000");
    aquamarineInt = new Color(127, 255, 212);
    aquamarineHex = new Color("7FFFD4");
  }

  @Test
  public void testGetRed() {
    assertEquals(201, softRedInt.getRed());
    assertEquals(201, softRedHex.getRed());
    assertEquals(255, whiteInt.getRed());
    assertEquals(255, whiteHex.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(20, softRedInt.getGreen());
    assertEquals(20, softRedHex.getGreen());
    assertEquals(255, whiteInt.getGreen());
    assertEquals(255, whiteHex.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(60, softRedInt.getBlue());
    assertEquals(60, softRedHex.getBlue());
    assertEquals(255, whiteInt.getBlue());
    assertEquals(255, whiteHex.getBlue());
  }

  @Test
  public void testSetMethods() {
    assertEquals(true, blackHex.equals(blackInt));

    blackHex.setRed(127);
    blackHex.setGreen(255);
    blackHex.setBlue(212);

    assertEquals(true, blackHex.equals(aquamarineHex));

    blackInt.setHex("7FFFD4");

    assertEquals(true, blackHex.equals(blackInt));
    assertEquals(true, blackHex.equals(aquamarineInt));

    blackInt.setRGB(0,0,0);
    blackHex.setHex("000000");

    assertEquals(false, blackHex.equals(aquamarineInt));
    assertEquals(true, blackHex.equals(blackInt));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSet() {
    blackHex.setBlue(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPositiveSet() {
    blackHex.setBlue(256);
  }

  @Test
  public void testAllValidColors() {
    assertEquals(255, whiteInt.getRed());
    for (int r = 0; r < 256; r++) {
      for (int g = 0; g < 256; g++) {
        for (int b = 0; b < 256; b++) {
          new Color(r,g,b);
        }
      }
    }
  }

  @Test
  public void testNegativeSets() {
    assertEquals(255, whiteInt.getRed());
    for (int i = -1; i > -1000; i--) {

      try {
        blackHex.setRed(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }

      try {
        blackHex.setGreen(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }

      try {
        blackHex.setBlue(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }
    }
  }

  @Test
  public void testBigSets() {
    assertEquals(255, whiteInt.getRed());
    for (int i = 256; i <= 1000; i++) {

      try {
        blackHex.setRed(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }

      try {
        blackHex.setGreen(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }

      try {
        blackHex.setBlue(i);
      } catch (IllegalArgumentException e) {
        assertEquals(255, whiteInt.getRed());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallHex() {
    new Color("12345");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLargeHex() {
    new Color("1234567");
  }

  @Test
  public void testEquals() {
    assertEquals(true, softRedInt.equals(softRedHex));
    assertEquals(true, softRedInt.equals(softRedInt));
    assertEquals(true, whiteHex.equals(whiteInt));
    assertEquals(false, softRedInt.equals(whiteInt));
  }

  @Test
  public void testGetRGB() {
    assertEquals("(255, 255, 255)", whiteInt.getRGB());
    assertEquals("(255, 255, 255)", whiteHex.getRGB());
    assertEquals("(0, 0, 0)", blackInt.getRGB());
    assertEquals("(0, 0, 0)", blackHex.getRGB());
    assertEquals("(127, 255, 212)", aquamarineInt.getRGB());
    assertEquals("(127, 255, 212)", aquamarineHex.getRGB());
    assertEquals("(201, 20, 60)", softRedInt.getRGB());
    assertEquals("(201, 20, 60)", softRedHex.getRGB());
  }

  @Test
  public void testGetHex() {
    assertEquals("FFFFFF", whiteInt.getHex());
    assertEquals("FFFFFF", whiteHex.getHex());
    assertEquals("000000", blackInt.getHex());
    assertEquals("000000", blackHex.getHex());
    assertEquals("7FFFD4", aquamarineInt.getHex());
    assertEquals("7FFFD4", aquamarineHex.getHex());
    assertEquals("C9143C", softRedInt.getHex());
    assertEquals("C9143C", softRedHex.getHex());
  }

}
