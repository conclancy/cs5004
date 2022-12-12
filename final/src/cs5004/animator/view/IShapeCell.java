package cs5004.animator.view;

/**
 * Represents a list node for shape objects for display in the {@link ViewGUIEditor}.
 */
public interface IShapeCell {

  /**
   * Get the name of the shape held within the node.
   *
   * @return the shape name, as a String.
   */
  String getName();

  /**
   * Get the shape type held within the node.
   *
   * @return the shape type, as a String.
   */
  String getShapeType();
}
