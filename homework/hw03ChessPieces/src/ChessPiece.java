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
   * @param cell the cell for which to test a move
   * @return true if the move is valid
   */
  boolean canMove(Cell cell);

  /**
   * Determines if the chess piece can kill another chess piece.
   *
   * @param piece the chess piece for which to test a kill move
   * @return true if the kill is valid
   */
  boolean canKill(ChessPiece piece);
}
