package model;

public class Square extends Rectangle {

  /**
   * Constructor for the square class.  Squares are special cases of rectangles where each of the 4
   * sides are the same length.
   *
   * @param x          coordinate of the horizontal location of the circle's center, as a double.
   * @param y          coordinate of the vertical location of the circle's center as a double.
   * @param sideLength the length of a single side of the square.
   * @param color      the color of the square.
   */
  public Square(double x, double y, double sideLength, Color color) {
    super(x, y, sideLength, sideLength, color);
  }
}
