package model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The Animation Interface allows access to a series of Animation objects that can be read in order
 * to describe the automation and movement of another object throughout an automation visual.
 *
 * @param <T> Support action objects, including {@link Point2D} to describe movement, {@link Color}
 *            to describe a color change, and {@link Size} to describe a change in the objects
 *            physical dimensions.
 */
public interface IAnimation<T> {

  /**
   * Adds a new animation to the beginning of the animation series. All existing animations will be
   * moved by the {@param intervalLength} backwards in the series.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param to    The ending object of this animation.
   */
  public void addAnimationFront(int start, int end, T to);

  /**
   * Adds a new animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param to             the ending object of this animation.
   */
  public void addAnimationBack(int intervalLength, T to);

  /**
   * Adds a new animation to the middle of the animation series.  This method is intended to add an
   * animation exactly where another automation already begins. Existing animations that begin after
   * this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start the time this interval should start.
   * @param end   the time this interval should end.
   * @param to    the desired state of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0.
   * @throws NoSuchElementException   if there is no action with a start value matching
   *                                  {@param start}.
   */
  public void addAnimation(int start, int end, T to)
      throws IllegalArgumentException, NoSuchElementException;

  /**
   * Remove the first animation in this series.  All other animations will be shifted forward by the
   * interval contained by this removed value.
   */
  public void removeAnimationFront();

  /**
   * Remove the final animation in this series.  All other animation will not be affected by this
   * method.
   */
  public void removeAnimationBack();

  /**
   * Remove an animation at a specific {@param index}.  Note that we begin indexing at 0, e.g. the
   * first animation is 0, the second animation is 1, etc.
   *
   * @param index animation to be removed from the series.
   * @throws IndexOutOfBoundsException if the {@param index} provided does not exist in the current
   *                                   animation series.
   */
  public void removeAnimationAtIndex(int index) throws IndexOutOfBoundsException;

  /**
   * Remove an animation at a specific {@param start} value.  All animations proceeding this
   * animation will be moved forward by the length of the removed Animation.
   *
   * @param start specific start value to be removed.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  public void removeAnimationAtStart(int start) throws NoSuchElementException;

  /**
   * Get the number of animation objects currently stored in this series.
   *
   * @return the number of animation objects, as an integer.
   */
  public int getNumberOfAnimations();

  /**
   * Retrieve the Action at a given {@param index}. Note that we begin indexing at 0, e.g. the first
   * animation is 0, the second animation is 1, etc.
   *
   * @param index animation to be retrieved by the method.
   * @return an {@link IAction} object representing what happens during this Animation.
   * @throws IndexOutOfBoundsException if the provided {@param index} does not exist in the current
   *                                   series.
   */
  public IAction<T> getActionAtIndex(int index) throws IndexOutOfBoundsException;

  /**
   * Retrieve the animation at a specific {@param start} value.
   *
   * @param start specific start value to be retrieved.
   * @return an {@link IAction} object representing what happens during this Animation.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  public IAction<T> getActionAtStart(int start) throws NoSuchElementException;

  /**
   * Return a String description of each Action contained in the series.
   *
   * @return a list of String description for each Action contained in the series.
   */
  public List<String> playTextDescription();

}
