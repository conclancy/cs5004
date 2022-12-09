package model;

import static org.junit.Assert.assertEquals;

import cs5004.animatoVersion1.model.Circle;
import cs5004.animatoVersion1.model.Color;
import cs5004.animatoVersion1.model.Oval;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Circle class.
 */
public class TestCircle {

  private Circle smallCircle;
  private Circle largeCircle;
  private Circle nineCircle;

  @Before
  public void init() {
    smallCircle = new Circle(0,0,1, new Color("000000"));
    largeCircle = new Circle(1, -1, 10, new Color("FFFFFF"));
    nineCircle = new Circle(10, 8, 9, new Color(0,0,0));
  }

  @Test
  public void testArea() {
    assertEquals(3.14, smallCircle.area(), .01);
    assertEquals(314.16, largeCircle.area(), .01);
    assertEquals(254.47, nineCircle.area(), .01);
  }

  @Test
  public void testPerimeter() {
    assertEquals(6.28, smallCircle.perimeter(), .01);
    assertEquals(62.83, largeCircle.perimeter(), .01);
    assertEquals(56.55, nineCircle.perimeter(), .01);
  }

  @Test
  public void testResize() {
    // TODO
    assertEquals(6.28, smallCircle.perimeter(), .01);
  }

  @Test
  public void testCopy() {
    Oval smallCircleCopy = (Oval) smallCircle.copy();

    assertEquals(0, smallCircle.compareTo(smallCircleCopy));
    assertEquals(smallCircle.getColor(), smallCircleCopy.getColor());
    assertEquals(true, smallCircle.equals(smallCircleCopy));

    assertEquals(false, smallCircleCopy.equals(largeCircle));
  }

  @Test
  public void testOvalEquality() {
    Oval smallCircleOval = new Oval(0,0,1,1, new Color("000000"));
    assertEquals(true, smallCircle.equals(smallCircleOval));
  }
}
