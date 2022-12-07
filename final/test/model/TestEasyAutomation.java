package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests an automation.
 */
public class TestEasyAutomation {

  private IEasyAutomation normal;
  private IEasyAutomation triple;
  private IEasyAutomation empty;
  private Color black = new Color("000000");
  private Color white = new Color("FFFFFF");

  @Before
  public void init() {
    normal = new EasyAutomation();
    normal.addRectangle("rn", 0, 0, 1, 2, black);
    normal.addSquare("sn", 100, 100, 8, white);
    normal.addOval("on", 4, 8, 8, 4, black);
    normal.addCircle("cn", 2, 2, 9, white);

    triple = new EasyAutomation(3);
    triple.addRectangle("rt", 1, 4, 6, 3, white);
    triple.addSquare("st", 7, 2, 8, black);
    triple.addOval("ot", 4, 8, 9, 2, white);
    triple.addCircle("ct", 3, 3, 1, black);

    empty = new EasyAutomation();
  }

  @Test
  public void testSpeedMethods() {
    assertEquals(1, normal.getSpeed(), .01);
    normal.setSpeed(1.1);
    assertEquals(1.1, normal.getSpeed(), .01);
    normal.setSpeed(1);
    assertEquals(1, normal.getSpeed(), .01);

    assertEquals(3, triple.getSpeed(), .01);
    triple.setSpeed(1);
    assertEquals(1, triple.getSpeed(), .01);
    triple.setSpeed(3);
    assertEquals(3, triple.getSpeed(), .01);

    assertEquals(1, empty.getSpeed(), .01);
  }

  @Test
  public void testAllSpeeds() {
    for (double i = 0.1; i < 1000; i += .1) {
      empty.setSpeed(i);
      assertEquals(i, empty.getSpeed(), .01);
    }

    empty.setSpeed(1);
    assertEquals(1, empty.getSpeed(), .01);
  }

  @Test
  public void testInvalidSpeeds() {
    for (double i = 0; i > -100; i -= .1) {
      try {
        empty.setSpeed(i);
      } catch (IllegalArgumentException e) {
        assertEquals(1, empty.getSpeed(), .01);
      }
    }
  }

  @Test
  public void testGetShapes() {

    List<IShape> normalShapes = new ArrayList<>(Arrays.asList(
        new Rectangle("rn", 0, 0, 1, 2, black),
        new Square("sn", 100, 100, 8, white),
        new Oval("on", 4, 8, 8, 4, black),
        new Circle("cn", 2, 2, 9, white)
    ));

    assertEquals(normalShapes, normal.getShapes());

    List<IShape> tripleShapes = new ArrayList<>(Arrays.asList(
        new Rectangle("rt", 1, 4, 6, 3, white),
        new Square("st", 7, 2, 8, black),
        new Oval("ot", 4, 8, 9, 2, white),
        new Circle("ct", 3, 3, 1, black)
    ));

    assertEquals(tripleShapes, triple.getShapes());

    assertEquals(new ArrayList<>(), empty.getShapes());
  }

  @Test
  public void testGetShapeNames() {

    List<String> normalNames = new ArrayList<>(Arrays.asList("rn", "sn", "on", "cn"));
    assertEquals(normalNames, normal.getShapeNames());

    List<String> tripleNames = new ArrayList<>(Arrays.asList("rt", "st", "ot", "ct"));
    assertEquals(tripleNames, triple.getShapeNames());

    List<String> emptyNames = new ArrayList<>();
    assertEquals(emptyNames, empty.getShapeNames());
  }

  @Test
  public void testGetShapeByName() {
    assertEquals(new Rectangle("rn", 0, 0, 1, 2, black), normal.getShapeByName("rn"));
    assertEquals(new Square("sn", 100, 100, 8, white), normal.getShapeByName("sn"));
    assertEquals(new Oval("on", 4, 8, 8, 4, black), normal.getShapeByName("on"));
    assertEquals(new Circle("cn", 2, 2, 9, white), normal.getShapeByName("cn"));

    assertNotEquals(new Circle("cn", 3, 2, 9, white), normal.getShapeByName("cn"));
  }

  @Test(expected = IllegalStateException.class)
  public void testDuplicateNames() {
    normal.addRectangle("rn", 1, 0, 1, 2, white);
  }

  @Test
  public void testGetLength() {
    normal.getShapeByName("rn").addAutomationFrontColor(0,1,white);
    assertEquals(1, normal.getLength());

    normal.getShapeByName("rn").addAutomationFrontColor(0,1,white);
    assertEquals(3, normal.getLength());
  }
}
