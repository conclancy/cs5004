package model;

/**
 * This class represents a shape's action. Each object represents the state of the shape from the
 * appears int until the disappears int.
 */
public class Action<T> implements IAction<T> {

  private int start;
  private int end;
  private T from;
  private T to;

  /**
   * Constructor for the Action class.
   *
   * @param start the start value of this action, as an int.
   * @param end   the end value of this action, as an int.
   * @param from  the beginning state of the action, as a generic object.
   * @param to    the ending state of the action, as a generic object.
   * @throws IllegalArgumentException The start value must be less than the end value.
   * @throws IllegalStateException     The from and to types must match.
   */
  public Action(int start, int end, T from, T to)
      throws IllegalArgumentException, IllegalStateException {
    this.checkStartEnd(start, end);

    this.start = start;
    this.end = end;
    this.from = from;
    this.to = to;
  }

  /**
   * Checks to ensure the {@param start} value is less than the {@param end} value.
   *
   * @param start value to test, as an int.
   * @param end   value to test, as an int.
   * @throws IllegalArgumentException The start value must be less than the end value.
   */
  private void checkStartEnd(int start, int end) throws IllegalArgumentException {
    if (start >= end) {
      throw new IllegalArgumentException("Action start value must be less than the end value.");
    }
  }

  /**
   * Provides the type of action that an automation will preform while reading this action.
   *
   * @return the {@link EActionType} of this action.
   */
  @Override
  public EActionType getType() {
    if(this.from.getClass().equals(Point2D.class)) {
      return EActionType.MOVE;
    } else if (this.from.getClass().equals(Color.class)) {
      return EActionType.COLOR;
    } else if (this.from.getClass().equals(Size.class)) {
      return EActionType.SIZE;
    }
    return EActionType.UNKNOWN;
  }

  /**
   * Provide the starting interval for this action.
   *
   * @return starting interval, as an int.
   */
  @Override
  public int getStart() {
    return this.start;
  }

  /**
   * Provide the ending interval for this action.
   *
   * @return ending interval, as an int.
   */
  @Override
  public int getEnd() {
    return this.end;
  }

  /**
   * Provide the starting state of the action.
   *
   * @return starting state, relating to {@link EActionType}.
   */
  @Override
  public T getFrom() {
    return this.from;
  }

  /**
   * Provide the ending state of the action.
   *
   * @return ending state, relating to {@link EActionType}.
   */
  @Override
  public T getTo() {
    return this.to;
  }

  /**
   * Modify the starting interval for an action.
   *
   * @param start beginning of the interval, as an int.
   * @throws IllegalArgumentException if the start is not less than the end value.
   */
  @Override
  public void setStart(int start) throws IllegalArgumentException {
    this.checkStartEnd(start, this.end);
    this.start = start;
  }

  /**
   * Modify the ending interval for the action.
   *
   * @param end the end of the interval, as an int.
   * @throws IllegalArgumentException if the start is not less than the end value.
   */
  @Override
  public void setEnd(int end) throws IllegalArgumentException {
    this.checkStartEnd(this.start, end);
    this.end = end;
  }

  /**
   * Modify the type of action performed during this action.
   *
   * @param from the beginning state of the action.
   * @param to   the ending state of the action
   * @throws IllegalStateException if the from and to values do not match.
   */
  @Override
  public void setToFrom(T from, T to) throws IllegalStateException {
    this.from = from;
    this.to = to;
  }
}
