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
   * @param row    of the cell to move to
   * @param column of the cell to move to
   * @return boolean for valid move
   */
  @Override
  public boolean canMove(int row, int column) {
    Cell cell = new Cell(row, column);
    return super.helpCanMoveStraight(cell);
  }
}
