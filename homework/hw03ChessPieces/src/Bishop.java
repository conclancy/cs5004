/**
 * Class representing a Bishop chess piece that can move diagonally.
 */
public class Bishop extends AbstractChessPiece {

  /**
   * Constructor for bishop.
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  of the chess piece
   */
  public Bishop(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Validates moves of a bishop piece.
   *
   * @param cell the cell for which to test a move
   * @return boolean for valid movements
   */
  @Override
  public boolean canMove(Cell cell) {
    return super.helpCanMoveDiagonal(cell);
  }
}
