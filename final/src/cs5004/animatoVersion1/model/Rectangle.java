package cs5004.animatoVersion1.model;

import java.util.Objects;

/**
 * This class represents a rectangle as an instance of {@link IShape}.  Rectangles have 4 sides and
 * can be identified by their lower left-hand corner with an x and y coordinate.
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double height;

  /**
   * Constructor for the Rectangle class.
   *
   * @param x      coordinate of the horizontal location of the oval's center, as a double.
   * @param y      coordinate of the vertical location of the oval's center as a double.
   * @param width  the width of the rectangle, as a double.
   * @param height the height of the rectangle, as a double.
   * @param color  the color of the rectangle, a {@link Color} object.
   * @throws IllegalArgumentException if the width or height of the object is 0 or less.
   */
  public Rectangle(double x, double y, double width, double height, Color color)
      throws IllegalArgumentException {
    super(new Point2D(x, y), color);

    this.width = positiveIntChecker(width);
    this.height = positiveIntChecker(height);
  }

  /**
   * Construct a rectangle with a name.
   *
   * @param name   the name of the rectangle.
   * @param x      coordinate of the horizontal location of the oval's center, as a double.
   * @param y      coordinate of the vertical location of the oval's center as a double.
   * @param width  the width of the rectangle, as a double.
   * @param height the height of the rectangle, as a double.
   * @param color  the color of the rectangle, a {@link Color} object.
   * @throws IllegalArgumentException if the width or height of the object is 0 or less.
   */
  public Rectangle (String name, double x, double y, double width, double height, Color color)
    throws IllegalArgumentException {
    super(name, new Point2D(x, y), color);

    this.width = positiveIntChecker(width);
    this.height = positiveIntChecker(height);
  }

  /**
   * Get the area of the shape.
   *
   * @return area of the shape, as a double.
   */
  @Override
  public double area() {
    return this.width * this.height;
  }

  /**
   * Get the perimeter of the shape.
   *
   * @return perimeter of the shape, as a double.
   */
  @Override
  public double perimeter() {
    return (this.width * 2) + (this.height * 2);
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
      throw new IllegalArgumentException("The resizing factor must be greater than 0.");
    }

    return new Rectangle(super.getReference().getX(), super.getReference().getY(),
        this.width * size, this.height * size, this.color);
  }

  /**
   * Create a duplicate of this shape.
   *
   * @return an exact replica of this shape, as an {@link IShape}.
   */
  @Override
  public IShape copy() {
    return new Rectangle(super.getReference().getX(), super.getReference().getY(), this.width,
        this.height, this.color);
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }

  /**
   * Determine if two oval objects are equal to one another.
   *
   * @param other the other object for comparison.
   * @return true if the objects are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Rectangle)) {
      return false;
    }

    Rectangle otherRectangle = (Rectangle) other;

    return super.getReference().getX() == otherRectangle.getReference().getX()
        && super.getReference().getY() == otherRectangle.getReference().getY()
        && this.width == otherRectangle.getWidth()
        && this.height == otherRectangle.getHeight()
        && super.getColor().getHex().equals(otherRectangle.getColor().getHex());
  }

  /**
   * Create a hash table of an Oval object.
   *
   * @return Oval hash table.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.reference, ",", this.width, ",", this.height, ",",
        super.getColor());
  }
}
