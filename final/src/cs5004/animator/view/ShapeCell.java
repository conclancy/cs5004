package cs5004.animator.view;

import java.util.Objects;

/**
 * Represents a list node for shape objects for display in the {@link ViewGUIEditor}.  This
 * represents an {@link cs5004.animator.model.IShape} from the model in the View.
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
   * Get the name of the shape held within the node.
   *
   * @return the shape name, as a String.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the shape type held within the node.
   *
   * @return the shape type, as a String.
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  /**
   * Get the name and type of the shape as a String seperated by a hyphen.
   *
   * @return the name and type of the shape, as a String, seperated by a hyphen.
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
