package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestColor {

  private Color softRedInt;
  private Color softRedHex;

  @Before
  public void init() {
    softRedInt = new Color(201, 20, 60);
    softRedHex = new Color("C9143C");
  }

  @Test
  public void testGetRed() {
    assertEquals(201, softRedInt.getRed());
    assertEquals(201, softRedHex.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(20, softRedInt.getGreen());
    assertEquals(20, softRedHex.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(60, softRedInt.getBlue());
    assertEquals(60, softRedHex.getBlue());
  }

  @Test
  public void testEquals() {
    assertEquals(true, softRedInt.equals(softRedHex));
  }

}
