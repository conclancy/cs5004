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
   * @return true for valid movements
   */
  @Override
  public boolean canMove(Cell cell) {
    int rowChange = Math.abs(super.getRow() - cell.getRow());
    int columnChange = Math.abs(super.getColumn() - cell.getColumn());
    return rowChange == columnChange;
  }
}
