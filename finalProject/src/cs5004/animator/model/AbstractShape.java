package cs5004.animator.model;

/**
 * Abstraction of the shape object.
 */
public abstract class AbstractShape implements IShape {

  protected Point2D reference;
  protected Color color;
  protected String name;
  protected int width;
  protected int height;
  protected int appearsAt;
  protected int disappearsAt;

  protected boolean visible;

  /**
   * Constructor for a shape.  This constructor assumes we know most of the details about the
   * shape.
   *
   * @param reference    the cartesian coordinates of the shape, as a {@link Point2D} object.
   * @param color        the RGB color of this object, as a {@link Color} object.
   * @param name         the name of the shape, as a String.
   * @param width        the distance the shape traverses along the X axis, as an int.
   * @param height       the distance the shape traverses along the Y axis, as an int.
   * @param appearsAt    the tick mark at which the shape appears, as an int.
   * @param disappearsAt the tick mark at which the shape disappears, as an int.
   * @throws IllegalArgumentException if the width, height, appearsAt, or disappearsAt values are a
   *                                  negative number.
   */
  public AbstractShape(Point2D reference, Color color, String name, int width, int height,
      int appearsAt, int disappearsAt) throws IllegalArgumentException {
    this.reference = reference;
    this.color = color;
    this.name = name;
    this.width = this.positiveIntChecker(width);
    this.height = this.positiveIntChecker(height);
    this.appearsAt = this.positiveIntChecker(appearsAt);
    this.disappearsAt = this.positiveIntChecker(disappearsAt);
    this.visible = false;
  }

  /**
   * Constructor for a shape.  This constructor assumes we only know the name and the type of the
   * shape desired.
   *
   * @param name the name of the shape, as a String.
   */
  public AbstractShape(String name) {
    this.name = name;
  }

  /**
   * Set the tick mark at which the shape appears.
   *
   * @param tick the tick mark at which the shape appears, as an int.
   * @throws IllegalArgumentException if the tick value is a negative number.
   */
  @Override
  public void setAppearsAt(int tick) throws IllegalArgumentException {
    this.appearsAt = this.positiveIntChecker(tick);
  }

  /**
   * Set the tick mark at which the shape disappears.
   *
   * @param tick the tick mark at which the shape disappears, as an int.
   * @throws IllegalArgumentException if the appears value is a negative number.
   */
  @Override
  public void setDisappearsAt(int tick) throws IllegalArgumentException {
    this.disappearsAt = this.positiveIntChecker(tick);
  }

  /**
   * Set the reference position of the shape.
   *
   * @param reference the cartesian coordinates of the shape, as a {@link Point2D} object.
   */
  @Override
  public void setReference(Point2D reference) {
    this.reference = reference;
  }

  /**
   * Set the reference position of the shape.
   *
   * @param x the X coordinate for the shape.
   * @param y the Y coordinate for the shape.
   */
  @Override
  public void setReference(int x, int y) {
    this.reference = new Point2D(x, y);
  }

  /**
   * Set the color of the object.
   *
   * @param color the RGB color the shape should change to, as a {@link Color} object.
   */
  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Set the color of the shape.
   *
   * @param red the amount of red light in the color.
   * @param green the amount of green light in the color.
   * @param blue the amount of blue light in the color.
   */
  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);
  }

  /**
   * Get the cartesian coordinates of the shape.
   *
   * @return the cartesian coordinates of the shape, as a {@link Point2D} object.
   */
  @Override
  public Point2D getReference() {
    return this.reference;
  }

  /**
   * Set the width of the shape.
   *
   * @param width the distance the shape traverses along the X axis, as an int.
   */
  @Override
  public void setWidth(int width) throws IllegalArgumentException {
    this.width = positiveIntChecker(width);
  }

  /**
   * Set the height of the shape.
   *
   * @param height the distance the shape traverses along the Y axis, as an int.
   */
  @Override
  public void setHeight(int height) {
    this.height = positiveIntChecker(height);
  }

  /**
   * Get the color of the shape.
   *
   * @return the RGB color of this object, as a {@link Color} object.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Return's the shape's name.
   *
   * @return the name of the shape, as a String.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the width of the shape.  This is the distance the shape traverses along the X axis of the
   * cartesian plane.
   *
   * @return the width of the shape, as an int.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of the shape. This is the distance the shape traverses along the Y axis of the
   * cartesian plane.
   *
   * @return the height of the shape, as an int.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the tick mark at which the shape appears.
   *
   * @return the tick mark at which the shape appears, as an int.
   */
  @Override
  public int getAppearsAt() {
    return this.appearsAt;
  }

  /**
   * Get the tick mark at which the shape disappears.
   *
   * @return the tick mark at which the shape disappears, as an int.
   */
  @Override
  public int getDisappearsAt() {
    return this.disappearsAt;
  }

  /**
   * Causes the shape to appear.
   */
  @Override
  public void show() {
    this.visible = true;
  }

  /**
   * Causes the shape to disappear
   */
  @Override
  public void hide() {
    this.visible = false;
  }

  /**
   * Checks to ensure a tick value is valid, i.e. is greater than 0.
   *
   * @param i the value to check, as an int.
   * @return a valid tick value, as an int.
   * @throws IllegalArgumentException if the integer value is a negative number.
   */
  private int positiveIntChecker(int i) throws IllegalArgumentException {

    if (i < 0) {
      throw new IllegalArgumentException("The tick value must be greater than or equal to 0");
    } else {
      return i;
    }
  }
}
