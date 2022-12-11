package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents a {@link IShape} objects animation by holding the starting and ending values of the
 * shapes fields during a specific interval of the animation.
 */
public class Animation implements IAnimation {

  protected final int startTick;
  protected final int endTick;
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
   * @param shapeType      represents what type of process this is.
   * @param startingTick   represents starting time of the process.
   * @param startingX      represents the starting x coordinate of the shape in the process.
   * @param startingY      represents the starting y coordinate of the shape in this process.
   * @param startingWidth  represents the starting width of the shape in this process.
   * @param startingHeight represents the starting height of the shape in this process.
   * @param startingRed    represents the starting value for red of the shape.
   * @param startingGreen  represents the starting value for green of the shape.
   * @param startingBlue   represents the starting value for blue of the shape.
   * @param endingTick     represents the ending time of the process.
   * @param endingX        represents the ending x coordinate of the shape in this process.
   * @param endingY        represents the ending y coordinate of the shape in this process.
   * @param endingWidth    represents the ending width of the shape in this process.
   * @param endingHeight   represents the ending height of the shape.
   * @param endingRed      represents the ending red value for the shape.
   * @param endingGreen    represents the ending green value for the shape.
   * @param endingBlue     represents the ending blue value for the shape.
   * @throws IllegalArgumentException when the start time or end time is negative, if the width or
   *                                  height is negative at any point, or if the start time is after
   *                                  the end time.
   */
  public Animation(String shapeType, int startingTick, int startingX, int startingY,
      int startingWidth, int startingHeight, int startingRed, int startingGreen,
      int startingBlue, int endingTick, int endingX, int endingY, int endingWidth, int endingHeight,
      int endingRed, int endingGreen, int endingBlue)
      throws IllegalArgumentException {

    this.shapeType = shapeType;
    this.startTick = this.checkPositiveInt(startingTick, "Starting Tick");
    this.startingX = this.checkPositiveInt(startingX, "Starting X");
    this.startingY = this.checkPositiveInt(startingY, "Starting Y");
    this.startingWidth = this.checkPositiveInt(startingWidth, "Starting Width");
    this.startingHeight = this.checkPositiveInt(startingHeight, "Starting Height");
    this.startColor = new Color(startingRed, startingGreen, startingBlue);

    this.endTick = this.checkPositiveInt(endingTick, "Ending Tick");
    this.endX = this.checkPositiveInt(endingX, "Ending X");
    this.endY = this.checkPositiveInt(endingY, "Ending Y");
    this.endWidth = this.checkPositiveInt(endingWidth, "Ending Width");
    this.endHeight = this.checkPositiveInt(endingHeight, "Ending Height");
    this.endColor = new Color(endingRed, endingGreen, endingBlue);
    this.startOrientation = 0;
    this.endOrientation = 0;

  }

  /**
   * Get the type of shape contained within the Animation.
   *
   * @return the type of the shape, as a String.
   */
  @Override
  public String getType() {
    return this.shapeType;
  }

  /**
   * Get the starting tick of the Animation
   *
   * @return the starting tick of the Animation, as an int.
   */
  @Override
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Get the starting x coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting x coordinate, as an int.
   */
  @Override
  public int getStartX() {
    return startingX;
  }

  /**
   * Get the starting y coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting y coordinate, as an int.
   */
  @Override
  public int getStartY() {
    return startingY;
  }

  /**
   * Get the starting width of the {@link IShape} in the Animation.
   *
   * @return the starting width, as an int.
   */
  @Override
  public int getStartWidth() {
    return startingWidth;
  }

  /**
   * Get the starting height of the {@link IShape} in the Animation.
   *
   * @return the starting width, as an int.
   */
  @Override
  public int getStartHeight() {
    return startingHeight;
  }

  /**
   * Get the starting {@link Color} of the {@link IShape} in the Animation.
   *
   * @return the starting color of the shape, as an {@link Color} object.
   */
  @Override
  public Color getStartColor() {
    return startColor;
  }

  /**
   * Get the state of the associated {@link IShape} at a given tick.
   *
   * @param tick  is the tick to mutate the shape to the state.
   * @param shape the shape of the object before the process starts.
   * @return the current version of the shape, as an {@link IShape} object
   * @throws IllegalArgumentException if the tick value is larger than the ending tick value.
   */
  @Override
  public IShape setState(int tick, IShape shape) throws IllegalArgumentException {
    if (tick < this.startTick || tick > this.endTick) {
      throw new IllegalArgumentException("Please check the time input value.");
    } else if (this.startTick == this.endTick) {
      shape.setReference(new Point2D.Double(this.endX, this.endY));
      shape.setWidth(this.endWidth);
      shape.setHeight(this.endHeight);
      shape.setColor(this.endColor);
      return shape;
    }

    int newX = this.findPointAt(tick, this.startingX, this.endX);
    int newY = this.findPointAt(tick, this.startingY, this.endY);
    shape.setReference(new Point2D.Double(newX, newY));

    shape.setWidth(this.findPointAt(tick, this.startingWidth, this.endWidth));
    shape.setHeight(this.findPointAt(tick, this.startingHeight, this.endHeight));

    int newerRed = this.findPointAt(tick, this.startColor.getRed(), this.endColor.getRed());
    int newerGreen = this.findPointAt(tick, this.startColor.getGreen(), this.endColor.getGreen());
    int newerBlue = this.findPointAt(tick, this.startColor.getBlue(), this.endColor.getBlue());
    shape.setColor(new Color(newerRed, newerGreen, newerBlue));

    return shape;
  }

  /**
   * Get the starting degree of rotation of the associated {@link IShape}.
   *
   * @return the degree of rotation of the {@link IShape}, as an int.
   */
  @Override
  public int getStartRotationDegree() {
    return this.startOrientation;
  }

  /**
   * Get the ending degree of rotation of the associated {@link IShape}.
   *
   * @return the ending of rotation of the {@link IShape}, as an int.
   */
  @Override
  public int getEndRotationDegree() {
    return this.endOrientation;
  }

  /**
   * Get the last tick of an Animation
   *
   * @return the ending tick of the Animation, as an int.
   */
  @Override
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * Get the starting x coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting x coordinate, as an int.
   */
  @Override
  public int getEndX() {
    return endX;
  }

  /**
   * Get the starting y coordinate of the {@link IShape} in the Animation.
   *
   * @return the starting y coordinate, as an int.
   */
  @Override
  public int getEndY() {
    return endY;
  }

  /**
   * Get the ending width of the {@link IShape} in the Animation.
   *
   * @return the ending width, as an int.
   */
  @Override
  public int getEndWidth() {
    return endWidth;
  }

  /**
   * Get the ending height of the {@link IShape} in the Animation.
   *
   * @return the ending height, as an int.
   */
  @Override
  public int getEndHeight() {
    return endHeight;
  }

  /**
   * Get the ending {@link Color} of the {@link IShape} in the Animation.
   *
   * @return the ending color of the shape, as an {@link Color} object.
   */
  @Override
  public Color getEndColor() {
    return endColor;
  }

  /**
   * Calculates the middle coordinate position.
   *
   * @return the middle coordinate value, as an int.
   */
  private int findPointAt(int time, int startValue, int endValue) {
    if (this.startTick == this.endTick) {
      return endValue;
    } else {
      return ((endValue - startValue) * (time - this.startTick))
          / (this.endTick - this.startTick) + startValue;
    }
  }

  /**
   * Checks to ensure that an input value is greater than or equal to 0.
   *
   * @param i         the integer to be checked.
   * @param fieldName the checking field name, only used if an error is thrown.
   * @return the valid passed integer, as an int.
   * @throws IllegalArgumentException if the value passed is less than 0.
   */
  private int checkPositiveInt(int i, String fieldName) throws IllegalArgumentException {

    if (i >= 0) {
      return i;
    } else {
      throw new IllegalArgumentException(fieldName + " requires a positive input value.");
    }
  }

}
