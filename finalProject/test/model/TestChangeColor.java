package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

public class TestChangeColor {

  private ChangeColor toBlack;
  private ChangeColor toWhite;

  @Before
  public void init() {
    toBlack = new ChangeColor(0, 50, 255, 255, 255, 0, 0, 0);
    toWhite = new ChangeColor(51, 100, 0, 0, 0, 255, 255, 255);
  }

  @Test
  public void testGetStartTick() {
    assertEquals(0, toBlack.getStartTick());
    assertEquals(51, toWhite.getStartTick());
  }

  @Test
  public void testEndTick() {
    assertEquals(50, toBlack.getEndTick());
    assertEquals(100, toWhite.getEndTick());
  }

  @Test
  public void testShapeAndTickInterval() {
    Rectangle r = new Rectangle("bRectangle");

    assertEquals(null, toBlack.getShape());
    toBlack.setShape(r);
    assertEquals(r, toBlack.getShape());
    toBlack.setTickInterval(r, 25);
    assertEquals("rgb(127, 127, 127)", toBlack.getShape().getColor().getRGB());

    Oval o = new Oval("wOval");
    assertEquals(null, toWhite.getShape());
    toWhite.setShape(o);
    assertEquals(o, toWhite.getShape());
    toWhite.setTickInterval(o, 52);
    assertEquals("rgb(5, 5, 5)", toWhite.getShape().getColor().getRGB());
  }

  @Test
  public void testIllegalTickIntervals() {
    Rectangle r = new Rectangle("bRectangle");

    for(int i = -1; i > -1000; i--) {
      try{
        toWhite.setTickInterval(r, i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(e.getMessage(), "Tick value must be greater than or equal to 0.");
      }
    }

  }

  @Test
  public void testToString() {
    ChangeColor blue = new ChangeColor(0, 0, 255, 255, 0, 0, 0, 255);
    assertEquals(
        "<animate attributeType='xml' begin='0ms' dur='0ms' attributeName='fill' "
            + "from='rgb(255, 255, 0)' to='rgb(0, 0, 255)' fill='freeze' />",
        blue.toString());

  }
}
