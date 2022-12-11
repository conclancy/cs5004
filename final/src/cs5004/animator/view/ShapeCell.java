package cs5004.animator.view;

import java.util.Objects;

/**
 * This class represents a node in a list for the GUI display in which they correspond to the
 * shapes.
 */
public class ShapeCell implements IShapeCell {

  private final String name;
  private final String shapeType;

  /**
   * Constructor that creates a shape cell for a shape.
   *
   * @param name      the unique ID of the shape.
   * @param shapeType the type of shape.
   */
  public ShapeCell(String name, String shapeType) {
    this.name = name;
    this.shapeType = shapeType;
  }

  /**
   * Retrieves the shape ID.
   *
   * @return the shape ID
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves the shape's type such as rectangle or ellipse.
   *
   * @return the shape type
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  /**
   * Prints the String for the two assocaited attributes.
   *
   * @return the toString value
   */
  @Override
  public String toString() {
    return this.name + " - " + this.shapeType;
  }

  /**
   * Determines equality of two shape cells.
   *
   * @return true if the objects are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof ShapeCell)) {
      return false;
    }

    ShapeCell otherShapeCell = (ShapeCell) other;

    return this.name.equals(otherShapeCell.getName())
        && this.shapeType.equals(otherShapeCell.getShapeType());
  }

  /**
   * Provides the Hash of the object.
   *
   * @return the hash code of the object, as an int.
   */
  @Override
  public int hashCode() {

    return Objects.hash(this.name, this.shapeType);

  }
}
