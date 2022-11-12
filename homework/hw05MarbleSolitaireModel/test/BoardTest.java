import cs5004.marblesolitaire.model.Board;
import cs5004.marblesolitaire.model.MarbleCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {
  private Board boardZero;

  @Before
  public void init() {
    boardZero = new Board(7);
  }

  @Test
  public void testToString() {
    assertEquals("_______\n"
        + "_______\n"
        + "_______\n"
        + "_______\n"
        + "_______\n"
        + "_______\n"
        + "_______", boardZero.toString());
  }
}
