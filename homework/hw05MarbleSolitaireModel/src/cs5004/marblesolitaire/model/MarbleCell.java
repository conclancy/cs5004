package cs5004.marblesolitaire.model;

public class MarbleCell {
  private int row;
  private int column;
  private boolean isOccupied;
  private boolean isLegalMove;

  public MarbleCell(int row, int column) {
    this.row = row;
    this.column = column;
    this.isOccupied = false;
    this.isLegalMove = false;
  }

  @Override
  public String toString() {
    if(isOccupied) {
      return "O";
    } else {
      return "_";
    }
  }

  public void setOccupied(boolean isOccupied) {
    this.isOccupied = isOccupied;
  }


}
