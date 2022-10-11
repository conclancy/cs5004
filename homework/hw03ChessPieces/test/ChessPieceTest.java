import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessPieceTest {

  private Rook whiteRook;
  private Rook blackRook;

  @Before
  public void init() {
    
    whiteRook = new Rook(0, 0, Color.WHITE);
    blackRook = new Rook(0, 0, Color.BLACK);
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteRook.getColor());
  }

  @Test
  public void testGetRow() {
    assertEquals(0, whiteRook.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(0, whiteRook.getRow());
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

  /**
   * Test to make sure the Rook moves are working.
   */
  @Test
  public void testRookMoves() {
    assertEquals(true, whiteRook.canMove(new Cell(0,1)));
    assertEquals(true, whiteRook.canMove(new Cell(0,7)));
    assertEquals(true, whiteRook.canMove(new Cell(0,6)));
    assertEquals(true, whiteRook.canMove(new Cell(5,0)));
    assertEquals(false, whiteRook.canMove(new Cell(1,1)));
    assertEquals(false, whiteRook.canMove(new Cell(2,3)));
  }

  @Test
  public void testRookKill() {

    // Valid attack
    assertEquals(true, whiteRook.canKill(blackRook));

    // Invalid for color
    ChessPiece testBlack = new Rook(0, 7, Color.BLACK);
    assertEquals(false, blackRook.canKill(testBlack));

    // Invalid for location
    ChessPiece whiteRookInvalid = new Rook(7, 7, Color.WHITE);
    assertEquals(false, blackRook.canKill(whiteRookInvalid));

    // Neither color nor location
    ChessPiece blackRookInvalid = new Rook(7, 7, Color.BLACK);
    assertEquals(false, blackRook.canKill(blackRookInvalid));
  }

  // TODO add Pawn tests
}
