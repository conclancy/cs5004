package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Point2D class.
 */
public class TestPoint2D {

  private Point2D origin;
  private Point2D one;
  private Point2D tt;
  private Point2D fiveFour;
  private Point2D negative;

  @Before
  public void init() {
    origin = new Point2D(0,0);
    one = new Point2D(1,1);
    tt = new Point2D(22,18);
    fiveFour = new Point2D(5.4,0);
    negative = new Point2D(-1,-2);
  }

  @Test
  public void testGetX() {
    assertEquals(0, origin.getX(), .01);
    assertEquals(1, one.getX(), .01);
    assertEquals(22, tt.getX(), .01);
    assertEquals(5.4, fiveFour.getX(), .01);
    assertEquals(-1, negative.getX(), .01);
  }

  @Test
  public void testGetY() {
    assertEquals(0, origin.getY(), .01);
    assertEquals(1, one.getY(), .01);
    assertEquals(18, tt.getY(), .01);
    assertEquals(0, fiveFour.getY(), .01);
    assertEquals(-2, negative.getY(), .01);
  }

  @Test
  public void testDistToOrigin() {
    assertEquals(0, origin.distToOrigin(), .01);
    assertEquals(Math.sqrt(2), one.distToOrigin(), .01);
    assertEquals(Math.sqrt(18*18+22*22), tt.distToOrigin(), .01);
    assertEquals(Math.sqrt(5.4*5.4), fiveFour.distToOrigin(), .01);
    assertEquals(Math.sqrt(-1*-1+-2*-2), negative.distToOrigin(), .01);
  }
}
