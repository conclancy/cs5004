import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test for the Rectangle class.
 */
public class RectangleTest {

  private Rectangle rectangleSmall;
  private Rectangle rectangleLarge;
  private Rectangle rectangleSmallFalse;

  /**
   * Set up testing objects.
   */
  @Before
  public void setUp() {
    rectangleSmall = new Rectangle(0, 0, 1, 2);
    rectangleLarge = new Rectangle(0, 0, 100, 100);
    rectangleSmallFalse = new Rectangle(2, 2, 1, 1);
  }

  /**
   * Test illegal setup error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDimensions() {
    Rectangle rectangleIllegal;
    rectangleIllegal = new Rectangle(0, 0, -1, -1);
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("x:0, y:0, w:1, h:2", rectangleSmall.toString());
    assertEquals("x:0, y:0, w:100, h:100", rectangleLarge.toString());
  }

  /**
   * Test overlap method.
   */
  @Test
  public void testOverlap() {
    assertEquals(true, rectangleSmall.overlap(rectangleLarge));
    assertEquals(true, rectangleLarge.overlap(rectangleSmall));
    assertEquals(false, rectangleSmall.overlap(rectangleSmallFalse));
    assertEquals(true, rectangleSmallFalse.overlap(rectangleLarge));
  }

  /**
   * Test when rectangles do not overlap one another.
   */
  @Test(expected = NoSuchElementException.class)
  public void testNoIntersect() {
    rectangleSmall.intersect(rectangleSmallFalse);
  }

  /**
   * Test the intersection method.
   */
  @Test
  public void testIntersect() {
    Rectangle testRectangle;
    testRectangle = rectangleSmall.intersect(rectangleLarge);
    assertEquals("x:0, y:0, w:1, h:2", testRectangle.toString());
  }

  /**
   * Test the union method.
   */
  @Test
  public void testUnion() {
    Rectangle testRectangle;
    testRectangle = rectangleSmall.union(rectangleSmallFalse);
    assertEquals("x:0, y:0, w:3, h:3", testRectangle.toString());
  }

}
