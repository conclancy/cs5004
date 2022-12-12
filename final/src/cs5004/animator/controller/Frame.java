package cs5004.animator.controller;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents a frame within the automation.
 */
public class Frame implements IFrame {

  private final int tick;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final Color color;
  private final int degree;

  /**
   * Constructor for a frame object.
   *
   * @param tick   the tick at which this frame occurs.
   * @param x      the x coordinate of the shape within this frame.
   * @param y      the y coordinate of the shape within this frame.
   * @param width  the width of the shape within this frame.
   * @param height the height of the shape within this frame.
   * @param degree the degree of rotation of the shape within the frame.
   * @param color  the color of the shape within the frame.
   */
  public Frame(int tick, int x, int y, int width, int height, int degree, Color color) {
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.degree = degree;
    this.color = color;
  }

  /**
   * Get the rotation of the shape within this frame.
   *
   * @return the degree of rotation of the shape at this frame, as an int.
   */
  @Override
  public int getShapeRotation() {
    return this.degree;
  }

  /**
   * Get the tick where this frame occurs.
   *
   * @return the tick the frame occurs, as an int.
   */
  @Override
  public int getTick() {
    return this.tick;
  }

  /**
   * Get the x coordinate of this frame.
   *
   * @return the X coordinate of this frame, as an int.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Get the y coordinate of this frame.
   *
   * @return the Y coordinate of this frame, as an int.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Get the width of this frame.
   *
   * @return the width of this keyframe, as an int.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of this frame.
   *
   * @return the height of this keyframe, as an int.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the color of the shape in this frame.
   *
   * @return the {@link Color} of the shape in this frame.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Express this frame as a String.
   *
   * @return this frame, as a String.
   */
  @Override
  public String toString() {
    return "TIME : " + this.tick + ": Position (" + this.x + ", " + this.y + "), " + this.width
        + "x" + this.height + ", color: (" + this.color.getRed() + ", " + this.color.getGreen()
        + ", " + this.color.getBlue() + ")";
  }

  /**
   * Determines if two frames are logically equivalent.
   *
   * @param other the other object to be compared.
   * @return true if the two objects are the same.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Frame)) {
      return false;
    }

    Frame otherFrame = (Frame) other;

    return this.tick == otherFrame.getTick()
        && this.x == otherFrame.getX()
        && this.y == otherFrame.getY()
        && this.width == otherFrame.getWidth()
        && this.height == otherFrame.getHeight()
        && this.degree == otherFrame.getShapeRotation()
        && this.color.getRGB() == otherFrame.getColor().getRGB();
  }

  /**
   * Create a hash of the frame.
   *
   * @return the object hash, as an int.
   */
  @Override
  public int hashCode() {

    return Objects.hash(this.tick, this.x, this.y, this.width, this.height, this.degree,
        this.color);

  }
}
