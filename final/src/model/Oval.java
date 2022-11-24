package model;

/**
 * Class representing an oval shape.  Ovals have an x and y coordinate, a majorAxis, a minorAxis,
 * and a color.
 */
public class Oval extends AbstractShape {

  protected double majorAxis;
  protected double minorAxis;

  public Oval(double x, double y, double majorAxis, double minorAxis, Color color)
      throws IllegalArgumentException {
    super(new Point2D(x, y), color);

    if (majorAxis <= 0 || minorAxis <= 0) {
      throw new IllegalArgumentException("Axis values must be greater than 0.");
    }

    this.majorAxis = majorAxis;
    this.minorAxis = minorAxis;
  }

  /**
   * Get the area of the shape.
   *
   * @return area of the shape, as a double.
   */
  @Override
  public double area() {
    return Math.PI * this.majorAxis * this.minorAxis;
  }

  /**
   * Get the perimeter of the shape.
   *
   * @return perimeter of the shape, as a double.
   */
  @Override
  public double perimeter() {
    return Math.PI * 2 * Math.sqrt((Math.pow(majorAxis, 2) * Math.pow(minorAxis, 2)) / 2);
  }

  /**
   * Scale the shape.  {@param size} larger than 1.0 will make the shape larger, and {@param size}
   * smaller than 1.0 will decrease the size of the shape.
   *
   * @param size of the new scaled object.
   * @return A new {@link IShape} with the appropriate scaled dimensions.
   * @throws IllegalArgumentException thrown when {@param size} is less than or equal to 0.
   */
  @Override
  public IShape resize(double size) throws IllegalArgumentException {

    if (size <= 0) {
      throw new IllegalArgumentException("Size factor must be greater than 0.");
    }

    return new Oval(super.reference.getX(), super.reference.getY(), Math.sqrt(size) *
        majorAxis, Math.sqrt(size) * minorAxis, super.color);
  }

  /**
   * Create a duplicate of this shape.
   *
   * @return an exact replica of this shape, as an {@link IShape}.
   */
  @Override
  public IShape copy() {
    return new Oval(super.reference.getX(), super.reference.getY(), this.majorAxis, this.minorAxis,
        super.color);
  }
}
