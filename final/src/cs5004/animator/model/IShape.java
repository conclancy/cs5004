package cs5004.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Interface for shape objects that can be used in the Easy Animation program.  EShapeTypes have a
 * type, {@link Point2D}, width, height, degree of rotation, and {@link Color}.
 */
public interface IShape {

  /**
   * Get the type of the shape.
   *
   * @return The shape type, as a String.
   */
  String getShapeType();

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
   * Get the degree of rotation of the shape.
   *
   * @return the degree of rotation of the shape, as an int.
   */
  int getDegrees();

  /**
   * Get current cartesian coordinates of the shape represented by a {@link Point2D} object.
   *
   * @return the cartesian coordinates of the shape, as a {@link Point2D}.
   */
  Point2D getReference();

  /**
   * Get the color of the shape represented by a {@link Color} object.
   *
   * @return the color of the shape, as a {@link Color}.
   */
  Color getColor();

  /**
   * Get a deep copy of the shape.  This allows access to a shape without risking the changing of
   * the underlying shape attributes.
   *
   * @return an exact replica of the shape, as an {@link IShape} object.
   */
  IShape getCopy();

  /**
   * Change the coordinates of the shape.
   *
   * @param point cartesian coordinates of the shape, represented by a {@link Point2D} object.
   */

  void setReference(Point2D point);

  /**
   * Change the color of the shape.
   *
   * @param color the shae of the shape, represented by a {@link Color} object.
   */
  void setColor(Color color);

  /**
   * Change the width of the shape.
   *
   * @param width is the width of the shape.
   * @throws IllegalArgumentException if the {@param width} is less than 0.
   */
  void setWidth(int width) throws IllegalArgumentException;

  /**
   * Sets the height of an object.
   *
   * @param height is the height.
   * @throws IllegalArgumentException if the {@param height} is less than 0.
   */
  void setHeight(int height) throws IllegalArgumentException;

  /**
   * Change the degree of rotation for the shape, where the Y access represents 0 degrees rotation,
   * and rotation happens clockwise around the access on a basis of 360 degrees.
   *
   * @param degrees is the degree of rotation as an int.
   */
  void setDegrees(int degrees);

  /**
   * Allows for the conversion of the Shape to a {@link Graphics2D} object for display on a GUI.
   *
   * @param g the graphics variable to display the shape, as a {@link Graphics2D} .
   */
  void draw(Graphics2D g);
}