package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Action class.
 */
public class TestAction {

  private Color black;
  private Color white;

  private Action initial;
  private Action changeX;
  private Action changeY;
  private Action changeColor;

  @Before
  public void init() {

    black = new Color("000000");
    white = new Color("FFFFFF");

    initial = new Action(0,1,0,0, black);
    changeX = new Action(0,1,1,0, black);
    changeY = new Action(1,2, 1,1,black);
    changeColor = new Action(1,2, 1,1,black);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalAction() {
    Action error = new Action(1,0,0,0, black);
  }

  @Test
  public void testGetAppears() {
    assertEquals(0, initial.getAppears());
    assertEquals(0, changeX.getAppears());
    assertEquals(1, changeY.getAppears());
    assertEquals(1, changeColor.getAppears());
  }

  @Test
  public void testGetDisappears() {
    assertEquals(1, initial.getDisappears());
    assertEquals(1, changeX.getDisappears());
    assertEquals(2, changeY.getDisappears());
    assertEquals(2, changeColor.getDisappears());
  }

  @Test
  public void testSetTimingMethods() {
    initial.setDisappears(11);
    assertEquals(11, initial.getDisappears());
    initial.setAppears(10);
    assertEquals(10, initial.getAppears());
    initial.setAppears(0);
    initial.setDisappears(1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetAppears() {
    initial.setAppears(2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSetDisappears() {
    changeY.setDisappears(0);
  }

  @Test
  public void testGetReference() {
    assertEquals(new Point2D(0,0), initial.getPoint2D());
    assertEquals(new Point2D(1,0), changeX.getPoint2D());
    assertEquals(new Point2D(1,1), changeY.getPoint2D());
    assertEquals(new Point2D(1,1), changeColor.getPoint2D());
  }

  @Test
  public void testSetReference() {
    initial.setPoint2D(10,22);
    assertEquals(new Point2D(10,22), initial.getPoint2D());
    initial.setPoint2D(0,0);
    assertEquals(new Point2D(0,0), initial.getPoint2D());
  }

  @Test
  public void testColorMethods() {
    assertEquals(black, initial.getColor());
    initial.setColor(white);
    assertEquals(white, initial.getColor());
    initial.setColor(black);
    assertEquals(black, initial.getColor());
  }

  @Test
  public void testSizeMethods() {
    assertEquals(1, initial.getSize(),.01);
    initial.setSize(4);
    assertEquals(4, initial.getSize(),.01);
    initial.setSize(1);
    assertEquals(1, initial.getSize(),.01);
  }
}