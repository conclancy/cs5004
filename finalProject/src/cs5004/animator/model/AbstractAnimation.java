package cs5004.animator.model;

/**
 * Abstraction of the animation class.
 */
public abstract class AbstractAnimation implements IAnimation {

  protected int startTick;
  protected int endTick;
  protected IShape shape;

  /**
   * Calculates the `tween` value for the automation.
   *
   * @param currentTime the current time within the animation.
   * @param startTime   the starting time.
   * @param endTime     the ending time.
   * @param startValue  the starting value.
   * @param endValue    the ending value.
   * @return the `tween` value.
   */
  protected static int tween(int currentTime, int startTime, int endTime, int startValue,
      int endValue) {
    double totalTime = (double)endTime - (double)startTime;
    return (int) (startValue * ((endTime - currentTime) / totalTime) + endValue * ((currentTime - startTime) / totalTime));
  }

  public AbstractAnimation(int startTick, int endTick) {
    this.startTick = startTick;
    this.endTick = endTick;
  }

  /**
   * Get the starting tick for this automation.
   *
   * @return the starting tick, as an int.
   */
  @Override
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Get the end tick for this automation.
   *
   * @return the ending tick, as an int.
   */
  @Override
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * Get the {@link IShape} for this animation.
   *
   * @return the {@link IShape} for this animation.
   */
  @Override
  public IShape getShape() {
    return this.shape;
  }

  /**
   * Check to ensure a valid Tick value.
   *
   * @param i the integer to check.
   * @return the valid integer.
   * @throws IllegalArgumentException if the tick value is less than 0.
   */
  protected int checkValidTick(int i) throws IllegalArgumentException {
    if(i < 0) {
      throw new IllegalArgumentException("Tick value must be greater than or equal to 0.");
    }

    return i;
  }

}
