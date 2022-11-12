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
    cellZero = new MarbleCell(0,0);
  }

  @Test
  public void testToString() {
    assertEquals("_", cellZero.toString());
  }

  @Test
  public void testSetOccupied() {
    cellZero.setOccupied(true);
    assertEquals("O", cellZero.toString());
    cellZero.setOccupied(false);
    assertEquals("_", cellZero.toString());
  }

}
