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
   * @param row    the row of the cell to move to
   * @param column the column of the cell to move to
   * @return boolean for valid move
   */
  @Override
  public boolean canMove(int row, int column) {
    return super.helpCanMoveDiagonal(new Cell(row, column));
  }
}
