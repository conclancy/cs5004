package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests an automation.
 */
public class TestEasyAutomation {

  private Color black;

  private EasyAutomation simpleSquare;
  private EasyAutomation multiSquare;


  @Before
  public void init() {

    black = new Color("FFFFFF");

    simpleSquare = new EasyAutomation();
    simpleSquare.addShape("square", new Square(0, 0, 1, black));
  }

  @Test
  public void testGetShapes() {
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("square");
    assertEquals(expected, simpleSquare.getShapes());
  }

  @Test
  public void testSetAction() {
    simpleSquare.setAction("square", new Action(0,1,0,0, black));
    assertEquals(0, simpleSquare.getLength());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidAction() {
    simpleSquare.setAction("rectangle", new Action(0,1,0,0, black));
  }
}
