package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests an automation
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
  public void testSetSpeed() {
    simpleSquare.setSpeed(1);
  }

  @Test
  public void testSetAction() {
    simpleSquare.setAction("square", new Action(0,1,0,0, black));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidAction() {
    simpleSquare.setAction("rectangle", new Action(0,1,0,0, black));
  }
}
