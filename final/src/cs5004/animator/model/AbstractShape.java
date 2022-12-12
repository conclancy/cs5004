package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * Abstraction class for {@link IShape} objects.  This class contains all methods that are common to
 * shape objects including their type, width, height, reference point, color, and degrees of
 * rotation.
 */
public abstract class AbstractShape implements IShape {

  protected String shapeType;
  protected int width;
  protected int height;
  protected Point2D reference;
  protected Color color;
  protected int degrees;
  protected String name;

  /**
   * Constructs a shape with data inputs for all Shape fields.
   */
  protected AbstractShape(int width, int height, Point2D reference, int degrees, Color color,
      String name) {

    this.width = this.checkPositiveInt(width, "Width");
    this.height = this.checkPositiveInt(height, "Height");
    this.reference = reference;
    this.degrees = degrees;
    this.color = color;
    this.name = name;

  }

  /**
   * Constructor for a shape, that creates an "empty" shape at (0,0) with no height, width, or
   * color.
   */
  protected AbstractShape(String name) {
    this(0, 0, new Point2D.Double(0, 0), 0, new Color(0), name);
  }


  /**
   * Get the type of the shape.
   *
   * @return the type of the shape, as a String.
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  /**
   * Get the color of the shape as a {@link Color} object.
   *
   * @return the color of the shape, as a {@link Color}.
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }

  /**
   * Change the color of the shape.
   *
   * @param color the color of the shape, represented by a {@link Color} object.
   */
  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get the width of the shape.
   *
   * @return the width of the shape, as an int.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape, as a String.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Change the width of the shape.
   *
   * @param width is the width of the shape.
   * @throws IllegalArgumentException if the {@param width} is less than 0.
   */
  @Override
  public void setWidth(int width) throws IllegalArgumentException {
    this.width = this.checkPositiveInt(width, "Width");
  }

  /**
   * Get the height of the shape.
   *
   * @return the height of the shape.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Sets the height of an object.
   *
   * @param height is the height.
   * @throws IllegalArgumentException if the {@param height} is less than 0.
   */
  @Override
  public void setHeight(int height) throws IllegalArgumentException {
    this.height = this.checkPositiveInt(height, "Height");
  }

  /**
   * Get the rotational degrees of the shape.
   *
   * @return the width of the shape.
   */
  @Override
  public int getDegrees() {
    return this.degrees;
  }

  /**
   * Method that returns the coordinate position of a shape in a process.
   *
   * @return the coordinate position.
   */
  @Override
  public Point2D getReference() {
    return new Point2D.Double(this.reference.getX(), this.reference.getY());
  }

  /**
   * Sets the coordinate position of the shape.
   *
   * @param coordinatePosition the new coordinates of the shape.
   */
  @Override
  public void setReference(Point2D coordinatePosition) {
    this.reference = coordinatePosition;
  }

  /**
   * Method that returns the coordinate position of a shape in a process.
   *
   * @return the coordinate position as a String.
   */
  public String getTextPosition() {
    return "(" + reference.getX() + ", " + reference.getY() + ")";
  }

  /**
   * Sets the rotation angle of the shape.
   *
   * @param degrees is the rotation degree.
   */
  @Override
  public void setDegrees(int degrees) {
    this.degrees = degrees;
  }

  /**
   * Method that prints the attributes of a shape as a single string.
   *
   * @return a string with the attributes.
   */
  @Override
  public String toString() {
    return this.shapeType + "  " + this.reference.getX() + "  "
        + this.reference.getY() + "  "
        + this.width + "  " + this.height + "  " + this.degrees + "  "
        + this.color.getRed() + "  " + this.color.getGreen() + "  " + this.color.getBlue();
  }


  /**
   * Method that creates a hashcode for the objects.
   *
   * @return hashcode integer.
   */
  @Override
  public int hashCode() {

    return Objects.hash(this.shapeType, this.width, this.height, this.reference.getX(),
        this.reference.getY(), this.degrees, this.color.getRGB());

  }

  /**
   * Method that determines if objects are equal based on all attributes.
   *
   * @param other which is the other object.
   * @return boolean value of true if equal, false if not.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof AbstractShape)) {
      return false;
    }

    AbstractShape otherShape = (AbstractShape) other;

    return this.shapeType.equals(otherShape.getShapeType())
        && this.width == otherShape.getWidth()
        && this.height == otherShape.getHeight()
        && this.reference.getX() == otherShape.getReference().getX()
        && this.reference.getY() == otherShape.getReference().getY()
        && this.degrees == otherShape.getDegrees()
        && this.color.getRGB() == otherShape.getColor().getRGB();
  }

  /**
   * Checks to ensure that an input value is greater than or equal to 0.
   *
   * @param i         the integer to be checked.
   * @param fieldName the checking field name, only used if an error is thrown.
   * @return the valid passed integer, as an int.
   * @throws IllegalArgumentException if the value passed is less than 0.
   */
  private int checkPositiveInt(int i, String fieldName) throws IllegalArgumentException {

    if (i >= 0) {
      return i;
    } else {
      throw new IllegalArgumentException(fieldName + " requires a positive input value.");
    }
  }

}

