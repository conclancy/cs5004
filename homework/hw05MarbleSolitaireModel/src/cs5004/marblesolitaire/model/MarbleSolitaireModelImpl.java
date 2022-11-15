package cs5004.marblesolitaire.model;

import java.util.ArrayList;

public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private ArrayList<ArrayList<CellState>> marbleBoard;

  public MarbleSolitaireModelImpl() {
    this(3,3,3);
  }

  public MarbleSolitaireModelImpl(int sRow, int sColumn) throws IllegalArgumentException {
    this(3, sRow, sColumn);
  }

  public MarbleSolitaireModelImpl (int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness, armThickness);
  }

  public MarbleSolitaireModelImpl (int armThickness, int sRow, int sColumn)
      throws IllegalArgumentException {

    if(armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("The size of the board must be an odd number greater than "
          + "or equal to 3.");
    }

    int voidSize = this.getVoidSize(armThickness);
    int boardSize = this.getBoardSize(armThickness);
    marbleBoard = new ArrayList<ArrayList<CellState>>(boardSize);

    for (int r = 0; r < armThickness; r++) {
      ArrayList<CellState> tempRow = new ArrayList<>(boardSize);

      for (int c = 0; c < armThickness; c++) {
        if(c < voidSize && r < voidSize) {
          tempRow.add(CellState.INVALID);
        } else if (c > (voidSize + armThickness) && r < voidSize) {
          tempRow.add(CellState.INVALID);
        } else if (c < voidSize && r > (voidSize + armThickness)) {
          tempRow.add(CellState.INVALID);
        } else if (c > (voidSize + armThickness) && r > (voidSize + armThickness)) {
          tempRow.add(CellState.INVALID);
        } else if (c == sColumn && r == sRow) {
          tempRow.add(CellState.EMPTY);
        } else {
          tempRow.add(CellState.MARBLE);
        }
      }

      this.marbleBoard.add(tempRow);
    }
  }

  private int getBoardSize(int armThickness) {
    return armThickness + (2 * this.getVoidSize(armThickness));
  }

  private int getVoidSize(int armThickness) {
    return (armThickness + 1) / 2;
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Test to ensure the spot is on the board
    // Test to make sure `to` is 2 sports away and empty
    // Test to make sure there is a marble in the middle
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String getGameState() {
    return null;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return 0;
  }
}
