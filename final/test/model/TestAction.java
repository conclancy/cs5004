package model;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Action;
import cs5004.animator.model.Color;
import cs5004.animator.model.EActionType;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Size;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Action class.
 */
public class TestAction {

  private Action<Point2D> basicMove;
  private Action<Color> basicColor;
  private Action<Size> basicSize;

  @Before
  public void init() {
    basicMove = new Action<Point2D>(0, 1, new Point2D(1, 1));
    basicColor = new Action<Color>(1, 2, new Color("FFFFFF"));
    basicSize = new Action<Size>(2, 3, new Size(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartEnd() {
    Action<Color> test = new Action<Color>(1, 0, new Color("FFFFFF"));
  }

  @Test
  public void testGetType() {
    assertEquals(EActionType.MOVE, basicMove.getType());
    assertEquals(EActionType.COLOR, basicColor.getType());
    assertEquals(EActionType.SIZE, basicSize.getType());
  }

  @Test
  public void testGetStart() {
    assertEquals(0, basicMove.getStart());
    assertEquals(1, basicColor.getStart());
    assertEquals(2, basicSize.getStart());
  }

  @Test
  public void testGetEnd() {
    assertEquals(1, basicMove.getEnd());
    assertEquals(2, basicColor.getEnd());
    assertEquals(3, basicSize.getEnd());
  }

  @Test
  public void testGetTo() {
    assertEquals(new Point2D(1, 1), basicMove.getTo());
    assertEquals(new Color("FFFFFF"), basicColor.getTo());
    assertEquals(new Size(2, 2), basicSize.getTo());
  }

  @Test
  public void setStartEnd() {

    // Test basicMove
    assertEquals(0, basicMove.getStart());
    assertEquals(1, basicMove.getEnd());
    basicMove.setEnd(10);
    basicMove.setStart(8);
    assertEquals(8, basicMove.getStart());
    assertEquals(10, basicMove.getEnd());
    basicMove.setStart(0);
    basicMove.setEnd(1);
    assertEquals(0, basicMove.getStart());
    assertEquals(1, basicMove.getEnd());

    // Test basicColor
    assertEquals(1, basicColor.getStart());
    assertEquals(2, basicColor.getEnd());
    basicColor.setEnd(4);
    basicColor.setStart(3);
    assertEquals(3, basicColor.getStart());
    assertEquals(4, basicColor.getEnd());
    basicColor.setStart(1);
    basicColor.setEnd(2);
    assertEquals(1, basicColor.getStart());
    assertEquals(2, basicColor.getEnd());

    // Test basicSize
    assertEquals(2, basicSize.getStart());
    assertEquals(3, basicSize.getEnd());
    basicSize.setEnd(100);
    basicSize.setStart(8);
    assertEquals(8, basicSize.getStart());
    assertEquals(100, basicSize.getEnd());
    basicSize.setStart(2);
    basicSize.setEnd(3);
    assertEquals(2, basicSize.getStart());
    assertEquals(3, basicSize.getEnd());

  }

  @Test
  public void testSetToFrom() {

    // Test basicMove
    assertEquals(new Point2D(1, 1), basicMove.getTo());
    basicMove.setTo(new Point2D(12, 16));
    assertEquals(new Point2D(12, 16), basicMove.getTo());
    basicMove.setTo(new Point2D(1, 1));
    assertEquals(new Point2D(1, 1), basicMove.getTo());

    // Test basicColor
    assertEquals(new Color("FFFFFF"), basicColor.getTo());
    basicColor.setTo(new Color("ABCDEF"));
    assertEquals(new Color("ABCDEF"), basicColor.getTo());
    basicColor.setTo(new Color("FFFFFF"));
    assertEquals(new Color("FFFFFF"), basicColor.getTo());

    // Test basicSize
    assertEquals(new Size(2, 2), basicSize.getTo());
    basicSize.setTo(new Size(5, 6));
    assertEquals(new Size(5, 6), basicSize.getTo());
    basicSize.setTo(new Size(2));
    assertEquals(new Size(2, 2), basicSize.getTo());
  }
}