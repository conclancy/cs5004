package cs5004.animator.view;

import java.awt.Color;

/**
 * Interface that represents changes within the key frames of a shape in the animation.
 */
public interface IFrameChangeEvent {

  /**
   * Get the change type of this event.
   *
   * @return the event, as a {@link EFrameChangeType}
   */
  EFrameChangeType getType();

  /**
   * Get the name of the shape that is being changed.
   *
   * @return the name of the shape, as a String.
   */
  String getShapeName();

  /**
   * Get the tick at which this change occurs.
   *
   * @return the tick at which the event occurs, as an int.
   */
  int getTick();

  /**
   * Get the X coordinate of the shape.
   *
   * @return the X coordinate of the shape, as an int.
   */
  int getX();

  /**
   * Get the Y coordinate of the shape.
   *
   * @return the y coordinate of the shape, as an int.
   */
  int getY();

  /**
   * Get the width of the shape.
   *
   * @return the width of the shape, as an int.
   */
  int getWidth();

  /**
   * Get the height of the shape.
   *
   * @return the height of the shape, as an int.
   */
  int getHeight();

  /**
   * Get the rotation degree of the shape.
   *
   * @return the rotation degree of the shape, as an int.
   */
  int getShapeRotation();

  /**
   * Get the {@link Color} of the shape.
   *
   * @return the color of the shape, as a {@link Color}.
   */
  Color getColor();
}
