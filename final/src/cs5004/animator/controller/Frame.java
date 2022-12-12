package cs5004.animator.controller;

import java.awt.Color;

/**
 * Represents a keyframe that includes rotation.
 */
public class Frame implements IFrame {

  protected final int time;
  protected final int x;
  protected final int y;
  protected final int width;
  protected final int height;
  protected final Color color;
  private final int rotationDegree;

  /**
   * Constructor for a keyframe that initializes all of its fields.
   *
   * @param frameTime      is this time this key frame occurs.
   * @param x              is the x coordinatePosition of this keyframe.
   * @param y              is the y coordinatePosition of this keyframe.
   * @param width          is the width of the shape at this keyframe.
   * @param height         is the height of the shape at this keyframe.
   * @param rotationDegree is the rotationDegree of the shape at this keyframe.
   * @param color          is the color of the shape at this keyframe.
   */
  public Frame(int frameTime, int x, int y, int width, int height,
      int rotationDegree, Color color) {
    this.time = frameTime;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.rotationDegree = rotationDegree;
    this.color = color;
  }

  @Override
  public int getShapeRotation() {
    return this.rotationDegree;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Color getColor() {
    return this.color;
  }


  @Override
  public String toString() {
    return "TIME : " + this.time + ": Position (" + this.x + ", " + this.y + "), " + this.width
        + "x"
        + this.height + ", color: ("
        + this.color.getRed() + ", " + this.color.getGreen() + ", "
        + this.color.getBlue() + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Frame)) {
      return false;
    }

    Frame otherFrame = (Frame) other;

    return this.time == otherFrame.getTime()
        && this.x == otherFrame.getX()
        && this.y == otherFrame.getY()
        && this.width == otherFrame.getWidth()
        && this.height == otherFrame.getHeight()
        && this.color.getRGB() == otherFrame.getColor().getRGB();
  }

  @Override
  public int hashCode() {
    int result = 17;

    result = 31 * result + this.time;
    result = 31 * result + this.x;
    result = 31 * result + this.y;
    result = 31 * result + this.width;
    result = 31 * result + this.height;
    result = 31 * result + this.color.getRGB();

    return result;
  }
}
