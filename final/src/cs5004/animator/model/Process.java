package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents a process on a Shape, with each of the starting values and ending values of the fields
 * of the shape.
 */
public class Process implements IProcess {
  protected final int startingTime;
  protected final int endTime;
  private final String shapeType;
  private final int startingX;
  private final int startingY;
  private final int startingWidth;
  private final int startingHeight;
  private final Color startColor;
  private final int endX;
  private final int endY;
  private final int endWidth;
  private final int endHeight;
  private final Color endColor;
  private final int startOrientation;
  private final int endOrientation;

  /**
   * Constructor for master process that initializes each of the fields of the process.
   *
   * @param shapeType represents what type of process this is.
   * @param t1        represents starting time of the process.
   * @param x1        represents the starting x coordinate of the shape in the process.
   * @param y1        represents the starting y coordinate of the shape in this process.
   * @param w1        represents the starting width of the shape in this process.
   * @param h1        represents the starting height of the shape in this process.
   * @param r1        represents the starting value for red of the shape.
   * @param g1        represents the starting value for green of the shape.
   * @param b1        represents the starting value for blue of the shape.
   * @param t2        represents the ending time of the process.
   * @param x2        represents the ending x coordinate of the shape in this process.
   * @param y2        represents the ending y coordinate of the shape in this process.
   * @param w2        represents the ending width of the shape in this process.
   * @param h2        represents the ending height of the shape.
   * @param r2        represents the ending red value for the shape.
   * @param g2        represents the ending green value for the shape.
   * @param b2        represents the ending blue value for the shape.
   * @throws IllegalArgumentException when the start time or end time is negative, if the width or
   *                                  height is negative at any point, or if the start time is after
   *                                  the end time.
   */
  public Process(String shapeType, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
                        int b1,
                        int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
          throws IllegalArgumentException {
    if (t1 > t2 || t1 < 0) {
      throw new IllegalArgumentException("-------- Invalid start or end time. Check settings.");
    }

    if (w1 < 0 || w2 < 0) {
      throw new IllegalArgumentException("-------- Width cannot be a negative number.");
    }

    if (h1 < 0 || h2 < 0) {
      throw new IllegalArgumentException("-------- Height cannot be negative number.");
    }

    if (r1 < 0 || r2 < 0 || g1 < 0 || g2 < 0 || b1 < 0 || b2 < 0
            || r1 > 255 || r2 > 255 || g1 > 255 || g2 > 255 || b1 > 255 || b2 > 255) {
      throw new IllegalArgumentException("-------- RGB values must be between 0 and 255");
    }

    this.shapeType = shapeType;
    this.startingTime = t1;
    this.startingX = x1;
    this.startingY = y1;
    this.startingWidth = w1;
    this.startingHeight = h1;
    this.startColor = new Color(r1, g1, b1);
    this.endTime = t2;
    this.endX = x2;
    this.endY = y2;
    this.endWidth = w2;
    this.endHeight = h2;
    this.endColor = new Color(r2, g2, b2);
    this.startOrientation = 0;
    this.endOrientation = 0;

  }

  /**
   * Constructor for super master process that initializes each of the fields of the process.
   *
   * @param type is what type of process.
   * @param t1   is starting time of the process.
   * @param x1   is the starting x coordinate of the shape.
   * @param y1   is the starting y coordinate of the shape.
   * @param w1   is the starting width of the shape.
   * @param h1   is the starting height of the shape.
   * @param o1   is the starting rotationDegree of the shape.
   * @param r1   is the starting value for red of the shape.
   * @param g1   is the starting value for green of the shape.
   * @param b1   is the starting value for blue of the shape.
   * @param t2   is the ending time of the process.
   * @param x2   is the ending x coordinate of the shape.
   * @param y2   is the ending y coordinate of the shape.
   * @param w2   is the ending width of the shape.
   * @param h2   is the ending height of the shape.
   * @param o2   is the ending rotationDegree of the shape.
   * @param r2   is the ending red value for the shape.
   * @param g2   is the ending green value for the shape.
   * @param b2   is the ending blue value for the shape.
   * @throws IllegalArgumentException will be thrown if the start time and or the end time is
   *                                  negative, or if the width and or the height are negative. the
   *                                  end time.
   */
  public Process(String type, int t1, int x1, int y1, int w1, int h1, int o1, int r1,
      int g1, int b1, int t2, int x2, int y2, int w2, int h2, int o2, int r2,
      int g2, int b2) throws IllegalArgumentException {

    this.shapeType = type;
    this.startingTime = t1;
    this.startingX = x1;
    this.startingY = y1;
    this.startingWidth = w1;
    this.startingHeight = h1;
    this.startColor = new Color(r1, g1, b1);
    this.endTime = t2;
    this.endX = x2;
    this.endY = y2;
    this.endWidth = w2;
    this.endHeight = h2;
    this.endColor = new Color(r2, g2, b2);

    this.startOrientation = o1;
    this.endOrientation = o2;
  }

  @Override
  public String getType() {
    return this.shapeType;
  }

  @Override
  public int getStartTime() {
    return this.startingTime;
  }

  @Override
  public int getStartX() {
    return startingX;
  }

  @Override
  public int getStartY() {
    return startingY;
  }

  @Override
  public int getStartWidth() {
    return startingWidth;
  }

  @Override
  public int getStartHeight() {
    return startingHeight;
  }

  @Override
  public Color getStartColor() {
    return startColor;
  }


  /**
   * Sets the state of an object at a given time.
   *
   * @param time  The time that state is to be set.
   * @param shape the starting shape of the set.
   * @return a new interfaceShape for the shape.
   */
  @Override
  public IShape setState(int time, IShape shape) {
    if (time < this.startingTime || time > this.endTime) {
      throw new IllegalArgumentException("Please check the time input value.");
    } else if (this.startingTime == this.endTime) {
      shape.setReference(new Point2D.Double(this.endX, this.endY));
      shape.setWidth(this.endWidth);
      shape.setHeight(this.endHeight);
      shape.setColor(this.endColor);
      return shape;
    }

    int newX = this.findPointAt(time, this.startingX, this.endX);
    int newY = this.findPointAt(time, this.startingY, this.endY);
    shape.setReference(new Point2D.Double(newX, newY));

    shape.setWidth(this.findPointAt(time, this.startingWidth, this.endWidth));
    shape.setHeight(this.findPointAt(time, this.startingHeight, this.endHeight));

    int newerRed = this.findPointAt(time, this.startColor.getRed(), this.endColor.getRed());
    int newerGreen = this.findPointAt(time, this.startColor.getGreen(), this.endColor.getGreen());
    int newerBlue = this.findPointAt(time, this.startColor.getBlue(), this.endColor.getBlue());
    shape.setColor(new Color(newerRed, newerGreen, newerBlue));

    return shape;
  }

  /**
   * This method will retrieve the rotationDegree of this process which the starting rotationDegree
   * of the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  @Override
  public int getStartRotationDegree() {
    return 0;
  }

  /**
   * This method will retrieve the rotationDegree of this process which the final rotationDegree of
   * the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  @Override
  public int getEndRotationDegree() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public int getEndX() {
    return endX;
  }

  @Override
  public int getEndY() {
    return endY;
  }

  @Override
  public int getEndWidth() {
    return endWidth;
  }

  @Override
  public int getEndHeight() {
    return endHeight;
  }

  @Override
  public Color getEndColor() {
    return endColor;
  }


  /**
   * helper method that calculates the middle coordinate position.
   *
   * @return int of the coordinate result.
   */
  protected int findPointAt(int time, int startValue, int endValue) {
    if (this.startingTime == this.endTime) {
      return endValue;
    } else {
      return ((endValue - startValue) * (time - this.startingTime))
              / (this.endTime - this.startingTime) + startValue;
    }
  }

  public IShape setDegreeState(int time, IShape shape) {
    shape.setDegrees(this.findPointAt(time, this.startOrientation, this.endOrientation));
    return this.setState(time, shape);
  }

}
