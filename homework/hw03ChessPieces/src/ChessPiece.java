/**
 * Represents a chess piece.
 */
public interface ChessPiece {

  /**
   * Return the row of the chess piece.
   *
   * @return the row of the chess piece
   */
  int getRow();

  /**
   * Return the column of the chess piece.
   *
   * @return the column of the chess piece
   */
  int getColumn();

  /**
   * Return the cell of the chess piece.
   *
   * @return the cell of the chess piece
   */
  Cell getCell();

  /**
   * Return the color of the chess piece.
   *
   * @return the Color of the chess piece
   */
  Color getColor();

  /**
   * Determines if the chess piece can move to a specified cell.
   *
   * @param row    of the cell to move to
   * @param column of the cell to move to
   * @return boolean for a valid movement
   */
  boolean canMove(int row, int column);

  /**
   * Determines if the chess piece can kill another chess piece.
   *
   * @param piece the chess piece for which to test a kill move
   * @return true if the kill is valid
   */
  boolean canKill(ChessPiece piece);
}
