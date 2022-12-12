package cs5004.animator.controller;

import java.awt.Color;

/**
 * This interface adds the functionality of rotating a shape.
 */
public interface IFrame {

  /**
   * This method gets the time of this key frame.
   * @return the time that this key frame is stationed at.
   */
  int getTime();

  /**
   * This method gets the X value of this keyframe.
   * @return the X coordinatePosition for this keyframe.
   */
  int getX();

  /**
   * This method gets the Y value of this key frame.
   * @return the Y coordinatePosition of this keyframe.
   */
  int getY();

  /**
   * This method gets the width.
   * @return the width of this keyframe.
   */
  int getWidth();

  /**
   * This method gets the height.
   * @return the height of this keyframe.
   */
  int getHeight();

  /**
   * This method gets the color.
   * @return the color of this keyframe.
   */
  Color getColor();

  /**
   * Getter for the rotationDegree of this key frame.
   *
   * @return the rotationDegree in degrees of the shape at this frame.
   */
  int getShapeRotation();
}
