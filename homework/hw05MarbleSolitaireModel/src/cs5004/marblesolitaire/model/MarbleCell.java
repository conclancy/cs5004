package cs5004.marblesolitaire.model;

public class MarbleCell {
  private final int row;
  private final int column;

  private CellState state;

  /**
   * Constructor for a cell on the Marble Solitaire Board.
   *
   * @param row the row location of the cell on the board, as an int.
   * @param column the column location of the cell on the board, as an int.
   * @param state the state of the cell.  Either INVALID, MARBLE, or EMPTY.
   * @throws IllegalArgumentException if an invalid cell location is passed to the constructor.
   */
  public MarbleCell(int row, int column, CellState state) throws IllegalArgumentException {

    if(row < 0 || column < 0) {
      throw new IllegalArgumentException("Row and column location must be a positive integer.");
    }

    this.row = row;
    this.column = column;
    this.state = state;
  }

  /**
   * Return the state of the cell, as a String.
   *
   * @return the state of the cell, as a String.
   */
  @Override
  public String toString() {
    return switch (this.state) {
      case MARBLE -> "O";
      case EMPTY -> "_";
      case INVALID -> " ";
    };
  }

  /**
   * Get the row location of the cell.
   *
   * @return the row of the cell, as an int.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Get the column location of the cell.
   *
   * @return the column of the cell, as an int.
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Get the state of the cell.
   *  - MARBLE = the cell contains a marble.
   *  - EMPTY = the cell does not contain a marble.
   *  - INVALID = the cell is not a valid location on the board for play.
   *
   * @return the state of the boawrd.
   */
  public CellState getState() {
    return this.state;
  }

  /**
   * Sets the state of the marble.
   *
   * @param state of the board as the CellState ENUM.
   */
  public void setState(CellState state) {
    this.state = state;
  }

  /**
   * Determines if another cell passed to this cell is a valid move for this cell.
   *
   * @param other the other cell to be tested.
   * @return true if the marble can jump this marble.
   */
  public boolean isValidMove(MarbleCell other) {

    // test to ensure the space contains a marble
    if(other.getState() != CellState.MARBLE) {
      return false;
    }

    // test to make sure the marble is in the right space
    if(this.column == other.getColumn()) {
      return this.row == other.getRow() + 1 || this.row == other.getRow() - 1;
    } else if (this.row == other.getRow()) {
      return this.column == other.getColumn() + 1 || this.column == other.getColumn() - 1;
    } else {
      return false;
    }
  }


}
