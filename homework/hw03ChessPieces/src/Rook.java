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

  @Override
  public boolean canMove(Cell other) {
    Boolean validRow = other.getRow() == super.getRow();
    Boolean validColumn = other.getColumn() == super.getColumn();
    return validColumn || validRow;
  }
}
