package cs5004.animator.model;

import java.awt.Color;

/**
 * Interface for Animations that can be paired with a {@link IShape}.
 */
public interface IAnimation {

  /**
   * Get the starting tick of the Animation
   *
   * @return the starting tick of the Animation, as an int.
   */
  int getStartTick();

  /**
   * Get the last tick of an Animation
   *
   * @return the ending tick of the Animation, as an int.
   */
  int getEndTick();

  /**
   * Get the type of shape contained within the Animation.
   *
   * @return the type of the shape, as a String.
   */
  String getType();

  /**
   * Get the starting x coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting x coordinate, as an int.
   */
  int getStartX();

  /**
   * Get the starting y coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting y coordinate, as an int.
   */
  int getStartY();

  /**
   * Get the starting width of the {@link IShape} in the Animation.
   *
   * @return the starting width, as an int.
   */
  int getStartWidth();

  /**
   * Get the starting height of the {@link IShape} in the Animation.
   *
   * @return the starting width, as an int.
   */
  int getStartHeight();

  /**
   * Get the starting {@link Color} of the {@link IShape} in the Animation.
   *
   * @return the starting color of the shape, as an {@link Color} object.
   */
  Color getStartColor();

  /**
   * Get the starting x coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting x coordinate, as an int.
   */
  int getEndX();

  /**
   * Get the starting y coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting y coordinate, as an int.
   */
  int getEndY();

  /**
   * Get the ending width of the {@link IShape} in the Animation.
   *
   * @return the ending width, as an int.
   */
  int getEndWidth();

  /**
   * Get the ending height of the {@link IShape} in the Animation.
   *
   * @return the ending height, as an int.
   */
  int getEndHeight();

  /**
   * Get the ending {@link Color} of the {@link IShape} in the Animation.
   *
   * @return the ending color of the shape, as an {@link Color} object.
   */
  Color getEndColor();

  /**
   * Get the state of the associated {@link IShape} at a given tick.
   *
   * @param tick  is the tick to mutate the shape to the state.
   * @param shape the shape of the object before the process starts.
   * @throws IllegalArgumentException if the tick value is larger than the ending tick value.
   */
  void setState(int tick, IShape shape) throws IllegalArgumentException;

  /**
   * Get the starting degree of rotation of the associated {@link IShape}.
   *
   * @return the degree of rotation of the {@link IShape}, as an int.
   */
  int getStartRotationDegree();

  /**
   * Get the ending degree of rotation of the associated {@link IShape}.
   *
   * @return the ending of rotation of the {@link IShape}, as an int.
   */
  int getEndRotationDegree();

  /**
   * Combine two animation objects into one.
   *
   * @param other the other animation to be added.
   * @return a new animation, as an {@link IAnimation}
   */
  IAnimation combine(IAnimation other);
}
