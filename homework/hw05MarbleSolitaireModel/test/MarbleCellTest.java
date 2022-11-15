import cs5004.marblesolitaire.model.CellState;
import cs5004.marblesolitaire.model.MarbleCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MarbleCellTest {

  private MarbleCell cellZero;

  @Before
  public void init() {
    cellZero = new MarbleCell(0,0, CellState.MARBLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRow() {
    new MarbleCell(-1,7, CellState.MARBLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColumn() {
    new MarbleCell(0,-7, CellState.MARBLE);
  }

  @Test
  public void testToString() {
    assertEquals("O", cellZero.toString());
  }

  @Test
  public void testSetGetState() {
    cellZero.setState(CellState.MARBLE);
    assertEquals(CellState.MARBLE, cellZero.getState());

    cellZero.setState(CellState.EMPTY);
    assertEquals(CellState.EMPTY, cellZero.getState());

    cellZero.setState(CellState.INVALID);
    assertEquals(CellState.INVALID, cellZero.getState());
  }

  @Test
  public void testIsValidMove() {
    assertEquals(true, cellZero.isValidMove(new MarbleCell(1,0, CellState.MARBLE)));
    assertEquals(true, cellZero.isValidMove(new MarbleCell(0,1, CellState.MARBLE)));

    assertEquals(false, cellZero.isValidMove(new MarbleCell(1,1, CellState.MARBLE)));
    assertEquals(false, cellZero.isValidMove(new MarbleCell(1,0, CellState.EMPTY)));
    assertEquals(false, cellZero.isValidMove(new MarbleCell(1,0, CellState.INVALID)));
  }

}
