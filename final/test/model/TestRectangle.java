package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Rectangle class.
 */
public class TestRectangle {

  private Rectangle smallRectangle;
  private Rectangle largeRectangle;
  private Color black;

  @Before
  public void init() {
    smallRectangle = new Rectangle(0, 0, 2, 1, new Color("000000"));
    largeRectangle = new Rectangle(-1, -1, 5, 4, new Color("000000"));
    black = new Color("000000");
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, smallRectangle.getWidth(), .01);
    assertEquals(5, largeRectangle.getWidth(), .01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, smallRectangle.getHeight(), .01);
    assertEquals(4, largeRectangle.getHeight(), .01);
  }

  @Test
  public void testArea() {
    assertEquals(2, smallRectangle.area(), .01);
    assertEquals(20, largeRectangle.area(), .01);
  }

  @Test
  public void testPerimeter() {
    assertEquals(6, smallRectangle.perimeter(), .01);
    assertEquals(18, largeRectangle.perimeter(), .01);
  }

  @Test
  public void testCopy() {
    IShape smallCopy = smallRectangle.copy();
    assertEquals(true, smallRectangle.equals(smallCopy));

    IShape largeCopy = largeRectangle.copy();
    assertEquals(true, largeRectangle.equals(largeCopy));
    assertEquals(false, smallRectangle.equals(largeCopy));
  }

  @Test
  public void testResize() {
    IShape tripleSmall = smallRectangle.resize(3);
    assertEquals(18, tripleSmall.perimeter(), .01);
    assertEquals(18, tripleSmall.area(), .01);

    IShape doubleLarge = largeRectangle.resize(2);
    assertEquals(36, doubleLarge.perimeter(), .01);
    assertEquals(80, doubleLarge.area(), .01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeZero() {
    IShape small = smallRectangle.resize(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeNegativeInt() {
    IShape small = smallRectangle.resize(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeNegativeDouble() {
    IShape small = smallRectangle.resize(-.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegWidth() {
    IShape errorRectangle = new Rectangle(0,0,-1,0, black);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegHeight() {
    IShape errorRectangle = new Rectangle(0,0,0,-100, black);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegBoth() {
    IShape errorRectangle = new Rectangle(0,0,-10,-8, black);
  }
}
