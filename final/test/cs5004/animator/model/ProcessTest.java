package cs5004.animator.model;

import org.junit.Test;

import java.awt.geom.Point2D;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * Testing methods for the Process class.
 */
public class ProcessTest {

  /**
   * Tests that the constructor throws an exception if it is given an end time before the start
   * time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsStartBeforeEnd() {
    IProcess move1 = new Process("Motion", 5, 10,
            10, 10, 30, 0, 255, 0, 0, 20, 10, 10, 30,
            0, 255, 0, 0, 0);
  }

  /**
   * Tests that the constructor throws an exception if it is given a negative start time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsNegativeTime() {
    IProcess move1 = new Process("Motion", -5, 10, 10,
            10, 30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0, 0, 0);
  }

  /**
   * Tests that the constructor throws an exception if it is given a negative width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsNegativeWidth() {
    IProcess move1 = new Process("Motion", 0, 10, 10,
            -10, 30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0, 0, 0);
  }

  /**
   * Tests that the constructor throws an exception if it is given a negative height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsNegativeHeight() {
    IProcess move1 = new Process("Motion", 0, 10, 10,
            10, 30, 0, 255, 0, 5, 20, 10, 10, -30,
            0, 255, 0, 0, 0);
  }

  /**
   * Tests that the constructor throws an exception if it is given bad R, G, B values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsBadRGB() {
    IProcess move1 = new Process("Motion", 0, 10, 10,
            10, 30, 0, 256, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
  }

  /**
   * Tests that the constructor throws an exception if it is given bad R, G, B values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailsBadRGB2() {
    IProcess move1 = new Process("Motion", 0, 10, 10,
            10, 30, 0, 255, 0, 5, 20, 10, 10, 30,
            -5, 255, 0);
  }

  /**
   * Tests the getter for start time.
   */
  @Test
  public void testGetStartTime() {
    IProcess move1 = new Process("Motion", 1, 10, 10,
            10, 30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 12, 10, 10,
            10, 30, 0, 255, 0, 17, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartTime(), 1);
    assertEquals(move2.getStartTime(), 12);
  }

  /**
   * Tests the getter for end time.
   */
  @Test
  public void testGetEndTime() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 2, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 5, 10, 10, 10,
            30, 0, 255, 0, 15, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getEndTime(), 2);
    assertEquals(move2.getEndTime(), 15);
  }

  /**
   * Tests the getter for type.
   */
  @Test
  public void testGetType() {
    IProcess move1 = new Process("Motion", 1, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Color", 5, 20, 10, 10,
            30, 0, 255, 0, 8, 20, 10, 10, 30,
            0, 0, 255);
    assertEquals(move1.getType(), "Motion");
    assertEquals(move2.getType(), "Color");

  }

  /**
   * Tests the set state method with a given time.
   */
  @Test
  public void testSetState() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    Rectangle rect = new Rectangle(10, 30,
            new Point2D.Double(10, 10), 0, Color.GREEN);
    move1.setState(0, rect);
    assertEquals(rect.getReference(), new Point2D.Double(10, 10));
    move1.setState(1, rect);
    assertEquals(rect.getReference(), new Point2D.Double(12, 10));
    move1.setState(2, rect);
    assertEquals(rect.getReference(), new Point2D.Double(14, 10));
    move1.setState(3, rect);
    assertEquals(rect.getReference(), new Point2D.Double(16, 10));
  }

  /**
   * Tests that setState() throws an Illegal Argument Exception when it is given a negative time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetState1() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    Rectangle rect = new Rectangle(10, 30,
            new Point2D.Double(10, 10), 0, Color.GREEN);
    move1.setState(-5, rect);
  }

  /**
   * Tests that setState() throws an Illegal Argument Exception when it is given a time after the
   * endtime of this process.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetState2() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    Rectangle rect = new Rectangle(10, 30,
            new Point2D.Double(10, 10), 0, Color.GREEN);
    move1.setState(14, rect);
  }

  /**
   * Tests the getter for start x.
   */
  @Test
  public void testGetStartX() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartX(), 10);
    assertEquals(move2.getStartX(), 115);
  }

  /**
   * Tests the getter for start y.
   */
  @Test
  public void testGetStartY() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartY(), 10);
    assertEquals(move2.getStartY(), 12);
  }

  /**
   * Tests the getter for start width.
   */
  @Test
  public void testGetStartWidth() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartWidth(), 10);
    assertEquals(move2.getStartWidth(), 51);
  }

  /**
   * Tests the getter for start height.
   */
  @Test
  public void testGetStartHeight() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0,
            255, 0, 5, 20, 10, 10, 30, 0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            31231,
            0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartHeight(), 30);
    assertEquals(move2.getStartHeight(), 31231);
  }

  /**
   * Tests the getter for start color.
   */
  @Test
  public void testGetStartColor() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            31231, 0, 0, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getStartColor(), new Color(0, 255, 0));
    assertEquals(move2.getStartColor(), new Color(0, 0, 0));
  }

  /**
   * Tests the getter for end x.
   */
  @Test
  public void testGetEndX() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 10, 10,
            30, 0, 255, 0, 5, 25, 10, 10, 30,
            0, 255, 0);
    assertEquals(move1.getEndX(), 20);
    assertEquals(move2.getEndX(), 25);
  }

  /**
   * Tests the getter for end y.
   */
  @Test
  public void testGetEndY() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 10,
            30, 0, 255, 0, 5, 20, 116, 10, 30,
            0, 255, 0);
    assertEquals(move1.getEndY(), 10);
    assertEquals(move2.getEndY(), 116);
  }

  /**
   * Tests the getter for end Width.
   */
  @Test
  public void testGetEndWidth() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            30, 0, 255, 0, 5, 20, 10, 110, 30,
            0, 255, 0);
    assertEquals(move1.getEndWidth(), 10);
    assertEquals(move2.getEndWidth(), 110);
  }

  /**
   * Tests the getter for end height.
   */
  @Test
  public void testGetEndHeight() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0,
            255, 0, 5, 20, 10, 10, 30, 0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            31231,
            0, 255, 0, 5, 20, 10, 10, 60, 0, 255, 0);
    assertEquals(move1.getEndHeight(), 30);
    assertEquals(move2.getEndHeight(), 60);
  }

  /**
   * Tests the getter for end color.
   */
  @Test
  public void testGetEndColor() {
    IProcess move1 = new Process("Motion", 0, 10, 10, 10,
            30, 0, 255, 0, 5, 20, 10, 10, 30,
            0, 255, 0);
    IProcess move2 = new Process("Motion", 0, 115, 12, 51,
            31231, 0, 0, 0, 5, 20, 10, 10, 30,
            0, 0, 0);
    assertEquals(move1.getEndColor(), new Color(0, 255, 0));
    assertEquals(move2.getEndColor(), new Color(0, 0, 0));
  }
}
