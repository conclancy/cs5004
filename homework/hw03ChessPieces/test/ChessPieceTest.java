import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessPieceTest {

  private Rook whiteRook;
  private Rook blackRook;
  private Pawn whitePawn;
  private Pawn blackPawn;

  @Before
  public void init() {
    
    whiteRook = new Rook(0, 0, Color.WHITE);
    blackRook = new Rook(0, 0, Color.BLACK);
    whitePawn = new Pawn(1, 1, Color.WHITE);
    blackPawn = new Pawn(6, 1, Color.BLACK);

  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteRook.getColor());
    assertEquals(Color.BLACK, blackRook.getColor());
    assertEquals(Color.WHITE, whitePawn.getColor());
    assertEquals(Color.BLACK, blackPawn.getColor());
  }

  @Test
  public void testGetRow() {
    assertEquals(0, whiteRook.getRow());
    assertEquals(0, blackRook.getRow());
    assertEquals(1, whitePawn.getRow());
    assertEquals(6, blackPawn.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(0, whiteRook.getColumn());
    assertEquals(0, blackRook.getColumn());
    assertEquals(1, whitePawn.getColumn());
    assertEquals(1, blackPawn.getColumn());
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
    assertTrue(whiteRook.canMove(new Cell(0, 1)));
    assertTrue(whiteRook.canMove(new Cell(0, 7)));
    assertTrue(whiteRook.canMove(new Cell(0, 6)));
    assertTrue(whiteRook.canMove(new Cell(5, 0)));
    assertFalse(whiteRook.canMove(new Cell(1, 1)));
    assertFalse(whiteRook.canMove(new Cell(2, 3)));
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
    assertTrue(whitePawn.canMove(new Cell(2, 1)));
    assertFalse(whitePawn.canMove(new Cell(3, 2)));
    assertFalse(whitePawn.canMove(new Cell(1, 1)));

    // black pawn movements
    assertTrue(blackPawn.canMove(new Cell(5, 1)));
    assertFalse(blackPawn.canMove(new Cell(4, 1)));
    assertFalse(blackPawn.canMove(new Cell(5, 2)));
  }

  /**
   *
   */
  @Test
  public void testPawnKill() {

    // white can kill
    ChessPiece killablePawn1 = new Pawn(2,2, Color.BLACK);
    assertTrue(whitePawn.canKill(killablePawn1));

    // black can kill
    ChessPiece killablePawn2 = new Rook(5,0, Color.WHITE);
    assertTrue(blackPawn.canKill(killablePawn2));

    // white cannot kill location directly ahead
    ChessPiece nonKillablePawn1 = new Pawn(2,1, Color.BLACK);
    assertFalse(whitePawn.canKill(nonKillablePawn1));

    // black cannot kill location far location
    ChessPiece nonKillablePawn2 = new Rook(3,2, Color.WHITE);
    assertFalse(blackPawn.canKill(nonKillablePawn2));

    // white cannot kill color
    ChessPiece nonKillablePawn3 = new Pawn(2,2, Color.WHITE);
    assertFalse(whitePawn.canKill(nonKillablePawn3));

    // black cannot kill color
    ChessPiece killablePawn4 = new Rook(5,0, Color.BLACK);
    assertFalse(blackPawn.canKill(killablePawn4));
  }
}
