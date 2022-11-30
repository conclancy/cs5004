package model;

/**
 * Abstract implementation of the IShape class containing all of the common methods shared between
 * shape objects.
 */
public abstract class AbstractShape implements IShape {

  protected Point2D reference;
  protected Color color;

  /**
   * Constructor for an AbstractShape objects.
   *
   * @param reference the reference point for the shape, as a {@link Point2D}.
   * @param color     the color of the shape, as a {@link Color}.
   */
  public AbstractShape(Point2D reference, Color color) {
    this.reference = reference;
    this.color = color;
  }

  /**
   * Get the distance between the shape and the origin - i.e. {@link Point2D} (0, 0).
   *
   * @return distance between the shape and the origin, as a double.
   */
  @Override
  public double distanceToOrigin() {
    return reference.distToOrigin();
  }

  /**
   * Compare two shape objects to determine which is larger.
   *
   * @param other the object to be compared.
   * @return an -1 if the shape is smaller, 1 if the shape is larger, and 0 if they are the same.
   */
  @Override
  public int compareTo(IShape other) {
    return Double.compare(this.area(), other.area());
  }

  /**
   * Get the color of the this {@link IShape}.
   *
   * @return the {@link Color} object of the this {@link IShape}.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Get the reference point for this {@link IShape}.
   *
   * @return the reference point of the shape, as a {@link Point2D} object.
   */
  @Override
  public Point2D getReference() {
    return this.reference;
  }
}
