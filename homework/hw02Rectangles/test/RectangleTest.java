import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.Rect;

/**
 * A JUnit test for the Rectangle class.
 */
public class RectangleTest {

  private Rectangle rectangleIllegal;
  private Rectangle rectangleSmall;
  private Rectangle rectangleLarge;
  private Rectangle rectangleSmallFalse;

  /**
   * Set up testing objects.
   */
  @Before
  public void setUp() {
    rectangleSmall = new Rectangle(0,0,1,2);
    rectangleLarge = new Rectangle(0,0,100,100);
    rectangleSmallFalse = new Rectangle(2,2,1,1);
  }

  /**
   * Test illegal setup error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDimensions() {
    rectangleIllegal = new Rectangle(0,0,-1,-1);
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

}
