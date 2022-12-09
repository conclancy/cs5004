package cs5004.animator.model;

/**
 * Interface representing a Shape object.  Each instance of IShape represents a single instance of a
 * shape within an EasyAnimator.
 */
public interface IShape {

  /**
   * Get the cartesian coordinates of the shape.
   *
   * @return the cartesian coordinates of the shape, as a {@link Point2D} object.
   */
  Point2D getReference();

  /**
   * Get the color of the shape.
   *
   * @return the RGB color of this object, as a {@link Color} object.
   */
  Color getColor();

  /**
   * Return's the shape's name.
   *
   * @return the name of the shape, as a String.
   */
  String getName();

  /**
   * The type of shape this object represents.  This will either be "Rectangle" or "Oval".
   *
   * @return the type of the shape, as a String.
   */
  String getShapeType();

  /**
   * Get the width of the shape.  This is the distance the shape traverses along the X axis of the
   * cartesian plane.
   *
   * @return the width of the shape, as an int.
   */
  int getWidth();

  /**
   * Get the height of the shape. This is the distance the shape traverses along the Y axis of the
   * cartesian plane.
   *
   * @return the height of the shape, as an int.
   */
  int getHeight();

  /**
   * Creates a deep clone of the shape.
   *
   * @return an exact replica of the shape, as a Shape.
   */
  IShape copy();

  /**
   * Set the tick mark at which the shape appears.
   *
   * @param tick the tick mark at which the shape appears, as an int.
   * @throws IllegalArgumentException if the appears value is a negative number.
   */
  void setAppearsAt(int tick) throws IllegalArgumentException;

  /**
   * Set the tick mark at which the shape disappears.
   *
   * @param tick the tick mark at which the shape disappears, as an int.
   * @throws IllegalArgumentException if the appears value is a negative number.
   */
  void setDisappearsAt(int tick);

  /**
   * Get the tick mark at which the shape appears.
   *
   * @return the tick mark at which the shape appears, as an int.
   */
  int getAppearsAt();

  /**
   * Get the tick mark at which the shape disappears.
   *
   * @return the tick mark at which the shape disappears, as an int.
   */
  int getDisappearsAt();

  /**
   * Set the reference position of the shape.
   *
   * @param reference the cartesian coordinates of the shape, as a {@link Point2D} object.
   */
  void setReference(Point2D reference);

  /**
   * Set the reference position of the shape.
   *
   * @param x the X coordinate for the shape.
   * @param y the Y coordinate for the shape.
   */
  void setReference(int x, int y);

  /**
   * Set the color of the object.
   *
   * @param color the RGB color the shape should change to, as a {@link Color} object.
   */
  void setColor(Color color);

  /**
   * Set the color of the shape.
   *
   * @param red the amount of red light in the color.
   * @param green the amount of green light in the color.
   * @param blue the amount of blue light in the color.
   */
  void setColor(int red, int green, int blue);

  /**
   * Set the width of the shape.
   *
   * @param width the distance the shape traverses along the X axis, as an int.
   */
  void setWidth(int width);

  /**
   * Set the height of the shape.
   *
   * @param height the distance the shape traverses along the Y axis, as an int.
   */
  void setHeight(int height);

  /**
   * Causes the shape to appear.
   */
  void show();

  /**
   * Causes the shape to disappear
   */
  void hide();

}
