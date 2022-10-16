/**
 * Class representing a Queen chess piece.  Moves diagonally, vertically, and horizontally.
 */
public class Queen extends AbstractChessPiece {

  /**
   * Constructor for a Queen piece.
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  of the chess piece
   */
  public Queen(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Determines valid moves for a Queen piece.
   *
   * @param cell the cell for which to test a move
   * @return boolean for valid queen movement
   */
  @Override
  public boolean canMove(Cell cell) {
    return super.helpCanMoveDiagonal(cell) || super.helpCanMoveStraight(cell);
  }
}
