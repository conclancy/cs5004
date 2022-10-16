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
   * @param row    of the cell to move to
   * @param column of the cell to move to
   * @return boolean for valid move
   */
  @Override
  public boolean canMove(int row, int column) {

    Cell cell = new Cell(row, column);

    return super.helpCanMoveDiagonal(cell) || super.helpCanMoveStraight(cell);
  }
}
