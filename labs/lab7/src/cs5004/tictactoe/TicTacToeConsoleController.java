package cs5004.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable appendable;
  private final Scanner scan;

  /**
   * @param readable   the input values to run the game.
   * @param appendable the output values for the game.
   * @throws IllegalArgumentException if either of the params are null when passed.
   * @throws IllegalStateException    if more than all the spaces on the board are filled, and we
   *                                  try to add another move.
   */
  public TicTacToeConsoleController(Readable readable, Appendable appendable)
      throws IllegalArgumentException, IllegalStateException {
    if (readable == null || appendable == null) {
      throw new IllegalArgumentException("Values cannot be null!");
    }
    this.appendable = appendable;
    scan = new Scanner(readable);
  }

  /**
   * Execute a single game of tic-tac-toe given a tic-tac-toe Model. When the game is over, the
   * playGame method ends.
   *
   * @param m a non-null tic tac toe Model
   * @throws IllegalStateException if more than all the spaces on the board are filled, and we try
   *                               to add another move.
   */
  @Override
  public void playGame(TicTacToe m) throws IllegalStateException {
    try {
      playGameHelper(m);
      Integer row = null;
      Integer col = null;
      String play = "";

      while (!m.isGameOver()) {
        play = scan.next();
        if (play.equalsIgnoreCase("q")) {
          break;
        }

        try {
          int i = Integer.parseInt(play);
          if (row == null) {
            row = i;
          } else {
            col = i;
            m.move(row - 1, col - 1);
            if (m.isGameOver()) {
              appendable.append(m.toString()).append("\n");
              appendable.append("Game is over! ");
              if (m.getWinner() != null) {
                appendable.append(m.getWinner().toString()).append(" wins!\n");
              } else {
                appendable.append("Tie game.\n");
              }
              break;
            }
            playGameHelper(m);
            row = null;
            col = null;
          }
        } catch (NumberFormatException e) {
          appendable.append("This is not a valid number: ").append(play).append("\n");
        }
      }

      if (!m.isGameOver() && play.equalsIgnoreCase("q")) {
        appendable.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
      } else if (!m.isGameOver()) {
        throw new IllegalStateException("Ran out of inputs");
      }
    } catch (IOException e) {
      scan.close();
      throw new IllegalStateException("Append failed:", e);
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Failed to read from readable");
    }
  }

  private void playGameHelper(TicTacToe t) throws IOException {
    appendable.append(t.toString()).append("\n");
    appendable.append("Enter a move for ").append(t.getTurn().toString()).append(":\n");
  }
}
