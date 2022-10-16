/**
 * Class for the Knight chess piece.  Moves in an "L" shape.
 */
public class Knight extends AbstractChessPiece {

  /**
   * Constructor for a knight piece.
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  of the chess piece
   */
  public Knight(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Determines if the chess piece can move to a specified cell.
   *
   * @param row    of the cell to move to
   * @param column of the cell to move to
   * @return boolean for valid movement
   */
  @Override
  public boolean canMove(int row, int column) {
    Cell cell = new Cell(row, column);
    return
        (Math.abs(super.getRow() - cell.getRow()) * Math.abs(super.getColumn() - cell.getColumn()))
            == 2;
  }
}
