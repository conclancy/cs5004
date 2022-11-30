package model;

/**
 * Object representing an action of a shape object at a specific point.
 */
public interface IAction {

  /**
   * Show when this action first appears within the automation.
   *
   * @return the first appears of the automation, as an int.
   */
  int getAppears();

  /**
   * Set when this action should first appear within an automation.
   *
   * @param appears first appearance of this automation, as an int.
   * @throws IllegalStateException if {@param appears} is less than 0.
   */
  void setAppears(int appears) throws IllegalArgumentException;

  /**
   * Show when this action last appears within the automation.
   *
   * @return the last appears of the automation, as an int.
   */
  int getDisappears();

  /**
   * Set when this action should last appear within an automation.
   *
   * @param disappears last appearance of this automation, as an int.
   * @throws IllegalStateException if {@param disappears} is less than 0.
   */
  void setDisappears(int disappears) throws IllegalArgumentException;

  /**
   * Get the position of the associated {@link IShape} during this automation.
   *
   * @return the coordinates of the {@link IShape} during this specific action, as a
   * {@link Point2D}.
   */
  Point2D getPoint2D();

  /**
   * Set the location of the associated {@link IShape} during this automation.
   *
   * @param x the horizontal coordinate of the {@link IShape}, as an int.
   * @param y the vertical coordinate of the {@link IShape}, as an int.
   */
  void setPoint2D(int x, int y);

  /**
   * Get the color of the associated {@link IShape} during the automation.
   *
   * @return the color of the associated {@link IShape} as a {@link Color}.
   */
  Color getColor();

  /**
   * Set the color of the associated {@link IShape} during this automation.
   *
   * @param color the coordinates of the {@link IShape} as a {@link Color}.
   */
  void setColor(Color color);

  /**
   * Get the scaled size of the {@link IShape} during this automation.
   *
   * @return the scaled size of the associated {@link IShape} during this automation as a double.
   */
  double getSize();

  /**
   * Set the scaled size of the {@link IShape} during this automation.  Default size is 1.0, size
   * greater than this will increase shape's scale, and soze lower than 1.0 will decrease the
   * scale.
   *
   * @param size the scaling factor of the associated {@link IShape} as a double.
   * @throws IllegalArgumentException if {@param size} is less than 0.
   */
  void setSize(double size) throws IllegalArgumentException;


}
