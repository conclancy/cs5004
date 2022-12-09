package model;

import static org.junit.Assert.assertEquals;


import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Resize;
import org.junit.Before;
import org.junit.Test;

public class TestResize {

  private Resize larger;
  private Resize smaller;

  @Before
  public void init() {
    larger = new Resize(0,12,1,1,12,12);
    smaller = new Resize(0,100,100,64,50,32);
  }

  @Test
  public void testGetStartTick() {
    assertEquals(0, larger.getStartTick());
    assertEquals(0, smaller.getStartTick());
  }

  @Test
  public void testEndTick() {
    assertEquals(12, larger.getEndTick());
    assertEquals(100, smaller.getEndTick());
  }

  @Test
  public void testShapeAndTickInterval() {
    Rectangle r = new Rectangle("Rectangle");

    assertEquals(null, larger.getShape());
    larger.setShape(r);
    assertEquals(r, larger.getShape());
    larger.setTickInterval(r, 6);
    assertEquals(6, larger.getShape().getWidth());
    assertEquals(6, larger.getShape().getHeight());

    Oval o = new Oval("Oval");
    assertEquals(null, smaller.getShape());
    smaller.setShape(o);
    assertEquals(o, smaller.getShape());
    smaller.setTickInterval(o, 75);
    assertEquals(62, smaller.getShape().getWidth());
    assertEquals(40, smaller.getShape().getHeight());
  }

  @Test
  public void testIllegalTickIntervals() {
    Rectangle r = new Rectangle("Rectangle");

    for(int i = -1; i > -1000; i--) {
      try{
        smaller.setTickInterval(r, i);
        assertEquals(false, true);
      } catch (IllegalArgumentException e) {
        assertEquals(e.getMessage(), "Tick value must be greater than or equal to 0.");
      }
    }

  }

  @Test
  public void testToString() {
    Resize size = new Resize(10, 85, 255, 255, 18, 4);
    size.setShape(new Rectangle("Rec"));

    assertEquals(
        "<animate attributeType='xml' begin='10000ms' dur='75000ms' attributeName='width' "
            + "from='255' to='18' fill='freeze' />\n" +
            "<animate attributeType='xml' begin='10000ms' dur='75000ms' attributeName='height' "
            + "from='255' to='4' fill='freeze' />",
        size.toString());

  }

}
