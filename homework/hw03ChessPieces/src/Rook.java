/**
 * Represents a Rook (castle) chess piece.  Moves vertically and horizontally.
 */
public class Rook extends AbstractChessPiece {

  /**
   * Constructor for a Rook Piece.
   *
   * @param row    the row number of the row
   * @param column the column number of the cell
   * @param color  of the piece.  Either BLACK or WHITE.
   */
  public Rook(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Determines if a Rook can move to a given square.
   *
   * @param other the cell for which to test a move
   * @return boolean for valid moves
   */
  @Override
  public boolean canMove(Cell other) {
    return super.helpCanMoveStraight(other);
  }
}
