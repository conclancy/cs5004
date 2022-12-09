package cs5004.animator.model;

import java.util.List;
import java.util.NoSuchElementException;

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
   * Set the reference point of the shape.
   *
   * @param name the name of the shape.
   * @param x    the x coordinate, as an int.
   * @param y    the y coordinate, as an int.
   * @throws NoSuchElementException if the shape name does not exist in the model.
   */
  void setShapeReference(String name, int x, int y) throws NoSuchElementException;

  /**
   * Set the color of a shape.
   *
   * @param name  the name of the shape.
   * @param red   the amount of red light in the shape's color.
   * @param green the amount of green light in the shape's color.
   * @param blue  the amount of blue light in the shape's color.
   * @throws NoSuchElementException if the shape name does not exist in the model.
   */
  void setShapeColor(String name, int red, int green, int blue) throws NoSuchElementException;

  /**
   * Set the dimensions of a shape.
   *
   * @param name   the name of the shape.
   * @param width  the width of the shape, as an int.
   * @param height the height of the shape, as an int.
   * @throws IllegalArgumentException if the shape name does not exist in the model.
   */
  void setShapeDimensions(String name, int width, int height) throws IllegalArgumentException;

  /**
   * Add an animation to the model.
   *
   * @param name      the name of the animation, as a String.
   * @param animation the animation, as an {@link IAnimation} object.
   * @throws NoSuchElementException if the shape name is not in the animation.
   */
  void addAnimation(String name, IAnimation animation) throws NoSuchElementException;

  /**
   * Set the model's canvas properties.
   *
   * @param x      the X coordinate, as an int.
   * @param y      the Y coordinate, as an int.
   * @param width  the width of the canvass, as an int.
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
