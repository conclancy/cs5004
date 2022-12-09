package cs5004.animator.model;

/**
 * Interface representing an Animation for a shape object.
 */
public interface IAnimation {

  /**
   * Get the starting tick for this automation.
   *
   * @return the starting tick, as an int.
   */
  int getStartTick();

  /**
   * Get the end tick for this automation.
   *
   * @return the ending tick, as an int.
   */
  int getEndTick();

  /**
   * Set the {@link IShape} of the animation.
   *
   * @param shape the {@link IShape} for the automation.
   */
  void setShape(IShape shape);

  /**
   * Get the {@link IShape} for this animation.
   *
   * @return the {@link IShape} for this animation.
   */
  IShape getShape();

  /**
   * Set the interval of an {@link IShape} by a tick value.
   *
   * @param shape the {@link IShape} for the animation.
   * @param tick the length of the animation, as an int.
   * @throws IllegalArgumentException if the tick is a negative number.
   */
  void setTickInterval(IShape shape, int tick) throws IllegalArgumentException;
}
