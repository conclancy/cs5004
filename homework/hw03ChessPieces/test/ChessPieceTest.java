import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessPieceTest {

  private Rook r1;

  @Before
  public void init() {
    r1 = new Rook(0, 0, Color.WHITE);
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, r1.getColor());
  }

  @Test
  public void testGetRow() {
    assertEquals(0, r1.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(0, r1.getRow());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRowMin() {
    ChessPiece faultyRook = new Rook(-1, 0, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRowMax() {
    ChessPiece faultyRook = new Rook(8, 0, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColumnMin() {
    ChessPiece faultyRook = new Rook(0, -1, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColumnMax() {
    ChessPiece faultyRook = new Rook(0, 8, Color.BLACK);
  }
}
