package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester for the shape cell class.
 */
public class ShapeCellTest {
  IShapeCell cell1;
  IShapeCell cell2;

  @Before
  public void setUp() {
    cell1 = new ShapeCell("R", "Rectangle");
    cell2 = new ShapeCell("E", "Ellipse");
  }

  @Test
  public void testGetId() {
    assertEquals(cell1.getName(), "R");
    assertEquals(cell2.getName(), "E");
  }

  @Test
  public void testGetType() {
    assertEquals(cell1.getShapeType(), "Rectangle");
    assertEquals(cell2.getShapeType(), "Ellipse");
  }
}

