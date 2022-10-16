import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the ChessPieces interface.
 */
public class ChessPieceTest {

  private Rook whiteRook;
  private Rook blackRook;
  private Pawn whitePawn;
  private Pawn blackPawn;
  private Knight whiteKnight;
  private Knight batman;
  private Bishop whiteBishop;
  private Bishop blackBishop;
  private Queen whiteQueen;
  private Queen blackQueen;

  /**
   * Initialize chess pieces for use in tests.
   */
  @Before
  public void init() {

    whiteRook = new Rook(0, 0, Color.WHITE);
    blackRook = new Rook(0, 0, Color.BLACK);
    whitePawn = new Pawn(1, 1, Color.WHITE);
    blackPawn = new Pawn(6, 1, Color.BLACK);
    whiteKnight = new Knight(0, 1, Color.WHITE);
    batman = new Knight(7, 1, Color.BLACK);
    whiteBishop = new Bishop(0, 2, Color.WHITE);
    blackBishop = new Bishop(7, 5, Color.BLACK);
    whiteQueen = new Queen(0, 4, Color.WHITE);
    blackQueen = new Queen(7, 4, Color.BLACK);

  }

  /**
   * Ensure that the color functionality works correctly.
   */
  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteRook.getColor());
    assertEquals(Color.BLACK, blackRook.getColor());
    assertEquals(Color.WHITE, whitePawn.getColor());
    assertEquals(Color.BLACK, blackPawn.getColor());
  }

  /**
   * Test the row getter method.
   */
  @Test
  public void testGetRow() {
    assertEquals(0, whiteRook.getRow());
    assertEquals(0, blackRook.getRow());
    assertEquals(1, whitePawn.getRow());
    assertEquals(6, blackPawn.getRow());
  }

  /**
   * Test the column getter method.
   */
  @Test
  public void testGetColumn() {
    assertEquals(0, whiteRook.getColumn());
    assertEquals(0, blackRook.getColumn());
    assertEquals(1, whitePawn.getColumn());
    assertEquals(1, blackPawn.getColumn());
  }

  /**
   * Test to make sure proper errors are thrown when an invalid cell is entered.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRowMin() {
    ChessPiece faultyRook = new Rook(-1, 0, Color.BLACK);
  }

  /**
   * Test to make sure proper errors are thrown when an invalid cell is entered.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRowMax() {
    ChessPiece faultyRook = new Rook(8, 0, Color.BLACK);
  }

  /**
   * Test to make sure proper errors are thrown when an invalid cell is entered.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColumnMin() {
    ChessPiece faultyRook = new Rook(0, -1, Color.BLACK);
  }

  /**
   * Test to make sure proper errors are thrown when an invalid cell is entered.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColumnMax() {
    ChessPiece faultyRook = new Rook(0, 8, Color.BLACK);
  }

  /**
   * Test to make sure the Rook moves are working.
   */
  @Test
  public void testRookMoves() {
    assertTrue(whiteRook.canMove(0, 1));
    assertTrue(whiteRook.canMove(0, 7));
    assertTrue(whiteRook.canMove(0, 6));
    assertTrue(whiteRook.canMove(5, 0));
    assertFalse(whiteRook.canMove(1, 1));
    assertFalse(whiteRook.canMove(2, 3));
  }

  /**
   * Test to make sure Rooks can kill other pieces correctly.
   */
  @Test
  public void testRookKill() {

    // Valid attack
    assertTrue(whiteRook.canKill(blackRook));

    // Invalid for color
    ChessPiece testBlack = new Rook(0, 7, Color.BLACK);
    assertFalse(blackRook.canKill(testBlack));

    // Invalid for location
    ChessPiece whiteRookInvalid = new Rook(7, 7, Color.WHITE);
    assertFalse(blackRook.canKill(whiteRookInvalid));

    // Neither color nor location
    ChessPiece blackRookInvalid = new Rook(7, 7, Color.BLACK);
    assertFalse(blackRook.canKill(blackRookInvalid));
  }

  /**
   * Test validity of Pawn piece movements.
   */
  @Test
  public void testPawnMove() {

    // white pawn movements
    assertTrue(whitePawn.canMove(2, 1));
    assertFalse(whitePawn.canMove(3, 2));
    assertFalse(whitePawn.canMove(1, 1));

    // black pawn movements
    assertTrue(blackPawn.canMove(5, 1));
    assertFalse(blackPawn.canMove(4, 1));
    assertFalse(blackPawn.canMove(5, 2));
  }

  /**
   * Test validity of Pawn kill movements.
   */
  @Test
  public void testPawnKill() {

    // white can kill
    ChessPiece killablePawn1 = new Pawn(2, 2, Color.BLACK);
    assertTrue(whitePawn.canKill(killablePawn1));

    // black can kill
    ChessPiece killablePawn2 = new Rook(5, 0, Color.WHITE);
    assertTrue(blackPawn.canKill(killablePawn2));

    // white cannot kill location directly ahead
    ChessPiece nonKillablePawn1 = new Pawn(2, 1, Color.BLACK);
    assertFalse(whitePawn.canKill(nonKillablePawn1));

    // black cannot kill location far location
    ChessPiece nonKillablePawn2 = new Rook(3, 2, Color.WHITE);
    assertFalse(blackPawn.canKill(nonKillablePawn2));

    // white cannot kill color
    ChessPiece nonKillablePawn3 = new Pawn(2, 2, Color.WHITE);
    assertFalse(whitePawn.canKill(nonKillablePawn3));

    // black cannot kill color
    ChessPiece killablePawn4 = new Rook(5, 0, Color.BLACK);
    assertFalse(blackPawn.canKill(killablePawn4));
  }

  /**
   * Test knight movements.
   */
  @Test
  public void testKnightMove() {
    // up 2 left 1
    assertTrue(whiteKnight.canMove(2, 0));

    // up 2 right 1
    assertTrue(whiteKnight.canMove(2, 2));

    // up 1 right 2
    assertTrue(whiteKnight.canMove(1, 3));

    // down 2 left 1
    assertTrue(batman.canMove(5, 0));

    // down 2 right 1
    assertTrue(batman.canMove(5, 2));

    // down 1 left 2
    assertTrue(batman.canMove(6, 3));

    // cannot move strait
    assertFalse(whiteKnight.canMove(2, 1));

    // cannot move diagonal
    assertFalse(batman.canMove(5, 3));
  }

  /**
   * Test Bishop movements.
   */
  @Test
  public void testBishopMove() {
    // white left
    assertTrue(whiteBishop.canMove(1, 1));

    // white right
    assertTrue(whiteBishop.canMove(5, 7));

    // black left
    assertTrue(blackBishop.canMove(5, 7));

    // black right
    assertTrue(blackBishop.canMove(4, 2));

    // straight ahead
    assertFalse(whiteBishop.canMove(6, 2));

    // lateral
    assertFalse(blackBishop.canMove(7, 1));
  }

  /**
   * Test Queen movements.
   */
  @Test
  public void testQueenMove() {
    // diagonal white left
    assertTrue(whiteQueen.canMove(1, 3));

    // diagonal white right
    assertTrue(whiteQueen.canMove(3, 7));

    // diagonal black left
    assertTrue(blackQueen.canMove(5, 2));

    // diagonal black right
    assertTrue(blackQueen.canMove(4, 7));

    // lateral movement
    assertTrue(blackQueen.canMove(7, 7));

    // horizontal movement
    assertTrue(whiteQueen.canMove(7, 4));

    // invalid close movement
    assertFalse(whiteQueen.canMove(2, 5));

    // invalid far movement
    assertFalse(blackQueen.canMove(1, 1));
  }
}
