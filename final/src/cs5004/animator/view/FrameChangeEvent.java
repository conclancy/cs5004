package cs5004.animator.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * This class represents an action event for key frame changes for the playback view.
 */
public class FrameChangeEvent extends ActionEvent implements IFrameChangeEvent {

  private final EFrameChange changeType;
  private final String name;
  private final int tick;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int degree;
  private final Color color;

  /**
   * Construct a FrameChangeEvent for receiving listener updates.
   *
   * @param source     object sending the change event
   * @param changeType {@link EFrameChange} type occurring within this keyframe.
   * @param name       name of the shape displayed within this keyframe.
   * @param tick       tick at which this keyframe occurs.
   * @param x          x coordinate of the shape at this tick.
   * @param y          y coordinate of the shape at this tick
   * @param width      width of the shape at this tick.
   * @param height     height of the shape at this tick.
   * @param degree     degree of rotation of the shape at this tick.
   * @param color      {@link Color} of the shape at this frame
   */
  public FrameChangeEvent(Object source, EFrameChange changeType, String name, int tick,
      int x, int y, int width, int height, int degree, Color color) {

    super(source, ActionEvent.ACTION_PERFORMED, "frame change");

    this.changeType = changeType;
    this.name = name;
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.degree = degree;
    this.color = color;
  }

  /**
   * Get the change type of this event.
   *
   * @return the event, as a {@link EFrameChange}
   */
  @Override
  public EFrameChange getType() {
    return this.changeType;
  }

  /**
   * Get the name of the shape that is being changed.
   *
   * @return the name of the shape, as a String.
   */
  @Override
  public String getShapeName() {
    return this.name;
  }

  /**
   * Get the tick at which this change occurs.
   *
   * @return the tick at which the event occurs, as an int.
   */
  @Override
  public int getTick() {
    return this.tick;
  }

  /**
   * Get the X coordinate of the shape.
   *
   * @return the X coordinate of the shape, as an int.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Get the Y coordinate of the shape.
   *
   * @return the y coordinate of the shape, as an int.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Get the width of the shape.
   *
   * @return the width of the shape, as an int.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of the shape.
   *
   * @return the height of the shape, as an int.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the rotation degree of the shape.
   *
   * @return the rotation degree of the shape, as an int.
   */
  @Override
  public int getShapeRotation() {
    return this.degree;
  }

  /**
   * Get the {@link Color} of the shape.
   *
   * @return the color of the shape, as a {@link Color}.
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }
}
