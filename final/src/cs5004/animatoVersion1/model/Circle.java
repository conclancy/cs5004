package cs5004.animatoVersion1.model;

/**
 * This class represents a circle.  Circles are special cases of ovals where the axis is the same
 * length (called a radius) around the entire perimeter of the circle.
 */
public class Circle extends Oval {

  /**
   * Constructor for the circle class. A circle is a special case of an oval where the major and
   * minor axis are equal to one another.
   *
   * @param x      coordinate of the horizontal location of the circle's center, as a double.
   * @param y      coordinate of the vertical location of the circle's center as a double.
   * @param radius distance between the center point and the perimeter of the circle, as a double.
   * @param color  the color of the circle, as a {@link Color} object.
   * @throws IllegalArgumentException if the radius provided is less than or equal to 0.
   */
  public Circle(double x, double y, double radius, Color color) throws IllegalArgumentException {
    super(x, y, radius, radius, color);
  }

  /**
   * Constructor for the circle class. A circle is a special case of an oval where the major and
   * minor axis are equal to one another.
   *
   * @param name   name of the circle.
   * @param x      coordinate of the horizontal location of the circle's center, as a double.
   * @param y      coordinate of the vertical location of the circle's center as a double.
   * @param radius distance between the center point and the perimeter of the circle, as a double.
   * @param color  the color of the circle, as a {@link Color} object.
   * @throws IllegalArgumentException if the radius provided is less than or equal to 0.
   */
  public Circle(String name, double x, double y, double radius, Color color) throws IllegalArgumentException {
    super(name, x, y, radius, radius, color);
  }

}