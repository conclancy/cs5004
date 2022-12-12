package cs5004.animator.controller;

import java.awt.Color;

/**
 * Interface for a Frame within the Easy Animation program.
 */
public interface IFrame {

  /**
   * Get the tick where this frame occurs.
   *
   * @return the tick the frame occurs, as an int.
   */
  int getTick();

  /**
   * Get the x coordinate of this frame.
   *
   * @return the X coordinate of this frame, as an int.
   */
  int getX();

  /**
   * Get the y coordinate of this frame.
   *
   * @return the Y coordinate of this frame, as an int.
   */
  int getY();

  /**
   * Get the width of this frame.
   *
   * @return the width of this keyframe, as an int.
   */
  int getWidth();

  /**
   * Get the height of this frame.
   *
   * @return the height of this keyframe, as an int.
   */
  int getHeight();

  /**
   * Get the color of the shape in this frame.
   *
   * @return the {@link Color} of the shape in this frame.
   */
  Color getColor();

  /**
   * Get the rotation of the shape within this frame.
   *
   * @return the degree of rotation of the shape at this frame, as an int.
   */
  int getShapeRotation();
}
