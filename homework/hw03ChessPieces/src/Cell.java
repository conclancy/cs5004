/**
 * A cell on a chess board containing a row and a column.
 */
public class Cell {

  private int row;
  private int column;

  /**
   * Constructor for the Cell class.  Lowest left-hand cell is (0,0) and top right-hand cell is
   * (7,7).
   *
   * @param row    value of the cell
   * @param column value of the cell
   * @throws IllegalArgumentException if a value of less than 0 or greater than 7 is entered for a
   *                                  row or column value.
   */
  public Cell(int row, int column) throws IllegalArgumentException {
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Valid rows and columns are between 0 and 7.");
    }
    this.row = row;
    this.column = column;
  }

  /**
   * Return the row of the cell.
   *
   * @return the row of the cell
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Return the column of the cell.
   *
   * @return the column of the cell
   */
  public int getColumn() {
    return this.column;
  }

}
