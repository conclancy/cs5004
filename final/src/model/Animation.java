package model;

import java.util.ArrayList;
import java.util.List;

public class Animation<T> implements IAnimation<T> {

  private List<IAction<T>> actions;

  /**
   * Constructor for the Animation object that initializes the Animation with no predefined
   * actions.
   */
  public Animation() {
    this.actions = new ArrayList<IAction<T>>();
  }

  /**
   * Adds a new animation to the beginning of the animation series. All existing animations will be
   * moved by the {@param intervalLength} backwards in the series.
   *
   * @param start the animation will take place from this value until {@param end} value.
   * @param end   the animation will end at this value. Existing animations will be pushed back from
   *              their current start point to occur after this value.
   * @param to    The ending object of this animation.
   */
  @Override
  public void addAnimationFront(int start, int end, T to) {

    // If the actions list is empty, just append the provided action.
    if (this.actions.size() > 0) {
      int currentStart = this.actions.get(0).getStart();

      if (currentStart <= end) {
        int updatedStart = end + 1;

        for (IAction<T> a : this.actions) {
          int aInterval = a.getEnd() - a.getStart();
          a.setEnd(updatedStart + aInterval);
          a.setStart(updatedStart);
          updatedStart = a.getEnd() + 1;
        }
      }
    }

    this.actions.add(0, new Action<T>(start, end, to));
  }

  /**
   * Adds a new animation to the end of the animation series.  Existing animations will not be
   * changed.
   *
   * @param intervalLength the length of this automation.
   * @param to             the ending object of this animation.
   */
  @Override
  public void addAnimationBack(int intervalLength, T to) {

  }

  /**
   * Adds a new animation to the middle of the animation series.  Existing animations that begin
   * after this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start the time this interval should start.
   * @param end   the time this interval should end.
   * @param to    the desired state of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0.
   */
  @Override
  public void addAnimation(int start, int end, T to) throws IllegalArgumentException {

  }

  /**
   * Remove the first animation in this series.  All other animations will be shifted forward by the
   * interval contained by this removed value.
   */
  @Override
  public void removeAnimationFront() {

  }

  /**
   * Remove the final animation in this series.  All other animation will not be affected by this
   * method.
   */
  @Override
  public void removeAnimationBack() {

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

  }

  /**
   * Remove an animation at a specific {@param start} value.  All animations proceeding this
   * animation will be moved forward by the length of the removed Animation.
   *
   * @param start specific start value to be removed.
   * @throws IllegalArgumentException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  @Override
  public void removeAnimationAtStart(int start) throws IllegalArgumentException {

  }

  /**
   * Get the number of animation objects currently stored in this series.
   *
   * @return the number of animation objects, as an integer.
   */
  @Override
  public int getNumberOfAnimations() {
    return 0;
  }

  /**
   * Retrieve the Action at a given {@param index}. Note that we begin indexing at 0, e.g. the first
   * animation is 0, the second animation is 1, etc.
   *
   * @param index animation to be retrieved by the method.
   * @return an {@link IAction} object representing what happens during this Animation.
   * @throws IndexOutOfBoundsException if the provided {@param index} does not exist in the current
   *                                   series.
   */
  @Override
  public IAction<T> getActionAtIndex(int index) throws IndexOutOfBoundsException {
    return null;
  }

  /**
   * Retrieve the animation at a specific {@param start} value.
   *
   * @param start specific start value to be retrieved.
   * @return an {@link IAction} object representing what happens during this Animation.
   * @throws IllegalArgumentException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  @Override
  public IAction<T> getActionAtStart(int start) throws IllegalArgumentException {
    return null;
  }

  /**
   * Return a String description of each Action contained in the series.
   *
   * @return a list of String description for each Action contained in the series.
   */
  @Override
  public List<String> playTextDescription() {

    List<String> playBack = new ArrayList<>();

    for (IAction<T> action : this.actions) {
      EActionType type = action.getType();
      String line;

      if (type == EActionType.COLOR) {
        line = "changes color to ";
      } else if (type == EActionType.MOVE) {
        line = "moves to ";
      } else if (type == EActionType.SIZE) {
        line = "scales to ";
      } else {
        line = "unknown action to ";
      }

      playBack.add(line + action.getTo().toString() + " from t=" + action.getStart() + " to t="
          + action.getEnd());
    }

    return playBack;
  }
}
