public class Rook extends AbstractChessPiece {

  public Rook(int row, int column, Color color){
    super(row, column, color);
  }

  @Override
  public boolean canMove(Cell cell) {
    return false;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return false;
  }
}
