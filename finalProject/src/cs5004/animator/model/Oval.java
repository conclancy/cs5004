package cs5004.animator.model;

/**
 * This class represents an oval.  Each instance of this class represents a single oval.
 */
public class Oval extends AbstractShape {

  /**
   * Constructor for an oval.  This constructor assumes we know most of the details about the
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
  public Oval(Point2D reference, Color color, String name, int width, int height,
      int appearsAt, int disappearsAt) throws IllegalArgumentException {
    super(reference, color, name, width, height, appearsAt, disappearsAt);
  }

  /**
   * Constructor for an Oval.  This constructor assumes we only know the name of the Oval.
   *
   * @param name the name of the shape, as a String.
   */
  public Oval(String name) {
    super(name);
  }

  /**
   * The type of shape this object represents.  This will either be "Rectangle" or "Oval".
   *
   * @return the type of the shape, as a String.
   */
  @Override
  public String getShapeType() {
    return "OVAL";
  }

  /**
   * Creates a deep clone of the shape.
   *
   * @return an exact replica of the shape, as a Shape.
   */
  @Override
  public IShape copy() {
    return new Oval(super.reference.copy(), super.color.copy(), super.name, super.width,
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

    return String.format("<ellipse id='%s' cx='%d' cy='%d' rx='%d' "
            + "ry='%d' fill='%s' visibility='visible' >***</ellipse>", super.name,
        super.reference.getX(), super.reference.getY(), super.width, super.height,
        super.color.toString());
  }
}

