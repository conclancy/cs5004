package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the oval class.
 */
public class TestOval {

  private Oval smallOval;

  @Before
  public void init() {
    smallOval = new Oval(0,0,2,1,new Color("000000"));
  }

  @Test
  public void testArea() {
    assertEquals(6.28, smallOval.area(), .01);
  }

  @Test
  public void testPerimeter() {
    assertEquals(9.69, smallOval.perimeter(), .01);
  }

  @Test
  public void testResize() {
    // TODO
  }

  @Test
  public void testCopy() {
    Oval smallOvalCopy = (Oval) smallOval.copy();

    assertEquals(0, smallOval.compareTo(smallOvalCopy));
    assertEquals(smallOval.getColor(), smallOvalCopy.getColor());
  }
}
