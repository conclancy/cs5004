import cs5004.marblesolitaire.model.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BoardTest {
  private Board boardZero;

  @Before
  public void init() {
    boardZero = new Board(7);
  }

  @Test
  public void testEvenSize() {

    for (int i = 2; i < 1000; i += 2) {
      try {
        Board testFail = new Board(i);
      } catch (IllegalArgumentException e) {
        assertEquals(true, true);
      }
    }
  }

  @Test
  public void testOddSize() {
    for (int i = 1; i < 1000; i += 2) {
      try {
        Board testFail = new Board(i);
      } catch (IllegalArgumentException e) {
        assertEquals(true, false);
      }
    }
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
