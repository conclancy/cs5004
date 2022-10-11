/**
 * Abstract class representing the Chess Piece implementation.
 */
public abstract class AbstractChessPiece implements ChessPiece {

  protected Cell cell;
  protected Color color;

  /**
   * Abstract constructor for a chess piece.
   *
   * @param row    row of the piece
   * @param column column of the piece
   * @param color  of the chess piece
   */
  public AbstractChessPiece(int row, int column, Color color) {
    this.cell = new Cell(row, column);
    this.color = color;
  }

  /**
   * Return the cell's row incrementing from bottom (row 0) to top (row 7).
   *
   * @return the row number of the piece's cell
   */
  @Override
  public int getRow() {
    return this.cell.getRow();
  }

  /**
   * Return the cell's column incrementing from left (column 0) to right (column 7).
   *
   * @return the column number of the piece's cell
   */
  @Override
  public int getColumn() {
    return this.cell.getColumn();
  }

  /**
   * Get the Color of the chess piece.
   *
   * @return BLACK or WHITE depending on the piece's color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Return the pieces cell object.
   *
   * @return pieces cell object.
   */
  @Override
  public Cell getCell() {
    return this.cell;
  }

  /**
   * If a piece of another color is a valid move for this piece, it can kill the other piece.
   *
   * @param other the chess piece for which to test a kill move
   * @return boolean for a valid kill
   */
  @Override
  public boolean canKill(ChessPiece other) {
    boolean validMove = this.canMove(other.getCell());

    if (!validMove) {
      return false;
    }
    else
      return !(this.color == other.getColor());
  }
}
