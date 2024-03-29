/**
 * Represents a Pawn chess piece.  Moves only forward, but kills diagonally.
 */
public class Pawn extends AbstractChessPiece {

  /**
   * Constructor for a Rook Piece.
   *
   * @param row    the row number of the row
   * @param column the column number of the cell
   * @param color  of the piece.  Either BLACK or WHITE.
   */
  public Pawn(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Validation for pawn movement.
   *
   * @param row    of the cell to move to
   * @param column of the cell to move to
   * @return boolean for a valid pawn movement
   */
  @Override
  public boolean canMove(int row, int column) {

    Cell cell = new Cell(row, column);

    if ((cell.getColumn() != super.getColumn())) {
      return false;
    }
    else if (super.getColor() == Color.WHITE) {
      return cell.getRow() == super.getRow() + 1;
    } else if (super.getColor() == Color.BLACK) {
      return cell.getRow() == super.getRow() - 1;
    } else {
      return false;
    }
  }

  /**
   * Validate if a cell is a valid kill move for a pawn.
   *
   * @param other cell location of other piece
   * @return boolean for valid kill move
   */
  private boolean validKillMove(Cell other) {
    if (!(other.getColumn() == super.getColumn() + 1
        || other.getColumn() == super.getColumn() - 1)) {
      return false;
    }
    else if (super.getColor() == Color.WHITE) {
      return other.getRow() == super.getRow() + 1;
    } else if (super.getColor() == Color.BLACK) {
      return other.getRow() == super.getRow() - 1;
    } else {
      return false;
    }

  }

  /**
   * Validate if a pawn can kill another chess piece.
   *
   * @param other the chess piece for which to test a kill move
   * @return boolean for valid kill
   */
  @Override
  public boolean canKill(ChessPiece other) {
    if (!this.validKillMove(other.getCell())) {
      return false;
    } else {
      return super.getColor() != other.getColor();
    }
  }
}
