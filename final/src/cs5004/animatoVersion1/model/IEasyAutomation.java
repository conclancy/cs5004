package cs5004.animatoVersion1.model;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The main entrypoint into the Easy Automation model.  One instance of this class represents a
 * single automation.  The interface allows input for {@link IShape} and takes {@link IAction}
 * events to modify the shapes throughout the process.
 */
public interface IEasyAutomation {

  /**
   * Add a new rectangle to the automation.
   *
   * @param name   the name of the rectangle.
   * @param x      coordinate of the horizontal location of the oval's center, as a double.
   * @param y      coordinate of the vertical location of the oval's center as a double.
   * @param width  the width of the rectangle, as a double.
   * @param height the height of the rectangle, as a double.
   * @param color  the color of the rectangle, a {@link Color} object.
   * @throws IllegalArgumentException if the width or height of the object is 0 or less.
   */
  void addRectangle(String name, double x, double y, double width, double height, Color color)
      throws IllegalArgumentException;

  /**
   * Add a new Square to the automation.
   *
   * @param name       The name of the square.
   * @param x          coordinate of the horizontal location of the circle's center, as a double.
   * @param y          coordinate of the vertical location of the circle's center as a double.
   * @param sideLength the length of a single side of the square.
   * @param color      the color of the square.
   */
  void addSquare(String name, double x, double y, double sideLength, Color color)
      throws IllegalArgumentException;

  /**
   * Add a new Oval to the automation.
   *
   * @param name      name of the oval.
   * @param x         coordinate of the horizontal location of the oval's center, as a double.
   * @param y         coordinate of the vertical location of the oval's center as a double.
   * @param majorAxis longest distance between the center point and the perimeter of the oval, as a
   *                  double.
   * @param minorAxis smallest distance between the center point and the perimeter of the oval, as a
   *                  double.
   * @param color     the color of the oval, as a {@link Color} object.
   * @throws IllegalArgumentException if either or both axis provided are less than or equal to 0.
   */
  void addOval(String name, double x, double y, double majorAxis, double minorAxis, Color color)
      throws IllegalArgumentException;

  /**
   * Adds a new Circle to the automation.
   *
   * @param name   name of the circle.
   * @param x      coordinate of the horizontal location of the circle's center, as a double.
   * @param y      coordinate of the vertical location of the circle's center as a double.
   * @param radius distance between the center point and the perimeter of the circle, as a double.
   * @param color  the color of the circle, as a {@link Color} object.
   * @throws IllegalArgumentException if the radius provided is less than or equal to 0.
   */
  void addCircle(String name, double x, double y, double radius, Color color)
      throws IllegalArgumentException;

  /**
   * Set the speed of the automation.  Default speed is 1.0, speed greater than this will increase
   * speed, and speeds lower than 1.0 will decrease speed.
   *
   * @param speed the speed at which the automation will play back.
   * @throws IllegalArgumentException if the {@param speed} is less than or equal to 0.
   */
  void setSpeed(double speed) throws IllegalArgumentException;

  /**
   * Get the current speed of the automation.
   *
   * @return the speed of the automation, as a double.
   */
  double getSpeed();

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return shapes available in the automation, as a List of IShape objects.
   */
  List<IShape> getShapes(); //TODO this needs to return a generic so the controller can handle it.

  /**
   * Get a list of all the shapes available in the automation.
   *
   * @return return a list of just the shape's names as a List of Strings.
   */
  List<String> getShapeNames();

  /**
   * Get a shape within the automation by providing its name.  This will allow users to manipulate
   * the shape and add additional animations to the shape.
   *
   * @param name the name of the shape to be obtained.
   * @return the {@link IShape} the caller requested.
   * @throws NoSuchElementException if the {@param name} provided is not contained in the
   *                                Automation.
   */
  IShape getShapeByName(String name) throws NoSuchElementException;

  /**
   * Get the length of the automation as an int.  This is the last `end` value of the automation.
   *
   * @return the length of the automation, as an int.
   * @throws NoSuchElementException if there are no animations in the automation.
   */
  int getLength() throws NoSuchElementException;


}
