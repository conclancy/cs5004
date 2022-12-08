package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Size;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Size class.
 */
public class TestSize {

  private Size sizeRectangle;
  private Size sizeSquare;
  private Size sizeOval;
  private Size sizeCircle;

  @Before
  public void init() {
    sizeRectangle = new Size(1, 2);
    sizeSquare = new Size(24);
    sizeOval = new Size(2.3, 4.6);
    sizeCircle = new Size(8.2);
  }

  @Test
  public void testGetDimensionOne() {
    assertEquals(1, sizeRectangle.getDimensionOne(), .01);
    assertEquals(24, sizeSquare.getDimensionOne(), .01);
    assertEquals(2.3, sizeOval.getDimensionOne(), .01);
    assertEquals(8.2, sizeCircle.getDimensionOne(), .01);
  }

  @Test
  public void testGetDimensionTwo() {
    assertEquals(2, sizeRectangle.getDimensionTwo(), .01);
    assertEquals(24, sizeSquare.getDimensionTwo(), .01);
    assertEquals(4.6, sizeOval.getDimensionTwo(), .01);
    assertEquals(8.2, sizeCircle.getDimensionTwo(), .01);
  }

  @Test
  public void testGetDimension() {
    assertEquals(24, sizeSquare.getDimension(), .01);
    assertEquals(8.2, sizeCircle.getDimension(), .01);
  }

  @Test(expected = IllegalStateException.class)
  public void testRectangleGetDimension() {
    assertEquals(1, sizeRectangle.getDimension(), .01);
  }

  @Test(expected = IllegalStateException.class)
  public void testOvalGetDimension() {
    assertEquals(1, sizeOval.getDimension(), .01);
  }

  @Test
  public void testSizes() {
    for (double i = 0.1; i < 1000; i += .1) {
      for (double j = 0.1; j < 1000; j += .1) {
        Size temp = new Size(i, j);

        if (i == j) {
          assertEquals(i, temp.getDimension(), .01);
        }

        assertEquals(i, temp.getDimensionOne(), .01);
        assertEquals(j, temp.getDimensionTwo(), .01);
      }
    }
  }

  @Test
  public void testIllegalSizes() {
    for (double i = 0; i > -100; i -= .1) {
      for (double j = 0; j > -100; j -= .1) {
        try {
          Size temp = new Size(i, j);
        } catch (IllegalArgumentException e) {
          assertEquals(1, sizeRectangle.getDimensionOne(), .01);
        }
      }
    }
  }

}
