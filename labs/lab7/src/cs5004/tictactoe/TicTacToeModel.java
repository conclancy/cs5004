package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A single game of Tic Tac Toe, played on a three-by-three grid with two players, with the object
 * of the game to achieve three markers in a row either vertically, horizontally, or diagonally.
 * {@link Player} X goes first.
 */
public class TicTacToeModel implements TicTacToe {

  private final Player[][] board;
  private boolean xTurn;

  /**
   * Constructor for a Tic Tac Toe game.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.xTurn = true;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
        row -> " " + Arrays.stream(row).map(
            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {

    if (isGameOver()) {
      throw new IllegalStateException("The game is over; no more moves can be made.");
    }

    if (r < 0 || c < 0 || r > 2 || c > 2) {
      throw new IllegalArgumentException("Row and column values must be between 0 and 2.");
    }

    if (this.board[r][c] != null) {
      throw new IllegalArgumentException("The position entered is already occupied.");
    }

    this.board[r][c] = this.getTurn();

    this.xTurn = !this.xTurn;
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override
  public Player getTurn() {
    if (this.xTurn) {
      return Player.X;
    } else {
      return Player.O;
    }
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or one player
   * has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    if (this.getWinner() != null) {
      return true;
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (this.board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not over,
   * returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {
    for (int i = 0; i < 3; i++) {
      if (this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2]) {
        return this.board[i][0];

      } else if (this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i]) {
        return this.board[0][i];
      }
    }

    if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
      return this.board[0][0];
    }
    if (this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
      return this.board[2][0];
    }
    return null;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for (int i = 0; i < this.board.length; i++) {
      System.arraycopy(this.board[i], 0, copyBoard[i], 0, 3);
    }
    return copyBoard;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 3 && c > 3 || c < 0) {
      throw new IllegalArgumentException("Location entered is not valid. Enter row or columns "
          + "between 0 and 2.");
    }
    return this.board[r][c];
  }
}
