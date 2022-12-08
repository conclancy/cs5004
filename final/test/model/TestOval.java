package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Color;
import cs5004.animator.model.Oval;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the oval class.
 */
public class TestOval {

  private Oval smallOval;
  private Oval smallCircle;

  @Before
  public void init() {
    smallOval = new Oval(0, 0, 2, 1, new Color("000000"));
    smallCircle = new Oval(0, 0, 1, 1, new Color("000000"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSingleNegativeMajorAxis() {
    Oval illegalOval = new Oval(0,0,-1,0, new Color("000000"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSingleNegativeMinorAxis() {
    Oval illegalOval = new Oval(0,0,0,-1, new Color("ABC123"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleNegativeAxis() {
    Oval illegalOval = new Oval(0,0,-1,-1, new Color("FFFFFF"));
  }

  @Test
  public void testArea() {
    assertEquals(6.28, smallOval.area(), .01);
    assertEquals(3.14, smallCircle.area(), .01);
  }

  @Test
  public void testPerimeter() {
    assertEquals(9.69, smallOval.perimeter(), .01);
    assertEquals(6.28, smallCircle.perimeter(), .01);
  }

  @Test
  public void testCopy() {
    Oval smallOvalCopy = (Oval) smallOval.copy();

    assertEquals(0, smallOval.compareTo(smallOvalCopy));
    assertEquals(smallOval.getColor(), smallOvalCopy.getColor());
    assertEquals(true, smallOval.equals(smallOvalCopy));

    assertEquals(false, smallOvalCopy.equals(smallCircle));
  }

  @Test
  public void testGetName() {
    Oval named = new Oval("test", 0, 0, 2, 1, new Color("000000"));
    assertEquals("test", named.getName());
  }
}
