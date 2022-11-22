package model;

/**
 * Represents a shape object.  One instance of this object represents a single shape within the
 * automation.
 */
public interface IShape extends Comparable<IShape> {

  /**
   * Get the distance between the shape and the origin - i.e. {@link Point2D} (0, 0).
   *
   * @return distance between the shape and the origin, as a double.
   */
  double distanceToOrigin();

  /**
   * Get the area of the shape.
   *
   * @return area of the shape, as a double.
   */
  double area();

  /**
   * Get the perimeter of the shape.
   *
   * @return perimeter of the shape, as a double.
   */
  double perimeter();

  /**
   * Scale the shape.  {@param size} larger than 1.0 will make the shape larger, and {@param size}
   * smaller than 1.0 will decrease the size of the shape.
   *
   * @param size of the new scaled object.
   * @return A new {@link IShape} with the appropriate scaled dimensions.
   * @throws IllegalArgumentException thrown when {@param size} is less than or equal to 0.
   */
  IShape resize(double size) throws IllegalArgumentException;

  /**
   * Create a duplicate of this shape.
   *
   * @return an exact replica of this shape, as an {@link IShape}.
   */
  IShape copy();

  /**
   *
   * @param other the object to be compared.
   * @return
   */
  int compareTo(IShape other);
}
