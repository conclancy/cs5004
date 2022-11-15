package cs5004.marblesolitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

  private int size;
  private List<List<MarbleCell>> boardGrid;

  public Board(int size) throws IllegalArgumentException {

    if(size % 2 == 0) {
      throw new IllegalArgumentException("The size of the board must be an odd number.");
    }

    this.size = size;

    this.boardGrid = new ArrayList<List<MarbleCell>>(size);

    for (int r = 0; r < size; r++) {
      ArrayList<MarbleCell> tempRow = new ArrayList<>(size);

      // TODO fix state
      for (int c = 0; c < size; c++) {
        tempRow.add(new MarbleCell(r, c, CellState.MARBLE));
      }

      this.boardGrid.add(tempRow);
    }
  }

  private void initBoard(int thickness) {

  }

  @Override
  public String toString() {
    StringBuilder boardString = new StringBuilder();

    for (int r = 0; r < size; r++) {
      List<MarbleCell> tempRow = (List<MarbleCell>) this.boardGrid.get(r);

      for (int c = 0; c < size; c++) {
        boardString.append(tempRow.get(c).toString());
      }

      boardString.append("\n");
    }

    String s = boardString.toString();

    return Optional.ofNullable(s).filter(str -> str.length() != 0)
        .map(str -> str.substring(0, str.length() - 1))
        .orElse(s);
  }

  public boolean hasValidMove() {
    return true;
    //TODO
  }

  public int getScore() {
    return 0;
    //TODO
  }

  private boolean validateMove(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {

    MarbleCell fromCell;
    MarbleCell toCell;
    MarbleCell middleCell = null;

    try{
      fromCell = boardGrid.get(fromRow).get(fromCol);
      toCell = boardGrid.get(toRow).get(toCol);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Row ane column values must be between 0 and "
          + String.valueOf(this.size));
    }

    // Test to make sure the `to` cell is empty
    if (toCell.getState() != CellState.EMPTY) {
      return false;
    }

    // Test to determine if the two cells are within two cells of each other
    if (fromRow == toRow) {
      if (fromCell.getColumn() == toCell.getColumn() + 2) {
        middleCell = boardGrid.get(fromRow).get(fromCell.getColumn() + 1);
      } else if (fromCell.getColumn() == toCell.getColumn() - 2) {
        middleCell = boardGrid.get(fromRow).get(fromCell.getColumn() - 1);
      }
    } else if (fromCol == toCol) {
      if (fromCell.getRow() == toCell.getRow() + 2) {
        middleCell = boardGrid.get(fromCell.getRow() + 1).get(fromCol);
      } else if (fromCell.getRow() == toCell.getRow() - 2) {
        middleCell = boardGrid.get(fromCell.getRow() - 1).get(fromCol);
      }
    }

    // Test to determine if there is a marble in the cell between them
    if (middleCell == null) {
      return false;
    } else {
      return (middleCell.getState() == CellState.MARBLE);
    }
  }
}
