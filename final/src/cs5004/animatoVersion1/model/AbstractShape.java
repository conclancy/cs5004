package cs5004.animatoVersion1.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Abstract implementation of the IShape class containing all of the common methods shared between
 * shape objects.
 */
public abstract class AbstractShape implements IShape {

  protected Point2D reference;
  protected Color color;
  protected final IAnimation<Object> animation;
  protected final String name;

  /**
   * Constructor for an AbstractShape objects.
   *
   * @param reference the reference point for the shape, as a {@link Point2D}.
   * @param color     the color of the shape, as a {@link Color}.
   */
  public AbstractShape(Point2D reference, Color color) {
    this.reference = reference;
    this.color = color;
    this.animation = new Animation<Object>();
    this.name = null;
  }

  /**
   * Constructor for an AbstractShape with a name.
   *
   * @param name      the name of the shape.
   * @param reference the reference point for the shape, as a {@link Point2D}.
   * @param color     the color of the shape, as a {@link Color}.
   */
  public AbstractShape(String name, Point2D reference, Color color) {
    this.reference = reference;
    this.color = color;
    this.animation = new Animation<Object>();
    this.name = name;
  }

  /**
   * Get the distance between the shape and the origin - i.e. {@link Point2D} (0, 0).
   *
   * @return distance between the shape and the origin, as a double.
   */
  @Override
  public double distanceToOrigin() {
    return reference.distToOrigin();
  }

  /**
   * Compare two shape objects to determine which is larger.
   *
   * @param other the object to be compared.
   * @return an -1 if the shape is smaller, 1 if the shape is larger, and 0 if they are the same.
   */
  @Override
  public int compareTo(IShape other) {
    return Double.compare(this.area(), other.area());
  }

  /**
   * Get the color of the this {@link IShape}.
   *
   * @return the {@link Color} object of the this {@link IShape}.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Get the reference point for this {@link IShape}.
   *
   * @return the reference point of the shape, as a {@link Point2D} object.
   */
  @Override
  public Point2D getReference() {
    return this.reference;
  }

  /**
   * Adds a new Color animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param color The color for this object to start the automation with.
   */
  @Override
  public void addAutomationFrontColor(int start, int end, Color color) {
    this.animation.addAnimationFront(start, end, color);
  }

  /**
   * Adds a new Size animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param size  The size for this object to start the automation with.
   */
  @Override
  public void addAutomationFrontSize(int start, int end, Size size) {
    this.animation.addAnimationFront(start, end, size);
  }

  /**
   * Adds a new Movement animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start     the animation will take place from this value until {@param end} value.
   * @param end       the animation will end at this value. Existing animations will be pushed back
   *                  from their current start point to occur after this value.
   * @param reference The size for this object to start the automation with.
   */
  @Override
  public void addAutomationFrontMovement(int start, int end, Point2D reference) {
    this.animation.addAnimationFront(start, end, reference);
  }

  /**
   * Adds a new Color animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param color          the ending object of this animation.
   */
  @Override
  public void addAnimationBackColor(int intervalLength, Color color) {
    this.animation.addAnimationBack(intervalLength, color);
  }

  /**
   * Adds a new Size animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param size           the ending object of this animation.
   */
  @Override
  public void addAnimationBackSize(int intervalLength, Size size) {
    this.animation.addAnimationBack(intervalLength, size);
  }

  /**
   * Adds a new Movement animation to the end of the animation series.  Existing animations will not
   * be changed.
   *
   * @param intervalLength the length of this automation.
   * @param reference      the ending object of this animation.
   */
  @Override
  public void addAnimationBackMovement(int intervalLength, Point2D reference) {
    this.animation.addAnimationBack(intervalLength, reference);
  }

  /**
   * Adds a new animation to the middle of the animation series.  This method is intended to add an
   * animation exactly where another automation already begins. Existing animations that begin after
   * this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start the time this interval should start.
   * @param end   the time this interval should end.
   * @param color the desired {@link Color} of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0.
   * @throws NoSuchElementException   if there is no action with a start value matching
   *                                  {@param start}.
   */
  @Override
  public void addAnimationColor(int start, int end, Color color)
      throws IllegalArgumentException, NoSuchElementException {
    this.animation.addAnimation(start, end, color);
  }

  /**
   * Adds a new animation to the middle of the animation series.  This method is intended to add an
   * animation exactly where another automation already begins. Existing animations that begin after
   * this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start the time this interval should start.
   * @param end   the time this interval should end.
   * @param size  the desired {@link Size} of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0.
   * @throws NoSuchElementException   if there is no action with a start value matching
   *                                  {@param start}.
   */
  @Override
  public void addAnimationSize(int start, int end, Size size)
      throws IllegalArgumentException, NoSuchElementException {
    this.animation.addAnimation(start, end, size);
  }

  /**
   * Adds a new animation to the middle of the animation series.  This method is intended to add an
   * animation exactly where another automation already begins. Existing animations that begin after
   * this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start     the time this interval should start.
   * @param end       the time this interval should end.
   * @param reference the desired {@link Point2D} of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0.
   * @throws NoSuchElementException   if there is no action with a start value matching
   *                                  {@param start}.
   */
  @Override
  public void addAnimationMovement(int start, int end, Point2D reference)
      throws IllegalArgumentException, NoSuchElementException {
    this.animation.addAnimation(start, end, reference);
  }

  /**
   * Remove the first animation in this series.  All other animations will be shifted forward by the
   * interval contained by this removed value.
   */
  @Override
  public void removeAnimationFront() {
    this.animation.removeAnimationFront();
  }

  /**
   * Remove the final animation in this series.  All other animation will not be affected by this
   * method.
   */
  @Override
  public void removeAnimationBack() {
    this.animation.removeAnimationBack();
  }

  /**
   * Remove an animation at a specific {@param index}.  Note that we begin indexing at 0, e.g. the
   * first animation is 0, the second animation is 1, etc.
   *
   * @param index animation to be removed from the series.
   * @throws IndexOutOfBoundsException if the {@param index} provided does not exist in the current
   *                                   animation series.
   */
  @Override
  public void removeAnimationAtIndex(int index) throws IndexOutOfBoundsException {
    this.animation.removeAnimationAtIndex(index);
  }

  /**
   * Remove an animation at a specific {@param start} value.  All animations proceeding this
   * animation will be moved forward by the length of the removed Animation.
   *
   * @param start specific start value to be removed.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                value.
   */
  @Override
  public void removeAnimationAtStart(int start) throws NoSuchElementException {
    this.animation.removeAnimationAtStart(start);
  }

  /**
   * Get the number of animation objects currently stored in this series.
   *
   * @return the number of animation objects, as an integer.
   */
  @Override
  public int getNumberOfAnimations() {
    return this.animation.getNumberOfAnimations();
  }

  /**
   * Return a String description of each Action contained in the series.
   *
   * @return a list of String description for each Action contained in the series.
   */
  @Override
  public List<String> playTextDescription() {
    return this.animation.playTextDescription();
  }

  /**
   * Get the name of this shape.
   *
   * @return the name of the shape, as a String.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Checks to make sure numbers that are passed are positive.
   *
   * @param n the number to check, as a double.
   * @return the number
   */
  protected double positiveIntChecker(double n) throws IllegalArgumentException {
    if (n <= 0) {
      throw new IllegalArgumentException("Dimensions must be greater than 0.");
    } else {
      return n;
    }
  }

  /**
   * Get the length of the animation as an int.  This is the last `end` value of the automation.
   *
   * @return the length of the automation, as an int.
   */
  @Override
  public int getAnimationLength() {
    return this.animation.getAnimationLength();
  }
}
