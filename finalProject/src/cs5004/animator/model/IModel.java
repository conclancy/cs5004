package cs5004.animator.model;

import java.util.List;

/**
 * Interface for the EasyAnimation Model component.
 */
public interface IModel {

  /**
   * Add a shape to the model.
   *
   * @param name      the name of the shape, as a String.
   * @param shapeType the type of the shape, as a String, accepts 'RECTANGLE' or 'OVAL'.
   * @throws IllegalArgumentException if the {@param shapeType} does not receive a valid input.
   */
  void addShape(String name, String shapeType) throws IllegalArgumentException;

  /**
   * Add an animation to the model.
   *
   * @param name the name of the animation, as a String.
   * @param animation the animation, as an {@link IAnimation} object.
   */
  void addAnimation(String name, IAnimation animation);

  /**
   * Set the model's canvas properties.
   *
   * @param x the X coordinate, as an int.
   * @param y the Y coordinate, as an int.
   * @param width the width of the canvass, as an int.
   * @param height the height of the canvass, as an int.
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Get the x coordinate of the canvass.
   *
   * @return the x coordinate of the canvass, as an int.
   */
  int getX();

  /**
   * Get the y coordinate of the canvass.
   *
   * @return the y coordinate of the canvass, as an int.
   */
  int getY();

  /**
   * Get the width coordinate of the canvass.
   *
   * @return the width coordinate of the canvass, as an int.
   */
  int getWidth();

  /**
   * Get the height coordinate of the canvass.
   *
   * @return the height coordinate of the canvass, as an int.
   */
  int getHeight();

  /**
   * Get the state of shapes at a specific time to rendered on the canvas.
   *
   * @param tick the desired tick.
   * @return a list of shapes in their state at the given tick.
   */
  List<IShape> getStateByTick(int tick);

  /**
   * The entire model formatted as an SVG string.
   *
   * @return entire model formatted as an SVG string.
   */
  String getSVG();

  /**
   * Get the value of the final tick in the animation.
   *
   * @return the final tick value, as an int.
   */
  int getEndTick();

}
