package cs5004.tictactoe;

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
