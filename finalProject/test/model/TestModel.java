package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Color;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Model;
import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Resize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the model.
 */
public class TestModel {

  private Model model;

  @Before
  public void init() {
    model = new Model();

    model.addShape("R", "RECTANGLE");
    model.addShape("O", "OVAL");

    model.addAnimation("R", new Move(0, 10, 200, 200, 200, 200));
    model.addAnimation("O", new Move(0, 20, 500, 100, 500, 100));

    model.addAnimation("R", new Resize(0, 80, 50, 100, 50, 100));
    model.addAnimation("O", new Resize(0, 80, 60, 30, 60, 30));
    model.addAnimation("R", new ChangeColor(0, 80, 255, 0, 0, 255, 0, 0));
    model.addAnimation("O", new ChangeColor(0, 50, 0, 0, 225, 0, 0, 255));

    model.addAnimation("R", new Move(10, 50, 200, 200, 300, 300));
    model.addAnimation("O", new Move(20, 70, 500, 100, 500, 400));

    model.addAnimation("O", new ChangeColor(50, 80, 0, 0, 225, 0, 255, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalid() {
    model.addShape("I", "CIRCLE");
  }

  @Test(expected = NoSuchElementException.class)
  public void testAddAnimationInvalid() {
    model.addAnimation("I", new Resize(0, 0, 50, 100, 50, 100));
  }

  @Test
  public void testCanvas() {
    model.setCanvas(0, 0, 1000, 600);
  }

  @Test
  public void testGetStateByTick() {
    List<IShape> temp = new ArrayList<IShape>(
        Arrays.asList(
            new Rectangle(new Point2D(225, 225), new Color(255, 0, 0), "R", 50, 100, 0, 0),
            new Oval(new Point2D(500, 100), new Color(0, 0, 237), "O", 60, 30, 0, 0)
        ));
    assertEquals(temp, model.getStateByTick(20));
  }

  @Test
  public void testGetSVG() {
    String expected = "<svg width='1000' height='600' version='1.1' "
        + "xmlns='http://www.w3.org/2000/svg'>\n"
        + "<rect id='R' x='200' y='200' width='50' height='100' fill='rgb(255, 0, 0)' "
        + "visibility='visible'>\n"
        + "<animate attributeType='xml' begin='0ms' dur='80000ms' attributeName='width' from='50' "
        + "to='50' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='80000ms' attributeName='height' from='100' "
        + "to='100' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='80000ms' attributeName='fill' "
        + "from='rgb(255, 0, 0)' to='rgb(255, 0, 0)' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='10000ms' attributeName='x' from='200' "
        + "to='200' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='10000ms' attributeName='y' from='200' "
        + "to='200' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='10000ms' dur='40000ms' attributeName='x' from='200' "
        + "to='300' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='10000ms' dur='40000ms' attributeName='y' from='200' "
        + "to='300' fill='freeze' /></rect>\n"
        + "\n"
        + "<ellipse id='O' cx='500' cy='100' rx='60' ry='30' fill='rgb(0, 0, 225)' "
        + "visibility='visible' >\n"
        + "<animate attributeType='xml' begin='0ms' dur='50000ms' attributeName='fill' "
        + "from='rgb(0, 0, 225)' to='rgb(0, 0, 255)' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='80000ms' attributeName='rx' from='60' "
        + "to='60' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='80000ms' attributeName='ry' from='30' "
        + "to='30' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='20000ms' attributeName='cx' from='500' "
        + "to='500' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='0ms' dur='20000ms' attributeName='cy' from='100' "
        + "to='100' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='20000ms' dur='50000ms' attributeName='cx' from='500'"
        + " to='500' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='20000ms' dur='50000ms' attributeName='cy' from='100'"
        + " to='400' fill='freeze' />\n"
        + "<animate attributeType='xml' begin='50000ms' dur='30000ms' attributeName='fill' "
        + "from='rgb(0, 0, 225)' to='rgb(0, 255, 0)' fill='freeze' /></ellipse>\n"
        + "\n"
        + "</svg>";

    assertEquals(expected, model.getSVG());
  }

  @Test
  public void testGetEndTick() {
    assertEquals(80, model.getEndTick());
  }

}
