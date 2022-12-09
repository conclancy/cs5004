package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

public class TestMove {

  public Move larger;
  public Move smaller;

  @Before
  public void init() {
    larger = new Move(0,12,0,0,12,12);
    smaller = new Move(1,100,100,64,0,0);
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
    assertEquals("(x: 6, y: 6)", larger.getShape().getReference().toString());

    Oval o = new Oval("Oval");
    assertEquals(null, smaller.getShape());
    smaller.setShape(o);
    assertEquals(o, smaller.getShape());
    smaller.setTickInterval(o, 75);
    assertEquals("(x: 25, y: 16)", smaller.getShape().getReference().toString());
  }

  @Test
  public void testIllegalTickIntervals() {
    Rectangle r = new Rectangle("bRectangle");

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
    Move move = new Move(10, 85, 255, 255, 18, 4);
    move.setShape(new Rectangle("Rec"));

    assertEquals(
        "<animate attributeType='xml' begin='10000ms' dur='75000ms' attributeName='x' from='255' "
            + "to='18' fill='freeze' />\n" +
        "<animate attributeType='xml' begin='10000ms' dur='75000ms' attributeName='y' from='255' "
            + "to='4' fill='freeze' />",
        move.toString());

  }
}
