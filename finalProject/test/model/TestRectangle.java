package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Color;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Rectangle Shape class.
 */
public class TestRectangle {

  private Rectangle initName;
  private Rectangle initFull;

  private final Color black = new Color("000000");

  @Before
  public void init() {
    initName = new Rectangle("onlyName");
    initFull = new Rectangle(new Point2D(0, 0), black, "fullRec", 10, 5, 0, 100);
  }

  @Test
  public void testGetName() {
    assertEquals("onlyName", initName.getName());
    assertEquals("fullRec", initFull.getName());
  }

  @Test
  public void testReference() {
    assertEquals(null, initName.getReference());
    assertEquals(new Point2D(0, 0), initFull.getReference());

    initName.setReference(new Point2D(10, 4));
    assertEquals(new Point2D(10, 4), initName.getReference());

    initName.setReference(null);
    assertEquals(null, initName.getReference());
  }

  @Test
  public void testColor() {
    assertEquals(null, initName.getColor());
    assertEquals(new Color(0, 0, 0), initFull.getColor());

    initFull.setColor(new Color("FFFFFF"));
    assertEquals(new Color(255, 255, 255), initFull.getColor());

    initFull.setColor(black);
    assertEquals(new Color(0, 0, 0), initFull.getColor());
  }

  @Test
  public void testWidth() {
    assertEquals(0, initName.getWidth());
    assertEquals(10, initFull.getWidth());

    initName.setWidth(8);
    assertEquals(8, initName.getWidth());

    initName.setWidth(0);
    assertEquals(0, initName.getWidth());
  }

  @Test
  public void testWidthFailures() {
    for(int i = -1; i > -1000; i--) {
      try {
        initName.setWidth(i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(true, true);
      }
    }
  }

  @Test
  public void testHeight() {
    assertEquals(0, initName.getHeight());
    assertEquals(5, initFull.getHeight());

    initFull.setHeight(3);
    assertEquals(3, initFull.getHeight());

    initFull.setHeight(0);
    assertEquals(0, initFull.getHeight());
  }

  @Test
  public void testHeightFailures() {
    for(int i = -1; i > -1000; i--) {
      try {
        initName.setHeight(i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(true, true);
      }
    }
  }

  @Test
  public void testAppearsAt() {
    assertEquals(0, initName.getAppearsAt());
    assertEquals(0, initFull.getAppearsAt());

    initFull.setAppearsAt(436);
    assertEquals(436, initFull.getAppearsAt());

    initFull.setAppearsAt(0);
    assertEquals(0, initFull.getAppearsAt());
  }

  @Test
  public void testAppearsFailures() {
    for(int i = -1; i > -1000; i--) {
      try {
        initName.setAppearsAt(i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(true, true);
      }
    }
  }

  @Test
  public void testDisappearsAt() {
    assertEquals(0, initName.getDisappearsAt());
    assertEquals(100, initFull.getDisappearsAt());

    initFull.setDisappearsAt(2);
    assertEquals(2, initFull.getDisappearsAt());

    initFull.setDisappearsAt(0);
    assertEquals(0, initFull.getDisappearsAt());
  }

  @Test
  public void testDisappearsFailures() {
    for(int i = -1; i > -1000; i--) {
      try {
        initName.setDisappearsAt(i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(true, true);
      }
    }
  }

  @Test
  public void testGetShapeType() {
    assertEquals("RECTANGLE", initName.getShapeType());
    assertEquals("RECTANGLE", initFull.getShapeType());
  }

  @Test
  public void testToString() {
    assertEquals(
        "<rect id='fullRec' x='0' y='0' width='10' height='5' fill='000000' visibility='visible'>***</rect>",
        initFull.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testToStringException() {
    assertEquals(
        "there is no reference or color in this shape",
        initName.toString());
  }

}
