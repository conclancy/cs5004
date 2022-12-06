package model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a shape object.  One instance of this object represents a single shape within the
 * automation.
 */
public interface IShape extends Comparable<IShape> {

  /**
   * Get the distance between the shape and the origin - i.e. {@link Point2D} (0, 0).
   *
   * @return distance between the shape and the origin, as a double.
   */
  double distanceToOrigin();

  /**
   * Get the area of the shape.
   *
   * @return area of the shape, as a double.
   */
  double area();

  /**
   * Get the perimeter of the shape.
   *
   * @return perimeter of the shape, as a double.
   */
  double perimeter();

  /**
   * Scale the shape.  {@param size} larger than 1.0 will make the shape larger, and {@param size}
   * smaller than 1.0 will decrease the size of the shape.
   *
   * @param size of the new scaled object.
   * @return A new {@link IShape} with the appropriate scaled dimensions.
   * @throws IllegalArgumentException thrown when {@param size} is less than or equal to 0.
   */
  IShape resize(double size) throws IllegalArgumentException;

  /**
   * Create a duplicate of this shape.
   *
   * @return an exact replica of this shape, as an {@link IShape}.
   */
  IShape copy();

  /**
   * Compare two shape objects to determine which is larger.
   *
   * @param other the object to be compared.
   * @return an -1 if the shape is smaller, 1 if the shape is larger, and 0 if they are the same.
   */
  int compareTo(IShape other);

  /**
   * Get the color of the this {@link IShape}.
   *
   * @return the {@link Color} object of the this {@link IShape}.
   */
  Color getColor();

  /**
   * Get the reference point for this {@link IShape}.
   *
   * @return the reference point of the shape, as a {@link Point2D} object.
   */
  Point2D getReference();

  // TODO update with IAnimation methods

  /**
   * Adds a new Color animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param color The color for this object to start the automation with.
   */
  void addAutomationFrontColor(int start, int end, Color color);

  /**
   * Adds a new Size animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param size  The size for this object to start the automation with.
   */
  void addAutomationFrontSize(int start, int end, Size size);

  /**
   * Adds a new Movement animation to the beginning of the animation series. All existing animations
   * will be moved by the interval length between the {@param start} and {@param end}.
   *
   * @param start     the animation will take place from this value until {@param end} value.
   * @param end       the animation will end at this value. Existing animations will be pushed back
   *                  from their current start point to occur after this value.
   * @param reference The size for this object to start the automation with.
   */
  void addAutomationFrontMovement(int start, int end, Point2D reference);

  /**
   * Adds a new Color animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param color          the ending object of this animation.
   */
  void addAnimationBackColor(int intervalLength, Color color);

  /**
   * Adds a new Size animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param size           the ending object of this animation.
   */
  void addAnimationBackSize(int intervalLength, Size size);

  /**
   * Adds a new Movement animation to the end of the animation series.  Existing animations will not
   * be changed.
   *
   * @param intervalLength the length of this automation.
   * @param reference      the ending object of this animation.
   */
  void addAnimationBackMovement(int intervalLength, Point2D reference);

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
  void addAnimationColor(int start, int end, Color color)
      throws IllegalArgumentException, NoSuchElementException;

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
  void addAnimationSize(int start, int end, Size size)
      throws IllegalArgumentException, NoSuchElementException;

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
  void addAnimationMovement(int start, int end, Point2D reference)
      throws IllegalArgumentException, NoSuchElementException;

  /**
   * Remove the first animation in this series.  All other animations will be shifted forward by the
   * interval contained by this removed value.
   */
  void removeAnimationFront();

  /**
   * Remove the final animation in this series.  All other animation will not be affected by this
   * method.
   */
  void removeAnimationBack();

  /**
   * Remove an animation at a specific {@param index}.  Note that we begin indexing at 0, e.g. the
   * first animation is 0, the second animation is 1, etc.
   *
   * @param index animation to be removed from the series.
   * @throws IndexOutOfBoundsException if the {@param index} provided does not exist in the current
   *                                   animation series.
   */
  void removeAnimationAtIndex(int index) throws IndexOutOfBoundsException;

  /**
   * Remove an animation at a specific {@param start} value.  All animations proceeding this
   * animation will be moved forward by the length of the removed Animation.
   *
   * @param start specific start value to be removed.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                value.
   */
  void removeAnimationAtStart(int start) throws NoSuchElementException;

  /**
   * Get the number of animation objects currently stored in this series.
   *
   * @return the number of animation objects, as an integer.
   */
  int getNumberOfAnimations();

  /**
   * Return a String description of each Action contained in the series.
   *
   * @return a list of String description for each Action contained in the series.
   */
  List<String> playTextDescription();

  /**
   * Return the shape's name. Shapes are not required to have a name, if the shape does not have a
   * name, this method returns null.
   *
   * @return the name of the shape, as a string.
   */
  String getName();
}
