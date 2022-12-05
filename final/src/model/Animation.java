package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    if (this.actions.size() == 0) {
      this.actions.add(new Action<T>(0, intervalLength, to));
    } else {
      int currentEnd = this.actions.get(this.actions.size() - 1).getEnd();
      this.actions.add(new Action<T>(currentEnd + 1, currentEnd + intervalLength, to));
    }
  }

  /**
   * Adds a new animation to the middle of the animation series.  This method is intended to add an
   * animation exactly where another automation already begins. Existing animations that begin after
   * this animation new start point will be pushed back by the difference in value between the
   * {@param start} and the {@param end} values. Preceding actions will not be affected.
   *
   * @param start the time this interval should start.
   * @param end   the time this interval should end.
   * @param to    the desired state of this automation.
   * @throws IllegalArgumentException if the {@param start} value is less than 0 or greater than the
   *                                  end value.
   * @throws NoSuchElementException   if there is no action with a start value matching
   *                                  {@param start}.
   */
  @Override
  public void addAnimation(int start, int end, T to)
      throws IllegalArgumentException, NoSuchElementException {

    // Instantiate new action immediately to see if an IllegalArgumentException is thrown.
    Action<T> newAction = new Action<T>(start, end, to);

    // Find the index where the new action should be inserted.
    int index = -1;

    index = this.findStartIndex(start);

    // Shift the end and start values for proceeding elements.
    int interval = end - start + 1;

    for (int j = index; j < this.actions.size(); j++) {
      this.actions.get(j).setEnd(this.actions.get(j).getEnd() + interval);
      this.actions.get(j).setStart(this.actions.get(j).getStart() + interval);
    }

    // Add the new action to the actions list.
    this.actions.add(index, newAction);

  }

  /**
   * Remove the first animation in this series.  All other animations will be shifted forward by the
   * interval contained by this removed value.
   */
  @Override
  public void removeAnimationFront() {
    this.actions.remove(0);
  }

  /**
   * Remove the final animation in this series.  All other animation will not be affected by this
   * method.
   */
  @Override
  public void removeAnimationBack() {
    this.actions.remove(this.actions.size() - 1);
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
    this.actions.remove(index);
  }

  /**
   * Remove an animation at a specific {@param start} value.  All animations proceeding this
   * animation will be moved forward by the length of the removed Animation.
   *
   * @param start specific start value to be removed.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  @Override
  public void removeAnimationAtStart(int start) throws NoSuchElementException {
    this.actions.remove(this.findStartIndex(start));
  }

  /**
   * Get the number of animation objects currently stored in this series.
   *
   * @return the number of animation objects, as an integer.
   */
  @Override
  public int getNumberOfAnimations() {
    return this.actions.size();
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
    return this.actions.get(index);
  }

  /**
   * Retrieve the animation at a specific {@param start} value.
   *
   * @param start specific start value to be retrieved.
   * @return an {@link IAction} object representing what happens during this Animation.
   * @throws NoSuchElementException if no animations start exactly at the provided {@param start}
   *                                  value.
   */
  @Override
  public IAction<T> getActionAtStart(int start) throws NoSuchElementException {
    return this.actions.get(this.findStartIndex(start));
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

  /**
   * Method for finding the index of an action with a give start value.
   *
   * @param start the start value we are looking to find.
   * @return the index of the start value, as an int.
   * @throws NoSuchElementException if the passed start value does not correspond with an index
   *                                value in the list.
   */
  private int findStartIndex(int start) throws NoSuchElementException {
    for (int i = 0; i < this.actions.size(); i++) {
      if (this.actions.get(i).getStart() == start) {
        return i;
      }
    }

    throw new NoSuchElementException(
        "No action exists with the given start value.  The start value passed must match an "
            + "existing start value");
  }
}
