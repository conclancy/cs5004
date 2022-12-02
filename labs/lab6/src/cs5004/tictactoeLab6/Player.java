package cs5004.tictactoeLab6;

/**
 * Represents the two players in a Tic Tac Toe game.
 */
public enum Player {
  X("X"),
  O("O");

  private final String string;

  Player(String name) {
    this.string = name;
  }

  @Override
  public String toString() {
    return this.string;
  }
}
