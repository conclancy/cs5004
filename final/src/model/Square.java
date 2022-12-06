package model;

/**
 * Special instance of a {@link Rectangle} where all 4 sides of the square are the same length.
 */
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

  /**
   * Constructor for the square class.  Squares are special cases of rectangles where each of the 4
   * sides are the same length.
   *
   * @param name       The name of the square.
   * @param x          coordinate of the horizontal location of the circle's center, as a double.
   * @param y          coordinate of the vertical location of the circle's center as a double.
   * @param sideLength the length of a single side of the square.
   * @param color      the color of the square.
   */
  public Square(String name, double x, double y, double sideLength, Color color) {
    super(name, x, y, sideLength, sideLength, color);
  }
}
