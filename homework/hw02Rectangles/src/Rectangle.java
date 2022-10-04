import java.util.NoSuchElementException;

/**
 * This class implments a rectangle with x, y coordinates and a width and height.
 */
public class Rectangle {
  private double x;
  private double y;
  private double w;
  private double h;

  /**
   * Constructor for the rectangle class.
   *
   * @param x coordinate of the rectangle
   * @param y coordinate of the rectangle
   * @param w width of the rectangle
   * @param h height of the rectangle
   */
  public Rectangle(double x, double y, double w, double h) throws IllegalArgumentException {
    if (w<0 || h<0) {
      throw new IllegalArgumentException("Rectangles must have positive width and height.");
    }
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  /**
   * A method to determine overlap with another Rectangle object.
   *
   * @param other another rectangle
   * @return boolean
   */
  public boolean overlap(Rectangle other) {
    return (x < other.x + other.w && x + w > other.x && y < other.y + other.h && y + h > other.y);
  }























}