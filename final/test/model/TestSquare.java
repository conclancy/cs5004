package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test of the Square class.
 */
public class TestSquare {

  private Square intSquare;
  private Square doubleSquare;

  @Before
  public void init() {
    intSquare = new Square(0,0,1, new Color("000000"));
    doubleSquare = new Square(0,0, 2.5, new Color("000000"));
  }

  @Test
  public void testArea() {
    assertEquals(1, intSquare.area(), .01);
    assertEquals(6.25, doubleSquare.area(), .01);
  }

  @Test
  public void testPerimeter() {
    assertEquals(4, intSquare.perimeter(), .01);
    assertEquals(10, doubleSquare.perimeter(), .01);
  }

}
