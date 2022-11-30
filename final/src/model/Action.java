package model;

/**
 * This class represents a shape's action. Each object represents the state of the shape from the
 * appears int until the disappears int.
 */
public class Action implements IAction {

  private int appears;
  private int disappears;
  private final Point2D reference;
  private Color color;
  private double size;

  /**
   * Constructor for the action class.
   *
   * @param appears    the time at which the action appears.
   * @param disappears the time at which the action disappears.
   * @param x          the x coordinate of the shape while this action is taking place.
   * @param y          the y coordinate of the shape while this action is taking place.
   * @param color      the color of the shape during this action.
   * @throws IllegalArgumentException is thrown if the user inputs a {@param disappears} time that
   *                                  occurs before the {@param appears} time.
   */
  public Action(int appears, int disappears, int x, int y, Color color)
      throws IllegalArgumentException {

    if (appears >= disappears) {
      throw new IllegalArgumentException("The action cannot disappear before it appears.");
    }

    this.appears = appears;
    this.disappears = disappears;
    this.reference = new Point2D(x, y);
    this.color = color;
    this.size = 1;
  }

  /**
   * Constructor for the action class.
   *
   * @param appears    the time at which the action appears.
   * @param disappears the time at which the action disappears.
   * @param x          the x coordinate of the shape while this action is taking place.
   * @param y          the y coordinate of the shape while this action is taking place.
   * @param size       the size to scale the shape effected by the action.
   * @param color      the color of the shape during this action.
   * @throws IllegalArgumentException is thrown if the user inputs a {@param disappears} time that
   *                                  occurs before the {@param appears} time.
   */
  public Action(int appears, int disappears, int x, int y, double size, Color color)
      throws IllegalArgumentException {

    if (appears >= disappears) {
      throw new IllegalArgumentException("The action cannot disappear before it appears.");
    }

    this.appears = appears;
    this.disappears = disappears;
    this.reference = new Point2D(x, y);
    this.color = color;
    this.size = size;
  }

  /**
   * Show when this action first appears within the automation.
   *
   * @return the first appears of the automation, as an int.
   */
  @Override
  public int getAppears() {
    return this.appears;
  }

  /**
   * Set when this action should first appear within an automation.
   *
   * @param appears first appearance of this automation, as an int.
   * @throws IllegalStateException if {@param appears} is less than 0.
   */
  @Override
  public void setAppears(int appears) throws IllegalArgumentException {
    if (appears < 0 || appears >= this.disappears) {
      throw new IllegalArgumentException(
          "Appears cannot be less than 0 or greater than the disappears value for this action.");
    } else {
      this.appears = appears;
    }
  }

  /**
   * Show when this action last appears within the automation.
   *
   * @return the last appears of the automation, as an int.
   */
  @Override
  public int getDisappears() {
    return this.disappears;
  }

  /**
   * Set when this action should last appear within an automation.
   *
   * @param disappears last appearance of this automation, as an int.
   * @throws IllegalStateException if {@param disappears} is less than 0.
   */
  @Override
  public void setDisappears(int disappears) throws IllegalArgumentException {

    if (disappears < 1 || disappears <= this.appears) {
      throw new IllegalArgumentException(
          "Disappears cannot be less than 1 or less than the appears value for this action.");
    } else {
      this.disappears = disappears;
    }
  }

  /**
   * Get the position of the associated {@link IShape} during this automation.
   *
   * @return the coordinates of the {@link IShape} during this specific action, as a
   * {@link Point2D}.
   */
  @Override
  public Point2D getPoint2D() {
    return this.reference;
  }

  /**
   * Set the location of the associated {@link IShape} during this automation.
   *
   * @param x the horizontal coordinate of the {@link IShape}, as an int.
   * @param y the vertical coordinate of the {@link IShape}, as an int.
   */
  @Override
  public void setPoint2D(int x, int y) {
    this.reference.setX(x);
    this.reference.setY(y);
  }

  /**
   * Get the color of the associated {@link IShape} during the automation.
   *
   * @return the color of the associated {@link IShape} as a {@link Color}.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Set the color of the associated {@link IShape} during this automation.
   *
   * @param color the coordinates of the {@link IShape} as a {@link Color}.
   */
  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get the scaled size of the {@link IShape} during this automation.
   *
   * @return the scaled size of the associated {@link IShape} during this automation as a double.
   */
  @Override
  public double getSize() {
    return this.size;
  }

  /**
   * Set the scaled size of the {@link IShape} during this automation.  Default size is 1.0, size
   * greater than this will increase shape's scale, and soze lower than 1.0 will decrease the
   * scale.
   *
   * @param size the scaling factor of the associated {@link IShape} as a double.
   * @throws IllegalArgumentException if {@param size} is less than 0.
   */
  @Override
  public void setSize(double size) throws IllegalArgumentException {

    if (size <= 0) {
      throw new IllegalArgumentException("Cannot scale to 0 or less.");
    }

    this.size = size;

  }
}
