package model;

import com.sun.jdi.InvalidTypeException;

/**
 * Object representing an action of a shape object at a specific point.
 */
public interface IAction<T> {

  /**
   * Provides the type of action that an automation will preform while reading this action.
   *
   * @return the {@link EActionType} of this action.
   */
  EActionType getType();

  /**
   * Provide the starting interval for this action.
   *
   * @return starting interval, as an int.
   */
  int getStart();

  /**
   * Provide the ending interval for this action.
   *
   * @return ending interval, as an int.
   */
  int getEnd();

  /**
   * Provide the ending state of the action.
   *
   * @return ending state, relating to {@link EActionType}.
   */
  T getTo();

  /**
   * Modify the starting interval for an action.
   *
   * @param start beginning of the interval, as an int.
   * @throws IllegalArgumentException if the start is not less than the end value.
   */
  void setStart(int start) throws IllegalArgumentException;

  /**
   * Modify the ending interval for the action.
   *
   * @param end the end of the interval, as an int.
   * @throws IllegalArgumentException if the start is not less than the end value.
   */
  void setEnd(int end) throws IllegalArgumentException;

  /**
   * Modify the type of action performed during this action.
   *
   * @param to the ending state of the action
   * @throws InvalidTypeException if the from and to values do not match.
   */
  void setTo(T to) throws InvalidTypeException;
}
