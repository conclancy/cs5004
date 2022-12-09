package cs5004.animator.model;

/**
 * This class represents a rectangle, a four sided polynomial with four 90 degree angles. Each
 * instance of this class represents a single rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for a rectangle.  This constructor assumes we know most of the details about the
   * shape.
   *
   * @param reference    the cartesian coordinates of the shape, as a {@link Point2D} object.
   * @param color        the RGB color of this object, as a {@link Color} object.
   * @param name         the name of the shape, as a String.
   * @param width        the distance the shape traverses along the X axis, as an int.
   * @param height       the distance the shape traverses along the Y axis, as an int.
   * @param appearsAt    the tick mark at which the shape appears, as an int.
   * @param disappearsAt the tick mark at which the shape disappears, as an int.
   * @throws IllegalArgumentException if the width, height, appearsAt, or disappearsAt values are a
   *                                  negative number.
   */
  public Rectangle(Point2D reference, Color color, String name, int width, int height,
      int appearsAt, int disappearsAt) throws IllegalArgumentException {
    super(reference, color, name, width, height, appearsAt, disappearsAt);
  }

  /**
   * Constructor for a Rectangle. This constructor assumes we only know the name of the Rectangle.
   *
   * @param name the name of the shape, as a String.
   */
  public Rectangle(String name) {
    super(name);
  }

  /**
   * The type of shape this object represents.  This will either be "Rectangle" or "Oval".
   *
   * @return the type of the shape, as a String.
   */
  @Override
  public String getShapeType() {
    return "RECTANGLE";
  }

  /**
   * Creates a deep clone of the shape.
   *
   * @return an exact replica of the shape, as a Shape.
   */
  @Override
  public IShape copy() {
    return new Rectangle(super.reference.copy(), super.color.copy(), super.name, super.width,
        super.height, super.appearsAt, super.disappearsAt);
  }

  /**
   * Provide a reference to the shape in SVG format.
   *
   * @return a description of the shape in SVG format.
   */
  @Override
  public String toString() {
    if (super.reference == null || super.color == null) {
      return "there is no reference or color in this shape";
    }

    return String.format("<rect id='%s' x='%d' y='%d' width='%d' "
            + "height='%d' fill='%s' visibility='visible'>***</rect>", super.name,
        super.reference.getX(), super.reference.getY(), super.width, super.height,
        super.color.toString());
  }
}
