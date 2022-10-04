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
   * @param x lower-left x coordinate of the rectangle
   * @param y lower-left y coordinate of the rectangle
   * @param w width of the rectangle
   * @param h height of the rectangle
   * @throws IllegalArgumentException
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

  /**
   * Returns a new rectangle representing the intersect region.
   *
   * @param other the other rectangle for intersect
   * @return A new rectangle
   * @throws NoSuchElementException
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    if (!this.overlap(other)) {
      throw new NoSuchElementException("These two rectangles do no overlap or intersect.");
    }
    double newUpperRightX = Math.min(this.x + this.w, other.x + other.w);
    double newUpperRightY = Math.min(this.y + this.h, other.y + other.h);
    double newLowerLeftX = Math.max(this.x, other.x);
    double newLowerLeftY = Math.max(this.y, other.y);

    return new Rectangle(
        newLowerLeftX,
        newLowerLeftY,
        newUpperRightX - newLowerLeftX,
        newUpperRightY -  newLowerLeftY);
  }

  /**
   * A new rectangle representing the union region between two rectangles
   *
   * @param other the other rectangle in the union
   * @return rectangle representing the union region between two rectangles
   */
  public Rectangle union(Rectangle other) {
    double xMin = Math.min(this.x, other.x);
    double yMin = Math.min(this.y, other.y);
    double xMax = Math.max((this.x + this.w), (other.x + other.w));
    double yMax = Math.max((this.y + this.h), (other.y + other.h));
    double wNew = xMax - xMin;
    double hNew = yMax - yMin;
    return new Rectangle(xMin, yMin, wNew, hNew);
  }

  /**
   * The string representation of the rectangle in the format "x:1, y:2, w:3, h:4".
   *
   * @return string representation of the rectangle
   */
  @Override
  public String toString() {
    String xString = "x:" + Math.round(this.x);
    String yString = ", y:" + Math.round(this.y);
    String wString = ", w:" + Math.round(this.w);
    String hString = ", h:" + Math.round(this.h);
    return xString + yString + wString + hString;
  }























}