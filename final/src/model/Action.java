package model;

/**
 * This class represents a shape's action.
 */
public class Action implements IAction {

  // TODO create fields

  public Action() {
    // TODO implement instantiation
  }

  /**
   * Show when this action first appears within the automation.
   *
   * @return the first appears of the automation, as an int.
   */
  @Override
  public int getAppears() {
    return 0;
  }

  /**
   * Set when this action should first appear within an automation
   *
   * @param appears first appearance of this automation, as an int.
   * @throws IllegalStateException if {@param appears} is less than 0.
   */
  @Override
  public void setAppears(int appears) throws IllegalArgumentException {

  }

  /**
   * Show when this action last appears within the automation.
   *
   * @return the last appears of the automation, as an int.
   */
  @Override
  public int getDisappears() {
    return 0;
  }

  /**
   * Set when this action should last appear within an automation
   *
   * @param disappears last appearance of this automation, as an int.
   * @throws IllegalStateException if {@param disappears} is less than 0.
   */
  @Override
  public void setDisappears(int disappears) throws IllegalArgumentException {

  }

  /**
   * Get the position of the associated {@link IShape} during this automation.
   *
   * @return the coordinates of the {@link IShape} during this specific action, as a
   * {@link Point2D}.
   */
  @Override
  public Point2D getPoint2D() {
    return null;
  }

  /**
   * Set the location of the associated {@link IShape} during this automation.
   *
   * @param point the coordinates of the {@link IShape}, as a {@link Point2D}.
   */
  @Override
  public void setPoint2D(Point2D point) {

  }

  /**
   * Get the color of the associated {@link IShape} during the automation.
   *
   * @return the color of the associated {@link IShape} as a {@link Color}.
   */
  @Override
  public Color getColor() {
    return null;
  }

  /**
   * Set the color of the associated {@link IShape} during this automation.
   *
   * @param color the coordinates of the {@link IShape} as a {@link Color}.
   */
  @Override
  public void setColor(Color color) {

  }

  /**
   * Get the scaled size of the {@link IShape} during this automation.
   *
   * @return the scaled size of the associated {@link IShape} during this automation as a double.
   */
  @Override
  public double getSize() {
    return 0;
  }

  /**
   * Set the scaled size of the {@link IShape} during this automation.  Default size is 1.0, size
   * greater than this will increase shape's scale, and soze lower than 1.0 will decrease the
   * scale.
   *
   * @param size the scaling factor of the associated {@link IShape} as a double.
   */
  @Override
  public void setSize(double size) {

  }
}
