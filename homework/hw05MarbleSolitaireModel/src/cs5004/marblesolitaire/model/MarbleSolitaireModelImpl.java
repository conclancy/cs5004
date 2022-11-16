package cs5004.marblesolitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the operations offered by the marble solitaire model. One object of the
 * model represents one game of marble solitaire.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private ArrayList<ArrayList<CellState>> marbleBoard;

  /**
   * Constructor for a standard marble solitaire game with a standard centered empty start
   * location.
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Constructor for a standard-sized marble solitaire game with a non-centered empty start
   * location.
   *
   * @param sRow    row number of the empty starting space, as an int.
   * @param sColumn column number of the empty start space, as an int.
   * @throws IllegalArgumentException if the location of the empty location is not on the board.
   */
  public MarbleSolitaireModelImpl(int sRow, int sColumn) throws IllegalArgumentException {
    this(3, sRow, sColumn);
  }

  /**
   * Constructor for a custom-sized marble solitaire game with a standard centered empty start
   * location.
   *
   * @param armThickness the thickness of the arm on the board, as an int.
   * @throws IllegalArgumentException if the arm thickness is not an odd number.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness, armThickness);
  }

  /**
   * Constructor for a custom-sized marble solitaire game with a non-centered empty start location.
   *
   * @param armThickness the thickness of the arm on the board, as an int.
   * @param sRow         row number of the empty starting space, as an int.
   * @param sColumn      column number of the empty start space, as an int.
   * @throws IllegalArgumentException if the armThickness is not odd, or the empty location is not
   *                                  placed in a valid location on the board.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sColumn)
      throws IllegalArgumentException {

    if (armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("The size of the board must be an odd number greater than "
          + "or equal to 3.");
    }

    int voidSize = this.getVoidSize(armThickness);
    int boardSize = this.getBoardSize(armThickness);
    marbleBoard = new ArrayList<ArrayList<CellState>>(boardSize);

    if (sRow < 0 || sRow > boardSize || sColumn < 0 || sColumn > boardSize) {
      throw new IllegalArgumentException("The empty location must in a column and row greater than"
          + "0 and less than" + String.valueOf(boardSize));
    }

    for (int r = 0; r < boardSize; r++) {
      ArrayList<CellState> tempRow = new ArrayList<>(boardSize);

      for (int c = 0; c < boardSize; c++) {
        if (c < voidSize && r < voidSize) {
          tempRow.add(CellState.INVALID);
        } else if (c >= (voidSize + armThickness) && r < voidSize) {
          tempRow.add(CellState.INVALID);
        } else if (c < voidSize && r >= (voidSize + armThickness)) {
          tempRow.add(CellState.INVALID);
        } else if (c >= (voidSize + armThickness) && r >= (voidSize + armThickness)) {
          tempRow.add(CellState.INVALID);
        } else if (c == sColumn && r == sRow) {
          tempRow.add(CellState.EMPTY);
        } else {
          tempRow.add(CellState.MARBLE);
        }
      }

      this.marbleBoard.add(tempRow);
    }

    // Ensure there is exactly 1 empty space to begin the game.
    int emptySpaces = this.getFlatBoard().stream().map(e -> {
      if (e == CellState.EMPTY) {
        return 1;
      } else {
        return 0;
      }
    }).reduce(0, Integer::sum);

    if (emptySpaces != 1) {
      throw new IllegalArgumentException("The board must have exactly 1 valid empty location.  "
          + "Current board has " + String.valueOf(emptySpaces) + " empty spaces.");
    }

  }

  /**
   * Get the height/width of the square game board based on the arm thickness of a game.
   *
   * @param armThickness the thickness of the arm on the board, as an int.
   * @return the height/width of the board, as an int.
   */
  private int getBoardSize(int armThickness) {
    return armThickness + (2 * this.getVoidSize(armThickness));
  }

  /**
   * Get the height/width of current instantiated game board.
   *
   * @return the height/width of the board, as an int.
   */
  private int getBoardSize() {
    return marbleBoard.size();
  }

  /**
   * Get the size of the void spaces around the edge of the board that are not valid locations for
   * marble movement during game play.  This `void` space take the form of a square.
   *
   * @param armThickness the thickness of the arm on the board, as an int.
   * @return the height/width of the void area, as an int.
   */
  private int getVoidSize(int armThickness) {
    return armThickness - 1;
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

    int size = this.getBoardSize() - 1;

    CellState middleCell = CellState.EMPTY;
    int middleRow = -1;
    int middleCol = -1;

    // Test to ensure the spot is on the board
    if (fromRow < 0 || toRow < 0 || fromCol < 0 || toCol < 0) {
      throw new IllegalArgumentException("Rows and columns must be positive integers.");
    } else if (fromRow > size || toRow > size || fromCol > size || toCol > size) {
      throw new IllegalArgumentException("Rows and columns must be less than "
          + String.valueOf(size));
    }

    // Test to make sure there is a marble in the `from` location
    if (marbleBoard.get(fromRow).get(fromCol) == CellState.EMPTY) {
      throw new IllegalArgumentException("`from` location does not contain a marble to move. ");
    } else if (marbleBoard.get(fromRow).get(fromCol) == CellState.INVALID) {
      throw new IllegalArgumentException("`from` location is not a valid location on the board.");
    }

    // Test to make sure the `to` location is an EMPTY location
    if (marbleBoard.get(toRow).get(toCol) == CellState.MARBLE) {
      throw new IllegalArgumentException("`to` location is occupied with a marble. Marbles can only"
          + " be moved to empty spaces");
    } else if (marbleBoard.get(toRow).get(toCol) == CellState.INVALID) {
      throw new IllegalArgumentException("`to` location is not a valid location on the board.");
    }

    // Test to make sure `to` is 2 spots away
    if (fromRow == toRow) {
      if (fromCol == toCol + 2) {
        middleRow = fromRow;
        middleCol = toCol + 1;
        middleCell = marbleBoard.get(middleRow).get(middleCol);
      } else if (fromCol == toCol - 2) {
        middleRow = fromRow;
        middleCol = toCol - 1;
        middleCell = marbleBoard.get(middleRow).get(middleCol);
      }
    } else if (fromCol == toCol) {
      if (fromRow == toRow + 2) {
        middleRow = toRow + 1;
        middleCol = fromCol;
        middleCell = marbleBoard.get(middleRow).get(middleCol);
      } else if (fromRow == toRow - 2) {
        middleRow = toRow - 1;
        middleCol = fromCol;
        middleCell = marbleBoard.get(middleRow).get(middleCol);
      } else {
        throw new IllegalArgumentException("Invalid move. `to` location must be located two sports"
            + " away vertically or horizontally.");
      }
    }

    // Test to make sure there is a marble in the middle
    if (middleCell != CellState.MARBLE) {
      throw new IllegalArgumentException("Invalid move.  `from` marble must jump over a marble into"
          + " an empty space.");
    }

    // Movement logic
    this.marbleBoard.get(fromRow).set(fromCol, CellState.EMPTY);
    this.marbleBoard.get(middleRow).set(middleCol, CellState.EMPTY);
    this.marbleBoard.get(toRow).set(toCol, CellState.MARBLE);
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return !(this.horizontalMoveAvailable() || this.verticalMoveAvailable());
  }

  /**
   * Function to determine if there are any valid horizontal moves left on the board.
   *
   * @return true if there is one or more available horizontal moves available on the board.
   */
  private boolean horizontalMoveAvailable() {
    for (ArrayList<CellState> colStates : marbleBoard) {
      for (int i = 0; i < this.getBoardSize() - 2; i++) {
        if (
            (
                // Movement to the right available
                colStates.get(i) == CellState.MARBLE
                    && colStates.get(i + 1) == CellState.MARBLE
                    && colStates.get(i + 2) == CellState.EMPTY
            ) || (
                // Movement to the left available
                colStates.get(i) == CellState.EMPTY
                    && colStates.get(i + 1) == CellState.MARBLE
                    && colStates.get(i + 2) == CellState.MARBLE
            )
        ) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Function to determine if there are any valid vertical moves left on the board.
   *
   * @return true if there is one or more available vertical moves available on the board.
   */
  private boolean verticalMoveAvailable() {
    for (int row = 0; row < this.getBoardSize() - 2; row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (
            (
                this.marbleBoard.get(row).get(col) == CellState.MARBLE
                    && this.marbleBoard.get(row + 1).get(col) == CellState.MARBLE
                    && this.marbleBoard.get(row + 2).get(col) == CellState.EMPTY
            ) || (
                this.marbleBoard.get(row).get(col) == CellState.EMPTY
                    && this.marbleBoard.get(row + 1).get(col) == CellState.MARBLE
                    && this.marbleBoard.get(row + 2).get(col) == CellState.MARBLE
            )
        ) {
          return true;
        }
      }
    }
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
    StringBuilder gameString = new StringBuilder();

    for (ArrayList<CellState> row : this.marbleBoard) {
      for (CellState cell : row) {
        switch (cell) {
          case INVALID:
            gameString.append("  ");
            break;
          case MARBLE:
            gameString.append("O ");
            break;
          case EMPTY:
            gameString.append("_ ");
            break;
          default:
            gameString.append("X ");
            break;
        }
      }
      while (gameString.charAt(gameString.length() - 1) == ' ') {
        gameString.deleteCharAt(gameString.length() - 1);
      }
      gameString.append("\n");
    }

    return gameString.toString().substring(0, gameString.length() - 1);
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return this.getFlatBoard().stream().map(e -> {
      if (e == CellState.MARBLE) {
        return 1;
      } else {
        return 0;
      }
    }).reduce(0, Integer::sum);
  }

  /**
   * Turn the game board into a flat list for use with Higher Order functions.
   *
   * @return a list of CellState enums representing the game board.
   */
  private List<CellState> getFlatBoard() {
    return this.marbleBoard.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
