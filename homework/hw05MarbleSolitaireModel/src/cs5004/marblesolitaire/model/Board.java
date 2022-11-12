package cs5004.marblesolitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

  private int size;
  private List boardGrid;

  public Board(int size) {
    this.size = size;

    this.boardGrid = new ArrayList<ArrayList<MarbleCell>>(size);

    for (int r = 0; r < size; r++) {
      ArrayList<MarbleCell> tempRow = new ArrayList<>(size);

      for (int c = 0; c < size; c++) {
        tempRow.add(new MarbleCell(r, c));
      }

      this.boardGrid.add(tempRow);
    }
  }

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
}
